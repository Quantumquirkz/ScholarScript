#include <stdio.h>
#include <string.h>

int esPalindromo(char cadena[]) {
    int inicio = 0;
    int fin = strlen(cadena) - 1;

    while (inicio < fin) {
        if (cadena[inicio] != cadena[fin]) {
            return 0; 
        }
        inicio++;
        fin--;
    }
    return 1; 
}

int main() {
    char cadena[100];

    printf("Ingresa una cadena: ");
    scanf("%s", cadena); 

    if (esPalindromo(cadena)) {
        printf("La cadena es un palíndromo.\n");
    } else {
        printf("La cadena no es un palíndromo.\n");
    }

    return 0;
}
