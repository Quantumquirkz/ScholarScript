# 📚 Guía Completa de Programación Orientada a Objetos en C++

## 📋 Introducción
Esta guía proporciona una introducción completa a los conceptos de Programación Orientada a Objetos (POO) en C++, desde los fundamentos hasta técnicas avanzadas.

## 🎯 Principios Fundamentales de POO

### 🏗️ 1. Encapsulación
La encapsulación es el mecanismo que agrupa datos y métodos que operan sobre esos datos en una sola unidad (clase).

```cpp
class CuentaBancaria {
private:
    // Datos encapsulados
    string numeroCuenta;
    double saldo;
    string titular;
    
public:
    // Interfaz pública
    CuentaBancaria(string num, string tit, double saldoInicial);
    bool depositar(double cantidad);
    bool retirar(double cantidad);
    double consultarSaldo() const;
    string obtenerTitular() const;
};
```

**Beneficios**:
- Control de acceso a datos
- Ocultación de implementación
- Reducción de acoplamiento
- Facilita mantenimiento

### 🔄 2. Herencia
La herencia permite crear nuevas clases basadas en clases existentes, reutilizando código y estableciendo relaciones jerárquicas.

```cpp
// Clase base
class Empleado {
protected:
    string nombre;
    string id;
    double salarioBase;
    
public:
    Empleado(string n, string i, double s);
    virtual double calcularSalario() = 0;
    virtual void mostrarInfo() const;
    virtual ~Empleado() {}
};

// Clase derivada
class EmpleadoTiempoCompleto : public Empleado {
private:
    int horasExtras;
    
public:
    EmpleadoTiempoCompleto(string n, string i, double s, int he);
    double calcularSalario() override;
    void mostrarInfo() const override;
};
```

**Tipos de Herencia**:
- **Pública**: `class Derivada : public Base`
- **Protegida**: `class Derivada : protected Base`
- **Privada**: `class Derivada : private Base`

### 🔀 3. Polimorfismo
El polimorfismo permite que objetos de diferentes clases respondan de manera diferente al mismo mensaje.

#### Polimorfismo Estático (Sobrecarga)
```cpp
class Calculadora {
public:
    int sumar(int a, int b) { return a + b; }
    double sumar(double a, double b) { return a + b; }
    int sumar(int a, int b, int c) { return a + b + c; }
};
```

#### Polimorfismo Dinámico (Funciones Virtuales)
```cpp
class Figura {
public:
    virtual double calcularArea() = 0;
    virtual double calcularPerimetro() = 0;
    virtual ~Figura() {}
};

class Circulo : public Figura {
private:
    double radio;
    
public:
    Circulo(double r) : radio(r) {}
    double calcularArea() override { return 3.14159 * radio * radio; }
    double calcularPerimetro() override { return 2 * 3.14159 * radio; }
};

class Rectangulo : public Figura {
private:
    double base, altura;
    
public:
    Rectangulo(double b, double a) : base(b), altura(a) {}
    double calcularArea() override { return base * altura; }
    double calcularPerimetro() override { return 2 * (base + altura); }
};
```

### 📦 4. Abstracción
La abstracción permite representar características esenciales de un objeto sin mostrar detalles de implementación.

```cpp
// Interfaz abstracta
class IReproductor {
public:
    virtual void reproducir() = 0;
    virtual void pausar() = 0;
    virtual void detener() = 0;
    virtual void siguiente() = 0;
    virtual void anterior() = 0;
    virtual ~IReproductor() {}
};

// Implementación concreta
class ReproductorMP3 : public IReproductor {
private:
    string archivoActual;
    bool reproduciendo;
    
public:
    void reproducir() override {
        // Implementación específica para MP3
        cout << "Reproduciendo archivo MP3..." << endl;
        reproduciendo = true;
    }
    
    void pausar() override {
        // Implementación específica para MP3
        cout << "Pausando reproducción MP3..." << endl;
        reproduciendo = false;
    }
    
    // ... otras implementaciones
};
```

## 🏗️ Diseño de Clases

### 📝 Convenciones de Nomenclatura
```cpp
// Clases: PascalCase
class GestorUsuarios { }

// Métodos: camelCase
void agregarUsuario() { }
bool validarCredenciales() { }

// Variables: camelCase
string nombreUsuario;
int edadEstudiante;

// Constantes: UPPER_CASE
const int MAX_USUARIOS = 100;
const double PI = 3.14159;

// Miembros privados: prefijo m_
class MiClase {
private:
    string m_nombre;
    int m_edad;
};
```

### 🔧 Constructores y Destructores
```cpp
class MiClase {
private:
    string* m_datos;
    int m_tamanio;
    
public:
    // Constructor por defecto
    MiClase() : m_datos(nullptr), m_tamanio(0) {}
    
    // Constructor con parámetros
    MiClase(int tamanio) : m_tamanio(tamanio) {
        m_datos = new string[tamanio];
    }
    
    // Constructor de copia
    MiClase(const MiClase& otro) : m_tamanio(otro.m_tamanio) {
        m_datos = new string[m_tamanio];
        for (int i = 0; i < m_tamanio; i++) {
            m_datos[i] = otro.m_datos[i];
        }
    }
    
    // Operador de asignación
    MiClase& operator=(const MiClase& otro) {
        if (this != &otro) {
            delete[] m_datos;
            m_tamanio = otro.m_tamanio;
            m_datos = new string[m_tamanio];
            for (int i = 0; i < m_tamanio; i++) {
                m_datos[i] = otro.m_datos[i];
            }
        }
        return *this;
    }
    
    // Destructor
    ~MiClase() {
        delete[] m_datos;
    }
};
```

### 🔒 Modificadores de Acceso
```cpp
class Ejemplo {
public:
    // Accesible desde cualquier lugar
    void metodoPublico() { }
    
protected:
    // Accesible desde la clase y clases derivadas
    void metodoProtegido() { }
    
private:
    // Solo accesible desde la clase
    void metodoPrivado() { }
    
    // Datos privados
    string datoPrivado;
};
```

## 🔄 Herencia Avanzada

### 🏗️ Herencia Múltiple
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
    void volar() override {
        cout << "El pato está volando" << endl;
    }
    
    void nadar() override {
        cout << "El pato está nadando" << endl;
    }
};
```

### 🔍 Resolución de Ambiguidad
```cpp
class A {
public:
    void metodo() { cout << "A::metodo()" << endl; }
};

class B {
public:
    void metodo() { cout << "B::metodo()" << endl; }
};

class C : public A, public B {
public:
    void llamarMetodos() {
        A::metodo();  // Llama al método de A
        B::metodo();  // Llama al método de B
    }
};
```

## 🔀 Polimorfismo Avanzado

### 🎯 Funciones Virtuales
```cpp
class Base {
public:
    // Función virtual
    virtual void metodo() {
        cout << "Base::metodo()" << endl;
    }
    
    // Función virtual pura (clase abstracta)
    virtual void metodoPuro() = 0;
    
    // Destructor virtual (importante para herencia)
    virtual ~Base() {}
};

class Derivada : public Base {
public:
    // Override de función virtual
    void metodo() override {
        cout << "Derivada::metodo()" << endl;
    }
    
    // Implementación de función virtual pura
    void metodoPuro() override {
        cout << "Derivada::metodoPuro()" << endl;
    }
};
```

### 🔄 Polimorfismo Dinámico
```cpp
void procesarObjeto(Base* objeto) {
    objeto->metodo();  // Llamada polimórfica
}

int main() {
    Base* objetos[] = {
        new Derivada(),
        new OtraDerivada(),
        new Base()
    };
    
    for (Base* obj : objetos) {
        procesarObjeto(obj);
        delete obj;
    }
    
    return 0;
}
```

## 📦 Interfaces y Clases Abstractas

### 🎯 Clases Abstractas
```cpp
class Figura {
public:
    // Funciones virtuales puras
    virtual double calcularArea() = 0;
    virtual double calcularPerimetro() = 0;
    
    // Funciones virtuales con implementación por defecto
    virtual void mostrarInfo() {
        cout << "Área: " << calcularArea() << endl;
        cout << "Perímetro: " << calcularPerimetro() << endl;
    }
    
    virtual ~Figura() {}
};
```

### 🔌 Interfaces
```cpp
// En C++, las interfaces son clases abstractas con solo funciones virtuales puras
class IComparable {
public:
    virtual bool esIgual(const IComparable& otro) const = 0;
    virtual bool esMenor(const IComparable& otro) const = 0;
    virtual bool esMayor(const IComparable& otro) const = 0;
    virtual ~IComparable() {}
};

class Entero : public IComparable {
private:
    int valor;
    
public:
    Entero(int v) : valor(v) {}
    
    bool esIgual(const IComparable& otro) const override {
        const Entero& e = dynamic_cast<const Entero&>(otro);
        return valor == e.valor;
    }
    
    bool esMenor(const IComparable& otro) const override {
        const Entero& e = dynamic_cast<const Entero&>(otro);
        return valor < e.valor;
    }
    
    bool esMayor(const IComparable& otro) const override {
        const Entero& e = dynamic_cast<const Entero&>(otro);
        return valor > e.valor;
    }
};
```

## 🛡️ Manejo de Memoria

### 🔧 RAII (Resource Acquisition Is Initialization)
```cpp
class GestorRecurso {
private:
    int* recurso;
    
public:
    // Constructor adquiere el recurso
    GestorRecurso() : recurso(new int[100]) {
        cout << "Recurso adquirido" << endl;
    }
    
    // Destructor libera el recurso
    ~GestorRecurso() {
        delete[] recurso;
        cout << "Recurso liberado" << endl;
    }
    
    // Métodos para usar el recurso
    void usarRecurso() {
        cout << "Usando recurso..." << endl;
    }
};
```

### 🎯 Smart Pointers
```cpp
#include <memory>

class MiClase {
public:
    void metodo() { cout << "Método ejecutado" << endl; }
};

int main() {
    // unique_ptr - propiedad única
    unique_ptr<MiClase> ptr1(new MiClase());
    ptr1->metodo();
    
    // shared_ptr - propiedad compartida
    shared_ptr<MiClase> ptr2 = make_shared<MiClase>();
    shared_ptr<MiClase> ptr3 = ptr2;  // Referencia compartida
    
    // weak_ptr - referencia débil
    weak_ptr<MiClase> ptr4 = ptr2;
    
    return 0;
}
```

## 🔧 Patrones de Diseño Básicos

### 🏭 Factory Pattern
```cpp
class Producto {
public:
    virtual void operacion() = 0;
    virtual ~Producto() {}
};

class ProductoA : public Producto {
public:
    void operacion() override {
        cout << "Operación de Producto A" << endl;
    }
};

class ProductoB : public Producto {
public:
    void operacion() override {
        cout << "Operación de Producto B" << endl;
    }
};

class Fabrica {
public:
    static Producto* crearProducto(string tipo) {
        if (tipo == "A") {
            return new ProductoA();
        } else if (tipo == "B") {
            return new ProductoB();
        }
        return nullptr;
    }
};
```

### 👁️ Observer Pattern
```cpp
#include <vector>
#include <algorithm>

class Observador {
public:
    virtual void actualizar(string mensaje) = 0;
    virtual ~Observador() {}
};

class Sujeto {
private:
    vector<Observador*> observadores;
    
public:
    void agregarObservador(Observador* obs) {
        observadores.push_back(obs);
    }
    
    void removerObservador(Observador* obs) {
        observadores.erase(
            remove(observadores.begin(), observadores.end(), obs),
            observadores.end()
        );
    }
    
    void notificar(string mensaje) {
        for (Observador* obs : observadores) {
            obs->actualizar(mensaje);
        }
    }
};
```

## 📚 Mejores Prácticas

### ✅ Principios SOLID
1. **S** - Single Responsibility Principle
2. **O** - Open/Closed Principle
3. **L** - Liskov Substitution Principle
4. **I** - Interface Segregation Principle
5. **D** - Dependency Inversion Principle

### 🛡️ Reglas de Diseño
- Usar composición sobre herencia cuando sea posible
- Preferir interfaces sobre implementaciones
- Mantener clases pequeñas y enfocadas
- Usar const cuando sea apropiado
- Implementar destructores virtuales en clases base

### 🔧 Optimizaciones
- Usar referencias para evitar copias
- Implementar move semantics cuando sea apropiado
- Usar smart pointers para gestión automática de memoria
- Aplicar el principio de "const correctness"

## 📖 Recursos Adicionales

### 📚 Libros Recomendados
- **"Effective C++"** por Scott Meyers
- **"Modern C++ Design"** por Alexandrescu
- **"Design Patterns"** por Gang of Four
- **"Clean Code"** por Robert C. Martin

### 🌐 Enlaces Útiles
- [C++ Core Guidelines](https://isocpp.github.io/CppCoreGuidelines/)
- [C++ Reference](https://en.cppreference.com/)
- [C++ FAQ](https://isocpp.org/faq)

---

**Última Actualización**: Diciembre 2024 