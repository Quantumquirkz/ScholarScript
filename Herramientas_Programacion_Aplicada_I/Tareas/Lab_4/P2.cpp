#include <stdio.h>

int main() {
    int arr[10], i;
    int max, min;

    printf("Ingresa 10 números enteros:\n");
    for(i = 0; i < 10; i++) {
        printf("Número %d: ", i + 1);
        scanf("%d", &arr[i]);
    }

    max = arr[0];
    min = arr[0];

    for(i = 1; i < 10; i++) {
        if(arr[i] > max) {
            max = arr[i];
        }
        if(arr[i] < min) {
            min = arr[i];
        }
    }

    printf("El número mayor es: %d\n", max);
    printf("El número menor es: %d\n", min);

    return 0;
}
