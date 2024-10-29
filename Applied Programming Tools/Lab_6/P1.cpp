#include <stdio.h>
#include <string.h>
#include <ctype.h>

int esPalindromo(char cadena[]) {
    int inicio = 0;
    int fin = strlen(cadena) - 1;

    while (inicio < fin) {
        if (tolower(cadena[inicio]) != tolower(cadena[fin])) {
            return 0; 
        }
        inicio++;
        fin--;
    }
    return 1; 
}

int main() {
    char cadena[100];
    printf("Ingrese una cadena: ");
    gets(cadena);

    if (esPalindromo(cadena)) {
        printf("La cadena es un palindromo.\n");
    } else {
        printf("La cadena no es un palindromo.\n");
    }

    return 0;
}
