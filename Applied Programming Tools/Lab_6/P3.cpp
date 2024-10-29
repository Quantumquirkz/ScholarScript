#include <stdio.h>
#include <ctype.h>

int contarVocales(char cadena[]) {
    int contador = 0;
    for (int i = 0; cadena[i] != '\0'; i++) {
        char letra = tolower(cadena[i]);
        if (letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u') {
            contador++;
        }
    }
    return contador;
}

int main() {
    char cadena[100];
    printf("Ingrese una cadena para contar las vocales: ");
    gets(cadena);

    int numeroVocales = contarVocales(cadena);
    printf("El numero de vocales en la cadena es: %d\n", numeroVocales);

    return 0;
}
