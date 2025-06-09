package models;

public class ElementoQuimico {
    private String nombre;
    private String simbolo;
    private double pesoAtomico;
    private int numeroAtomico;
    private int posicion;
    
    // Constructor vacío necesario para deserialización JSON
    public ElementoQuimico() {
    }
    
    // Constructor completo
    public ElementoQuimico(String nombre, String simbolo, double pesoAtomico, int numeroAtomico, int posicion) {
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.pesoAtomico = pesoAtomico;
        this.numeroAtomico = numeroAtomico;
        this.posicion = posicion;
    }
    
    // Getters y setters
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getSimbolo() {
        return simbolo;
    }
    
    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }
    
    public double getPesoAtomico() {
        return pesoAtomico;
    }
    
    public void setPesoAtomico(double pesoAtomico) {
        this.pesoAtomico = pesoAtomico;
    }
    
    public int getNumeroAtomico() {
        return numeroAtomico;
    }
    
    public void setNumeroAtomico(int numeroAtomico) {
        this.numeroAtomico = numeroAtomico;
    }
    
    public int getPosicion() {
        return posicion;
    }
    
    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
    
    @Override
    public String toString() {
        return "ElementoQuimico{" +
                "nombre='" + nombre + '\'' +
                ", simbolo='" + simbolo + '\'' +
                ", pesoAtomico=" + pesoAtomico +
                ", numeroAtomico=" + numeroAtomico +
                ", posicion=" + posicion +
                '}';
    }
}
