/*
 * Ejercicio 1: Implementación de Vector Dinámico
 * Lab 1 - Arrays y Vectores
 * 
 * Este programa implementa una clase Vector dinámico con funcionalidad completa,
 * incluyendo redimensionamiento automático y análisis de complejidad.
 */

#include <iostream>
#include <stdexcept>
#include <iomanip>
using namespace std;

template<typename T>
class MiVector {
private:
    T* datos;
    int tamanio;
    int capacidad;
    
public:
    // Constructor por defecto
    MiVector() : datos(nullptr), tamanio(0), capacidad(0) {}
    
    // Constructor con capacidad inicial
    MiVector(int capacidadInicial) : tamanio(0), capacidad(capacidadInicial) {
        if (capacidadInicial > 0) {
            datos = new T[capacidad];
        } else {
            datos = nullptr;
            capacidad = 0;
        }
    }
    
    // Constructor de copia
    MiVector(const MiVector& otro) : tamanio(otro.tamanio), capacidad(otro.capacidad) {
        if (capacidad > 0) {
            datos = new T[capacidad];
            for (int i = 0; i < tamanio; i++) {
                datos[i] = otro.datos[i];
            }
        } else {
            datos = nullptr;
        }
    }
    
    // Operador de asignación
    MiVector& operator=(const MiVector& otro) {
        if (this != &otro) {
            delete[] datos;
            tamanio = otro.tamanio;
            capacidad = otro.capacidad;
            
            if (capacidad > 0) {
                datos = new T[capacidad];
                for (int i = 0; i < tamanio; i++) {
                    datos[i] = otro.datos[i];
                }
            } else {
                datos = nullptr;
            }
        }
        return *this;
    }
    
    // Destructor
    ~MiVector() {
        delete[] datos;
    }
    
    // Métodos básicos
    void push_back(const T& valor) {
        if (tamanio >= capacidad) {
            redimensionar(capacidad == 0 ? 1 : capacidad * 2);
        }
        datos[tamanio] = valor;
        tamanio++;
    }
    
    void pop_back() {
        if (tamanio > 0) {
            tamanio--;
        }
    }
    
    void insert(int posicion, const T& valor) {
        if (posicion < 0 || posicion > tamanio) {
            throw out_of_range("Posición fuera de rango");
        }
        
        if (tamanio >= capacidad) {
            redimensionar(capacidad == 0 ? 1 : capacidad * 2);
        }
        
        // Mover elementos hacia la derecha
        for (int i = tamanio; i > posicion; i--) {
            datos[i] = datos[i - 1];
        }
        
        datos[posicion] = valor;
        tamanio++;
    }
    
    void erase(int posicion) {
        if (posicion < 0 || posicion >= tamanio) {
            throw out_of_range("Posición fuera de rango");
        }
        
        // Mover elementos hacia la izquierda
        for (int i = posicion; i < tamanio - 1; i++) {
            datos[i] = datos[i + 1];
        }
        
        tamanio--;
    }
    
    // Operador de acceso con verificación
    T& operator[](int indice) {
        if (indice < 0 || indice >= tamanio) {
            throw out_of_range("Índice fuera de rango");
        }
        return datos[indice];
    }
    
    // Método at con verificación
    T& at(int indice) {
        return operator[](indice);
    }
    
    // Métodos de información
    int size() const { return tamanio; }
    int capacity() const { return capacidad; }
    bool empty() const { return tamanio == 0; }
    
    // Método para reservar capacidad
    void reserve(int nuevaCapacidad) {
        if (nuevaCapacidad > capacidad) {
            redimensionar(nuevaCapacidad);
        }
    }
    
    // Método para cambiar tamaño
    void resize(int nuevoTamanio, const T& valor = T()) {
        if (nuevoTamanio > capacidad) {
            redimensionar(nuevoTamanio);
        }
        
        if (nuevoTamanio > tamanio) {
            // Llenar con valor por defecto
            for (int i = tamanio; i < nuevoTamanio; i++) {
                datos[i] = valor;
            }
        }
        
        tamanio = nuevoTamanio;
    }
    
    // Método para limpiar
    void clear() {
        tamanio = 0;
    }
    
    // Método para mostrar información
    void mostrarInfo() const {
        cout << "=== Información del Vector ===" << endl;
        cout << "Tamaño: " << tamanio << endl;
        cout << "Capacidad: " << capacidad << endl;
        cout << "Vacío: " << (empty() ? "Sí" : "No") << endl;
        cout << "Elementos: ";
        
        if (empty()) {
            cout << "[]" << endl;
        } else {
            cout << "[";
            for (int i = 0; i < tamanio; i++) {
                cout << datos[i];
                if (i < tamanio - 1) cout << ", ";
            }
            cout << "]" << endl;
        }
        cout << "=============================" << endl;
    }
    
private:
    void redimensionar(int nuevaCapacidad) {
        T* nuevosDatos = new T[nuevaCapacidad];
        
        // Copiar elementos existentes
        for (int i = 0; i < tamanio; i++) {
            nuevosDatos[i] = datos[i];
        }
        
        delete[] datos;
        datos = nuevosDatos;
        capacidad = nuevaCapacidad;
        
        cout << "Vector redimensionado a capacidad: " << capacidad << endl;
    }
};

// Función para probar el vector
void probarVector() {
    cout << "=== Pruebas del Vector Dinámico ===" << endl;
    
    // Crear vector
    MiVector<int> vec;
    vec.mostrarInfo();
    
    // Agregar elementos
    cout << "\nAgregando elementos..." << endl;
    for (int i = 1; i <= 10; i++) {
        vec.push_back(i);
        cout << "Agregado: " << i << endl;
    }
    vec.mostrarInfo();
    
    // Insertar en posición específica
    cout << "\nInsertando 100 en posición 5..." << endl;
    vec.insert(5, 100);
    vec.mostrarInfo();
    
    // Acceder a elementos
    cout << "\nAccediendo a elementos:" << endl;
    cout << "Elemento en posición 0: " << vec[0] << endl;
    cout << "Elemento en posición 5: " << vec.at(5) << endl;
    
    // Eliminar elementos
    cout << "\nEliminando elemento en posición 3..." << endl;
    vec.erase(3);
    vec.mostrarInfo();
    
    // Probar pop_back
    cout << "\nEliminando último elemento..." << endl;
    vec.pop_back();
    vec.mostrarInfo();
    
    // Probar resize
    cout << "\nRedimensionando a tamaño 15..." << endl;
    vec.resize(15, 0);
    vec.mostrarInfo();
    
    // Probar clear
    cout << "\nLimpiando vector..." << endl;
    vec.clear();
    vec.mostrarInfo();
}

// Función para demostrar análisis de complejidad
void analisisComplejidad() {
    cout << "\n=== Análisis de Complejidad ===" << endl;
    cout << "Operación\t\tComplejidad" << endl;
    cout << "Acceso [i]\t\tO(1)" << endl;
    cout << "push_back\t\tO(1) amortizado" << endl;
    cout << "pop_back\t\tO(1)" << endl;
    cout << "insert(i)\t\tO(n)" << endl;
    cout << "erase(i)\t\tO(n)" << endl;
    cout << "resize\t\t\tO(n)" << endl;
    cout << "clear\t\t\tO(1)" << endl;
    cout << "Redimensionamiento\tO(n)" << endl;
}

int main() {
    cout << "=== Implementación de Vector Dinámico ===" << endl;
    cout << "Análisis de Complejidad y Funcionalidad" << endl;
    cout << endl;
    
    try {
        probarVector();
        analisisComplejidad();
        
        // Probar con diferentes tipos de datos
        cout << "\n=== Prueba con Strings ===" << endl;
        MiVector<string> vecStrings;
        vecStrings.push_back("Hola");
        vecStrings.push_back("Mundo");
        vecStrings.push_back("C++");
        vecStrings.mostrarInfo();
        
    } catch (const exception& e) {
        cout << "Error: " << e.what() << endl;
    }
    
    return 0;
} 