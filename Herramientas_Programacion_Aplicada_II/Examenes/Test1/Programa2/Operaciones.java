package Programa2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Operaciones {
    private int valorA, valorB;
    private double radio, altura;
    private StringBuilder resultados;
    
    public Operaciones() {
        resultados = new StringBuilder();
    }
    
    public void asignarValoresSuma() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        resultados.setLength(0);
        
        try {
            System.out.print("Ingrese valor A: ");
            valorA = Integer.parseInt(br.readLine());
            System.out.print("Ingrese valor B: ");
            valorB = Integer.parseInt(br.readLine());
            
            resultados.append("\nREPORTE DE SUMA\n");
            resultados.append("Valor A: ").append(valorA).append("\n");
            resultados.append("Valor B: ").append(valorB).append("\n");
            resultados.append("Resultado: ").append(sumarAB()).append("\n");
        } catch (NumberFormatException e) {
            resultados.append("Error: Debe ingresar números enteros válidos\n");
        }
    }
    
    public void asignarValoresCono() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        resultados.setLength(0);
        
        try {
            System.out.print("Ingrese radio del cono: ");
            radio = Double.parseDouble(br.readLine());
            System.out.print("Ingrese altura del cono: ");
            altura = Double.parseDouble(br.readLine());
            
            resultados.append("\nREPORTE DE VOLUMEN\n");
            resultados.append("Radio: ").append(radio).append("\n");
            resultados.append("Altura: ").append(altura).append("\n");
            resultados.append("Volumen: ").append(calcularVolumenCono()).append("\n");
        } catch (NumberFormatException e) {
            resultados.append("Error: Debe ingresar valores numéricos válidos\n");
        }
    }
    
    public int sumarAB() {
        return valorA + valorB;
    }
    
    public double calcularVolumenCono() {
        return (1.0/3.0) * Math.PI * Math.pow(radio, 2) * altura;
    }
    
    public String getResultados() {
        return resultados.toString();
    }
} 