#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Nodo {
    char nombre[50];
    struct Nodo *opciones[3];  
} Nodo;

Nodo* crearNodo(char nombre[]) {
    Nodo *nuevoNodo = (Nodo*) malloc(sizeof(Nodo));
    strcpy(nuevoNodo->nombre, nombre);
    for (int i = 0; i < 3; i++) {
        nuevoNodo->opciones[i] = NULL;
    }
    return nuevoNodo;
}

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

int main() {
    Nodo *elDorado = crearNodo("El Dorado");
    Nodo *uip = crearNodo("Universidad Interamericana de Panamá (UIP)");
    Nodo *isae = crearNodo("Universidad ISAE");
    Nodo *usma = crearNodo("Universidad Santa María La Antigua (USMA)");
    Nodo *uLatina = crearNodo("Universidad Latina de Panamá (U LATINA)");
    Nodo *centennial = crearNodo("Centennial");
    Nodo *utp = crearNodo("Universidad Tecnológica de Panamá (UTP)");
    Nodo *up = crearNodo("Universidad de Panamá (UP)");
    Nodo *sanMiguelito = crearNodo("San Miguelito");

    elDorado->opciones[0] = uip;               
    uip->opciones[0] = isae;                   
    isae->opciones[0] = usma;                  
    usma->opciones[0] = uLatina;               
    uLatina->opciones[0] = utp;                
    uLatina->opciones[1] = centennial;         
    centennial->opciones[0] = utp;             
    utp->opciones[0] = up;                     
    up->opciones[0] = sanMiguelito;            

    printf("Bienvenido al mapa de puntos de interés de Panamá.\n");
    mostrarOpciones(elDorado);

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

