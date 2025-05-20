#include <stdio.h>

int main() {
    int edad;

    printf("Introduce tu edad: ");
    scanf("%d", &edad);

    // Clasificar la edad en categorías
    if (edad >= 0 && edad <= 12) {
        printf("Eres un niño.\n");
    } else if (edad >= 13 && edad <= 17) {
        printf("Eres un adolescente.\n");
    } else if (edad >= 18 && edad <= 64) {
        printf("Eres un adulto.\n");
    } else if (edad >= 65) {
        printf("Eres un anciano.\n");
    } else {
        printf("Edad no válida.\n");
    }

    return 0;
}
