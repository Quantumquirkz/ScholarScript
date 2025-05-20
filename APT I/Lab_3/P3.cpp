#include <stdio.h>
#include <math.h>

// Constante para el valor de pi
#define PI 3.14159265358979323846

void calcularCirculo() {
    float radio, area, perimetro;

    printf("Ingresa el radio del círculo: ");
    scanf("%f", &radio);

    area = PI * radio * radio;
    perimetro = 2 * PI * radio;

    printf("Área del círculo: %.2f\n", area);
    printf("Perímetro del círculo: %.2f\n", perimetro);
}

void calcularCuadrado() {
    float lado, area, perimetro;

    printf("Ingresa el lado del cuadrado: ");
    scanf("%f", &lado);

    area = lado * lado;
    perimetro = 4 * lado;

    printf("Área del cuadrado: %.2f\n", area);
    printf("Perímetro del cuadrado: %.2f\n", perimetro);
}

void calcularTriangulo() {
    float base, altura, lado1, lado2, lado3, area, perimetro;

    printf("Ingresa la base del triángulo: ");
    scanf("%f", &base);
    printf("Ingresa la altura del triángulo: ");
    scanf("%f", &altura);
    printf("Ingresa el primer lado del triángulo: ");
    scanf("%f", &lado1);
    printf("Ingresa el segundo lado del triángulo: ");
    scanf("%f", &lado2);
    printf("Ingresa el tercer lado del triángulo: ");
    scanf("%f", &lado3);

    area = (base * altura) / 2;
    perimetro = lado1 + lado2 + lado3;

    printf("Área del triángulo: %.2f\n", area);
    printf("Perímetro del triángulo: %.2f\n", perimetro);
}

int main() {
    int opcion;

    do {
        printf("\nCalculadora de Áreas y Perímetros\n");
        printf("Selecciona la figura geométrica:\n");
        printf("1. Círculo\n");
        printf("2. Cuadrado\n");
        printf("3. Triángulo\n");
        printf("4. Salir\n");
        printf("Ingresa tu opción (1-4): ");
        scanf("%d", &opcion);

        switch(opcion) {
            case 1:
                calcularCirculo();
                break;
            case 2:
                calcularCuadrado();
                break;
            case 3:
                calcularTriangulo();
                break;
            case 4:
                printf("Saliendo del programa...\n");
                break;
            default:
                printf("Opción inválida.\n");
                break;
        }

    } while (opcion != 4);

    return 0;
}
