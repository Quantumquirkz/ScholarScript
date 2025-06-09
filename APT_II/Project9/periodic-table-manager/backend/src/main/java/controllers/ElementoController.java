package controllers;

import models.ElementoQuimico;
import services.GestorElementos;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.lang.reflect.Type;
import java.util.List;

import static spark.Spark.*;

public class ElementoController {
    private final GestorElementos gestorElementos;
    private final Gson gson;
    
    public ElementoController() {
        this.gestorElementos = new GestorElementos();
        this.gson = new Gson();
        
        // Configurar CORS para permitir peticiones desde el frontend
        Spark.options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }
            
            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            
            return "OK";
        });
        
        Spark.before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.header("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With, Content-Length, Accept, Origin");
            response.type("application/json");
        });
        
        // Definir rutas
        post("/elementos", this::agregarElementos);
        get("/elementos", this::obtenerElementos);
        get("/elementos/maximo-atomico", this::obtenerMayorNumeroAtomico);
        get("/elementos/maximo-peso", this::obtenerMaximoPesoAtomico);
        delete("/elementos", this::limpiarElementos);
    }
    
    private Object agregarElementos(Request request, Response response) {
        try {
            Type listType = new TypeToken<List<ElementoQuimico>>(){}.getType();
            List<ElementoQuimico> nuevosElementos = gson.fromJson(request.body(), listType);
            
            // Validar elementos
            for (ElementoQuimico elemento : nuevosElementos) {
                if (elemento.getNombre() == null || elemento.getNombre().trim().isEmpty()) {
                    response.status(400);
                    return gson.toJson(new ErrorResponse("El nombre del elemento no puede estar vacío"));
                }
                
                if (elemento.getSimbolo() == null || elemento.getSimbolo().trim().isEmpty() || 
                    elemento.getSimbolo().length() > 3) {
                    response.status(400);
                    return gson.toJson(new ErrorResponse("El símbolo debe tener entre 1 y 3 caracteres"));
                }
                
                if (elemento.getNumeroAtomico() <= 0) {
                    response.status(400);
                    return gson.toJson(new ErrorResponse("El número atómico debe ser un entero positivo"));
                }
                
                if (elemento.getPesoAtomico() <= 0) {
                    response.status(400);
                    return gson.toJson(new ErrorResponse("El peso atómico debe ser un número positivo"));
                }
                
                if (gestorElementos.existeNumeroAtomico(elemento.getNumeroAtomico())) {
                    response.status(400);
                    return gson.toJson(new ErrorResponse("Ya existe un elemento con el número atómico " + elemento.getNumeroAtomico()));
                }
            }
            
            gestorElementos.agregarElementos(nuevosElementos);
            response.status(201);
            return gson.toJson(new SuccessResponse("Elementos agregados correctamente"));
        } catch (Exception e) {
            response.status(500);
            return gson.toJson(new ErrorResponse("Error al procesar la solicitud: " + e.getMessage()));
        }
    }
    
    private Object obtenerElementos(Request request, Response response) {
        try {
            List<ElementoQuimico> elementos = gestorElementos.obtenerTodos();
            return gson.toJson(elementos);
        } catch (Exception e) {
            response.status(500);
            return gson.toJson(new ErrorResponse("Error al obtener los elementos: " + e.getMessage()));
        }
    }
    
    private Object obtenerMayorNumeroAtomico(Request request, Response response) {
        try {
            ElementoQuimico elemento = gestorElementos.obtenerMayorNumeroAtomico();
            if (elemento == null) {
                response.status(404);
                return gson.toJson(new ErrorResponse("No hay elementos registrados"));
            }
            return gson.toJson(elemento);
        } catch (Exception e) {
            response.status(500);
            return gson.toJson(new ErrorResponse("Error al obtener el elemento con mayor número atómico: " + e.getMessage()));
        }
    }
    
    private Object obtenerMaximoPesoAtomico(Request request, Response response) {
        try {
            ElementoQuimico elemento = gestorElementos.obtenerMaximoPesoAtomico();
            if (elemento == null) {
                response.status(404);
                return gson.toJson(new ErrorResponse("No hay elementos registrados"));
            }
            return gson.toJson(elemento);
        } catch (Exception e) {
            response.status(500);
            return gson.toJson(new ErrorResponse("Error al obtener el elemento con máximo peso atómico: " + e.getMessage()));
        }
    }
    
    private Object limpiarElementos(Request request, Response response) {
        try {
            gestorElementos.limpiarElementos();
            return gson.toJson(new SuccessResponse("Todos los elementos han sido eliminados"));
        } catch (Exception e) {
            response.status(500);
            return gson.toJson(new ErrorResponse("Error al eliminar los elementos: " + e.getMessage()));
        }
    }
    
    // Clases para respuestas
    private static class ErrorResponse {
        private final String error;
        
        public ErrorResponse(String error) {
            this.error = error;
        }
    }
    
    private static class SuccessResponse {
        private final String message;
        
        public SuccessResponse(String message) {
            this.message = message;
        }
    }
}
