class NodoArbol:
    def __init__(self, info):
        self.info = info
        self.izq = None
        self.der = None

def SolicitarEntero(mensaje):
    while True:
        entrada = input(mensaje)
        try:
            return int(entrada)
        except ValueError:
            print("Entrada inválida. Por favor, ingrese un número entero.")


def SolicitarSiNo(mensaje):
    while True:
        respuesta = input(mensaje).strip().lower()
        if respuesta in ("s", "si", "sí"):
            return True
        if respuesta in ("n", "no"):
            return False
        print("Respuesta inválida. Responda con 's' o 'n'.")


def Crear(nodo):
    if nodo is None:
        return
    if SolicitarSiNo(f"¿Existe nodo a la izquierda de {nodo.info}? (s/n): "):
        valor_izq = SolicitarEntero(f"Ingrese el valor (entero) del nodo izquierdo de {nodo.info}: ")
        nodo.izq = NodoArbol(valor_izq)
        Crear(nodo.izq)
    if SolicitarSiNo(f"¿Existe nodo a la derecha de {nodo.info}? (s/n): "):
        valor_der = SolicitarEntero(f"Ingrese el valor (entero) del nodo derecho de {nodo.info}: ")
        nodo.der = NodoArbol(valor_der)
        Crear(nodo.der)


def CrearArbolInteractivo():
    valor_raiz = SolicitarEntero("Ingrese el valor (entero) del nodo raíz: ")
    raiz = NodoArbol(valor_raiz)
    Crear(raiz)
    return raiz

def ContarNodos(nodo):
    if nodo is None:
        return 0
    return 1 + ContarNodos(nodo.izq) + ContarNodos(nodo.der)

def _ConstruirLineasASCII(nodo):
    if nodo is None:
        return [" "], 1, 1, 0

    texto = str(nodo.info)
    texto_len = len(texto)

    if nodo.izq is None and nodo.der is None:
        return [texto], texto_len, 1, texto_len // 2

    if nodo.der is None:
        lineas_izq, ancho_izq, alto_izq, medio_izq = _ConstruirLineasASCII(nodo.izq)
        primero = (medio_izq + 1) * " " + (ancho_izq - medio_izq - 1) * "_" + texto
        segundo = medio_izq * " " + "/" + (ancho_izq - medio_izq - 1 + texto_len) * " "
        lineas = [primero, segundo]
        for i in range(alto_izq):
            lineas.append(lineas_izq[i] + texto_len * " ")
        return lineas, ancho_izq + texto_len, alto_izq + 2, ancho_izq + texto_len // 2

    if nodo.izq is None:
        lineas_der, ancho_der, alto_der, medio_der = _ConstruirLineasASCII(nodo.der)
        primero = texto + medio_der * "_" + (ancho_der - medio_der) * " "
        segundo = (texto_len + medio_der) * " " + "\\" + (ancho_der - medio_der - 1) * " "
        lineas = [primero, segundo]
        for i in range(alto_der):
            lineas.append(texto_len * " " + lineas_der[i])
        return lineas, texto_len + ancho_der, alto_der + 2, texto_len // 2

    lineas_izq, ancho_izq, alto_izq, medio_izq = _ConstruirLineasASCII(nodo.izq)
    lineas_der, ancho_der, alto_der, medio_der = _ConstruirLineasASCII(nodo.der)

    primero = (medio_izq + 1) * " " + (ancho_izq - medio_izq - 1) * "_" + texto + medio_der * "_" + (ancho_der - medio_der) * " "
    segundo = medio_izq * " " + "/" + (ancho_izq - medio_izq - 1 + texto_len + medio_der) * " " + "\\" + (ancho_der - medio_der - 1) * " "

    if alto_izq < alto_der:
        lineas_izq += [ancho_izq * " "] * (alto_der - alto_izq)
    elif alto_der < alto_izq:
        lineas_der += [ancho_der * " "] * (alto_izq - alto_der)

    lineas = [primero, segundo] + [lineas_izq[i] + texto_len * " " + lineas_der[i] for i in range(max(alto_izq, alto_der))]
    return lineas, ancho_izq + texto_len + ancho_der, max(alto_izq, alto_der) + 2, ancho_izq + texto_len // 2


def ImprimirArbol(nodo):
    lineas, _, _, _ = _ConstruirLineasASCII(nodo)
    for linea in lineas:
        print(linea)

def main():
    print("UNIVERSIDAD TECNOLÓGICA DE PANAMÁ")
    print("FACULTAD DE INGENIERÍA DE SISTEMAS COMPUTACIONALES")
    print("Laboratorio No.1: Creación de nodos en árboles binario")
    print("Grupo: 1IL-126")
    print("Integrantes: Terry He, Jhuomar Barría\n")

    raiz = CrearArbolInteractivo()
    print("\nDiagrama del árbol:\n")
    ImprimirArbol(raiz)
    total_nodos = ContarNodos(raiz)

    print("\nÁrbol binario no ordenado creado.")
    print(f"Total de nodos del árbol: {total_nodos}")

if __name__ == "__main__":
    main()
