#include <stdio.h>

int main() {
    int arr[10], i, num, pos = -1;

    printf("Ingresa 10 números enteros:\n");
    for(i = 0; i < 10; i++) {
        printf("Número %d: ", i + 1);
        scanf("%d", &arr[i]);
    }

    printf("Ingresa el número que deseas eliminar: ");
    scanf("%d", &num);

    for(i = 0; i < 10; i++) {
        if(arr[i] == num) {
            pos = i;
            break;
        }
    }

    if(pos != -1) {
        for(i = pos; i < 9; i++) {
            arr[i] = arr[i + 1];
        }
        printf("El número %d ha sido eliminado.\n", num);
    } else {
        printf("El número %d no se encuentra en el arreglo.\n", num);
    }

    printf("El arreglo modificado es:\n");
    for(i = 0; i < 9; i++) {  
        printf("%d ", arr[i]);
    }
    printf("\n");

    return 0;
}
