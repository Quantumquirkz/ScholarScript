# 📊 Data_Structures - Estructuras de Datos

## 📋 Descripción de la Materia
Estructuras de Datos es una materia fundamental que enseña cómo organizar, almacenar y manipular datos de manera eficiente. Esta materia proporciona las herramientas necesarias para diseñar algoritmos optimizados y resolver problemas computacionales complejos.

## 🎯 Objetivos de Aprendizaje
- Comprender los conceptos fundamentales de estructuras de datos
- Implementar estructuras de datos básicas y avanzadas
- Analizar la complejidad temporal y espacial de algoritmos
- Aplicar estructuras de datos apropiadas para diferentes problemas
- Desarrollar habilidades de optimización y eficiencia algorítmica

## 📚 Contenido del Curso

### 🧪 Laboratorios Prácticos
- **LAB_1**: Implementaciones básicas de estructuras de datos
  - Arrays y listas enlazadas
  - Pilas y colas
  - Estructuras de datos lineales

### 🏗️ Implementaciones Completas
- **Listas Enlazadas**: Simples, dobles y circulares
- **Pilas y Colas**: Implementaciones con arrays y listas
- **Árboles**: Binarios, AVL, B-trees
- **Grafos**: Matrices de adyacencia y listas
- **Tablas Hash**: Implementación y resolución de colisiones
- **Heaps**: Montículos binarios y de Fibonacci

### 📖 Documentación Teórica
- **Análisis de Complejidad**: Big O, Big Theta, Big Omega
- **Algoritmos de Ordenamiento**: Comparación y análisis
- **Algoritmos de Búsqueda**: Lineal, binaria, en árboles
- **Patrones de Diseño**: Aplicados a estructuras de datos

## 🛠️ Tecnologías Utilizadas
- **Lenguajes**: C++, Java, Python
- **Herramientas**: Valgrind (análisis de memoria), gprof (profiling)
- **IDE**: Visual Studio Code, CLion
- **Control de versiones**: Git

## 📖 Guías de Implementación

### 🔧 Compilación C++
```bash
# Compilar con debugging
g++ -g -o programa programa.cpp

# Compilar con optimizaciones
g++ -O2 -o programa programa.cpp

# Compilar con análisis de memoria
g++ -g -o programa programa.cpp
valgrind --leak-check=full ./programa
```

### 🚀 Compilación Java
```bash
# Compilar
javac *.java

# Ejecutar con profiling
java -Xprof MainClass
```

### 🐍 Ejecución Python
```bash
# Ejecutar con profiling
python -m cProfile programa.py

# Ejecutar con análisis de memoria
python -m memory_profiler programa.py
```

## 📝 Convenciones de Código

### 📋 Estándares de Nomenclatura
- **Clases**: PascalCase (ej: `LinkedList`)
- **Métodos**: camelCase (ej: `insertNode`)
- **Variables**: camelCase (ej: `headNode`)
- **Constantes**: UPPER_SNAKE_CASE (ej: `MAX_SIZE`)
- **Archivos**: snake_case (ej: `linked_list.cpp`)

### 📁 Estructura de Archivos
Cada implementación debe contener:
- `README.md`: Descripción de la estructura
- `*.h` o `*.hpp`: Archivos de cabecera (C++)
- `*.cpp` o `*.java`: Implementación
- `test_*.cpp`: Tests unitarios
- `benchmark_*.cpp`: Pruebas de rendimiento
- `docs/`: Documentación técnica

## 🎯 Criterios de Evaluación
- **Correctitud**: La implementación debe funcionar correctamente
- **Eficiencia**: Complejidad temporal y espacial óptima
- **Robustez**: Manejo de casos edge y errores
- **Documentación**: Comentarios claros y documentación técnica
- **Testing**: Cobertura completa de pruebas

## 📚 Estructuras de Datos Principales

### 📋 Estructuras Lineales
- **Arrays**: Acceso directo, tamaño fijo
- **Listas Enlazadas**: Inserción/eliminación eficiente
- **Pilas**: LIFO (Last In, First Out)
- **Colas**: FIFO (First In, First Out)
- **Deques**: Doble cola

### 🌳 Estructuras Jerárquicas
- **Árboles Binarios**: Búsqueda, inserción, eliminación
- **Árboles AVL**: Auto-balanceados
- **Árboles B**: Para bases de datos
- **Heaps**: Montículos para prioridades

### 🕸️ Estructuras de Grafos
- **Matrices de Adyacencia**: Grafos densos
- **Listas de Adyacencia**: Grafos dispersos
- **Grafos Dirigidos**: Con dirección
- **Grafos No Dirigidos**: Sin dirección

### 🔍 Estructuras de Búsqueda
- **Tablas Hash**: Acceso O(1) promedio
- **Árboles de Búsqueda**: Ordenados
- **Tries**: Para strings
- **Skip Lists**: Listas con múltiples niveles

## 📊 Análisis de Complejidad

### ⏱️ Complejidad Temporal
- **O(1)**: Constante
- **O(log n)**: Logarítmica
- **O(n)**: Lineal
- **O(n log n)**: Linealítmica
- **O(n²)**: Cuadrática
- **O(2ⁿ)**: Exponencial

### 💾 Complejidad Espacial
- **Auxiliar**: Espacio adicional requerido
- **In-place**: Sin espacio adicional
- **Recursivo**: Stack de llamadas

## 📚 Recursos Adicionales
- [GeeksforGeeks](https://www.geeksforgeeks.org/data-structures/)
- [LeetCode](https://leetcode.com/) - Práctica de problemas
- [HackerRank](https://www.hackerrank.com/) - Ejercicios prácticos
- [CLRS Book](https://en.wikipedia.org/wiki/Introduction_to_Algorithms) - Referencia teórica

## 🐛 Herramientas de Debugging
- **Valgrind**: Análisis de memoria (C++)
- **gprof**: Profiling de rendimiento
- **GDB**: Debugger avanzado
- **AddressSanitizer**: Detección de errores de memoria

## 📞 Contacto
Para dudas sobre esta materia, contactar al profesor o revisar la documentación específica de cada implementación.

---
*Última actualización: Diciembre 2024* 