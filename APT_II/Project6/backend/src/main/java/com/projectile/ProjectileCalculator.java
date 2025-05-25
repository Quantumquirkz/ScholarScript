package com.projectile;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectileCalculator {
    private double initialVelocity;
    private double launchAngle;
    private double totalFlightTime;
    private double timeToMaxHeight;
    private double maxHeight;
    private double maxDistance;
    private List<TrajectoryPoint> trajectory;
    private static final double GRAVITY = 9.81;

    public ProjectileCalculator() {
        this.trajectory = new ArrayList<>();
    }

    public void setInitialValues(double initialVelocity, double launchAngle) {
        this.initialVelocity = initialVelocity;
        this.launchAngle = Math.toRadians(launchAngle); // Convertir grados a radianes
    }

    public void calculateTotalFlightTime() {
        this.totalFlightTime = (2 * initialVelocity * Math.sin(launchAngle)) / GRAVITY;
    }

    public void calculateTimeToMaxHeight() {
        this.timeToMaxHeight = (initialVelocity * Math.sin(launchAngle)) / GRAVITY;
    }

    public void calculateMaxHeight() {
        this.maxHeight = (initialVelocity * initialVelocity * Math.sin(launchAngle) * Math.sin(launchAngle)) / (2 * GRAVITY);
    }

    public void calculateMaxDistance() {
        this.maxDistance = (initialVelocity * initialVelocity * Math.sin(2 * launchAngle)) / GRAVITY;
    }

    public void calculateTrajectory() {
        trajectory.clear();
        for (double t = 0; t <= Math.ceil(totalFlightTime); t += 0.1) {
            double height = Math.max(0, initialVelocity * Math.sin(launchAngle) * t - 0.5 * GRAVITY * t * t);
            double horizontalVelocity = initialVelocity * Math.cos(launchAngle);
            double verticalVelocity = initialVelocity * Math.sin(launchAngle) - GRAVITY * t;
            double horizontalPosition = initialVelocity * Math.cos(launchAngle) * t;

            trajectory.add(new TrajectoryPoint(
                Math.round(t * 10) / 10.0,
                Math.round(height * 100) / 100.0,
                Math.round(horizontalPosition * 100) / 100.0,
                Math.round(horizontalVelocity * 100) / 100.0,
                Math.round(verticalVelocity * 100) / 100.0
            ));

            if (height <= 0 && t > 0) break;
        }
    }

    public void saveResultsToTxt(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        content.append("CALCULADORA DE MOVIMIENTO DE PROYECTIL\n");
        content.append("=====================================\n\n");
        content.append("PARÁMETROS DE ENTRADA:\n");
        content.append(String.format("- Velocidad inicial: %.2f m/s\n", initialVelocity));
        content.append(String.format("- Ángulo de lanzamiento: %.2f°\n\n", Math.toDegrees(launchAngle)));
        content.append("RESULTADOS PRINCIPALES:\n");
        content.append(String.format("- Tiempo total de vuelo: %.2f s\n", totalFlightTime));
        content.append(String.format("- Tiempo de subida: %.2f s\n", timeToMaxHeight));
        content.append(String.format("- Altura máxima: %.2f m\n", maxHeight));
        content.append(String.format("- Distancia máxima: %.2f m\n\n", maxDistance));
        content.append("EVOLUCIÓN DEL PROYECTIL:\n");
        content.append("========================\n");
        content.append("Tiempo(s) | Altura(m) | Pos.Horiz(m) | Vel.Horiz(m/s) | Vel.Vert(m/s)\n");
        content.append("----------------------------------------------------------------------\n");

        for (TrajectoryPoint point : trajectory) {
            content.append(String.format("%-9.1f | %-9.2f | %-12.2f | %-14.2f | %.2f\n",
                point.getTime(),
                point.getHeight(),
                point.getHorizontalPosition(),
                point.getHorizontalVelocity(),
                point.getVerticalVelocity()
            ));
        }

        content.append("\nGenerado el: ").append(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));

        Files.write(Paths.get(filePath), content.toString().getBytes());
    }

    public void saveResultsToPdf(String filePath) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, Files.newOutputStream(Paths.get(filePath)));
        document.open();

        // Título
        document.add(new Paragraph("CALCULADORA DE MOVIMIENTO DE PROYECTIL"));
        document.add(new Paragraph("\n"));

        // Parámetros de entrada
        document.add(new Paragraph("PARÁMETROS DE ENTRADA:"));
        document.add(new Paragraph(String.format("Velocidad inicial: %.2f m/s", initialVelocity)));
        document.add(new Paragraph(String.format("Ángulo de lanzamiento: %.2f°", Math.toDegrees(launchAngle))));
        document.add(new Paragraph("\n"));

        // Resultados principales
        document.add(new Paragraph("RESULTADOS PRINCIPALES:"));
        document.add(new Paragraph(String.format("Tiempo total de vuelo: %.2f s", totalFlightTime)));
        document.add(new Paragraph(String.format("Tiempo de subida: %.2f s", timeToMaxHeight)));
        document.add(new Paragraph(String.format("Altura máxima: %.2f m", maxHeight)));
        document.add(new Paragraph(String.format("Distancia máxima: %.2f m", maxDistance)));
        document.add(new Paragraph("\n"));

        // Tabla de evolución
        document.add(new Paragraph("EVOLUCIÓN DEL PROYECTIL:"));
        document.add(new Paragraph("Tiempo(s) | Altura(m) | Pos.Horiz(m) | Vel.Horiz(m/s) | Vel.Vert(m/s)"));
        document.add(new Paragraph("----------------------------------------------------------------------"));

        for (TrajectoryPoint point : trajectory) {
            document.add(new Paragraph(String.format("%-9.1f | %-9.2f | %-12.2f | %-14.2f | %.2f",
                point.getTime(),
                point.getHeight(),
                point.getHorizontalPosition(),
                point.getHorizontalVelocity(),
                point.getVerticalVelocity()
            )));
        }

        document.add(new Paragraph("\nGenerado el: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())));
        document.close();
    }

    // Getters
    public double getTotalFlightTime() {
        return totalFlightTime;
    }

    public double getTimeToMaxHeight() {
        return timeToMaxHeight;
    }

    public double getMaxHeight() {
        return maxHeight;
    }

    public double getMaxDistance() {
        return maxDistance;
    }

    public List<TrajectoryPoint> getTrajectory() {
        return trajectory;
    }
} 