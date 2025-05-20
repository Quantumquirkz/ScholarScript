package geometria;

public class Volumen3D {
    // Volumen del cono
    public static double calcularVolumenCono(double radio, double altura) {
        return (Math.PI * Math.pow(radio, 2) * altura) / 3;
    }

    // Volumen del cilindro
    public static double calcularVolumenCilindro(double radio, double altura) {
        return Math.PI * Math.pow(radio, 2) * altura;
    }

    // Volumen de la esfera
    public static double calcularVolumenEsfera(double radio) {
        return (4.0 / 3.0) * Math.PI * Math.pow(radio, 3);
    }
}