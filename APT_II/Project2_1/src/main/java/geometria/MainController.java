package geometria;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.scene.control.Button;
import javafx.application.Platform;
import java.text.DecimalFormat;

public class MainController {
    @FXML
    private VBox rootVBox;
    @FXML
    private Button themeToggleButton;

    // Campos para el rectángulo
    @FXML
    private TextField rectanguloBase;
    @FXML
    private TextField rectanguloAltura;
    @FXML
    private Label resultadoRectangulo;
    @FXML
    private WebView procedimientoRectangulo;

    // Campos para el triángulo
    @FXML
    private TextField trianguloBase;
    @FXML
    private TextField trianguloAltura;
    @FXML
    private Label resultadoTriangulo;
    @FXML
    private WebView procedimientoTriangulo;

    // Campos para volúmenes
    @FXML
    private TextField conoRadio;
    @FXML
    private TextField conoAltura;
    @FXML
    private Label resultadoCono;
    @FXML
    private WebView procedimientoCono;

    @FXML
    private TextField cilindroRadio;
    @FXML
    private TextField cilindroAltura;
    @FXML
    private Label resultadoCilindro;
    @FXML
    private WebView procedimientoCilindro;

    @FXML
    private TextField esferaRadio;
    @FXML
    private Label resultadoEsfera;
    @FXML
    private WebView procedimientoEsfera;

    private final DecimalFormat df = new DecimalFormat("#,##0.00");
    private static final double PI = Math.PI;
    private boolean isDarkMode = true; // Siempre en modo oscuro

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            if (rootVBox != null) {
                rootVBox.getStyleClass().add("dark-mode");
            }

            // Inicializar WebViews
            if (procedimientoRectangulo != null) {
                procedimientoRectangulo.getEngine().setJavaScriptEnabled(true);
                mostrarProcedimiento(procedimientoRectangulo, "\\text{Ingrese valores para calcular}");
            }
            if (procedimientoTriangulo != null) {
                procedimientoTriangulo.getEngine().setJavaScriptEnabled(true);
                mostrarProcedimiento(procedimientoTriangulo, "\\text{Ingrese valores para calcular}");
            }
            if (procedimientoCono != null) {
                procedimientoCono.getEngine().setJavaScriptEnabled(true);
                mostrarProcedimiento(procedimientoCono, "\\text{Ingrese valores para calcular}");
            }
            if (procedimientoCilindro != null) {
                procedimientoCilindro.getEngine().setJavaScriptEnabled(true);
                mostrarProcedimiento(procedimientoCilindro, "\\text{Ingrese valores para calcular}");
            }
            if (procedimientoEsfera != null) {
                procedimientoEsfera.getEngine().setJavaScriptEnabled(true);
                mostrarProcedimiento(procedimientoEsfera, "\\text{Ingrese valores para calcular}");
            }
        });
    }

    private double validarNumero(String texto, String campo) throws NumberFormatException {
        if (texto == null || texto.trim().isEmpty()) {
            throw new NumberFormatException("El campo " + campo + " no puede estar vacío");
        }
        try {
            // Reemplazar comas por puntos para manejar ambos formatos
            String numeroNormalizado = texto.trim().replace(',', '.');
            double valor = Double.parseDouble(numeroNormalizado);
            if (valor <= 0) {
                throw new IllegalArgumentException("El valor de " + campo + " debe ser mayor que cero");
            }
            return valor;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("El valor de " + campo + " debe ser un número válido");
        }
    }

    private void mostrarError(Label label, String mensaje) {
        label.setText("Error: " + mensaje);
        label.setStyle("-fx-text-fill: #FF3333; -fx-effect: dropshadow(gaussian, rgba(255,51,51,0.3), 5, 0, 0, 0);");
    }

    private void mostrarResultado(Label label, String figura, double resultado) {
        String unidades = figura.toLowerCase().contains("volumen") ? "³" : "²";
        label.setText(String.format("%s: %s unidades%s", figura, df.format(resultado), unidades));
        String color = isDarkMode ? "#00FF2A" : "#2196F3";
        label.setStyle(String.format("-fx-text-fill: %s; -fx-effect: dropshadow(gaussian, %s, 5, 0, 0, 0);",
                color, isDarkMode ? "rgba(0,255,42,0.3)" : "rgba(33,150,243,0.3)"));
    }

    private void mostrarProcedimiento(WebView webView, String latex) {
        String htmlTemplate = """
                    <!DOCTYPE html>
                    <html>
                    <head>
                        <meta charset="UTF-8">
                        <script type="text/javascript" id="MathJax-script" async
                            src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js">
                        </script>
                        <style>
                            body {
                                background-color: %s;
                                color: %s;
                                font-family: 'Segoe UI', Arial, sans-serif;
                                margin: 0;
                                padding: 10px;
                                overflow-x: hidden;
                            }
                            .math-container {
                                padding: 15px;
                                border-radius: 8px;
                                background-color: %s;
                                box-shadow: 0 4px 6px %s;
                                overflow-x: auto;
                                max-width: 100%%;
                            }
                            .mjx-chtml {
                                max-width: 100%%;
                                overflow-x: auto;
                                overflow-y: hidden;
                            }
                        </style>
                        <script>
                            window.MathJax = {
                                tex: {
                                    inlineMath: [['$', '$'], ['\\\\(', '\\\\)']],
                                    displayMath: [['$$', '$$'], ['\\\\[', '\\\\]']],
                                    processEscapes: true
                                },
                                svg: {
                                    fontCache: 'global'
                                },
                                options: {
                                    enableMenu: false
                                },
                                chtml: {
                                    scale: 1.0,
                                    matchFontHeight: true
                                }
                            };
                        </script>
                    </head>
                    <body>
                        <div class="math-container">
                            $$\\begin{array}{l}
                            %s
                            \\end{array}$$
                        </div>
                    </body>
                    </html>
                """.formatted(
                isDarkMode ? "#161B22" : "#FFFFFF",
                isDarkMode ? "#00FF2A" : "#2196F3",
                isDarkMode ? "#0D1117" : "#F5F5F5",
                isDarkMode ? "rgba(0,255,42,0.1)" : "rgba(33,150,243,0.1)",
                latex.replace("\\\\", "\\"));

        webView.getEngine().setJavaScriptEnabled(true);
        webView.getEngine().loadContent(htmlTemplate);
    }

    private String formatearLatex(String procedimiento) {
        return procedimiento
                .replace("\\\\", "\\")
                .replace("\\text{Procedimiento:}", "\\text{Procedimiento: }")
                .replace("\\begin{align*}", "\\begin{aligned}")
                .replace("\\end{align*}", "\\end{aligned}");
    }

    @FXML
    private void calcularAreaRectangulo() {
        try {
            double base = validarNumero(rectanguloBase.getText(), "base");
            double altura = validarNumero(rectanguloAltura.getText(), "altura");
            double area = base * altura;
            mostrarResultado(resultadoRectangulo, "Área del rectángulo", area);

            String procedimiento = """
                    \\text{Procedimiento:} \\\\
                    A = \\text{base} \\times \\text{altura} \\\\
                    A = %s \\times %s \\\\
                    A = %s \\text{ unidades}^2
                    """.formatted(df.format(base), df.format(altura), df.format(area));

            mostrarProcedimiento(procedimientoRectangulo, procedimiento);
        } catch (Exception e) {
            mostrarError(resultadoRectangulo, e.getMessage());
            mostrarProcedimiento(procedimientoRectangulo, "\\text{Ingrese valores válidos para calcular}");
        }
    }

    @FXML
    private void calcularAreaTriangulo() {
        try {
            double base = validarNumero(trianguloBase.getText(), "base");
            double altura = validarNumero(trianguloAltura.getText(), "altura");
            double area = (base * altura) / 2;
            mostrarResultado(resultadoTriangulo, "Área del triángulo", area);

            String procedimiento = """
                    \\text{Procedimiento:} \\\\
                    A = \\frac{\\text{base} \\times \\text{altura}}{2} \\\\
                    A = \\frac{%s \\times %s}{2} \\\\
                    A = \\frac{%s}{2} \\\\
                    A = %s \\text{ unidades}^2
                    """.formatted(df.format(base), df.format(altura),
                    df.format(base * altura), df.format(area));

            mostrarProcedimiento(procedimientoTriangulo, procedimiento);
        } catch (Exception e) {
            mostrarError(resultadoTriangulo, e.getMessage());
            mostrarProcedimiento(procedimientoTriangulo, "\\text{Ingrese valores válidos para calcular}");
        }
    }

    @FXML
    private void calcularVolumenCono() {
        try {
            double radio = validarNumero(conoRadio.getText(), "radio");
            double altura = validarNumero(conoAltura.getText(), "altura");
            double volumen = (PI * radio * radio * altura) / 3;
            mostrarResultado(resultadoCono, "Volumen del cono", volumen);

            String procedimiento = formatearLatex("""
                    \\text{Procedimiento: }
                    \\begin{aligned}
                    V &= \\frac{\\pi r^2 h}{3} \\\\
                    V &= \\frac{\\pi \\times %s^2 \\times %s}{3} \\\\
                    V &= \\frac{%s}{3} \\\\
                    V &= %s \\text{ unidades}^3
                    \\end{aligned}
                    """.formatted(
                    df.format(radio),
                    df.format(altura),
                    df.format(PI * radio * radio * altura),
                    df.format(volumen)));
            mostrarProcedimiento(procedimientoCono, procedimiento);
        } catch (Exception e) {
            mostrarError(resultadoCono, e.getMessage());
        }
    }

    @FXML
    private void calcularVolumenCilindro() {
        try {
            double radio = validarNumero(cilindroRadio.getText(), "radio");
            double altura = validarNumero(cilindroAltura.getText(), "altura");
            double volumen = PI * radio * radio * altura;
            mostrarResultado(resultadoCilindro, "Volumen del cilindro", volumen);

            String procedimiento = formatearLatex("""
                    \\text{Procedimiento: }
                    \\begin{aligned}
                    V &= \\pi r^2 h \\\\
                    V &= \\pi \\times %s^2 \\times %s \\\\
                    V &= %s \\text{ unidades}^3
                    \\end{aligned}
                    """.formatted(
                    df.format(radio),
                    df.format(altura),
                    df.format(volumen)));
            mostrarProcedimiento(procedimientoCilindro, procedimiento);
        } catch (Exception e) {
            mostrarError(resultadoCilindro, e.getMessage());
        }
    }

    @FXML
    private void calcularVolumenEsfera() {
        try {
            double radio = validarNumero(esferaRadio.getText(), "radio");
            double volumen = (4.0 / 3.0) * PI * Math.pow(radio, 3);
            mostrarResultado(resultadoEsfera, "Volumen de la esfera", volumen);

            String procedimiento = formatearLatex("""
                    \\text{Procedimiento: }
                    \\begin{aligned}
                    V &= \\frac{4}{3}\\pi r^3 \\\\
                    V &= \\frac{4}{3}\\pi \\times %s^3 \\\\
                    V &= %s \\text{ unidades}^3
                    \\end{aligned}
                    """.formatted(
                    df.format(radio),
                    df.format(volumen)));
            mostrarProcedimiento(procedimientoEsfera, procedimiento);
        } catch (Exception e) {
            mostrarError(resultadoEsfera, e.getMessage());
        }
    }
}