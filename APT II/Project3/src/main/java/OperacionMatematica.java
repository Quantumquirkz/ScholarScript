public class OperacionMatematica {
    public static int sumar(int a, int b) {
        return a + b;
    }

    public static int restar(int a, int b) {
        return a - b;
    }

    public static int multiplicar(int a, int b) {
        return a * b;
    }

    public static String dividir(int a, int b) {
        if (b == 0) {
            return "Error: división por cero";
        } else {
            return String.format("%.2f", (double) a / b);
        }
    }
}