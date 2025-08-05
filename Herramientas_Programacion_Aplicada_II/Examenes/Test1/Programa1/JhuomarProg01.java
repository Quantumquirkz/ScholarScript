package Programa1;

import java.awt.*;
import javax.swing.*;

public class JhuomarProg01 {
    public static void main(String[] args) {
        // Crear StringBuilder para almacenar los resultados
        StringBuilder resultados = new StringBuilder();
        resultados.append("<html><body style='font-family: Courier New; background: #fbeee6; color: #6b4226;'>");
        resultados.append("<h2 style='color:#a47149;'>Resultados de Conversión de Temperatura</h2>");
        resultados.append("<table border='1' cellpadding='6' style='border-collapse:collapse; background:#fff7f0;'>");
        resultados.append("<tr style='background:#e6ccb2;'><th>Fahrenheit</th><th>Centígrados</th><th>Kelvin</th></tr>");

        // Calcular temperaturas desde 30°F hasta 100°F con incrementos de 10
        for (double fahrenheit = 30; fahrenheit <= 100; fahrenheit += 10) {
            double celsius = (fahrenheit - 32) / 1.8;
            double kelvin = celsius + 273.15;
            resultados.append(String.format("<tr><td>%.2f °F</td><td>%.2f °C</td><td>%.2f K</td></tr>", fahrenheit, celsius, kelvin));
        }
        resultados.append("</table>");
        resultados.append("<p style='font-style:italic; color:#a47149;'>Hecho con estilo bohemio ☕</p>");
        resultados.append("<hr style='border:1px solid #a47149;'>");
        resultados.append("<p style='text-align:center; font-size:12px; color:#6b4226;'>Universidad Tecnológica de Panamá<br>Grupo: 1IL-128<br>Jhuomar Boskoll Barría Quintero | 9-766-196</p>");
        resultados.append("</body></html>");

        // Crear un JLabel con HTML para mostrar en JOptionPane
        JLabel label = new JLabel(resultados.toString());
        label.setFont(new Font("Courier New", Font.PLAIN, 14));
        JOptionPane.showMessageDialog(null, label, "Conversión de Temperatura Bohemia", JOptionPane.INFORMATION_MESSAGE);
    }
} 