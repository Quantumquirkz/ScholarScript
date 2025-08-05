# 🧪 Lab 1: Programación Orientada a Objetos - Clases y Objetos

## 📋 Objetivos del Laboratorio
Este laboratorio introduce los conceptos fundamentales de la Programación Orientada a Objetos (POO) en C++, incluyendo la definición de clases, creación de objetos, encapsulación y métodos.

## 🎯 Resultados de Aprendizaje
Al finalizar este laboratorio, los estudiantes serán capaces de:
- Definir clases con atributos y métodos
- Crear objetos e instanciar clases
- Implementar encapsulación usando modificadores de acceso
- Diseñar constructores y destructores
- Aplicar principios de POO en problemas reales

## 📚 Marco Teórico

### 🏗️ Conceptos de POO

#### Clases y Objetos
```cpp
// Definición de una clase
class Estudiante {
private:
    // Atributos privados (encapsulación)
    string nombre;
    int edad;
    double promedio;
    
public:
    // Constructor
    Estudiante(string n, int e, double p) {
        nombre = n;
        edad = e;
        promedio = p;
    }
    
    // Métodos públicos
    void mostrarInfo() {
        cout << "Nombre: " << nombre << endl;
        cout << "Edad: " << edad << endl;
        cout << "Promedio: " << promedio << endl;
    }
    
    // Getters y Setters
    string getNombre() { return nombre; }
    void setNombre(string n) { nombre = n; }
    
    double getPromedio() { return promedio; }
    void setPromedio(double p) { 
        if (p >= 0.0 && p <= 100.0) {
            promedio = p;
        }
    }
};

// Creación de objetos
Estudiante estudiante1("Juan", 20, 85.5);
Estudiante estudiante2("María", 19, 92.0);
```

#### Encapsulación
```cpp
class CuentaBancaria {
private:
    string numeroCuenta;
    double saldo;
    string titular;
    
public:
    CuentaBancaria(string num, string tit, double saldoInicial) {
        numeroCuenta = num;
        titular = tit;
        if (saldoInicial >= 0) {
            saldo = saldoInicial;
        } else {
            saldo = 0;
        }
    }
    
    bool depositar(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
            return true;
        }
        return false;
    }
    
    bool retirar(double cantidad) {
        if (cantidad > 0 && cantidad <= saldo) {
            saldo -= cantidad;
            return true;
        }
        return false;
    }
    
    double consultarSaldo() {
        return saldo;
    }
};
```

## 🛠️ Ejercicios del Laboratorio

### 📝 Ejercicio 1: Clase Rectángulo
**Objetivo**: Crear una clase para representar un rectángulo con métodos para calcular área y perímetro.

**Requisitos**:
- Atributos privados para base y altura
- Constructor que valide dimensiones positivas
- Métodos para calcular área y perímetro
- Método para mostrar información del rectángulo
- Getters y setters con validación

**Código Base**:
```cpp
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
            base = 1.0;
            altura = 1.0;
        }
    }
    
    // Métodos
    double calcularArea() {
        return base * altura;
    }
    
    double calcularPerimetro() {
        return 2 * (base + altura);
    }
    
    void mostrarInfo() {
        cout << "Rectángulo:" << endl;
        cout << "Base: " << base << endl;
        cout << "Altura: " << altura << endl;
        cout << "Área: " << calcularArea() << endl;
        cout << "Perímetro: " << calcularPerimetro() << endl;
    }
    
    // Getters y Setters
    double getBase() { return base; }
    double getAltura() { return altura; }
    
    void setBase(double b) {
        if (b > 0) base = b;
    }
    
    void setAltura(double a) {
        if (a > 0) altura = a;
    }
};
```

### 📝 Ejercicio 2: Clase Libro
**Objetivo**: Implementar una clase para gestionar información de libros.

**Requisitos**:
- Atributos: título, autor, año, ISBN, precio
- Constructor con parámetros
- Método para mostrar información completa
- Método para calcular descuento
- Validación de datos

### 📝 Ejercicio 3: Clase Empleado
**Objetivo**: Crear una clase para manejar información de empleados.

**Requisitos**:
- Atributos: nombre, ID, salario, departamento
- Métodos para calcular salario neto (con impuestos)
- Método para aplicar aumento de salario
- Método para cambiar departamento

### 📝 Ejercicio 4: Clase Producto
**Objetivo**: Implementar una clase para gestión de productos en un inventario.

**Requisitos**:
- Atributos: código, nombre, precio, stock
- Métodos para actualizar stock
- Método para calcular valor total del inventario
- Validación de stock mínimo

## 🧪 Ejercicios Avanzados

### 📝 Ejercicio 5: Clase Punto en 2D
**Objetivo**: Crear una clase para representar puntos en el plano cartesiano.

**Requisitos**:
- Atributos: coordenadas x, y
- Método para calcular distancia a otro punto
- Método para calcular distancia al origen
- Método para mover el punto

### 📝 Ejercicio 6: Clase Tiempo
**Objetivo**: Implementar una clase para manejar tiempo (horas, minutos, segundos).

**Requisitos**:
- Validación de rangos (0-23 horas, 0-59 minutos/segundos)
- Método para sumar tiempo
- Método para convertir a segundos
- Método para mostrar en formato HH:MM:SS

## 📊 Criterios de Evaluación

### 🎯 Calidad del Código (30%)
- Implementación correcta de clases y objetos
- Uso apropiado de encapsulación
- Nomenclatura consistente y clara
- Documentación del código

### 🔧 Funcionalidad (40%)
- Todos los métodos funcionan correctamente
- Validación apropiada de datos
- Manejo de casos límite
- Interfaz de usuario clara

### 📝 Diseño (20%)
- Diseño orientado a objetos apropiado
- Separación de responsabilidades
- Reutilización de código
- Extensibilidad del diseño

### 🧪 Pruebas (10%)
- Casos de prueba completos
- Validación de entrada
- Manejo de errores
- Documentación de pruebas

## 📚 Recursos Adicionales

### 📖 Referencias Bibliográficas
- **"Object-Oriented Programming in C++"** por Robert Lafore
- **"C++ Primer"** por Stanley Lippman - Capítulos 7-9
- **"Effective C++"** por Scott Meyers - Items 1-10

### 🌐 Recursos en Línea
- [C++ Classes Tutorial](https://www.cplusplus.com/doc/tutorial/classes/)
- [Object-Oriented Programming Concepts](https://docs.oracle.com/javase/tutorial/java/concepts/)
- [C++ Encapsulation](https://en.cppreference.com/w/cpp/language/classes)

### 🎥 Videos Recomendados
- **"C++ OOP Tutorial"** - The Cherno (YouTube)
- **"Object-Oriented Programming in C++"** - Programming with Mosh
- **"C++ Classes and Objects"** - CodeBeauty

### 🛠️ Herramientas de Desarrollo
- **IDE**: Visual Studio Code, CLion, Qt Creator
- **Debugger**: GDB con soporte para clases
- **Documentación**: Doxygen para documentar clases

## 🚀 Compilación y Ejecución

### 🔧 Comandos de Compilación
```bash
# Compilación básica
g++ -std=c++17 -o programa programa.cpp

# Con advertencias
g++ -Wall -Wextra -std=c++17 -o programa programa.cpp

# Con información de depuración
g++ -g -std=c++17 -o programa programa.cpp
```

### 📝 Estándares de Código
- Usar `std::` en lugar de `using namespace std;`
- Nombres de clases en PascalCase
- Nombres de métodos en camelCase
- Documentar todas las clases públicas

---

**Duración**: 3 horas  
**Dificultad**: Intermedio  
**Prerrequisitos**: Conocimientos básicos de C++  
**Última Actualización**: Diciembre 2024 