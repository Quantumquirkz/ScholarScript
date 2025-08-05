#include <stdio.h>

int main() {
    int limite, numero, suma = 0;

    printf("Ingresa el límite para la suma: ");
    scanf("%d", &limite);

    while (suma < limite) {
        printf("Ingresa un número positivo: ");
        scanf("%d", &numero);

        for (;;) {
            if (numero > 0) {
                break;
            } else {
                printf("Número inválido. Ingresa un número positivo: ");
                scanf("%d", &numero);
            }
        }

        suma += numero;

        printf("Suma actual: %d\n", suma);
    }

    printf("¡Límite alcanzado! Suma total: %d\n", suma);

    return 0;
}
