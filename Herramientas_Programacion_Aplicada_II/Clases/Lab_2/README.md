# 🧪 Lab 2: Herencia y Polimorfismo - Jerarquías de Clases

## 📋 Objetivos del Laboratorio
Este laboratorio introduce los conceptos de herencia y polimorfismo en C++, permitiendo a los estudiantes crear jerarquías de clases, implementar herencia múltiple y utilizar polimorfismo dinámico.

## 🎯 Resultados de Aprendizaje
Al finalizar este laboratorio, los estudiantes serán capaces de:
- Implementar herencia simple y múltiple
- Usar funciones virtuales y polimorfismo dinámico
- Crear clases abstractas e interfaces
- Aplicar el principio de sustitución de Liskov
- Diseñar jerarquías de clases complejas

## 📚 Marco Teórico

### 🏗️ Herencia en C++

#### Herencia Simple
```cpp
// Clase base
class Animal {
protected:
    string nombre;
    int edad;
    
public:
    Animal(string n, int e) : nombre(n), edad(e) {}
    
    virtual void hacerSonido() {
        cout << "Sonido genérico de animal" << endl;
    }
    
    virtual void mostrarInfo() {
        cout << "Nombre: " << nombre << ", Edad: " << edad << endl;
    }
    
    virtual ~Animal() {} // Destructor virtual
};

// Clase derivada
class Perro : public Animal {
private:
    string raza;
    
public:
    Perro(string n, int e, string r) : Animal(n, e), raza(r) {}
    
    void hacerSonido() override {
        cout << "¡Guau! ¡Guau!" << endl;
    }
    
    void mostrarInfo() override {
        Animal::mostrarInfo();
        cout << "Raza: " << raza << endl;
    }
    
    void ladrar() {
        cout << "El perro está ladrando" << endl;
    }
};
```

#### Herencia Múltiple
```cpp
class Volador {
public:
    virtual void volar() = 0;
    virtual ~Volador() {}
};

class Nadador {
public:
    virtual void nadar() = 0;
    virtual ~Nadador() {}
};

class Pato : public Animal, public Volador, public Nadador {
public:
    Pato(string n, int e) : Animal(n, e) {}
    
    void hacerSonido() override {
        cout << "¡Cuac! ¡Cuac!" << endl;
    }
    
    void volar() override {
        cout << "El pato está volando" << endl;
    }
    
    void nadar() override {
        cout << "El pato está nadando" << endl;
    }
};
```

### 🔄 Polimorfismo

#### Polimorfismo Dinámico
```cpp
void procesarAnimal(Animal* animal) {
    animal->hacerSonido();
    animal->mostrarInfo();
}

// Uso del polimorfismo
Animal* animales[] = {
    new Perro("Rex", 5, "Labrador"),
    new Pato("Donald", 3),
    new Gato("Mittens", 2)
};

for (Animal* animal : animales) {
    procesarAnimal(animal);
    delete animal;
}
```

#### Clases Abstractas
```cpp
class Figura {
public:
    virtual double calcularArea() = 0;
    virtual double calcularPerimetro() = 0;
    virtual void mostrarInfo() = 0;
    virtual ~Figura() {}
};

class Circulo : public Figura {
private:
    double radio;
    
public:
    Circulo(double r) : radio(r) {}
    
    double calcularArea() override {
        return 3.14159 * radio * radio;
    }
    
    double calcularPerimetro() override {
        return 2 * 3.14159 * radio;
    }
    
    void mostrarInfo() override {
        cout << "Círculo - Radio: " << radio << endl;
    }
};
```

## 🛠️ Ejercicios del Laboratorio

### 📝 Ejercicio 1: Jerarquía de Empleados
**Objetivo**: Crear una jerarquía de clases para diferentes tipos de empleados.

**Requisitos**:
- Clase base `Empleado` con atributos comunes
- Clases derivadas: `EmpleadoTiempoCompleto`, `EmpleadoTiempoParcial`, `Gerente`
- Método virtual para calcular salario
- Polimorfismo para procesar diferentes tipos de empleados

**Estructura Sugerida**:
```cpp
class Empleado {
protected:
    string nombre;
    string id;
    double salarioBase;
    
public:
    Empleado(string n, string i, double s) : nombre(n), id(i), salarioBase(s) {}
    virtual double calcularSalario() = 0;
    virtual void mostrarInfo() = 0;
    virtual ~Empleado() {}
};

class EmpleadoTiempoCompleto : public Empleado {
private:
    int horasExtras;
    
public:
    EmpleadoTiempoCompleto(string n, string i, double s, int he) 
        : Empleado(n, i, s), horasExtras(he) {}
    
    double calcularSalario() override {
        return salarioBase + (horasExtras * 15.0); // $15 por hora extra
    }
    
    void mostrarInfo() override {
        cout << "Empleado Tiempo Completo: " << nombre << endl;
        cout << "Salario: $" << calcularSalario() << endl;
    }
};
```

### 📝 Ejercicio 2: Sistema de Formas Geométricas
**Objetivo**: Implementar una jerarquía de formas geométricas con polimorfismo.

**Requisitos**:
- Clase abstracta `Forma` con métodos virtuales
- Clases derivadas: `Rectangulo`, `Circulo`, `Triangulo`
- Método para calcular área y perímetro
- Función para procesar un array de formas

### 📝 Ejercicio 3: Sistema de Vehículos
**Objetivo**: Crear una jerarquía de vehículos con herencia múltiple.

**Requisitos**:
- Clases base: `Vehiculo`, `Motorizado`, `Electrico`
- Clases derivadas: `Coche`, `Moto`, `BicicletaElectrica`
- Métodos específicos para cada tipo de vehículo
- Uso de herencia múltiple donde sea apropiado

### 📝 Ejercicio 4: Sistema de Medios de Comunicación
**Objetivo**: Implementar un sistema de medios con interfaces.

**Requisitos**:
- Interfaces: `Reproducible`, `Grabable`, `Transmisible`
- Clases: `CD`, `DVD`, `Streaming`, `Radio`
- Implementación de múltiples interfaces
- Métodos específicos para cada medio

## 🧪 Ejercicios Avanzados

### 📝 Ejercicio 5: Sistema de Pagos
**Objetivo**: Crear un sistema de procesamiento de pagos con polimorfismo.

**Requisitos**:
- Clase base `MetodoPago` con método virtual `procesarPago()`
- Clases derivadas: `TarjetaCredito`, `PayPal`, `Transferencia`
- Sistema de procesamiento polimórfico
- Manejo de errores específicos por método

### 📝 Ejercicio 6: Sistema de Notificaciones
**Objetivo**: Implementar un sistema de notificaciones con herencia múltiple.

**Requisitos**:
- Interfaces: `Enviable`, `Priorizable`, `Programable`
- Clases: `Email`, `SMS`, `PushNotification`
- Combinación de interfaces según el tipo de notificación
- Sistema de prioridades y programación

## 📊 Criterios de Evaluación

### 🎯 Diseño de Clases (30%)
- Jerarquía de clases bien diseñada
- Uso apropiado de herencia
- Implementación correcta de interfaces
- Separación de responsabilidades

### 🔧 Polimorfismo (30%)
- Uso correcto de funciones virtuales
- Implementación de polimorfismo dinámico
- Manejo apropiado de clases abstractas
- Principio de sustitución de Liskov

### 📝 Funcionalidad (25%)
- Todos los métodos funcionan correctamente
- Manejo adecuado de herencia múltiple
- Procesamiento polimórfico correcto
- Interfaz de usuario clara

### 🧪 Pruebas (15%)
- Casos de prueba para cada clase
- Verificación de polimorfismo
- Manejo de casos límite
- Documentación de pruebas

## 📚 Recursos Adicionales

### 📖 Referencias Bibliográficas
- **"C++ Primer"** por Stanley Lippman - Capítulos 15-18
- **"Effective C++"** por Scott Meyers - Items 32-40
- **"Design Patterns"** por Gang of Four - Patrones de herencia

### 🌐 Recursos en Línea
- [C++ Inheritance Tutorial](https://www.cplusplus.com/doc/tutorial/inheritance/)
- [C++ Polymorphism](https://www.cplusplus.com/doc/tutorial/polymorphism/)
- [Virtual Functions](https://en.cppreference.com/w/cpp/language/virtual)

### 🎥 Videos Recomendados
- **"C++ Inheritance Tutorial"** - The Cherno (YouTube)
- **"C++ Polymorphism Explained"** - Programming with Mosh
- **"Virtual Functions in C++"** - CodeBeauty

### 🛠️ Herramientas de Desarrollo
- **IDE**: Visual Studio Code, CLion, Qt Creator
- **Debugger**: GDB con soporte para herencia
- **Análisis**: Valgrind para detección de memory leaks

## 🚀 Compilación y Ejecución

### 🔧 Comandos de Compilación
```bash
# Compilación con soporte para C++17
g++ -std=c++17 -o programa programa.cpp

# Con advertencias y optimización
g++ -Wall -Wextra -O2 -std=c++17 -o programa programa.cpp

# Con información de depuración
g++ -g -std=c++17 -o programa programa.cpp
```

### 📝 Mejores Prácticas
- Usar destructores virtuales en clases base
- Implementar el principio de sustitución de Liskov
- Preferir composición sobre herencia cuando sea posible
- Usar `override` para funciones virtuales
- Documentar jerarquías de clases

---

**Duración**: 4 horas  
**Dificultad**: Avanzado  
**Prerrequisitos**: Lab 1 (Clases y Objetos)  
**Última Actualización**: Diciembre 2024 