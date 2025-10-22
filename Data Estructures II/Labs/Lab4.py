class Nodo:
    """Clase que representa un nodo en el árbol binario de búsqueda"""
    def __init__(self, valor):
        self.valor = valor
        self.izquierda = None
        self.derecha = None

class ArbolBinarioBusqueda:
    """Clase que implementa un árbol binario de búsqueda"""
    
    def __init__(self):
        self.raiz = None
    
    def insertar(self, valor):
        """Inserta un nuevo nodo en el árbol manteniendo la propiedad del ABB"""
        if self.raiz is None:
            self.raiz = Nodo(valor)
        else:
            self._insertar_recursivo(self.raiz, valor)
    
    def _insertar_recursivo(self, nodo_actual, valor):
        """Método auxiliar para insertar de forma recursiva"""
        if valor < nodo_actual.valor:
            if nodo_actual.izquierda is None:
                nodo_actual.izquierda = Nodo(valor)
            else:
                self._insertar_recursivo(nodo_actual.izquierda, valor)
        elif valor > nodo_actual.valor:
            if nodo_actual.derecha is None:
                nodo_actual.derecha = Nodo(valor)
            else:
                self._insertar_recursivo(nodo_actual.derecha, valor)
        # Si valor == nodo_actual.valor, no se inserta (no duplicados)
    
    def buscar(self, valor):
        """Busca un valor en el árbol y retorna True si existe, False en caso contrario"""
        return self._buscar_recursivo(self.raiz, valor)
    
    def _buscar_recursivo(self, nodo_actual, valor):
        """Método auxiliar para buscar de forma recursiva"""
        if nodo_actual is None:
            return False
        
        if valor == nodo_actual.valor:
            return True
        elif valor < nodo_actual.valor:
            return self._buscar_recursivo(nodo_actual.izquierda, valor)
        else:
            return self._buscar_recursivo(nodo_actual.derecha, valor)
    
    def eliminar(self, valor):
        """Elimina un nodo del árbol manteniendo la propiedad del ABB"""
        self.raiz = self._eliminar_recursivo(self.raiz, valor)
    
    def _eliminar_recursivo(self, nodo_actual, valor):
        """Método auxiliar para eliminar de forma recursiva"""
        if nodo_actual is None:
            return nodo_actual
        
        # Buscar el nodo a eliminar
        if valor < nodo_actual.valor:
            nodo_actual.izquierda = self._eliminar_recursivo(nodo_actual.izquierda, valor)
        elif valor > nodo_actual.valor:
            nodo_actual.derecha = self._eliminar_recursivo(nodo_actual.derecha, valor)
        else:
            # Nodo encontrado, proceder con la eliminación
            # Caso 1: Nodo sin hijos (hoja)
            if nodo_actual.izquierda is None and nodo_actual.derecha is None:
                return None
            
            # Caso 2: Nodo con un solo hijo
            elif nodo_actual.izquierda is None:
                return nodo_actual.derecha
            elif nodo_actual.derecha is None:
                return nodo_actual.izquierda
            
            # Caso 3: Nodo con dos hijos
            else:
                # Encontrar el sucesor inorden (menor valor en el subárbol derecho)
                sucesor = self._encontrar_minimo(nodo_actual.derecha)
                # Reemplazar el valor del nodo actual con el valor del sucesor
                nodo_actual.valor = sucesor.valor
                # Eliminar el sucesor
                nodo_actual.derecha = self._eliminar_recursivo(nodo_actual.derecha, sucesor.valor)
        
        return nodo_actual
    
    def _encontrar_minimo(self, nodo):
        """Encuentra el nodo con el valor mínimo en un subárbol"""
        while nodo.izquierda is not None:
            nodo = nodo.izquierda
        return nodo
    
    def imprimir_inorden(self):
        """Imprime los valores del árbol usando recorrido inorden"""
        if self.raiz is None:
            print("El árbol está vacío")
        else:
            valores = []
            self._inorden_recursivo(self.raiz, valores)
            print("Recorrido inorden:", " -> ".join(map(str, valores)))
    
    def _inorden_recursivo(self, nodo_actual, valores):
        """Método auxiliar para recorrido inorden recursivo"""
        if nodo_actual is not None:
            self._inorden_recursivo(nodo_actual.izquierda, valores)
            valores.append(nodo_actual.valor)
            self._inorden_recursivo(nodo_actual.derecha, valores)
    
    def esta_vacio(self):
        """Verifica si el árbol está vacío"""
        return self.raiz is None
    
    def obtener_altura(self):
        """Obtiene la altura del árbol"""
        return self._calcular_altura(self.raiz)
    
    def _calcular_altura(self, nodo):
        """Calcula la altura de un nodo de forma recursiva"""
        if nodo is None:
            return 0
        return 1 + max(self._calcular_altura(nodo.izquierda), self._calcular_altura(nodo.derecha))
    
    def mostrar_diagrama(self):
        """Muestra un diagrama visual del árbol en formato jerárquico"""
        if self.raiz is None:
            print("🌳 El árbol está vacío - no hay nada que mostrar")
            return
        
        print("\n" + "="*60)
        print("🌳 DIAGRAMA DEL ÁRBOL BINARIO DE BÚSQUEDA")
        print("="*60)
        
        # Obtener la altura del árbol
        altura = self.obtener_altura()
        
        # Crear una matriz más compacta
        ancho = 2 ** altura * 2  # Reducir el ancho
        matriz = [[" " for _ in range(ancho)] for _ in range(altura * 2)]
        
        # Llenar la matriz con los nodos
        self._llenar_matriz_compacta(self.raiz, 0, ancho // 2, matriz, altura)
        
        # Imprimir la matriz
        for fila in matriz:
            print("".join(fila).rstrip())
        
        print("="*60)
    
    def _llenar_matriz_compacta(self, nodo, nivel, posicion, matriz, altura_maxima):
        """Llena la matriz con los nodos del árbol de forma compacta"""
        if nodo is None or nivel >= altura_maxima:
            return
        
        # Calcular la posición en la matriz
        fila = nivel * 2
        columna = posicion
        
        # Asegurar que no excedamos los límites
        if fila < len(matriz) and 0 <= columna < len(matriz[fila]):
            # Colocar el nodo
            valor_str = str(nodo.valor)
            for i, char in enumerate(valor_str):
                if columna + i < len(matriz[fila]):
                    matriz[fila][columna + i] = char
        
        # Calcular espaciado más compacto para los hijos
        espaciado = max(3, 2 ** (altura_maxima - nivel - 2))
        
        # Llenar subárbol izquierdo
        if nodo.izquierda is not None:
            pos_izq = posicion - espaciado
            # Dibujar línea de conexión (solo una diagonal)
            if fila + 1 < len(matriz):
                if pos_izq < len(matriz[fila + 1]) and pos_izq >= 0:
                    matriz[fila + 1][pos_izq] = "/"
            self._llenar_matriz_compacta(nodo.izquierda, nivel + 1, pos_izq, matriz, altura_maxima)
        
        # Llenar subárbol derecho
        if nodo.derecha is not None:
            pos_der = posicion + espaciado
            # Dibujar línea de conexión (solo una diagonal)
            if fila + 1 < len(matriz):
                if pos_der < len(matriz[fila + 1]) and pos_der >= 0:
                    matriz[fila + 1][pos_der] = "\\"
            self._llenar_matriz_compacta(nodo.derecha, nivel + 1, pos_der, matriz, altura_maxima)
    
    def mostrar_diagrama_simple(self):
        """Muestra un diagrama simple del árbol por niveles"""
        if self.raiz is None:
            print("🌳 El árbol está vacío - no hay nada que mostrar")
            return
        
        print("\n" + "="*60)
        print("🌳 DIAGRAMA SIMPLE DEL ÁRBOL")
        print("="*60)
        
        # Obtener todos los nodos por niveles
        niveles = self._obtener_niveles()
        
        # Mostrar el árbol nivel por nivel
        for i, nivel in enumerate(niveles):
            if nivel:  # Si el nivel no está vacío
                print(f"\nNivel {i}: ", end="")
                for j, nodo in enumerate(nivel):
                    if nodo is not None:
                        print(f"[{nodo.valor}]", end="")
                        if j < len(nivel) - 1:
                            print(" - ", end="")
                    else:
                        print("[ ]", end="")
                        if j < len(nivel) - 1:
                            print(" - ", end="")
                print()
        
        print("="*60)
    
    def _obtener_niveles(self):
        """Obtiene todos los nodos organizados por niveles"""
        if self.raiz is None:
            return []
        
        niveles = []
        cola = [self.raiz]
        
        while cola:
            nivel_actual = []
            tamano_nivel = len(cola)
            
            for _ in range(tamano_nivel):
                nodo = cola.pop(0)
                nivel_actual.append(nodo)
                
                if nodo is not None:
                    cola.append(nodo.izquierda)
                    cola.append(nodo.derecha)
                else:
                    cola.append(None)
                    cola.append(None)
            
            # Solo agregar el nivel si tiene al menos un nodo no nulo
            if any(nodo is not None for nodo in nivel_actual):
                niveles.append(nivel_actual)
            else:
                break
        
        return niveles
    
    def _mostrar_conexiones(self, nodo, prefijo):
        """Muestra las conexiones del árbol de forma recursiva"""
        if nodo is None:
            return
        
        print(f"{prefijo}└── {nodo.valor}")
        
        if nodo.izquierda is not None or nodo.derecha is not None:
            if nodo.izquierda is not None:
                print(f"{prefijo}    ├── Izq: {nodo.izquierda.valor}")
                self._mostrar_conexiones(nodo.izquierda, prefijo + "    │   ")
            else:
                print(f"{prefijo}    ├── Izq: (vacío)")
            
            if nodo.derecha is not None:
                print(f"{prefijo}    └── Der: {nodo.derecha.valor}")
                self._mostrar_conexiones(nodo.derecha, prefijo + "        ")
            else:
                print(f"{prefijo}    └── Der: (vacío)")
    
    def mostrar_estadisticas(self):
        """Muestra estadísticas del árbol"""
        if self.raiz is None:
            print("📊 El árbol está vacío - no hay estadísticas que mostrar")
            return
        
        altura = self.obtener_altura()
        total_nodos = self._contar_nodos(self.raiz)
        
        print("\n📊 ESTADÍSTICAS DEL ÁRBOL:")
        print(f"   • Altura: {altura}")
        print(f"   • Total de nodos: {total_nodos}")
        print(f"   • Nivel máximo: {altura - 1}")
    
    def _contar_nodos(self, nodo):
        """Cuenta el número total de nodos en el árbol"""
        if nodo is None:
            return 0
        return 1 + self._contar_nodos(nodo.izquierda) + self._contar_nodos(nodo.derecha)

def mostrar_menu():
    """Muestra el menú de opciones"""
    print("\n" + "="*60)
    print("    SISTEMA DE GESTIÓN DE ÁRBOL BINARIO DE BÚSQUEDA")
    print("="*60)
    print("1. Insertar un nodo")
    print("2. Buscar un nodo")
    print("3. Eliminar un nodo")
    print("4. Imprimir el árbol (recorrido inorden)")
    print("5. Mostrar diagrama visual del árbol")
    print("6. Mostrar diagrama simple del árbol")
    print("7. Mostrar estadísticas del árbol")
    print("8. Salir")
    print("="*60)

def main():
    """Función principal que ejecuta el programa"""
    arbol = ArbolBinarioBusqueda()
    
    print("¡Bienvenido al Sistema de Gestión de Árbol Binario de Búsqueda!")
    print("El árbol ha sido inicializado y está listo para recibir nodos.")
    
    while True:
        mostrar_menu()
        
        try:
            opcion = input("\nSeleccione una opción (1-8): ").strip()
            
            if opcion == "1":
                # Insertar un nodo
                try:
                    valor = int(input("Ingrese el valor a insertar: "))
                    arbol.insertar(valor)
                    print(f"✓ Valor {valor} insertado exitosamente en el árbol.")
                except ValueError:
                    print("✗ Error: Por favor ingrese un número entero válido.")
            
            elif opcion == "2":
                # Buscar un nodo
                if arbol.esta_vacio():
                    print("✗ El árbol está vacío. No hay valores para buscar.")
                else:
                    try:
                        valor = int(input("Ingrese el valor a buscar: "))
                        if arbol.buscar(valor):
                            print(f"✓ El valor {valor} SÍ está presente en el árbol.")
                        else:
                            print(f"✗ El valor {valor} NO está presente en el árbol.")
                    except ValueError:
                        print("✗ Error: Por favor ingrese un número entero válido.")
            
            elif opcion == "3":
                # Eliminar un nodo
                if arbol.esta_vacio():
                    print("✗ El árbol está vacío. No hay valores para eliminar.")
                else:
                    try:
                        valor = int(input("Ingrese el valor a eliminar: "))
                        if arbol.buscar(valor):
                            arbol.eliminar(valor)
                            print(f"✓ Valor {valor} eliminado exitosamente del árbol.")
                        else:
                            print(f"✗ El valor {valor} no está presente en el árbol.")
                    except ValueError:
                        print("✗ Error: Por favor ingrese un número entero válido.")
            
            elif opcion == "4":
                # Imprimir el árbol
                arbol.imprimir_inorden()
            
            elif opcion == "5":
                # Mostrar diagrama visual del árbol
                arbol.mostrar_diagrama()
            
            elif opcion == "6":
                # Mostrar diagrama simple del árbol
                arbol.mostrar_diagrama_simple()
            
            elif opcion == "7":
                # Mostrar estadísticas del árbol
                arbol.mostrar_estadisticas()
            
            elif opcion == "8":
                # Salir
                print("\n¡Gracias por usar el Sistema de Gestión de Árbol Binario de Búsqueda!")
                print("¡Hasta luego!")
                break
            
            else:
                print("✗ Opción inválida. Por favor seleccione una opción del 1 al 8.")
        
        except KeyboardInterrupt:
            print("\n\n¡Programa interrumpido por el usuario!")
            print("¡Hasta luego!")
            break
        except Exception as e:
            print(f"✗ Error inesperado: {e}")

if __name__ == "__main__":
    main()
