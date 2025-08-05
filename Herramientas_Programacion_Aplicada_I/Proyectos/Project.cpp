#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <unistd.h>

#define MAX_NODOS 12
#define MAX_ANIMATRONICOS 5
#define MAX_NOMBRE 50

// Estructura de Nodo
typedef struct {
    int id;
    char nombre[MAX_NOMBRE];
    int conexiones[MAX_NODOS];
    int num_conexiones;
    int es_visible_camara;
} Nodo;

// Estados de Animatrónico
typedef enum {
    PATRULLAJE,
    CAZA,
    ESCONDIDO,
    INACTIVO
} EstadoAnimatronico;

// Estructura de Animatrónico
typedef struct {
    int id;
    EstadoAnimatronico estado;
    int nodo_actual;
    char nombre[MAX_NOMBRE];
    int agresividad;
} Animatronico;

// Estructura del Jugador
typedef struct {
    int nodo_actual;
    int energia;
    int noches_sobrevividas;
    int tiempo_restante;
} Jugador;

// Variables Globales
Nodo mapa[MAX_NODOS];
Animatronico animatronicos[MAX_ANIMATRONICOS];
Jugador jugador;

// Prototipos de Funciones
void inicializar_mapa();
void inicializar_animatronicos();
void inicializar_jugador();
void mostrar_mapa_actual();
void mostrar_estado_jugador();
void mover_animatronicos();
int verificar_colision();
void menu_principal();
void iniciar_noche();
void usar_camara();
void usar_puerta(int lado);
void reiniciar_generador();
int seleccionar_accion();

// Función para inicializar el mapa con todos los nodos
void inicializar_mapa() {
    // Definir nombres y conexiones de los nodos según el documento
    struct {
        int id;
        char nombre[MAX_NOMBRE];
        int conexiones[MAX_NODOS];
        int num_conexiones;
        int es_visible_camara;
    } nodos_predefinidos[] = {
        {0, "Oficina", {1, 2}, 2, 1},
        {1, "Pasillo Izquierdo", {0, 3, 5}, 3, 1},
        {2, "Pasillo Derecho", {0, 4, 6}, 3, 1},
        {3, "Habitación Inicial 1", {1, 7}, 2, 1},
        {4, "Habitación Inicial 2", {2, 7}, 2, 1},
        {5, "Zona Distracción Izquierda", {1, 7}, 2, 1},
        {6, "Zona Distracción Derecha", {2, 7}, 2, 1},
        {7, "Pasadizo Oculto", {3, 4, 5, 6}, 4, 0},
        {8, "Sala Generador", {10, 11}, 2, 1},
        {9, "Sala Seguridad", {11}, 1, 1},
        {10, "Zona Mantenimiento Izquierda", {8, 1}, 2, 1},
        {11, "Zona Mantenimiento Derecha", {8, 9, 2}, 3, 1}
    };

    for (int i = 0; i < MAX_NODOS; i++) {
        mapa[i].id = nodos_predefinidos[i].id;
        strcpy(mapa[i].nombre, nodos_predefinidos[i].nombre);
        mapa[i].num_conexiones = nodos_predefinidos[i].num_conexiones;
        mapa[i].es_visible_camara = nodos_predefinidos[i].es_visible_camara;

        memcpy(mapa[i].conexiones, nodos_predefinidos[i].conexiones, 
               sizeof(nodos_predefinidos[i].conexiones));
    }
}

// Función para inicializar animatrónicos con diferentes personalidades
void inicializar_animatronicos() {
    char *nombres[] = {"Freddy", "Bonnie", "Chica", "Foxy", "Golden Freddy"};
    
    for (int i = 0; i < MAX_ANIMATRONICOS; i++) {
        animatronicos[i].id = i;
        strcpy(animatronicos[i].nombre, nombres[i]);
        animatronicos[i].estado = PATRULLAJE;
        
        // Personalidades diferentes
        switch(i) {
            case 0: // Freddy (Agresivo)
                animatronicos[i].agresividad = 8;
                animatronicos[i].nodo_actual = 3;
                break;
            case 1: // Bonnie (Sigiloso)
                animatronicos[i].agresividad = 6;
                animatronicos[i].nodo_actual = 1;
                break;
            case 2: // Chica (Distractor)
                animatronicos[i].agresividad = 5;
                animatronicos[i].nodo_actual = 5;
                break;
            case 3: // Foxy (Impredecible)
                animatronicos[i].agresividad = 7;
                animatronicos[i].nodo_actual = 4;
                break;
            case 4: // Golden Freddy (Letal)
                animatronicos[i].agresividad = 9;
                animatronicos[i].nodo_actual = 7;
                break;
        }
    }
}

// Inicializar jugador
void inicializar_jugador() {
    jugador.nodo_actual = 0;  // Comienza en la Oficina
    jugador.energia = 100;    // Energía inicial
    jugador.noches_sobrevividas = 0;
    jugador.tiempo_restante = 360; // 6 minutos (6 * 60 segundos)
}

// Mostrar mapa actual
void mostrar_mapa_actual() {
    printf("\n--- MAPA ACTUAL ---\n");
    printf("Ubicacion del Jugador: %s (Nodo %d)\n", 
           mapa[jugador.nodo_actual].nombre, jugador.nodo_actual);
    
    printf("\nAnimatronicos:\n");
    for (int i = 0; i < MAX_ANIMATRONICOS; i++) {
        printf("%s: %s (Nodo %d)\n", 
               animatronicos[i].nombre, 
               animatronicos[i].estado == PATRULLAJE ? "Patrullando" : 
               animatronicos[i].estado == CAZA ? "Cazando" : 
               animatronicos[i].estado == ESCONDIDO ? "Escondido" : "Inactivo",
               animatronicos[i].nodo_actual);
    }
}

// Movimiento de animatrónicos
void mover_animatronicos() {
    for (int i = 0; i < MAX_ANIMATRONICOS; i++) {
        // Lógica de movimiento basada en agresividad
        if (rand() % 10 < animatronicos[i].agresividad / 2) {
            // Movimiento aleatorio entre conexiones
            int conexion = rand() % mapa[animatronicos[i].nodo_actual].num_conexiones;
            animatronicos[i].nodo_actual = 
                mapa[animatronicos[i].nodo_actual].conexiones[conexion];
            
            // Cambiar estado según probabilidad
            if (rand() % 10 < 3) {
                animatronicos[i].estado = (EstadoAnimatronico)(rand() % 4); // Cambiar aleatoriamente
            }
        }
    }
}

// Verificar si un animatrónico ha alcanzado al jugador
int verificar_colision() {
    for (int i = 0; i < MAX_ANIMATRONICOS; i++) {
        if (animatronicos[i].nodo_actual == jugador.nodo_actual) {
            return 1;
        }
    }
    return 0;
}

// Usar cámara (drena energía)
void usar_camara() {
    if (jugador.energia > 10) {
        printf("\n--- SISTEMA DE CAMARAS ---\n");
        for (int i = 0; i < MAX_NODOS; i++) {
            if (mapa[i].es_visible_camara) {
                printf("Nodo %d: %s\n", i, mapa[i].nombre);
            }
        }
        jugador.energia -= 10;
    } else {
        printf("¡No hay suficiente energia para usar las cámaras!\n");
    }
}

// Usar puerta (drena energía)
void usar_puerta(int lado) {
    if (jugador.energia > 15) {
        printf("\n--- PUERTA %s CERRADA ---\n", lado ? "DERECHA" : "IZQUIERDA");
        jugador.energia -= 15;
    } else {
        printf("¡No hay suficiente energia para cerrar la puerta!\n");
    }
}

// Reiniciar generador
void reiniciar_generador() {
    if (jugador.nodo_actual == 8) {
        printf("\n--- REINICIANDO GENERADOR ---\n");
        jugador.energia = 100;
        sleep(3);  // Tiempo para reiniciar
    } else {
        printf("Debes estar en la Sala de Generador para reiniciarlo.\n");
    }
}

// Menú de selección de acción
int seleccionar_accion() {
    int opcion;
    printf("\n--- ACCIONES DISPONIBLES ---\n");
    printf("1. Usar Camara\n");
    printf("2. Cerrar Puerta Izquierda\n");
    printf("3. Cerrar Puerta Derecha\n");
    printf("4. Reiniciar Generador\n");
    printf("5. Cambiar Nodo\n");
    printf("6. Terminar Noche\n");
    printf("Seleccione una opcion: ");
    scanf("%d", &opcion);
    return opcion;
}

// Iniciar una noche
void iniciar_noche() {
    inicializar_mapa();
    inicializar_animatronicos();
    inicializar_jugador();

    printf("\n--- NOCHE %d ---\n", jugador.noches_sobrevividas + 1);

    while (jugador.tiempo_restante > 0) {
        mostrar_mapa_actual();
        mostrar_estado_jugador();

        int accion = seleccionar_accion();
        switch(accion) {
            case 1: usar_camara(); break;
            case 2: usar_puerta(0); break;
            case 3: usar_puerta(1); break;
            case 4: reiniciar_generador(); break;
            case 5: {
                int nuevo_nodo;
                printf("Ingrese numero de nodo: ");
                scanf("%d", &nuevo_nodo);
                jugador.nodo_actual = nuevo_nodo;
                break;
            }
            case 6: break;
        }

        mover_animatronicos();
        
        if (verificar_colision()) {
            printf("\n¡Un animatronico te ha atrapado!\n");
            return;
        }

        jugador.tiempo_restante--;
        jugador.energia = (jugador.energia > 0) ? jugador.energia - 1 : 0;

        if (jugador.energia <= 0) {
            printf("\n¡Sin energia! Fin del juego.\n");
            return;
        }
    }

    jugador.noches_sobrevividas++;
}

// Mostrar estado del jugador
void mostrar_estado_jugador() {
    printf("\n--- ESTADO DEL JUGADOR ---\n");
    printf("Energia: %d%%\n", jugador.energia);
    printf("Tiempo Restante: %d segundos\n", jugador.tiempo_restante);
}

// Menú principal
void menu_principal() {
    int opcion;
    do {
        printf("\n--- FABRICA DELFICA DE DIVERSIÓN ---\n");
        printf("1. Nueva Partida\n");
        printf("2. Instrucciones\n");
        printf("3. Salir\n");
        printf("Seleccione una opcion: ");
        scanf("%d", &opcion);

        switch(opcion) {
            case 1: 
                srand(time(NULL));
                iniciar_noche(); 
                break;
            case 2:
                printf("\nSobrevive 6 minutos en la fabrica. Usa camaras, puertas y generador con cuidado.\n");
                break;
            case 3:
                printf("¡Gracias por jugar!\n");
                break;
        }
    } while (opcion != 3);
}

int main() {
    menu_principal();
    return 0;
}
