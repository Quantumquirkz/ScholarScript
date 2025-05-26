package Programa2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JhuomarProg02 {
    public static void main(String[] args) {
        Operaciones op = new Operaciones();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            System.out.println("\nMENÚ PRINCIPAL");
            System.out.println("1. Sumar dos números enteros");
            System.out.println("2. Calcular volumen de un cono");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            
            try {
                int opcion = Integer.parseInt(br.readLine());
                
                switch (opcion) {
                    case 1:
                        op.asignarValoresSuma();
                        System.out.println(op.getResultados());
                        break;
                    case 2:
                        op.asignarValoresCono();
                        System.out.println(op.getResultados());
                        break;
                    case 3:
                        System.out.println("Saliendo del sistema...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opción no válida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido");
            } catch (IOException e) {
                System.out.println("Error de entrada/salida");
            }
        }
    }
} 