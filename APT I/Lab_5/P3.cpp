#include <stdio.h>
#include <string.h>
#include <ctype.h>

int contarVocales(char cadena[]) {
    int contador = 0;
    int longitud = strlen(cadena);

    for (int i = 0; i < longitud; i++) {
        char c = tolower(cadena[i]);

        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            contador++;
        }
    }
    return contador;
}

int main() {
    char cadena[100];

    printf("Ingresa una cadena: ");
    fgets(cadena, sizeof(cadena), stdin);
    cadena[strcspn(cadena, "\n")] = 0;

    int numeroVocales = contarVocales(cadena);

    printf("El número de vocales en la cadena es: %d\n", numeroVocales);

    return 0;
}
