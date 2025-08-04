#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h> 
#include <time.h>   

#define MAX_NODES 97

typedef struct Nodo {
    int id; 
    char nombre[50]; 
} Nodo;

typedef struct Automata {
    Nodo *posicion_actual;
} Automata;

void inicializar_nodo(Nodo *nodo, int id, const char *nombre) {
    nodo->id = id;
    strcpy(nodo->nombre, nombre);
}

void shuffle(Nodo *nodos[], int n) {
    for (int i = n - 1; i > 0; i--) {
        int j = rand() % (i + 1);
        Nodo *temp = nodos[i];
        nodos[i] = nodos[j];
        nodos[j] = temp;
    }
}

void recorrer_mapa_aleatorio(Automata *auto1, Nodo *nodos[], int num_nodos) {
    shuffle(nodos, num_nodos); 
    for (int i = 0; i < num_nodos; i++) {
        auto1->posicion_actual = nodos[i];
        printf("El automata esta en: %s (ID: %d)\n", auto1->posicion_actual->nombre, auto1->posicion_actual->id);
        sleep(1); 
    }
}

int main() {
    srand(time(NULL));

    Nodo nodos[MAX_NODES];
    inicializar_nodo(&nodos[0], 1, "Vestibulo 1");
    inicializar_nodo(&nodos[1], 2, "Cuarto de UPS");
    inicializar_nodo(&nodos[2], 3, "Escalera N1");
    inicializar_nodo(&nodos[3], 4, "Sala de Estudio N1");
    inicializar_nodo(&nodos[4], 5, "Escalera N2");
    inicializar_nodo(&nodos[5], 6, "Sanitarios N1");
    inicializar_nodo(&nodos[6], 7, "SS1 - Sala de Sistemas N1");
    inicializar_nodo(&nodos[7], 8, "SS2 - Sala de Sistemas N2");
    inicializar_nodo(&nodos[8], 9, "SS3 - Sala de Sistemas N3");
    inicializar_nodo(&nodos[9], 10, "SS4 - Sala de Sistemas N4");
    inicializar_nodo(&nodos[10], 11, "SS5 - Sala de Sistemas N5");
    inicializar_nodo(&nodos[11], 12, "Laboratorio de Sistemas N1");
    inicializar_nodo(&nodos[12], 13, "Laboratorio de Sistemas N2");
    inicializar_nodo(&nodos[13], 14, "Laboratorio de Sistemas N3");
    inicializar_nodo(&nodos[14], 15, "Laboratorio de Sistemas N4");
    inicializar_nodo(&nodos[15], 16, "Laboratorio de Administración N1");
    inicializar_nodo(&nodos[16], 17, "Laboratorio de Administración N2");
    inicializar_nodo(&nodos[17], 18, "Cuarto de Servicio");
    inicializar_nodo(&nodos[18], 19, "Sanitarios N2");
    inicializar_nodo(&nodos[19], 20, "Cuarto de Aseo");
    inicializar_nodo(&nodos[20], 21, "Oficinas de Servicios Generales");
    inicializar_nodo(&nodos[21], 22, "Decanato de la Facultad de Sistemas");
    inicializar_nodo(&nodos[22], 23, "Baño de Profesores");
    inicializar_nodo(&nodos[23], 24, "Depósito de Profesores");

    inicializar_nodo(&nodos[24], 25, "Salón de Clases N1");
    inicializar_nodo(&nodos[25], 26, "Salón de Clases N2");
    inicializar_nodo(&nodos[26], 27, "Salón de Clases N3");
    inicializar_nodo(&nodos[27], 28, "Salón de Clases N4");
    inicializar_nodo(&nodos[28], 29, "Salón de Clases N5");
    inicializar_nodo(&nodos[29], 30, "Salón de Clases N6");
    inicializar_nodo(&nodos[30], 31, "Salón de Clases N7");
    inicializar_nodo(&nodos[31], 32, "Salón de Clases N8");
    inicializar_nodo(&nodos[32], 33, "Salón de Clases N9");
    inicializar_nodo(&nodos[33], 34, "Salón de Clases N10");
    inicializar_nodo(&nodos[34], 35, "Salón de Clases N11");
    inicializar_nodo(&nodos[35], 36, "Salón de Clases N12");

    inicializar_nodo(&nodos[36], 37, "Aula de Investigacion");
    inicializar_nodo(&nodos[37], 38, "Oficina de Direccion");
    inicializar_nodo(&nodos[38], 39, "Cocineta");
    inicializar_nodo(&nodos[39], 40, "Cuarto de Aseo");
    
    for (int i = 41; i < MAX_NODES; i++) {
        char nombre[50];
        sprintf(nombre, "Oficina de Profesor N%d", i - 40); 
        inicializar_nodo(&nodos[i], i + 1, nombre); 
    }

    Nodo *nodos_en_orden[MAX_NODES];
    for (int i = 0; i < MAX_NODES; i++) {
        nodos_en_orden[i] = &nodos[i];
    }

    Automata auto1;
    auto1.posicion_actual = nodos_en_orden[0]; // Empieza en el primer nodo

    printf("Recorrido aleatorio del automata en el mapa:\n");
    recorrer_mapa_aleatorio(&auto1, nodos_en_orden, MAX_NODES);

    return 0;
}

