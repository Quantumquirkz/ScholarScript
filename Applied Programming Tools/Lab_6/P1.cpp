#include <stdio.h>
#include <stdlib.h>

void cifrarCesar(char *texto, int desplazamiento) {
    for (int i = 0; texto[i] != '\0'; i++) {
        if (texto[i] >= 'a' && texto[i] <= 'z') {
            texto[i] = (texto[i] - 'a' + desplazamiento) % 26 + 'a';
        } else if (texto[i] >= 'A' && texto[i] <= 'Z') {
            texto[i] = (texto[i] - 'A' + desplazamiento) % 26 + 'A';
        }
    }
}

int main() {
    char texto[100];
    int desplazamiento = 3; 

    printf("Ingrese el texto a cifrar: ");
    fgets(texto, sizeof(texto), stdin);

    cifrarCesar(texto, desplazamiento);

    FILE *archivo = fopen("archivo_cifrado.txt", "w");
    if (archivo == NULL) {
        printf("Error al crear el archivo.\n");
        return 1;
    }

    fprintf(archivo, "%s", texto);
    fclose(archivo);

    printf("El texto ha sido cifrado y guardado en 'archivo_cifrado.txt'.\n");

    return 0;
}
