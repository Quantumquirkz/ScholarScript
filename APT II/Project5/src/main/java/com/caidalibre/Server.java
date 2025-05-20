package com.caidalibre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "*")
public class Server {

    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }

    @PostMapping("/calcular")
    public ResponseEntity<Resultado> calcular(@RequestBody CalculoRequest request) {
        metodos op = new metodos();

        double altura = request.getAltura();
        String planeta = request.getPlaneta();

        double gravedad = op.Gravedad(planeta);
        double tiempo = op.calcularTiempoCaida(altura, gravedad);

        Resultado resultado = new Resultado(altura, gravedad, tiempo);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
}

class CalculoRequest {
    private double altura;
    private String planeta;

    // Getters y Setters
    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public String getPlaneta() {
        return planeta;
    }

    public void setPlaneta(String planeta) {
        this.planeta = planeta;
    }
}

class Resultado {
    private double altura;
    private double gravedad;
    private double tiempo;

    public Resultado(double altura, double gravedad, double tiempo) {
        this.altura = altura;
        this.gravedad = gravedad;
        this.tiempo = tiempo;
    }

    // Getters
    public double getAltura() {
        return altura;
    }

    public double getGravedad() {
        return gravedad;
    }

    public double getTiempo() {
        return tiempo;
    }
}