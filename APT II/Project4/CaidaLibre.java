public class CaidaLibre {
    // Constantes
    private static final double GRAVEDAD = 9.8; // m/s^2

    // Variables de instancia
    private double velocidadInicial;
    private double velocidadFinal;
    private double desplazamiento;
    private double tiempo;

    // Constructor
    public CaidaLibre() {
        this.velocidadInicial = 0;
        this.velocidadFinal = 0;
        this.desplazamiento = 0;
        this.tiempo = 0;
    }

    // Método 1: Calcular velocidad final
    public double calcularVelocidadFinal() {
        return velocidadInicial + (GRAVEDAD * tiempo);
    }

    // Método 2: Calcular desplazamiento usando tiempo
    public double calcularDesplazamientoConTiempo() {
        return (velocidadInicial * tiempo) + (0.5 * GRAVEDAD * tiempo * tiempo);
    }

    // Método 3: Calcular velocidad final usando desplazamiento
    public double calcularVelocidadFinalConDesplazamiento() {
        return Math.sqrt(Math.pow(velocidadInicial, 2) + (2 * GRAVEDAD * desplazamiento));
    }

    // Método 4: Calcular desplazamiento usando velocidades
    public double calcularDesplazamientoConVelocidades() {
        return ((velocidadFinal + velocidadInicial) / 2) * tiempo;
    }

    // Método 1: Calcular tiempo usando velocidad final
    public double calcularTiempoConVelocidadFinal() {
        if (GRAVEDAD == 0) {
            throw new ArithmeticException("La gravedad no puede ser cero");
        }
        return (velocidadFinal - velocidadInicial) / GRAVEDAD;
    }

    // Método 2: Calcular tiempo usando desplazamiento
    public double calcularTiempoConDesplazamiento() {
        // Resolviendo la ecuación cuadrática: h = v0*t + (1/2)*g*t^2
        double a = 0.5 * GRAVEDAD;
        double b = velocidadInicial;
        double c = -desplazamiento;

        double discriminante = b * b - 4 * a * c;

        if (discriminante < 0) {
            throw new ArithmeticException("No hay solución real para el tiempo");
        }

        double t1 = (-b + Math.sqrt(discriminante)) / (2 * a);
        double t2 = (-b - Math.sqrt(discriminante)) / (2 * a);

        // Retornamos el tiempo positivo
        return Math.max(t1, t2);
    }

    // Método 3: Calcular tiempo usando velocidad final y desplazamiento
    public double calcularTiempoConVelocidadYDesplazamiento() {
        if (velocidadFinal + velocidadInicial == 0) {
            throw new ArithmeticException("La suma de las velocidades no puede ser cero");
        }
        return (2 * desplazamiento) / (velocidadFinal + velocidadInicial);
    }

    // Getters y Setters
    public void setVelocidadInicial(double velocidadInicial) {
        this.velocidadInicial = velocidadInicial;
    }

    public void setVelocidadFinal(double velocidadFinal) {
        this.velocidadFinal = velocidadFinal;
    }

    public void setDesplazamiento(double desplazamiento) {
        this.desplazamiento = desplazamiento;
    }

    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }

    public double getVelocidadInicial() {
        return velocidadInicial;
    }

    public double getVelocidadFinal() {
        return velocidadFinal;
    }

    public double getDesplazamiento() {
        return desplazamiento;
    }

    public double getTiempo() {
        return tiempo;
    }
}