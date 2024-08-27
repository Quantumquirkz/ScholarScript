#include <stdio.h>

int sumar_digitos(int numero) {
    int suma = 0;
    while (numero > 0) {
        suma += numero % 10;
        numero /= 10;
    }
    return suma;
}

int main() {
    int numero, suma;

    printf("Ingresa un número entero: ");
    scanf("%d", &numero);

    for (;;) {
        suma = sumar_digitos(numero);
        if (suma < 10) {
            break;
        }
        numero = suma;
    }

    printf("La suma de los dígitos reducida a un solo dígito es: %d\n", suma);

    return 0;
}
