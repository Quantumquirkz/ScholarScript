package com.caidalibre.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.OPTIONS})
public class CalculadoraController {

    @PostMapping("/calcular")
    public ResponseEntity<?> calcular(@RequestBody Map<String, Object> request) {
        try {
            if (request == null || !request.containsKey("altura") || !request.containsKey("planeta")) {
                return ResponseEntity.badRequest().body("Datos incompletos");
            }

            double altura;
            try {
                altura = Double.parseDouble(request.get("altura").toString());
                if (altura <= 0) {
                    return ResponseEntity.badRequest().body("La altura debe ser mayor que 0");
                }
            } catch (NumberFormatException e) {
                return ResponseEntity.badRequest().body("La altura debe ser un número válido");
            }

            String planeta = request.get("planeta").toString().toLowerCase();
            if (!planeta.equals("tierra") && !planeta.equals("marte")) {
                return ResponseEntity.badRequest().body("Planeta no válido");
            }
            
            double gravedad = planeta.equals("tierra") ? 9.81 : 3.72;
            double tiempo = Math.sqrt((2 * altura) / gravedad);
            
            Map<String, Double> resultado = new HashMap<>();
            resultado.put("altura", altura);
            resultado.put("gravedad", gravedad);
            resultado.put("tiempo", tiempo);
            
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error en el servidor: " + e.getMessage());
        }
    }
} 