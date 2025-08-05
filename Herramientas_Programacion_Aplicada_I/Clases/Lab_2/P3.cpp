#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main() {
    int numero_secreto, adivinanza, intentos = 0;

    srand(time(0));
    numero_secreto = rand() % 100 + 1;  

    printf("¡Adivina el número (entre 1 y 100)!\n");

    while (1) {
        printf("Ingresa tu adivinanza: ");
        scanf("%d", &adivinanza);

        for (;;) {
            intentos++;
            break;
        }

        if (adivinanza > numero_secreto) {
            printf("Demasiado alto. Intenta nuevamente.\n");
        } else if (adivinanza < numero_secreto) {
            printf("Demasiado bajo. Intenta nuevamente.\n");
        } else {
            printf("¡Felicidades! Adivinaste el número en %d intentos.\n", intentos);
            break;
        }
    }

    return 0;
}
