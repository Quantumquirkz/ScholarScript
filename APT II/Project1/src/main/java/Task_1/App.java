package Task_1;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import com.formdev.flatlaf.FlatDarkLaf;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

public class App {
    private static final Color NEON_BLUE = new Color(66, 230, 245);
    private static final Color NEON_PINK = new Color(255, 41, 117);
    private static final Color NEON_GREEN = new Color(57, 255, 136);
    private static final Color DARK_BG = new Color(25, 25, 35);
    private static final Color DARKER_BG = new Color(18, 18, 25);
    private static final Color TEXT_COLOR = new Color(240, 240, 250);

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());

            UIManager.put("Panel.background", DARK_BG);
            UIManager.put("Label.foreground", TEXT_COLOR);
            UIManager.put("TextField.background", DARKER_BG);
            UIManager.put("TextField.foreground", TEXT_COLOR);
            UIManager.put("TextField.caretForeground", NEON_BLUE);
            UIManager.put("TextField.selectionBackground", NEON_BLUE.darker());
            UIManager.put("TitledBorder.titleColor", NEON_BLUE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JFrame frame = new JFrame("Resolución de Ecuaciones Cuadráticas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Iniciar maximizado
        frame.setMinimumSize(new Dimension(700, 500));

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(DARK_BG);
        frame.setContentPane(mainPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel titlePanel = createTitlePanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;
        mainPanel.add(titlePanel, gbc);

        JTextField aField = new JTextField();
        styleTextField(aField, NEON_BLUE);

        JTextField bField = new JTextField();
        styleTextField(bField, NEON_PINK);

        JTextField cField = new JTextField();
        styleTextField(cField, NEON_GREEN);

        JPanel inputPanel = createInputPanel(aField, bField, cField);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;
        mainPanel.add(inputPanel, gbc);

        JPanel buttonPanel = createButtonPanel();
        JButton resolverBtn = (JButton) buttonPanel.getComponent(0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;
        mainPanel.add(buttonPanel, gbc);

        // Panel de resultados
        JPanel resultadoPanel = createResultPanel();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(resultadoPanel, gbc);

        resolverBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultadoPanel.removeAll();
                resultadoPanel.setLayout(new GridBagLayout());

                try {
                    double a = Double.parseDouble(aField.getText());
                    double b = Double.parseDouble(bField.getText());
                    double c = Double.parseDouble(cField.getText());

                    if (a == 0) {
                        mostrarError(resultadoPanel, "El coeficiente A no puede ser cero (no sería una ecuación cuadrática)");
                        return;
                    }

                    String ecuacionOriginal = String.format("%.2fx^2 + %.2fx + %.2f = 0", a, b, c);
                    TeXFormula formulaOriginal = new TeXFormula(ecuacionOriginal);
                    TeXIcon iconOriginal = formulaOriginal.createTeXIcon(TeXFormula.SERIF, 20);
                    JLabel labelOriginal = new JLabel(iconOriginal);

                    JPanel ecuacionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    ecuacionPanel.setBackground(DARKER_BG);
                    ecuacionPanel.setBorder(BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(NEON_GREEN, 2),
                        "Ecuación", TitledBorder.CENTER, TitledBorder.TOP,
                        new Font("SansSerif", Font.BOLD, 14), NEON_GREEN));
                    ecuacionPanel.add(labelOriginal);

                    GridBagConstraints resultGbc = new GridBagConstraints();
                    resultGbc.gridx = 0;
                    resultGbc.gridy = 0;
                    resultGbc.weightx = 1.0;
                    resultGbc.fill = GridBagConstraints.HORIZONTAL;
                    resultGbc.insets = new Insets(10, 10, 5, 10);
                    resultadoPanel.add(ecuacionPanel, resultGbc);

                    double discriminante = b * b - 4 * a * c;
                    double dosA = 2 * a;
                    String latex;
                    Color colorResultado;

                    if (discriminante > 0) {
                        double sqrtD = Math.sqrt(discriminante);
                        double x1 = (-b + sqrtD) / dosA;
                        double x2 = (-b - sqrtD) / dosA;
                        latex = String.format(
                            "x = \\frac{%.2f \\pm \\sqrt{%.2f}}{2 \\cdot %.2f} \\Rightarrow x_1 = %.4f,\\ x_2 = %.4f",
                            -b, discriminante, a, x1, x2);
                        colorResultado = NEON_BLUE;
                    } else if (discriminante == 0) {
                        double x = -b / dosA;
                        latex = String.format(
                            "x = \\frac{%.2f \\pm \\sqrt{0}}{2 \\cdot %.2f} \\Rightarrow x_1 = x_2 = %.4f",
                            -b, a, x);
                        colorResultado = NEON_GREEN;
                    } else {
                        double real = -b / dosA;
                        double imag = Math.sqrt(-discriminante) / dosA;
                        latex = String.format(
                            "x = \\frac{%.2f \\pm \\sqrt{%.2f}}{2 \\cdot %.2f} = %.2f \\pm %.2fi \\Rightarrow x_1 = %.4f + %.4fi,\\ x_2 = %.4f - %.4fi",
                            -b, discriminante, a, real, imag, real, imag, real, imag);
                        colorResultado = NEON_PINK;
                    }

                    JPanel desarrolloPanel = new JPanel(new BorderLayout());
                    desarrolloPanel.setBackground(DARKER_BG);
                    desarrolloPanel.setBorder(BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(colorResultado, 2),
                        "Desarrollo", TitledBorder.CENTER, TitledBorder.TOP,
                        new Font("SansSerif", Font.BOLD, 14), colorResultado));

                    JPanel discriminantePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    discriminantePanel.setBackground(DARKER_BG);
                    JLabel discLabel = new JLabel("Discriminante: " + String.format("%.4f", discriminante));
                    discLabel.setForeground(colorResultado);
                    discLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
                    discriminantePanel.add(discLabel);
                    desarrolloPanel.add(discriminantePanel, BorderLayout.NORTH);

                    TeXFormula formula = new TeXFormula(latex);
                    TeXIcon icon = formula.createTeXIcon(TeXFormula.SERIF, 20);
                    JLabel label = new JLabel(icon);
                    JPanel latexPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    latexPanel.setBackground(DARKER_BG);
                    latexPanel.add(label);
                    desarrolloPanel.add(latexPanel, BorderLayout.CENTER);

                    resultGbc.gridy = 1;
                    resultGbc.weighty = 1.0;
                    resultGbc.fill = GridBagConstraints.BOTH;
                    resultadoPanel.add(desarrolloPanel, resultGbc);

                    // Agregar información adicional
                    JPanel infoPanel = new JPanel(new GridLayout(0, 1));
                    infoPanel.setBackground(DARKER_BG);
                    infoPanel.setBorder(BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(NEON_BLUE, 2),
                        "Información Adicional", TitledBorder.CENTER, TitledBorder.TOP,
                        new Font("SansSerif", Font.BOLD, 14), NEON_BLUE));

                    JLabel infoLabel = new JLabel("<html>Nombres: Geremi Tejeira (9-768-42), Terry He(8-1021-2180), Jhuomar Barría(9-766-196)<br>" +
                                                  "Facultad: Facultad de Ingeniería de Sistemas Computacionales<br>" +
                                                  "Universidad: Universidad Tecnológica de Panamá<br>" +
                                                  "Número de grupo: 1IL-128<br>" +
                                                  "Fecha: 17/04/2025</html>");
                    infoLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
                    infoLabel.setForeground(TEXT_COLOR);
                    infoPanel.add(infoLabel);

                    resultGbc.gridy = 2;
                    resultGbc.weighty = 0.0;
                    resultGbc.fill = GridBagConstraints.HORIZONTAL;
                    resultadoPanel.add(infoPanel, resultGbc);

                } catch (NumberFormatException ex) {
                    mostrarError(resultadoPanel, "Por favor ingrese valores numéricos válidos");
                }

                frame.revalidate();
                frame.repaint();
            }
        });

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel createTitlePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(DARKER_BG);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Resolución de Ecuaciones Cuadráticas", JLabel.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        titleLabel.setForeground(NEON_BLUE);
        panel.add(titleLabel, BorderLayout.CENTER);

        JLabel subtitleLabel = new JLabel("ax² + bx + c = 0", JLabel.CENTER);
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        subtitleLabel.setForeground(NEON_PINK);
        panel.add(subtitleLabel, BorderLayout.SOUTH);

        return panel;
    }

    private static void styleTextField(JTextField field, Color neonColor) {
        field.setFont(new Font("Monospaced", Font.PLAIN, 16));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(neonColor, 2),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        field.setBackground(DARKER_BG);
        field.setForeground(TEXT_COLOR);
        field.setCaretColor(neonColor);
    }

    private static JPanel createInputPanel(JTextField aField, JTextField bField, JTextField cField) {
        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 10, 15));
        mainPanel.setBackground(DARK_BG);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Coeficiente A
        JPanel aPanel = createCoeficientPanel("A", NEON_BLUE, aField);
        mainPanel.add(aPanel);

        // Coeficiente B
        JPanel bPanel = createCoeficientPanel("B", NEON_PINK, bField);
        mainPanel.add(bPanel);

        // Coeficiente C
        JPanel cPanel = createCoeficientPanel("C", NEON_GREEN, cField);
        mainPanel.add(cPanel);

        return mainPanel;
    }

    private static JPanel createCoeficientPanel(String coef, Color neonColor, JTextField field) {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBackground(DARK_BG);

        JLabel label = new JLabel("Coeficiente " + coef + ":");
        label.setFont(new Font("SansSerif", Font.BOLD, 16));
        label.setForeground(neonColor);

        JPanel fieldPanel = new JPanel(new BorderLayout());
        fieldPanel.setBackground(DARK_BG);
        fieldPanel.add(field, BorderLayout.CENTER);

        panel.add(label, BorderLayout.WEST);
        panel.add(fieldPanel, BorderLayout.CENTER);

        return panel;
    }

    private static JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panel.setBackground(DARK_BG);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        JButton resolverBtn = new JButton("Resolver Ecuación") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                GradientPaint gradient = new GradientPaint(
                    0, 0, NEON_BLUE,
                    getWidth(), getHeight(), NEON_PINK
                );
                g2.setPaint(gradient);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 15, 15));

                // Borde brillante
                g2.setStroke(new BasicStroke(2f));
                g2.setColor(new Color(255, 255, 255, 100));
                g2.draw(new RoundRectangle2D.Float(1, 1, getWidth()-2, getHeight()-2, 14, 14));

                // Efecto de sombra sutil
                g2.setColor(new Color(0, 0, 0, 80));
                g2.fill(new RoundRectangle2D.Float(3, 3, getWidth()-3, getHeight()-3, 14, 14));

                g2.dispose();

                super.paintComponent(g);
            }
        };

        resolverBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        resolverBtn.setForeground(Color.WHITE);
        resolverBtn.setContentAreaFilled(false);
        resolverBtn.setBorderPainted(false);
        resolverBtn.setFocusPainted(false);
        resolverBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        resolverBtn.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 25));

        panel.add(resolverBtn);

        return panel;
    }

    private static JPanel createResultPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(DARKER_BG);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(NEON_BLUE, 2),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JLabel placeholder = new JLabel("Los resultados se mostrarán aquí", JLabel.CENTER);
        placeholder.setFont(new Font("SansSerif", Font.ITALIC, 16));
        placeholder.setForeground(new Color(TEXT_COLOR.getRed(), TEXT_COLOR.getGreen(), TEXT_COLOR.getBlue(), 150));
        panel.add(placeholder, BorderLayout.CENTER);

        return panel;
    }

    private static void mostrarError(JPanel panel, String mensaje) {
        panel.setLayout(new BorderLayout());

        JPanel errorPanel = new JPanel();
        errorPanel.setBackground(DARKER_BG);
        errorPanel.setBorder(BorderFactory.createLineBorder(NEON_PINK, 2));
        errorPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel errorLabel = new JLabel(mensaje);
        errorLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        errorLabel.setForeground(NEON_PINK);
        errorPanel.add(errorLabel);

        panel.add(errorPanel, BorderLayout.CENTER);
    }
}
