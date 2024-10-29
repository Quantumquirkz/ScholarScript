#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Definición de un nodo en el mapa
typedef struct Nodo {
    char nombre[50];
    struct Nodo *opciones[3];  // Hasta 3 opciones de caminos por nodo
} Nodo;

// Función para crear un nodo
Nodo* crearNodo(char nombre[]) {
    Nodo *nuevoNodo = (Nodo*) malloc(sizeof(Nodo));
    strcpy(nuevoNodo->nombre, nombre);
    for (int i = 0; i < 3; i++) {
        nuevoNodo->opciones[i] = NULL;
    }
    return nuevoNodo;
}

// Función para recorrer el mapa con múltiples opciones
void mostrarOpciones(Nodo *inicio) {
    Nodo *actual = inicio;
    while (actual != NULL) {
        printf("Ubicación actual: %s\n", actual->nombre);
        printf("Opciones disponibles:\n");
        
        int opcionesDisponibles = 0;
        for (int i = 0; i < 3; i++) {
            if (actual->opciones[i] != NULL) {
                printf("%d. Ir a %s\n", i + 1, actual->opciones[i]->nombre);
                opcionesDisponibles++;
            }
        }
        printf("0. Salir del mapa\n");

        // Validación de entrada del usuario
        int opcion;
        do {
            printf("Seleccione una opción: ");
            scanf("%d", &opcion);
            if (opcion == 0) {
                printf("Saliendo del mapa...\n");
                return;
            } else if (opcion > 0 && opcion <= opcionesDisponibles) {
                actual = actual->opciones[opcion - 1];
                break;
            } else {
                printf("Opción no válida. Intente de nuevo.\n");
            }
        } while (1);
    }
}

// Función principal
int main() {
    // Creación de nodos (lugares)
    Nodo *elDorado = crearNodo("El Dorado");
    Nodo *uip = crearNodo("Universidad Interamericana de Panamá (UIP)");
    Nodo *isae = crearNodo("Universidad ISAE");
    Nodo *usma = crearNodo("Universidad Santa María La Antigua (USMA)");
    Nodo *uLatina = crearNodo("Universidad Latina de Panamá (U LATINA)");
    Nodo *centennial = crearNodo("Centennial");
    Nodo *utp = crearNodo("Universidad Tecnológica de Panamá (UTP)");
    Nodo *up = crearNodo("Universidad de Panamá (UP)");
    Nodo *sanMiguelito = crearNodo("San Miguelito");

    // Enlace entre nodos con opciones de caminos
    elDorado->opciones[0] = uip;               // Camino desde El Dorado
    uip->opciones[0] = isae;                   // Camino desde UIP
    isae->opciones[0] = usma;                  // Camino desde ISAE
    usma->opciones[0] = uLatina;               // Camino desde USMA
    uLatina->opciones[0] = utp;                // Camino principal desde U Latina
    uLatina->opciones[1] = centennial;         // Camino alternativo desde U Latina a Centennial
    centennial->opciones[0] = utp;             // Camino de regreso de Centennial a UTP
    utp->opciones[0] = up;                     // Camino desde UTP
    up->opciones[0] = sanMiguelito;            // Camino desde UP hasta San Miguelito

    // Menú interactivo para mostrar el mapa
    printf("Bienvenido al mapa de puntos de interés de Panamá.\n");
    mostrarOpciones(elDorado);

    // Liberación de memoria
    free(elDorado);
    free(uip);
    free(isae);
    free(usma);
    free(uLatina);
    free(centennial);
    free(utp);
    free(up);
    free(sanMiguelito);

    return 0;
}

