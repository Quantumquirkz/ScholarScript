# 🧪 Lab 1: Arrays y Vectores - Estructuras Lineales Básicas

## 📋 Objetivos del Laboratorio
Este laboratorio introduce las estructuras de datos lineales básicas: arrays estáticos y vectores dinámicos. Los estudiantes aprenderán a implementar operaciones fundamentales, analizar complejidad temporal y espacial, y aplicar estas estructuras en problemas prácticos.

## 🎯 Resultados de Aprendizaje
Al finalizar este laboratorio, los estudiantes serán capaces de:
- Implementar operaciones básicas en arrays y vectores
- Analizar complejidad temporal y espacial de algoritmos
- Diseñar algoritmos eficientes para búsqueda y ordenamiento
- Aplicar estructuras lineales en problemas del mundo real
- Comparar diferentes implementaciones y optimizaciones

## 📚 Marco Teórico

### 📊 Arrays Estáticos

#### Definición y Características
```cpp
// Declaración de arrays estáticos
int numeros[10];                    // Array de 10 enteros
double precios[5] = {10.5, 20.0, 15.75, 8.99, 12.50};
char letras[] = {'A', 'B', 'C', 'D', 'E'};

// Acceso a elementos
int primerElemento = numeros[0];    // Índice 0
int ultimoElemento = numeros[9];    // Índice 9
```

#### Operaciones Básicas
```cpp
class ArrayEstatico {
private:
    int datos[100];
    int tamanio;
    int capacidad;
    
public:
    ArrayEstatico() : tamanio(0), capacidad(100) {}
    
    // Inserción al final - O(1)
    bool insertar(int valor) {
        if (tamanio < capacidad) {
            datos[tamanio] = valor;
            tamanio++;
            return true;
        }
        return false;
    }
    
    // Búsqueda lineal - O(n)
    int buscar(int valor) {
        for (int i = 0; i < tamanio; i++) {
            if (datos[i] == valor) {
                return i;
            }
        }
        return -1; // No encontrado
    }
    
    // Eliminación - O(n)
    bool eliminar(int valor) {
        int posicion = buscar(valor);
        if (posicion != -1) {
            for (int i = posicion; i < tamanio - 1; i++) {
                datos[i] = datos[i + 1];
            }
            tamanio--;
            return true;
        }
        return false;
    }
};
```

### 📈 Vectores Dinámicos

#### Implementación Básica
```cpp
template<typename T>
class Vector {
private:
    T* datos;
    int tamanio;
    int capacidad;
    
public:
    Vector() : datos(nullptr), tamanio(0), capacidad(0) {}
    
    ~Vector() {
        delete[] datos;
    }
    
    // Inserción con redimensionamiento automático
    void push_back(const T& valor) {
        if (tamanio >= capacidad) {
            redimensionar(capacidad == 0 ? 1 : capacidad * 2);
        }
        datos[tamanio] = valor;
        tamanio++;
    }
    
    // Acceso con verificación de límites
    T& at(int indice) {
        if (indice < 0 || indice >= tamanio) {
            throw std::out_of_range("Índice fuera de rango");
        }
        return datos[indice];
    }
    
    // Obtener tamaño actual
    int size() const { return tamanio; }
    
    // Verificar si está vacío
    bool empty() const { return tamanio == 0; }
    
private:
    void redimensionar(int nuevaCapacidad) {
        T* nuevosDatos = new T[nuevaCapacidad];
        for (int i = 0; i < tamanio; i++) {
            nuevosDatos[i] = datos[i];
        }
        delete[] datos;
        datos = nuevosDatos;
        capacidad = nuevaCapacidad;
    }
};
```

### 🔍 Algoritmos de Búsqueda

#### Búsqueda Lineal
```cpp
// Complejidad: O(n)
int busquedaLineal(const vector<int>& arr, int valor) {
    for (int i = 0; i < arr.size(); i++) {
        if (arr[i] == valor) {
            return i;
        }
    }
    return -1;
}
```

#### Búsqueda Binaria
```cpp
// Complejidad: O(log n) - Requiere array ordenado
int busquedaBinaria(const vector<int>& arr, int valor) {
    int izquierda = 0;
    int derecha = arr.size() - 1;
    
    while (izquierda <= derecha) {
        int medio = izquierda + (derecha - izquierda) / 2;
        
        if (arr[medio] == valor) {
            return medio;
        }
        
        if (arr[medio] < valor) {
            izquierda = medio + 1;
        } else {
            derecha = medio - 1;
        }
    }
    
    return -1;
}
```

### 🔄 Algoritmos de Ordenamiento

#### Ordenamiento por Burbuja
```cpp
// Complejidad: O(n²)
void ordenamientoBurbuja(vector<int>& arr) {
    int n = arr.size();
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                swap(arr[j], arr[j + 1]);
            }
        }
    }
}
```

#### Ordenamiento por Selección
```cpp
// Complejidad: O(n²)
void ordenamientoSeleccion(vector<int>& arr) {
    int n = arr.size();
    for (int i = 0; i < n - 1; i++) {
        int minIndex = i;
        for (int j = i + 1; j < n; j++) {
            if (arr[j] < arr[minIndex]) {
                minIndex = j;
            }
        }
        swap(arr[i], arr[minIndex]);
    }
}
```

## 🛠️ Ejercicios del Laboratorio

### 📝 Ejercicio 1: Implementación de Vector Dinámico
**Objetivo**: Implementar una clase Vector con funcionalidad completa.

**Requisitos**:
- Constructor, destructor y constructor de copia
- Métodos: push_back, pop_back, insert, erase
- Operador de acceso [] con verificación de límites
- Método para obtener tamaño y capacidad
- Redimensionamiento automático eficiente

**Código Base**:
```cpp
template<typename T>
class MiVector {
private:
    T* datos;
    int tamanio;
    int capacidad;
    
public:
    // Constructores
    MiVector() : datos(nullptr), tamanio(0), capacidad(0) {}
    
    MiVector(int capacidadInicial) : tamanio(0), capacidad(capacidadInicial) {
        datos = new T[capacidad];
    }
    
    // Destructor
    ~MiVector() {
        delete[] datos;
    }
    
    // Métodos básicos
    void push_back(const T& valor);
    void pop_back();
    T& operator[](int indice);
    int size() const { return tamanio; }
    bool empty() const { return tamanio == 0; }
    
private:
    void redimensionar(int nuevaCapacidad);
};
```

### 📝 Ejercicio 2: Sistema de Gestión de Inventario
**Objetivo**: Crear un sistema de inventario usando arrays y vectores.

**Requisitos**:
- Clase Producto con atributos: código, nombre, precio, stock
- Array estático para productos de alta rotación
- Vector dinámico para productos especiales
- Métodos para agregar, buscar y actualizar productos
- Cálculo de valor total del inventario

### 📝 Ejercicio 3: Análisis de Datos Estadísticos
**Objetivo**: Implementar funciones para análisis estadístico de arrays.

**Requisitos**:
- Cálculo de media, mediana y moda
- Encontrar valores mínimo y máximo
- Calcular desviación estándar
- Generar histograma de frecuencias
- Ordenamiento y búsqueda eficiente

### 📝 Ejercicio 4: Sistema de Calificaciones
**Objetivo**: Crear un sistema de gestión de calificaciones de estudiantes.

**Requisitos**:
- Almacenar calificaciones en arrays
- Calcular promedio por estudiante
- Encontrar estudiantes con mejor/promedio
- Generar reportes ordenados
- Búsqueda por rango de calificaciones

## 🧪 Ejercicios Avanzados

### 📝 Ejercicio 5: Implementación de Matriz Esparsa
**Objetivo**: Implementar una matriz esparsa usando vectores.

**Requisitos**:
- Almacenar solo elementos no cero
- Operaciones de suma y multiplicación
- Compresión y descompresión de datos
- Análisis de eficiencia de memoria

### 📝 Ejercicio 6: Sistema de Caché LRU
**Objetivo**: Implementar un sistema de caché LRU (Least Recently Used).

**Requisitos**:
- Capacidad limitada de elementos
- Eliminación automática del elemento menos usado
- Acceso O(1) a elementos
- Estadísticas de hit/miss ratio

## 📊 Análisis de Complejidad

### ⏱️ Complejidad Temporal
| Operación | Array Estático | Vector Dinámico |
|-----------|----------------|-----------------|
| Acceso | O(1) | O(1) |
| Inserción al final | O(1) | O(1) amortizado |
| Inserción al inicio | O(n) | O(n) |
| Búsqueda lineal | O(n) | O(n) |
| Búsqueda binaria | O(log n) | O(log n) |
| Eliminación | O(n) | O(n) |

### 💾 Complejidad Espacial
- **Array Estático**: O(n) - tamaño fijo
- **Vector Dinámico**: O(n) - tamaño variable
- **Overhead**: Vector tiene overhead adicional para gestión de memoria

## 📊 Criterios de Evaluación

### 🎯 Implementación (30%)
- Código funcional y bien estructurado
- Uso apropiado de arrays y vectores
- Manejo correcto de memoria
- Documentación del código

### 🔧 Eficiencia (30%)
- Análisis correcto de complejidad
- Implementación de algoritmos optimizados
- Uso eficiente de estructuras de datos
- Manejo de casos límite

### 📝 Funcionalidad (25%)
- Todos los métodos funcionan correctamente
- Manejo adecuado de errores
- Interfaz de usuario clara
- Casos de prueba completos

### 🧪 Análisis (15%)
- Comprensión de complejidad temporal/espacial
- Comparación de diferentes implementaciones
- Optimización de algoritmos
- Documentación de análisis

## 📚 Recursos Adicionales

### 📖 Referencias Bibliográficas
- **"Introduction to Algorithms"** por Cormen, Leiserson, Rivest, Stein - Capítulos 1-3
- **"Data Structures and Algorithms in C++"** por Adam Drozdek - Capítulos 1-4
- **"The Art of Computer Programming"** por Donald Knuth - Volumen 1

### 🌐 Recursos en Línea
- [C++ Vector Reference](https://en.cppreference.com/w/cpp/container/vector)
- [Array Data Structure](https://en.wikipedia.org/wiki/Array_data_structure)
- [Big O Notation](https://www.bigocheatsheet.com/)

### 🎥 Videos Recomendados
- **"Data Structures: Arrays"** - mycodeschool (YouTube)
- **"Big O Notation"** - Back To Back SWE (YouTube)
- **"C++ STL Vector"** - The Cherno (YouTube)

### 🛠️ Herramientas de Desarrollo
- **IDE**: Visual Studio Code, CLion, Dev-C++
- **Profiling**: Valgrind, gprof
- **Visualización**: Python matplotlib para gráficos

## 🚀 Compilación y Ejecución

### 🔧 Comandos de Compilación
```bash
# Compilación básica
g++ -std=c++17 -o programa programa.cpp

# Con optimización
g++ -O2 -std=c++17 -o programa programa.cpp

# Con información de depuración
g++ -g -std=c++17 -o programa programa.cpp

# Con análisis de memoria
g++ -g -std=c++17 -o programa programa.cpp
valgrind --leak-check=full ./programa
```

### 📝 Mejores Prácticas
- Usar STL vector cuando sea posible
- Preferir referencias sobre copias
- Usar const cuando sea apropiado
- Documentar complejidad de algoritmos
- Implementar manejo de errores robusto

---

**Duración**: 3 horas  
**Dificultad**: Intermedio  
**Prerrequisitos**: Conocimientos básicos de C++  
**Última Actualización**: Diciembre 2024 