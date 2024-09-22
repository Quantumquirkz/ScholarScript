#include <stdio.h>
#include <string.h>

void invertirCadena(char cadena[]) {
    int longitud = strlen(cadena);
    int inicio = 0;
    int fin = longitud - 1;
    char temp;

    while (inicio < fin) {
        temp = cadena[inicio];
        cadena[inicio] = cadena[fin];
        cadena[fin] = temp;

        inicio++;
        fin--;
    }
}

int main() {
    char cadena[100];

    printf("Ingresa una cadena: ");
    fgets(cadena, sizeof(cadena), stdin);
    cadena[strcspn(cadena, "\n")] = 0;

    invertirCadena(cadena);

    printf("La cadena invertida es: %s\n", cadena);

    return 0;
}
