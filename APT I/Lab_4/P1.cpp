#include <stdio.h>

int main() {
    int arr[10], i, num, found = 0;

    printf("Ingresa 10 números enteros:\n");
    for(i = 0; i < 10; i++) {
        printf("Número %d: ", i + 1);
        scanf("%d", &arr[i]);
    }

    printf("Ingresa un número adicional para buscar: ");
    scanf("%d", &num);

    for(i = 0; i < 10; i++) {
        if(arr[i] == num) {
            found = 1;
            break;
        }
    }

    if(found) {
        printf("El número %d está en el arreglo.\n", num);
    } else {
        printf("El número %d NO está en el arreglo.\n", num);
    }

    return 0;
}
