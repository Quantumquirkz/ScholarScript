package com.projectile;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class ProjectileCalculator {
    private double initialVelocity;
    private double launchAngle;
    private double totalFlightTime;
    private double timeToMaxHeight;
    private double maxHeight;
    private double maxDistance;
    private List<TrajectoryPoint> trajectory;
    private static final DecimalFormat df = new DecimalFormat("#.##");
    private static final double GRAVITY = 9.81;

    public ProjectileCalculator() {
        this.trajectory = new ArrayList<>();
    }

    public void setInitialValues(double initialVelocity, double launchAngle) {
        this.initialVelocity = initialVelocity;
        this.launchAngle = Math.toRadians(launchAngle);
    }

    public void calculateTotalFlightTime() {
        double verticalVelocity = initialVelocity * Math.sin(launchAngle);
        this.totalFlightTime = (2 * verticalVelocity) / GRAVITY;
    }

    public void calculateTimeToMaxHeight() {
        double verticalVelocity = initialVelocity * Math.sin(launchAngle);
        this.timeToMaxHeight = verticalVelocity / GRAVITY;
    }

    public void calculateMaxHeight() {
        double verticalVelocity = initialVelocity * Math.sin(launchAngle);
        this.maxHeight = (verticalVelocity * verticalVelocity) / (2 * GRAVITY);
    }

    public void calculateMaxDistance() {
        double horizontalVelocity = initialVelocity * Math.cos(launchAngle);
        this.maxDistance = horizontalVelocity * totalFlightTime;
    }

    public void calculateTrajectory() {
        trajectory.clear();
        double timeStep = totalFlightTime / 20; // 20 puntos en la trayectoria
        double horizontalVelocity = initialVelocity * Math.cos(launchAngle);
        double verticalVelocity = initialVelocity * Math.sin(launchAngle);

        for (double t = 0; t <= totalFlightTime; t += timeStep) {
            double height = (verticalVelocity * t) - (0.5 * GRAVITY * t * t);
            double horizontalPosition = horizontalVelocity * t;
            double currentVerticalVelocity = verticalVelocity - (GRAVITY * t);

            trajectory.add(new TrajectoryPoint(
                Double.parseDouble(df.format(t)),
                Double.parseDouble(df.format(height)),
                Double.parseDouble(df.format(horizontalPosition)),
                Double.parseDouble(df.format(horizontalVelocity)),
                Double.parseDouble(df.format(currentVerticalVelocity))
            ));
        }
    }

    public void saveResultsToTxt(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Resultados del Movimiento de Proyectil\n");
            writer.write("=====================================\n\n");
            writer.write("Velocidad inicial: " + df.format(initialVelocity) + " m/s\n");
            writer.write("Ángulo de lanzamiento: " + df.format(Math.toDegrees(launchAngle)) + "°\n\n");
            writer.write("Tiempo total de vuelo: " + df.format(totalFlightTime) + " s\n");
            writer.write("Tiempo hasta altura máxima: " + df.format(timeToMaxHeight) + " s\n");
            writer.write("Altura máxima: " + df.format(maxHeight) + " m\n");
            writer.write("Distancia máxima: " + df.format(maxDistance) + " m\n\n");
            writer.write("Trayectoria:\n");
            writer.write("Tiempo (s)\tAltura (m)\tPosición Horizontal (m)\tVelocidad Horizontal (m/s)\tVelocidad Vertical (m/s)\n");
            
            for (TrajectoryPoint point : trajectory) {
                writer.write(String.format("%s\t%s\t%s\t%s\t%s\n",
                    df.format(point.getTime()),
                    df.format(point.getHeight()),
                    df.format(point.getHorizontalPosition()),
                    df.format(point.getHorizontalVelocity()),
                    df.format(point.getVerticalVelocity())
                ));
            }
        }
    }

    public void saveResultsToPdf(String filePath) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        // Título
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Paragraph title = new Paragraph("Resultados del Movimiento de Proyectil", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        // Datos principales
        Font normalFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
        document.add(new Paragraph("Velocidad inicial: " + df.format(initialVelocity) + " m/s", normalFont));
        document.add(new Paragraph("Ángulo de lanzamiento: " + df.format(Math.toDegrees(launchAngle)) + "°", normalFont));
        document.add(new Paragraph("Tiempo total de vuelo: " + df.format(totalFlightTime) + " s", normalFont));
        document.add(new Paragraph("Tiempo hasta altura máxima: " + df.format(timeToMaxHeight) + " s", normalFont));
        document.add(new Paragraph("Altura máxima: " + df.format(maxHeight) + " m", normalFont));
        document.add(new Paragraph("Distancia máxima: " + df.format(maxDistance) + " m", normalFont));
        document.add(new Paragraph("\nTrayectoria:", normalFont));

        // Tabla de trayectoria
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        
        // Encabezados
        String[] headers = {"Tiempo (s)", "Altura (m)", "Posición Horizontal (m)", 
                          "Velocidad Horizontal (m/s)", "Velocidad Vertical (m/s)"};
        for (String header : headers) {
            table.addCell(new PdfPCell(new Phrase(header, normalFont)));
        }

        // Datos
        for (TrajectoryPoint point : trajectory) {
            table.addCell(df.format(point.getTime()));
            table.addCell(df.format(point.getHeight()));
            table.addCell(df.format(point.getHorizontalPosition()));
            table.addCell(df.format(point.getHorizontalVelocity()));
            table.addCell(df.format(point.getVerticalVelocity()));
        }

        document.add(table);
        document.close();
    }

    // Getters
    public double getTotalFlightTime() { return totalFlightTime; }
    public double getTimeToMaxHeight() { return timeToMaxHeight; }
    public double getMaxHeight() { return maxHeight; }
    public double getMaxDistance() { return maxDistance; }
    public List<TrajectoryPoint> getTrajectory() { return trajectory; }
} 