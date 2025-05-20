#include <stdio.h>

int main() {
    int numero;

    printf("Introduce un número entero: ");
    scanf("%d", &numero);

    // Determinar si el número es par o impar
    if (numero % 2 == 0) {
        printf("%d es un número par.\n", numero);
    } else {
        printf("%d es un número impar.\n", numero);
    }

    return 0;
}
