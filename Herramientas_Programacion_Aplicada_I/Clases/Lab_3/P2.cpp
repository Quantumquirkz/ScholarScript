#include <stdio.h>

float suma(float a, float b) {
    return a + b;
}

float resta(float a, float b) {
    return a - b;
}

float multiplicacion(float a, float b) {
    return a * b;
}

float division(float a, float b) {
    if (b != 0) {
        return a / b;
    } else {
        printf("Error: División por cero no permitida.\n");
        return 0;
    }
}

int main() {
    int opcion;
    float num1, num2, resultado;

    do {
        printf("\nCalculadora Básica\n");
        printf("Selecciona la operación:\n");
        printf("1. Suma\n");
        printf("2. Resta\n");
        printf("3. Multiplicación\n");
        printf("4. División\n");
        printf("5. Salir\n");
        printf("Ingresa tu opción (1-5): ");
        scanf("%d", &opcion);

        if (opcion == 5) {
            printf("Saliendo del programa...\n");
            break;
        }

        if (opcion >= 1 && opcion <= 4) {
            printf("Ingresa el primer número: ");
            scanf("%f", &num1);
            printf("Ingresa el segundo número: ");
            scanf("%f", &num2);
        }

        // Realizar la operación seleccionada
        switch(opcion) {
            case 1:
                resultado = suma(num1, num2);
                printf("Resultado: %.2f\n", resultado);
                break;
            case 2:
                resultado = resta(num1, num2);
                printf("Resultado: %.2f\n", resultado);
                break;
            case 3:
                resultado = multiplicacion(num1, num2);
                printf("Resultado: %.2f\n", resultado);
                break;
            case 4:
                resultado = division(num1, num2);
                if (num2 != 0) {
                    printf("Resultado: %.2f\n", resultado);
                }
                break;
            default:
                if (opcion != 5) {
                    printf("Opción inválida.\n");
                }
                break;
        }

    } while (opcion != 5);

    return 0;
}
