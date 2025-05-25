package com.projectile;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import spark.Spark;
import spark.Request;
import spark.Response;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;

public class Main {
    private static final Gson gson = new Gson();
    private static final ProjectileCalculator calculator = new ProjectileCalculator();

    public static void main(String[] args) {
        // Obtener la ruta absoluta del directorio del proyecto
        String projectDir = new File("").getAbsolutePath();
        String frontendDir = new File(projectDir).getParentFile().getAbsolutePath() + "/frontend";

        System.out.println("Directorio del proyecto: " + projectDir);
        System.out.println("Directorio frontend: " + frontendDir);

        // Verificar que el directorio frontend existe
        File frontendFolder = new File(frontendDir);
        if (!frontendFolder.exists() || !frontendFolder.isDirectory()) {
            System.err.println("Error: El directorio frontend no existe en: " + frontendDir);
            System.exit(1);
        }

        // Verificar que index.html existe
        File indexFile = new File(frontendFolder, "index.html");
        if (!indexFile.exists()) {
            System.err.println("Error: El archivo index.html no existe en: " + indexFile.getAbsolutePath());
            System.exit(1);
        }

        // Configurar archivos estáticos
        Spark.staticFiles.location("/");
        Spark.staticFiles.externalLocation(frontendDir);

        // Configurar CORS
        Spark.before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            response.header("Access-Control-Allow-Headers", "Content-Type");
        });

        // Ruta principal para servir el frontend
        Spark.get("/", (request, response) -> {
            try {
                String content = new String(Files.readAllBytes(indexFile.toPath()));
                response.type("text/html");
                return content;
            } catch (Exception e) {
                response.status(500);
                return "Error al cargar la página principal: " + e.getMessage() + 
                       "\nRuta del directorio frontend: " + frontendDir +
                       "\nRuta del archivo index.html: " + indexFile.getAbsolutePath();
            }
        });

        // Endpoint para calcular el movimiento del proyectil
        Spark.post("/api/calculate", (request, response) -> {
            try {
                JsonObject jsonRequest = gson.fromJson(request.body(), JsonObject.class);
                double initialVelocity = jsonRequest.get("initialVelocity").getAsDouble();
                double launchAngle = jsonRequest.get("launchAngle").getAsDouble();

                // Validar entrada
                if (initialVelocity <= 0 || launchAngle <= 0 || launchAngle >= 90) {
                    response.status(400);
                    return gson.toJson(new ErrorResponse("Valores inválidos. La velocidad debe ser positiva y el ángulo entre 0 y 90 grados"));
                }

                // Calcular resultados
                calculator.setInitialValues(initialVelocity, launchAngle);
                calculator.calculateTotalFlightTime();
                calculator.calculateTimeToMaxHeight();
                calculator.calculateMaxHeight();
                calculator.calculateMaxDistance();
                calculator.calculateTrajectory();

                // Crear respuesta
                JsonObject jsonResponse = new JsonObject();
                jsonResponse.addProperty("totalFlightTime", calculator.getTotalFlightTime());
                jsonResponse.addProperty("timeToMaxHeight", calculator.getTimeToMaxHeight());
                jsonResponse.addProperty("maxHeight", calculator.getMaxHeight());
                jsonResponse.addProperty("maxDistance", calculator.getMaxDistance());
                jsonResponse.add("trajectory", gson.toJsonTree(calculator.getTrajectory()));

                response.type("application/json");
                return gson.toJson(jsonResponse);
            } catch (Exception e) {
                response.status(500);
                return gson.toJson(new ErrorResponse("Error al procesar la solicitud: " + e.getMessage()));
            }
        });

        // Endpoint para descargar resultados en TXT
        Spark.post("/api/download/txt", (request, response) -> {
            try {
                String filePath = new File(projectDir, "results.txt").getAbsolutePath();
                calculator.saveResultsToTxt(filePath);
                response.type("application/json");
                return gson.toJson(new SuccessResponse("Archivo TXT generado correctamente"));
            } catch (Exception e) {
                response.status(500);
                return gson.toJson(new ErrorResponse("Error al generar el archivo TXT: " + e.getMessage()));
            }
        });

        // Endpoint para descargar resultados en PDF
        Spark.post("/api/download/pdf", (request, response) -> {
            try {
                String filePath = new File(projectDir, "results.pdf").getAbsolutePath();
                calculator.saveResultsToPdf(filePath);
                response.type("application/json");
                return gson.toJson(new SuccessResponse("Archivo PDF generado correctamente"));
            } catch (Exception e) {
                response.status(500);
                return gson.toJson(new ErrorResponse("Error al generar el archivo PDF: " + e.getMessage()));
            }
        });

        // Endpoint para obtener el archivo TXT
        Spark.get("/results.txt", (request, response) -> {
            try {
                String filePath = new File(projectDir, "results.txt").getAbsolutePath();
                File file = new File(filePath);
                if (!file.exists()) {
                    response.status(404);
                    return "Archivo no encontrado";
                }
                response.type("text/plain");
                response.header("Content-Disposition", "attachment; filename=results.txt");
                return new String(Files.readAllBytes(file.toPath()));
            } catch (Exception e) {
                response.status(500);
                return "Error al leer el archivo: " + e.getMessage();
            }
        });

        // Endpoint para obtener el archivo PDF
        Spark.get("/results.pdf", (request, response) -> {
            try {
                String filePath = new File(projectDir, "results.pdf").getAbsolutePath();
                File file = new File(filePath);
                if (!file.exists()) {
                    response.status(404);
                    return "Archivo no encontrado";
                }
                response.type("application/pdf");
                response.header("Content-Disposition", "attachment; filename=results.pdf");
                return Files.readAllBytes(file.toPath());
            } catch (Exception e) {
                response.status(500);
                return "Error al leer el archivo: " + e.getMessage();
            }
        });
    }

    private static class ErrorResponse {
        private String error;

        public ErrorResponse(String error) {
            this.error = error;
        }
    }

    private static class SuccessResponse {
        private String message;

        public SuccessResponse(String message) {
            this.message = message;
        }
    }
} 