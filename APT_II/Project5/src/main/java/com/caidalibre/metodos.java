package com.caidalibre;

import java.util.Scanner;

public class metodos {

    public static double asignarAltura() {
        Scanner lee = new Scanner(System.in);
        System.out.print("Ingrese la altura desde la cual cae el objeto (en metros): ");
        return lee.nextDouble();
    }

    // Método 2: Asignar gravedad según el planeta
    public static double Gravedad(String planeta) {
        if (planeta.equalsIgnoreCase("tierra")) {
            return 9.8; // gravedad de la Tierra
        } else if (planeta.equalsIgnoreCase("marte")) {
            return 3.7; // gravedad del Marte
        } else {
            System.out.println("Planeta no reconocido. Se utilizará la gravedad de la Tierra por defecto.");
            return 9.8;
        }
    }

    // Método 3: Calcular tiempo de caída libre
    public static double calcularTiempoCaida(double altura, double gravedad) {
        return Math.sqrt((2 * altura) / gravedad);
    }

    // Método 4: Mostrar resultados
    public static void mostrarResultados(double altura, double gravedad, double tiempo, String planeta) {
        System.out.printf("\nResultados para %s:\n", planeta);
        System.out.printf("Altura: %.2f metros\n", altura);
        System.out.printf("Gravedad: %.2f m/s^2\n", gravedad);
        System.out.printf("Tiempo total de caída: %.2f segundos\n", tiempo);
    }

}
