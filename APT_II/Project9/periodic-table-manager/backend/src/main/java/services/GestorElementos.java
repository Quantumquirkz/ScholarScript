package services;

import models.ElementoQuimico;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class GestorElementos {
    private List<ElementoQuimico> elementos;
    
    public GestorElementos() {
        this.elementos = new ArrayList<>();
    }
    
    public void agregarElemento(ElementoQuimico elemento) {
        // Asignar la posición basada en el índice en la lista
        elemento.setPosicion(elementos.size());
        elementos.add(elemento);
    }
    
    public void agregarElementos(List<ElementoQuimico> nuevosElementos) {
        for (int i = 0; i < nuevosElementos.size(); i++) {
            ElementoQuimico elemento = nuevosElementos.get(i);
            elemento.setPosicion(elementos.size());
            elementos.add(elemento);
        }
    }
    
    public List<ElementoQuimico> obtenerTodos() {
        return new ArrayList<>(elementos);
    }
    
    public ElementoQuimico obtenerMayorNumeroAtomico() {
        if (elementos.isEmpty()) {
            return null;
        }
        
        return elementos.stream()
                .max(Comparator.comparingInt(ElementoQuimico::getNumeroAtomico))
                .orElse(null);
    }
    
    public ElementoQuimico obtenerMaximoPesoAtomico() {
        if (elementos.isEmpty()) {
            return null;
        }
        
        return elementos.stream()
                .max(Comparator.comparingDouble(ElementoQuimico::getPesoAtomico))
                .orElse(null);
    }
    
    public void limpiarElementos() {
        elementos.clear();
    }
    
    public boolean existeNumeroAtomico(int numeroAtomico) {
        return elementos.stream()
                .anyMatch(e -> e.getNumeroAtomico() == numeroAtomico);
    }
    
    public Optional<ElementoQuimico> buscarPorSimbolo(String simbolo) {
        return elementos.stream()
                .filter(e -> e.getSimbolo().equalsIgnoreCase(simbolo))
                .findFirst();
    }
    
    public List<ElementoQuimico> buscarPorNombre(String nombreParcial) {
        return elementos.stream()
                .filter(e -> e.getNombre().toLowerCase().contains(nombreParcial.toLowerCase()))
                .toList();
    }
}
