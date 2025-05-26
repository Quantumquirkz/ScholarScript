package Programa3;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class JhuomarProg03 {
    private static final int LIMITE_ROUTERS = 100;
    
    public static void main(String[] args) {
        ArrayList<String[]> registros = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        try {
            System.out.println("\n=== REGISTRO DE ROUTERS ===");
            System.out.println("Ingrese los datos de los routers (deje el nombre en blanco para terminar)");
            System.out.println("Límite máximo de routers: " + LIMITE_ROUTERS);
            
            while (registros.size() < LIMITE_ROUTERS) {
                System.out.print("\nNombre del Router: ");
                String nombre = br.readLine();
                
                if (nombre.isEmpty()) break;
                
                System.out.print("Monto de Venta: ");
                double venta = Double.parseDouble(br.readLine());
                
                System.out.print("Costo de Compra: ");
                double costo = Double.parseDouble(br.readLine());
                
                registros.add(new String[]{nombre, String.valueOf(venta), String.valueOf(costo)});
                
                if (registros.size() >= LIMITE_ROUTERS) {
                    System.out.println("\nSe ha alcanzado el límite máximo de " + LIMITE_ROUTERS + " routers.");
                    break;
                }
            }
            
            // Calcular totales
            double totalVentas = 0;
            double totalCostos = 0;
            
            for (String[] reg : registros) {
                totalVentas += Double.parseDouble(reg[1]);
                totalCostos += Double.parseDouble(reg[2]);
            }
            
            // Imprimir resultados
            System.out.println("\n=== REPORTE DE ROUTERS ===");
            System.out.println("Nombre\t\tVenta\t\tCosto");
            System.out.println("----------------------------------------");
            
            for (String[] reg : registros) {
                System.out.printf("%-15s\t$%.2f\t\t$%.2f\n", 
                    reg[0], Double.parseDouble(reg[1]), Double.parseDouble(reg[2]));
            }
            
            System.out.println("----------------------------------------");
            System.out.printf("TOTALES\t\t$%.2f\t\t$%.2f\n", totalVentas, totalCostos);
            System.out.printf("UTILIDAD\t\t$%.2f\n", (totalVentas - totalCostos));
            System.out.printf("Total de Routers: %d\n", registros.size());
            
            // Guardar en archivo
            try (FileWriter fw = new FileWriter("C:/PRUEBA/Jhuomar.txt")) {
                fw.write("=== REPORTE DE ROUTERS ===\n");
                fw.write("Nombre\t\tVenta\t\tCosto\n");
                fw.write("----------------------------------------\n");
                
                for (String[] reg : registros) {
                    fw.write(String.format("%-15s\t$%.2f\t\t$%.2f\n", 
                        reg[0], Double.parseDouble(reg[1]), Double.parseDouble(reg[2])));
                }
                
                fw.write("----------------------------------------\n");
                fw.write(String.format("TOTALES\t\t$%.2f\t\t$%.2f\n", totalVentas, totalCostos));
                fw.write(String.format("UTILIDAD\t\t$%.2f\n", (totalVentas - totalCostos)));
                fw.write(String.format("Total de Routers: %d\n", registros.size()));
                System.out.println("\nDatos guardados en archivo correctamente");
            } catch (IOException e) {
                System.out.println("Error al guardar en archivo: " + e.getMessage());
            }
            
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Los montos deben ser valores numéricos");
        }
    }
} 