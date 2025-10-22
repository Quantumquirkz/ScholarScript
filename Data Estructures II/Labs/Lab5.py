"""
Lab 5 - Árbol de Huffman para Compresión de Texto

Problema: Comprimir texto eficientemente sin pérdida de datos
Solución: Usar árbol binario con códigos de longitud variable basados en frecuencias

Operaciones implementadas:
- Creación: Construir árbol desde frecuencias de caracteres
- Inserción: Combinar nodos en el heap para formar el árbol
- Búsqueda: Codificar y decodificar caracteres usando el árbol
- Recorrido: Preorden, Inorden, Postorden para analizar la estructura

Autor: ScholarScript
Fecha: Octubre 2025
"""

import heapq
from collections import Counter


class NodoHuffman:
    """Nodo del Árbol de Huffman"""
    
    def __init__(self, caracter=None, frecuencia=0):
        self.caracter = caracter
        self.frecuencia = frecuencia
        self.izquierdo = None
        self.derecho = None
    
    def es_hoja(self):
        return self.caracter is not None
    
    def __lt__(self, otro):
        return self.frecuencia < otro.frecuencia


class ArbolHuffman:
    """Árbol de Huffman para compresión de texto"""
    
    def __init__(self):
        self.raiz = None
        self.codigos = {}
        self.frecuencias = {}
    
    def construir_arbol(self, texto):
        """Crea el árbol de Huffman a partir del texto"""
        if not texto:
            return False
        
        # Calcular frecuencias
        self.frecuencias = Counter(texto)
        
        # Crear heap con nodos hoja
        heap = [NodoHuffman(char, freq) for char, freq in self.frecuencias.items()]
        heapq.heapify(heap)
        
        # Construir árbol combinando nodos de menor frecuencia
        while len(heap) > 1:
            izq = heapq.heappop(heap)
            der = heapq.heappop(heap)
            
            padre = NodoHuffman(None, izq.frecuencia + der.frecuencia)
            padre.izquierdo = izq
            padre.derecho = der
            
            heapq.heappush(heap, padre)
        
        self.raiz = heap[0]
        self._generar_codigos(self.raiz, "")
        return True
    
    def _generar_codigos(self, nodo, codigo):
        """Genera códigos binarios: izquierda=0, derecha=1"""
        if nodo.es_hoja():
            self.codigos[nodo.caracter] = codigo or '0'
            return
        
        if nodo.izquierdo:
            self._generar_codigos(nodo.izquierdo, codigo + '0')
        if nodo.derecho:
            self._generar_codigos(nodo.derecho, codigo + '1')
    
    def comprimir(self, texto):
        """Convierte texto a cadena de bits"""
        return ''.join(self.codigos[char] for char in texto)
    
    def descomprimir(self, bits):
        """Convierte cadena de bits a texto original"""
        texto = ""
        nodo = self.raiz
        
        for bit in bits:
            nodo = nodo.izquierdo if bit == '0' else nodo.derecho
            
            if nodo.es_hoja():
                texto += nodo.caracter
                nodo = self.raiz
        
        return texto
    
    def recorrido_preorden(self):
        """Recorrido: Raíz -> Izquierda -> Derecha"""
        resultado = []
        self._preorden(self.raiz, resultado)
        return resultado
    
    def _preorden(self, nodo, resultado):
        if nodo:
            resultado.append(nodo)
            self._preorden(nodo.izquierdo, resultado)
            self._preorden(nodo.derecho, resultado)
    
    def recorrido_inorden(self):
        """Recorrido: Izquierda -> Raíz -> Derecha"""
        resultado = []
        self._inorden(self.raiz, resultado)
        return resultado
    
    def _inorden(self, nodo, resultado):
        if nodo:
            self._inorden(nodo.izquierdo, resultado)
            resultado.append(nodo)
            self._inorden(nodo.derecho, resultado)
    
    def recorrido_postorden(self):
        """Recorrido: Izquierda -> Derecha -> Raíz"""
        resultado = []
        self._postorden(self.raiz, resultado)
        return resultado
    
    def _postorden(self, nodo, resultado):
        if nodo:
            self._postorden(nodo.izquierdo, resultado)
            self._postorden(nodo.derecho, resultado)
            resultado.append(nodo)
    
    def mostrar_diagrama(self):
        """Muestra un diagrama visual del árbol de Huffman"""
        if not self.raiz:
            print("El árbol está vacío")
            return
        
        print("\nDIAGRAMA DEL ÁRBOL DE HUFFMAN")
        print("=" * 50)
        print("Leyenda: Nodos internos: [frecuencia]")
        print("         Hojas: [carácter:frecuencia] (código)")
        print("         /0 (izquierda)  \\1 (derecha)")
        print("-" * 50)
        
        # Calcular altura del árbol para espaciado
        altura = self._calcular_altura(self.raiz)
        self._imprimir_arbol_simple(self.raiz, 0, altura, "")
    
    def _calcular_altura(self, nodo):
        """Calcula la altura del árbol"""
        if not nodo:
            return 0
        return 1 + max(self._calcular_altura(nodo.izquierdo), 
                      self._calcular_altura(nodo.derecho))
    
    def _imprimir_arbol_simple(self, nodo, nivel, altura_max, codigo):
        """Imprime el árbol con formato simple usando / y \\"""
        if not nodo:
            return
        
        # Espaciado basado en el nivel
        espacios = "  " * (altura_max - nivel)
        
        # Mostrar el nodo actual
        if nodo.es_hoja():
            # Nodo hoja (carácter)
            print(f"{espacios}[{nodo.caracter}:{nodo.frecuencia}] ({codigo or '0'})")
        else:
            # Nodo interno
            print(f"{espacios}[{nodo.frecuencia}]")
        
        # Mostrar conexiones y subárboles
        if nodo.izquierdo or nodo.derecho:
            # Líneas de conexión
            espacios_conexion = "  " * (altura_max - nivel - 1)
            if nodo.izquierdo and nodo.derecho:
                print(f"{espacios_conexion}  /0    \\1")
            elif nodo.izquierdo:
                print(f"{espacios_conexion}  /0")
            elif nodo.derecho:
                print(f"{espacios_conexion}      \\1")
            
            print()
            
            # Recursivamente imprimir subárboles
            if nodo.izquierdo:
                self._imprimir_arbol_simple(nodo.izquierdo, nivel + 1, altura_max, codigo + "0")
            if nodo.derecho:
                self._imprimir_arbol_simple(nodo.derecho, nivel + 1, altura_max, codigo + "1")


def main():
    """Demostración del Árbol de Huffman"""
    print("="*60)
    print("ÁRBOL DE HUFFMAN - Compresión de Texto")
    print("="*60)
    
    # Texto de ejemplo
    texto = input("\nIngrese el texto a comprimir (o Enter para ejemplo): ").strip()
    if not texto:
        texto = "ABRACADABRA"
        print(f"Usando texto de ejemplo: '{texto}'")
    
    # Crear árbol
    print("\n1. CREACIÓN DEL ÁRBOL")
    print("-" * 60)
    arbol = ArbolHuffman()
    arbol.construir_arbol(texto)
    
    print(f"Texto original: '{texto}'")
    print(f"Caracteres: {len(texto)}")
    print(f"\nFrecuencias:")
    for char, freq in sorted(arbol.frecuencias.items(), key=lambda x: x[1], reverse=True):
        print(f"  '{char}': {freq} veces")
    
    # Mostrar diagrama del árbol
    arbol.mostrar_diagrama()
    
    # Mostrar códigos
    print("\n2. CÓDIGOS GENERADOS (Búsqueda)")
    print("-" * 60)
    print(f"{'Carácter':<12} {'Frecuencia':<12} {'Código':<15}")
    for char, freq in sorted(arbol.frecuencias.items(), key=lambda x: x[1], reverse=True):
        codigo = arbol.codigos[char]
        print(f"'{char}'         {freq}            {codigo}")
    
    # Comprimir
    print("\n3. COMPRESIÓN")
    print("-" * 60)
    comprimido = arbol.comprimir(texto)
    bits_originales = len(texto) * 8
    bits_comprimidos = len(comprimido)
    ahorro = (1 - bits_comprimidos/bits_originales) * 100
    
    print(f"Texto comprimido: {comprimido}")
    print(f"\nOriginal:    {bits_originales} bits ({len(texto)} caracteres × 8 bits)")
    print(f"Comprimido:  {bits_comprimidos} bits")
    print(f"Ahorro:      {ahorro:.1f}%")
    
    # Descomprimir
    print("\n4. DESCOMPRESIÓN")
    print("-" * 60)
    descomprimido = arbol.descomprimir(comprimido)
    print(f"Texto descomprimido: '{descomprimido}'")
    print(f"¿Coincide con original?: {'✓ SÍ' if descomprimido == texto else '✗ NO'}")
    
    # Recorridos
    print("\n5. RECORRIDOS DEL ÁRBOL")
    print("-" * 60)
    
    print("\nPreorden (Raíz-Izq-Der):")
    for i, nodo in enumerate(arbol.recorrido_preorden(), 1):
        if nodo.es_hoja():
            print(f"  {i}. '{nodo.caracter}' (freq: {nodo.frecuencia})")
        else:
            print(f"  {i}. Nodo interno (freq: {nodo.frecuencia})")
    
    print("\nInorden (Izq-Raíz-Der):")
    for i, nodo in enumerate(arbol.recorrido_inorden(), 1):
        if nodo.es_hoja():
            print(f"  {i}. '{nodo.caracter}' (freq: {nodo.frecuencia})")
        else:
            print(f"  {i}. Nodo interno (freq: {nodo.frecuencia})")
    
    print("\nPostorden (Izq-Der-Raíz):")
    for i, nodo in enumerate(arbol.recorrido_postorden(), 1):
        if nodo.es_hoja():
            print(f"  {i}. '{nodo.caracter}' (freq: {nodo.frecuencia})")
        else:
            print(f"  {i}. Nodo interno (freq: {nodo.frecuencia})")
    
    print("\n" + "="*60)
    print("CONCLUSIÓN: El árbol de Huffman reduce el tamaño")
    print(f"asignando códigos cortos a caracteres frecuentes.")
    print("="*60)


if __name__ == "__main__":
    main()