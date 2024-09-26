#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void inicializarTablero(char tablero[8][8]) {
    char piezasNegras[] = {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'};
    char piezasBlancas[] = {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'};
    
    // Colocar las piezas negras y blancas
    for (int i = 0; i < 8; i++) {
        tablero[0][i] = piezasNegras[i];
        tablero[1][i] = 'P';
        tablero[6][i] = 'p';
        tablero[7][i] = piezasBlancas[i];
        for (int j = 2; j < 6; j++) {
            tablero[j][i] = ' ';
        }
    }
}

void imprimirTablero(char tablero[8][8]) {
    printf("  a b c d e f g h\n");
    printf(" +----------------+\n");
    for (int i = 0; i < 8; i++) {
        printf("%d|", 8 - i); // Numeración de filas
        for (int j = 0; j < 8; j++) {
            printf("%c ", tablero[i][j]);
        }
        printf("|%d\n", 8 - i);
    }
    printf(" +----------------+\n");
    printf("  a b c d e f g h\n");
}

void convertirCoordenadas(char movimiento[], int *filaInicio, int *colInicio, int *filaFin, int *colFin) {
    *colInicio = movimiento[0] - 'a';
    *filaInicio = 8 - (movimiento[1] - '0');
    *colFin = movimiento[2] - 'a';
    *filaFin = 8 - (movimiento[3] - '0');
}

int moverPieza(char tablero[8][8], int filaInicio, int colInicio, int filaFin, int colFin) {
    if (filaInicio < 0 || filaInicio >= 8 || colInicio < 0 || colInicio >= 8 ||
        filaFin < 0 || filaFin >= 8 || colFin < 0 || colFin >= 8) {
        printf("Movimiento fuera de los límites del tablero.\n");
        return 0;
    }

    if (tablero[filaInicio][colInicio] == ' ') {
        printf("No hay ninguna pieza en la posición inicial.\n");
        return 0;
    }

    tablero[filaFin][colFin] = tablero[filaInicio][colInicio];
    tablero[filaInicio][colInicio] = ' ';
    return 1;
}

int main() {
    char tablero[8][8];
    inicializarTablero(tablero);

    char movimiento[5];
    int filaInicio, colInicio, filaFin, colFin;
    int turno = 1;

    while (1) {
        system("clear"); 
        imprimirTablero(tablero);

        if (turno == 1) {
            printf("Turno de las piezas mayúsculas (negras).\n");
        } else {
            printf("Turno de las piezas minúsculas (blancas).\n");
        }

        printf("Ingrese el movimiento (ej: e2e4) o 'salir' para terminar: ");
        scanf("%s", movimiento);

        if (strcmp(movimiento, "salir") == 0) {
            break;
        }

        convertirCoordenadas(movimiento, &filaInicio, &colInicio, &filaFin, &colFin);

        if (!moverPieza(tablero, filaInicio, colInicio, filaFin, colFin)) {
            printf("Movimiento inválido. Intente de nuevo.\n");
            continue;
        }

        turno = 1 - turno;
    }

    return 0;
}


