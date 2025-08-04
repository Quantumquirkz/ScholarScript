package com.integral.calculator;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import java.util.*;

@RestController
@RequestMapping("/api/calculate")
public class IntegralController {

    public static class CalculateRequest {
        public String function;
        public double lowerLimit;
        public double upperLimit;
        public int partitions;
        public String method;
    }

    public static class Point {
        public double x;
        public double y;
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class CalculationResult {
        public double area;
        public List<Point> points;
        public List<Double> partialAreas;
        public String latexFormula;
        public String method;
    }

    @PostMapping
    public ResponseEntity<?> calculate(@RequestBody CalculateRequest req) {
        try {
            if (req.function == null || req.function.trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "La función no puede estar vacía"));
            }
            if (req.lowerLimit >= req.upperLimit) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "El límite inferior debe ser menor que el superior"));
            }
            if (req.partitions < 1 || req.partitions > 1000) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "El número de particiones debe estar entre 1 y 1000"));
            }

            // Probar la función en algunos puntos
            evaluateFunction(req.function, req.lowerLimit);
            evaluateFunction(req.function, req.upperLimit);
            evaluateFunction(req.function, (req.lowerLimit + req.upperLimit) / 2);

            CalculationResult result;
            if ("trapecio".equals(req.method)) {
                result = trapezoidalRule(req.function, req.lowerLimit, req.upperLimit, req.partitions);
            } else if ("integral_definida".equals(req.method)) {
                result = definiteIntegral(req.function, req.lowerLimit, req.upperLimit, req.partitions);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Método no válido. Use 'trapecio' o 'integral_definida'"));
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        }
    }

    // Evaluador de funciones usando exp4j
    private double evaluateFunction(String func, double x) {
        try {
            Expression e = new ExpressionBuilder(func.replace("^", "^"))
                .variables("x")
                .build()
                .setVariable("x", x);
            return e.evaluate();
        } catch (Exception ex) {
            throw new RuntimeException("Error al evaluar la función en x=" + x + ": " + ex.getMessage());
        }
    }

    // Regla del trapecio
    private CalculationResult trapezoidalRule(String func, double a, double b, int n) {
        double h = (b - a) / n;
        List<Point> points = new ArrayList<>();
        List<Double> partialAreas = new ArrayList<>();
        for (int i = 0; i <= n * 2; i++) {
            double x = a + (i * (b - a)) / (n * 2);
            double y = evaluateFunction(func, x);
            points.add(new Point(x, y));
        }
        double area = 0;
        for (int i = 0; i < n; i++) {
            double x1 = a + i * h;
            double x2 = a + (i + 1) * h;
            double y1 = evaluateFunction(func, x1);
            double y2 = evaluateFunction(func, x2);
            double partialArea = (h / 2) * (y1 + y2);
            partialAreas.add(partialArea);
            area += partialArea;
        }
        String latexFormula = String.format("\\int_{%.2f}^{%.2f} %s \\, dx \\approx \\frac{h}{2}\\sum_{i=0}^{%d-1}[f(x_i) + f(x_{i+1})] = %.6f", a, b, func.replace("*", "").replace("^", "^"), n, area);
        CalculationResult result = new CalculationResult();
        result.area = area;
        result.points = points;
        result.partialAreas = partialAreas;
        result.latexFormula = latexFormula;
        result.method = "Regla del Trapecio";
        return result;
    }

    // Integral definida (alta precisión)
    private CalculationResult definiteIntegral(String func, double a, double b, int n) {
        int highPrecisionN = Math.max(n * 10, 1000);
        CalculationResult highPrecisionResult = trapezoidalRule(func, a, b, highPrecisionN);
        double h = (b - a) / n;
        List<Point> points = new ArrayList<>();
        List<Double> partialAreas = new ArrayList<>();
        for (int i = 0; i <= n * 2; i++) {
            double x = a + (i * (b - a)) / (n * 2);
            double y = evaluateFunction(func, x);
            points.add(new Point(x, y));
        }
        for (int i = 0; i < n; i++) {
            double x1 = a + i * h;
            double x2 = a + (i + 1) * h;
            double y1 = evaluateFunction(func, x1);
            double y2 = evaluateFunction(func, x2);
            double partialArea = (h / 2) * (y1 + y2);
            partialAreas.add(partialArea);
        }
        String latexFormula = String.format("\\int_{%.2f}^{%.2f} %s \\, dx = %.6f", a, b, func.replace("*", "").replace("^", "^"), highPrecisionResult.area);
        CalculationResult result = new CalculationResult();
        result.area = highPrecisionResult.area;
        result.points = points;
        result.partialAreas = partialAreas;
        result.latexFormula = latexFormula;
        result.method = "Integral Definida (Alta Precisión)";
        return result;
    }
} 