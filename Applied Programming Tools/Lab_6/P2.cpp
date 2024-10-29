#include <stdio.h>
#include <string.h>

void invertirCadena(char cadena[]) {
    int longitud = strlen(cadena);
    for (int i = 0; i < longitud / 2; i++) {
        char temp = cadena[i];
        cadena[i] = cadena[longitud - i - 1];
        cadena[longitud - i - 1] = temp;
    }
}

int main() {
    char cadena[100];
    printf("Ingrese una cadena para invertir: ");
    gets(cadena);

    invertirCadena(cadena);
    printf("Cadena invertida: %s\n", cadena);

    return 0;
}
