package com.caidalibre;

public class app {

    // Método principal
    public static void main(String[] args) {

        metodos op = new metodos();

        // Método 1: Asignar altura
        double altura = op.asignarAltura();

        // Cálculos para la Tierra
        double gravedadTierra = op.Gravedad("tierra");
        double tiempoTierra = op.calcularTiempoCaida(altura, gravedadTierra);
        op.mostrarResultados(altura, gravedadTierra, tiempoTierra, "Tierra");

        // Cálculos para Marte
        double gravedadMarte = op.Gravedad("marte");
        double tiempoMarte = op.calcularTiempoCaida(altura, gravedadMarte);
        op.mostrarResultados(altura, gravedadMarte, tiempoMarte, "Marte");
    }
}