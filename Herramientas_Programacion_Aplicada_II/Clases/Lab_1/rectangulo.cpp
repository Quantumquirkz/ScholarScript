/*
 * Ejercicio 1: Clase Rectángulo
 * Lab 1 - Programación Orientada a Objetos
 * 
 * Este programa implementa una clase Rectángulo con métodos para
 * calcular área y perímetro, demostrando los conceptos básicos de POO.
 */

#include <iostream>
#include <iomanip>
using namespace std;

class Rectangulo {
private:
    double base;
    double altura;
    
public:
    // Constructor
    Rectangulo(double b, double a) {
        if (b > 0 && a > 0) {
            base = b;
            altura = a;
        } else {
            cout << "Advertencia: Dimensiones inválidas. Usando valores por defecto." << endl;
            base = 1.0;
            altura = 1.0;
        }
    }
    
    // Constructor por defecto
    Rectangulo() {
        base = 1.0;
        altura = 1.0;
    }
    
    // Métodos para cálculos
    double calcularArea() const {
        return base * altura;
    }
    
    double calcularPerimetro() const {
        return 2 * (base + altura);
    }
    
    // Método para mostrar información
    void mostrarInfo() const {
        cout << "=== Información del Rectángulo ===" << endl;
        cout << "Base: " << fixed << setprecision(2) << base << endl;
        cout << "Altura: " << altura << endl;
        cout << "Área: " << calcularArea() << endl;
        cout << "Perímetro: " << calcularPerimetro() << endl;
        cout << "================================" << endl;
    }
    
    // Método para verificar si es cuadrado
    bool esCuadrado() const {
        return base == altura;
    }
    
    // Método para obtener la diagonal
    double calcularDiagonal() const {
        return sqrt(base * base + altura * altura);
    }
    
    // Getters
    double getBase() const { return base; }
    double getAltura() const { return altura; }
    
    // Setters con validación
    void setBase(double b) {
        if (b > 0) {
            base = b;
        } else {
            cout << "Error: La base debe ser positiva." << endl;
        }
    }
    
    void setAltura(double a) {
        if (a > 0) {
            altura = a;
        } else {
            cout << "Error: La altura debe ser positiva." << endl;
        }
    }
    
    // Método para cambiar ambas dimensiones
    void redimensionar(double nuevaBase, double nuevaAltura) {
        if (nuevaBase > 0 && nuevaAltura > 0) {
            base = nuevaBase;
            altura = nuevaAltura;
        } else {
            cout << "Error: Ambas dimensiones deben ser positivas." << endl;
        }
    }
};

// Función para comparar dos rectángulos
void compararRectangulos(const Rectangulo& r1, const Rectangulo& r2) {
    cout << "=== Comparación de Rectángulos ===" << endl;
    
    if (r1.calcularArea() > r2.calcularArea()) {
        cout << "El primer rectángulo tiene mayor área." << endl;
    } else if (r1.calcularArea() < r2.calcularArea()) {
        cout << "El segundo rectángulo tiene mayor área." << endl;
    } else {
        cout << "Ambos rectángulos tienen la misma área." << endl;
    }
    
    if (r1.calcularPerimetro() > r2.calcularPerimetro()) {
        cout << "El primer rectángulo tiene mayor perímetro." << endl;
    } else if (r1.calcularPerimetro() < r2.calcularPerimetro()) {
        cout << "El segundo rectángulo tiene mayor perímetro." << endl;
    } else {
        cout << "Ambos rectángulos tienen el mismo perímetro." << endl;
    }
    
    cout << "=================================" << endl;
}

int main() {
    cout << "=== Programa de Gestión de Rectángulos ===" << endl;
    cout << endl;
    
    // Crear rectángulos
    Rectangulo rect1(5.0, 3.0);
    Rectangulo rect2(4.0, 4.0);
    Rectangulo rect3; // Constructor por defecto
    
    cout << "Rectángulo 1:" << endl;
    rect1.mostrarInfo();
    
    cout << "Rectángulo 2:" << endl;
    rect2.mostrarInfo();
    
    cout << "Rectángulo 3 (por defecto):" << endl;
    rect3.mostrarInfo();
    
    // Verificar si es cuadrado
    cout << "¿El rectángulo 1 es cuadrado? " << (rect1.esCuadrado() ? "Sí" : "No") << endl;
    cout << "¿El rectángulo 2 es cuadrado? " << (rect2.esCuadrado() ? "Sí" : "No") << endl;
    
    // Calcular diagonales
    cout << "Diagonal del rectángulo 1: " << fixed << setprecision(2) << rect1.calcularDiagonal() << endl;
    cout << "Diagonal del rectángulo 2: " << rect2.calcularDiagonal() << endl;
    
    // Comparar rectángulos
    compararRectangulos(rect1, rect2);
    
    // Modificar dimensiones
    cout << "Modificando dimensiones del rectángulo 1..." << endl;
    rect1.setBase(6.0);
    rect1.setAltura(4.0);
    rect1.mostrarInfo();
    
    // Probar redimensionamiento
    cout << "Redimensionando rectángulo 3..." << endl;
    rect3.redimensionar(7.0, 2.0);
    rect3.mostrarInfo();
    
    // Probar validación
    cout << "Probando validación con valores negativos..." << endl;
    rect1.setBase(-2.0);  // Debería mostrar error
    rect1.setAltura(0.0); // Debería mostrar error
    
    return 0;
} 