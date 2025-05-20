import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculadoraGUI extends JFrame {
    private JTextField campoA, campoB;
    private JLabel resultadoLabel;

    public CalculadoraGUI() {
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480, 520);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(18, 22, 34)); // Fondo general oscuro

        // Panel central con fondo y bordes redondeados
        JPanel panelCentral = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(32, 36, 48));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 32, 32);
            }
        };
        panelCentral.setOpaque(false);
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        // Encabezado morado degradado
        JPanel header = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, new Color(168, 85, 247), 0, getHeight(),
                        new Color(232, 121, 249));
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 32, 32);
            }
        };
        header.setPreferredSize(new Dimension(400, 80));
        header.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        header.setOpaque(false);
        header.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Calculadora", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setForeground(Color.WHITE);
        titulo.setBorder(BorderFactory.createEmptyBorder(18, 0, 0, 0));
        header.add(titulo, BorderLayout.CENTER);

        panelCentral.add(header);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        // Primer número
        JLabel labelA = new JLabel("Primer número");
        labelA.setFont(new Font("Arial", Font.BOLD, 16));
        labelA.setForeground(new Color(232, 232, 255));
        labelA.setAlignmentX(Component.LEFT_ALIGNMENT);
        campoA = new JTextField();
        campoA.setFont(new Font("Arial", Font.PLAIN, 16));
        campoA.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        campoA.setBackground(new Color(36, 41, 54));
        campoA.setForeground(new Color(200, 200, 210));
        campoA.setCaretColor(new Color(200, 200, 210));
        campoA.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 60, 80), 1, true),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        campoA.setToolTipText("Ingrese el primer número");

        // Segundo número
        JLabel labelB = new JLabel("Segundo número");
        labelB.setFont(new Font("Arial", Font.BOLD, 16));
        labelB.setForeground(new Color(232, 232, 255));
        labelB.setAlignmentX(Component.LEFT_ALIGNMENT);
        campoB = new JTextField();
        campoB.setFont(new Font("Arial", Font.PLAIN, 16));
        campoB.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        campoB.setBackground(new Color(36, 41, 54));
        campoB.setForeground(new Color(200, 200, 210));
        campoB.setCaretColor(new Color(200, 200, 210));
        campoB.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 60, 80), 1, true),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        campoB.setToolTipText("Ingrese el segundo número");

        panelCentral.add(Box.createRigidArea(new Dimension(0, 10)));
        panelCentral.add(labelA);
        panelCentral.add(campoA);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 18)));
        panelCentral.add(labelB);
        panelCentral.add(campoB);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 28)));

        // Panel de botones
        JPanel panelBotones = new JPanel(new GridLayout(2, 2, 18, 18));
        panelBotones.setOpaque(false);

        JButton btnSumar = new JButton("Sumar");
        btnSumar.setBackground(new Color(139, 92, 246));
        btnSumar.setForeground(Color.WHITE);
        btnSumar.setFont(new Font("Arial", Font.BOLD, 18));
        btnSumar.setFocusPainted(false);
        btnSumar.setBorder(BorderFactory.createLineBorder(new Color(139, 92, 246), 1, true));

        JButton btnRestar = new JButton("Restar");
        btnRestar.setBackground(new Color(168, 85, 247));
        btnRestar.setForeground(Color.WHITE);
        btnRestar.setFont(new Font("Arial", Font.BOLD, 18));
        btnRestar.setFocusPainted(false);
        btnRestar.setBorder(BorderFactory.createLineBorder(new Color(168, 85, 247), 1, true));

        JButton btnMultiplicar = new JButton("Multiplicar");
        btnMultiplicar.setBackground(new Color(219, 39, 119));
        btnMultiplicar.setForeground(Color.WHITE);
        btnMultiplicar.setFont(new Font("Arial", Font.BOLD, 18));
        btnMultiplicar.setFocusPainted(false);
        btnMultiplicar.setBorder(BorderFactory.createLineBorder(new Color(219, 39, 119), 1, true));

        JButton btnDividir = new JButton("Dividir");
        btnDividir.setBackground(new Color(139, 92, 246));
        btnDividir.setForeground(Color.WHITE);
        btnDividir.setFont(new Font("Arial", Font.BOLD, 18));
        btnDividir.setFocusPainted(false);
        btnDividir.setBorder(BorderFactory.createLineBorder(new Color(139, 92, 246), 1, true));

        btnSumar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRestar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnMultiplicar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDividir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        panelBotones.add(btnSumar);
        panelBotones.add(btnRestar);
        panelBotones.add(btnMultiplicar);
        panelBotones.add(btnDividir);

        panelCentral.add(panelBotones);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        // Resultado
        resultadoLabel = new JLabel(" ", SwingConstants.CENTER);
        resultadoLabel.setFont(new Font("Arial", Font.BOLD, 18));
        resultadoLabel.setForeground(new Color(232, 121, 249));
        resultadoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(resultadoLabel);

        // Panel de información
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);
        infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Estilo común para las etiquetas de información
        Font infoFont = new Font("Arial", Font.PLAIN, 12);
        Color infoColor = new Color(200, 200, 210);

        // Crear etiquetas con la información
        JLabel universidadLabel = new JLabel("Universidad Tecnológica de Panamá");
        JLabel facilitadorLabel = new JLabel("Facilitador(a): Emilio Batista");
        JLabel asignaturaLabel = new JLabel("Asignatura: Herramientas de la Programación Aplicada II");
        JLabel estudiantesLabel = new JLabel("Estudiantes:");
        JLabel estudiante1Label = new JLabel("• Terry He | 8-1021-2180");
        JLabel estudiante2Label = new JLabel("• Jhuomar Barría | 9-766-196");
        JLabel estudiante3Label = new JLabel("• Geremi Tejeira | 9-768-42");
        JLabel grupoLabel = new JLabel("Grupo: 1IL128");
        JLabel fechaLabel = new JLabel("Fecha: 10/5/2025");

        // Aplicar estilo a todas las etiquetas
        for (JLabel label : new JLabel[] { universidadLabel, facilitadorLabel, asignaturaLabel,
                estudiantesLabel, estudiante1Label, estudiante2Label,
                estudiante3Label, grupoLabel, fechaLabel }) {
            label.setFont(infoFont);
            label.setForeground(infoColor);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        // Agregar etiquetas al panel
        infoPanel.add(universidadLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        infoPanel.add(facilitadorLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        infoPanel.add(asignaturaLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        infoPanel.add(estudiantesLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 2)));
        infoPanel.add(estudiante1Label);
        infoPanel.add(estudiante2Label);
        infoPanel.add(estudiante3Label);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        infoPanel.add(grupoLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 2)));
        infoPanel.add(fechaLabel);

        // Agregar el panel de información al panel central
        panelCentral.add(infoPanel);
        panelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        // Acciones de los botones
        btnSumar.addActionListener(e -> calcular("sumar"));
        btnRestar.addActionListener(e -> calcular("restar"));
        btnMultiplicar.addActionListener(e -> calcular("multiplicar"));
        btnDividir.addActionListener(e -> calcular("dividir"));

        // Layout principal
        setLayout(new GridBagLayout());
        add(panelCentral);
    }

    private void calcular(String operacion) {
        try {
            int a = Integer.parseInt(campoA.getText());
            int b = Integer.parseInt(campoB.getText());
            String resultado = "";
            switch (operacion) {
                case "sumar":
                    resultado = "Resultado: " + OperacionMatematica.sumar(a, b);
                    break;
                case "restar":
                    resultado = "Resultado: " + OperacionMatematica.restar(a, b);
                    break;
                case "multiplicar":
                    resultado = "Resultado: " + OperacionMatematica.multiplicar(a, b);
                    break;
                case "dividir":
                    resultado = "Resultado: " + OperacionMatematica.dividir(a, b);
                    break;
            }
            resultadoLabel.setText(resultado);
        } catch (NumberFormatException ex) {
            resultadoLabel.setText("Por favor, ingrese números válidos.");
        }
    }
}