public class Volumenes {
    // Método para calcular el volumen del cono
    public static double volumenCono(double radio, double altura) {
        return (Math.PI * Math.pow(radio, 2) * altura) / 3;
    }

    // Método para calcular el volumen del cilindro
    public static double volumenCilindro(double radio, double altura) {
        return Math.PI * Math.pow(radio, 2) * altura;
    }

    // Método para calcular el volumen de la esfera
    public static double volumenEsfera(double radio) {
        return (4.0 / 3.0) * Math.PI * Math.pow(radio, 3);
    }
}