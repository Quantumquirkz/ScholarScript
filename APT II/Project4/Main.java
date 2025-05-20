import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CaidaLibre caidaLibre = new CaidaLibre();

        System.out.println("Calculadora de Tiempo de Caída Libre");
        System.out.println("-----------------------------------");
        System.out.println("Seleccione la fórmula a utilizar:");
        System.out.println("1. Tiempo usando velocidad final (v = v0 + gt)");
        System.out.println("2. Tiempo usando desplazamiento (h = v0*t + (1/2)*g*t^2)");
        System.out.println("3. Tiempo usando velocidad final y desplazamiento (h = (v + v0)/2 * t)");
        System.out.print("\nIngrese su opción (1-3): ");

        int opcion = scanner.nextInt();

        try {
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la velocidad inicial (m/s): ");
                    double v0 = scanner.nextDouble();
                    caidaLibre.setVelocidadInicial(v0);

                    System.out.print("Ingrese la velocidad final (m/s): ");
                    double vf = scanner.nextDouble();
                    caidaLibre.setVelocidadFinal(vf);

                    double tiempo1 = caidaLibre.calcularTiempoConVelocidadFinal();
                    System.out.printf("\nEl tiempo de caída es: %.2f segundos%n", tiempo1);
                    break;

                case 2:
                    System.out.print("Ingrese la velocidad inicial (m/s): ");
                    v0 = scanner.nextDouble();
                    caidaLibre.setVelocidadInicial(v0);

                    System.out.print("Ingrese el desplazamiento (m): ");
                    double h = scanner.nextDouble();
                    caidaLibre.setDesplazamiento(h);

                    double tiempo2 = caidaLibre.calcularTiempoConDesplazamiento();
                    System.out.printf("\nEl tiempo de caída es: %.2f segundos%n", tiempo2);
                    break;

                case 3:
                    System.out.print("Ingrese la velocidad inicial (m/s): ");
                    v0 = scanner.nextDouble();
                    caidaLibre.setVelocidadInicial(v0);

                    System.out.print("Ingrese la velocidad final (m/s): ");
                    vf = scanner.nextDouble();
                    caidaLibre.setVelocidadFinal(vf);

                    System.out.print("Ingrese el desplazamiento (m): ");
                    h = scanner.nextDouble();
                    caidaLibre.setDesplazamiento(h);

                    double tiempo3 = caidaLibre.calcularTiempoConVelocidadYDesplazamiento();
                    System.out.printf("\nEl tiempo de caída es: %.2f segundos%n", tiempo3);
                    break;

                default:
                    System.out.println("Opción no válida");
            }
        } catch (ArithmeticException e) {
            System.out.println("Error en el cálculo: " + e.getMessage());
        }

        scanner.close();
    }
}