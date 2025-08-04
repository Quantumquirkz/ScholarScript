#include <stdio.h>
#include <stdlib.h>

void descifrarCesar(char *texto, int desplazamiento) {
    for (int i = 0; texto[i] != '\0'; i++) {
        if (texto[i] >= 'a' && texto[i] <= 'z') {
            texto[i] = (texto[i] - 'a' - desplazamiento + 26) % 26 + 'a';
        } else if (texto[i] >= 'A' && texto[i] <= 'Z') {
            texto[i] = (texto[i] - 'A' - desplazamiento + 26) % 26 + 'A';
        }
    }
}

int main() {
    char texto[100];
    int desplazamiento = 3; 

    FILE *archivo = fopen("archivo_cifrado.txt", "r");
    if (archivo == NULL) {
        printf("Error al abrir el archivo.\n");
        return 1;
    }

    fgets(texto, sizeof(texto), archivo);
    fclose(archivo);

    descifrarCesar(texto, desplazamiento);

    printf("Texto descifrado: %s\n", texto);

    return 0;
}
