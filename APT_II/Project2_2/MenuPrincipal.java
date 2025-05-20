import java.util.Scanner;
// import javax.swing.JOptionPane;

public class MenuPrincipal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Variables fijas de ejemplo
        double A = 5;
        double B = 3;
        System.out.println("Valores fijos de ejemplo: A = " + A + ", B = " + B);
        System.out.println("A + B = " + (A + B));
        // JOptionPane.showMessageDialog(null,
        // "Valores fijos de ejemplo:\nA = " + A + "\nB = " + B + "\nA + B = " + (A +
        // B));

        boolean continuar = true;
        while (continuar) {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Área del rectángulo");
            System.out.println("2. Área del triángulo");
            System.out.println("3. Volumen del cono");
            System.out.println("4. Volumen del cilindro");
            System.out.println("5. Volumen de la esfera");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            String input = scanner.nextLine();
            int opcion;
            try {
                opcion = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la base: ");
                    double baseRect = Double.parseDouble(scanner.nextLine());
                    System.out.print("Ingrese la altura: ");
                    double alturaRect = Double.parseDouble(scanner.nextLine());
                    double areaRect = Areas.areaRectangulo(baseRect, alturaRect);
                    String msgRect = "Área del rectángulo: " + areaRect;
                    System.out.println(msgRect);
                    // JOptionPane.showMessageDialog(null, msgRect);
                    break;
                case 2:
                    System.out.print("Ingrese la base: ");
                    double baseTri = Double.parseDouble(scanner.nextLine());
                    System.out.print("Ingrese la altura: ");
                    double alturaTri = Double.parseDouble(scanner.nextLine());
                    double areaTri = Areas.areaTriangulo(baseTri, alturaTri);
                    String msgTri = "Área del triángulo: " + areaTri;
                    System.out.println(msgTri);
                    // JOptionPane.showMessageDialog(null, msgTri);
                    break;
                case 3:
                    System.out.print("Ingrese el radio: ");
                    double radioCono = Double.parseDouble(scanner.nextLine());
                    System.out.print("Ingrese la altura: ");
                    double alturaCono = Double.parseDouble(scanner.nextLine());
                    double volCono = Volumenes.volumenCono(radioCono, alturaCono);
                    String msgCono = "Volumen del cono: " + volCono;
                    System.out.println(msgCono);
                    // JOptionPane.showMessageDialog(null, msgCono);
                    break;
                case 4:
                    System.out.print("Ingrese el radio: ");
                    double radioCil = Double.parseDouble(scanner.nextLine());
                    System.out.print("Ingrese la altura: ");
                    double alturaCil = Double.parseDouble(scanner.nextLine());
                    double volCil = Volumenes.volumenCilindro(radioCil, alturaCil);
                    String msgCil = "Volumen del cilindro: " + volCil;
                    System.out.println(msgCil);
                    // JOptionPane.showMessageDialog(null, msgCil);
                    break;
                case 5:
                    System.out.print("Ingrese el radio: ");
                    double radioEsf = Double.parseDouble(scanner.nextLine());
                    double volEsf = Volumenes.volumenEsfera(radioEsf);
                    String msgEsf = "Volumen de la esfera: " + volEsf;
                    System.out.println(msgEsf);
                    // JOptionPane.showMessageDialog(null, msgEsf);
                    break;
                case 0:
                    continuar = false;
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }
}