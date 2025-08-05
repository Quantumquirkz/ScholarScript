# 📚 Tema 2: Estructuras de Control en C++

## 📋 Objetivos del Tema
- Comprender y aplicar estructuras de control condicionales
- Dominar los diferentes tipos de bucles y su uso apropiado
- Implementar lógica de decisión compleja
- Analizar el flujo de ejecución de programas
- Aplicar estructuras de control en problemas prácticos

## 🎯 Conceptos Clave

### 🔀 Estructuras Condicionales

#### If Statement
```cpp
// Estructura básica if
if (condicion) {
    // Código que se ejecuta si la condición es verdadera
    cout << "La condición es verdadera" << endl;
}

// Ejemplo práctico
int edad = 18;
if (edad >= 18) {
    cout << "Eres mayor de edad" << endl;
}
```

#### If-Else Statement
```cpp
// Estructura if-else
if (condicion) {
    // Código si la condición es verdadera
    cout << "Condición verdadera" << endl;
} else {
    // Código si la condición es falsa
    cout << "Condición falsa" << endl;
}

// Ejemplo práctico
int numero = 10;
if (numero % 2 == 0) {
    cout << "El número es par" << endl;
} else {
    cout << "El número es impar" << endl;
}
```

#### If-Else If-Else Chain
```cpp
// Cadena de condiciones
if (condicion1) {
    // Código para condición1
} else if (condicion2) {
    // Código para condición2
} else if (condicion3) {
    // Código para condición3
} else {
    // Código por defecto
}

// Ejemplo: Clasificación de calificaciones
int calificacion = 85;
if (calificacion >= 90) {
    cout << "A - Excelente" << endl;
} else if (calificacion >= 80) {
    cout << "B - Bueno" << endl;
} else if (calificacion >= 70) {
    cout << "C - Satisfactorio" << endl;
} else if (calificacion >= 60) {
    cout << "D - Necesita mejorar" << endl;
} else {
    cout << "F - Reprobado" << endl;
}
```

#### Switch Statement
```cpp
// Estructura switch
switch (expresion) {
    case valor1:
        // Código para valor1
        break;
    case valor2:
        // Código para valor2
        break;
    case valor3:
        // Código para valor3
        break;
    default:
        // Código por defecto
        break;
}

// Ejemplo: Menú de opciones
int opcion = 2;
switch (opcion) {
    case 1:
        cout << "Opción 1 seleccionada" << endl;
        break;
    case 2:
        cout << "Opción 2 seleccionada" << endl;
        break;
    case 3:
        cout << "Opción 3 seleccionada" << endl;
        break;
    default:
        cout << "Opción inválida" << endl;
        break;
}
```

### 🔄 Estructuras de Bucle

#### For Loop
```cpp
// Estructura básica del for
for (inicializacion; condicion; incremento) {
    // Código del bucle
}

// Ejemplo: Contar del 1 al 10
for (int i = 1; i <= 10; i++) {
    cout << i << " ";
}
cout << endl;

// Ejemplo: Sumar elementos de un array
int numeros[] = {1, 2, 3, 4, 5};
int suma = 0;
for (int i = 0; i < 5; i++) {
    suma += numeros[i];
}
cout << "Suma: " << suma << endl;

// Ejemplo: Bucle descendente
for (int i = 10; i >= 1; i--) {
    cout << i << " ";
}
cout << endl;
```

#### While Loop
```cpp
// Estructura básica del while
while (condicion) {
    // Código del bucle
}

// Ejemplo: Contar hasta que el usuario ingrese 0
int numero;
cout << "Ingrese números (0 para salir): ";
cin >> numero;
while (numero != 0) {
    cout << "Ingresaste: " << numero << endl;
    cout << "Ingrese otro número (0 para salir): ";
    cin >> numero;
}

// Ejemplo: Calcular factorial
int n = 5;
int factorial = 1;
int i = 1;
while (i <= n) {
    factorial *= i;
    i++;
}
cout << "Factorial de " << n << " es: " << factorial << endl;
```

#### Do-While Loop
```cpp
// Estructura básica del do-while
do {
    // Código del bucle
} while (condicion);

// Ejemplo: Menú repetitivo
int opcion;
do {
    cout << "\n=== Menú ===" << endl;
    cout << "1. Opción 1" << endl;
    cout << "2. Opción 2" << endl;
    cout << "3. Opción 3" << endl;
    cout << "0. Salir" << endl;
    cout << "Seleccione una opción: ";
    cin >> opcion;
    
    switch (opcion) {
        case 1:
            cout << "Ejecutando opción 1..." << endl;
            break;
        case 2:
            cout << "Ejecutando opción 2..." << endl;
            break;
        case 3:
            cout << "Ejecutando opción 3..." << endl;
            break;
        case 0:
            cout << "Saliendo..." << endl;
            break;
        default:
            cout << "Opción inválida" << endl;
    }
} while (opcion != 0);
```

### ⚡ Operadores Lógicos

#### Operadores de Comparación
```cpp
// Operadores de comparación
int a = 5, b = 10;

bool igual = (a == b);        // false
bool diferente = (a != b);    // true
bool menor = (a < b);         // true
bool mayor = (a > b);         // false
bool menorIgual = (a <= b);   // true
bool mayorIgual = (a >= b);   // false
```

#### Operadores Lógicos
```cpp
// Operadores lógicos
bool p = true, q = false;

bool and_result = p && q;     // false (AND lógico)
bool or_result = p || q;      // true (OR lógico)
bool not_result = !p;         // false (NOT lógico)

// Ejemplo: Validación de entrada
int edad = 25;
bool tieneLicencia = true;
bool puedeConducir = (edad >= 18) && tieneLicencia;
```

#### Operadores de Asignación Compuesta
```cpp
int x = 10;

x += 5;   // x = x + 5 (15)
x -= 3;   // x = x - 3 (12)
x *= 2;   // x = x * 2 (24)
x /= 4;   // x = x / 4 (6)
x %= 4;   // x = x % 4 (2)
```

### 🛑 Control de Flujo

#### Break Statement
```cpp
// Break en bucles
for (int i = 1; i <= 10; i++) {
    if (i == 5) {
        break;  // Sale del bucle cuando i == 5
    }
    cout << i << " ";
}
cout << endl;  // Imprime: 1 2 3 4

// Break en switch
int opcion = 2;
switch (opcion) {
    case 1:
        cout << "Uno" << endl;
        break;  // Necesario para evitar fall-through
    case 2:
        cout << "Dos" << endl;
        break;
    default:
        cout << "Otro" << endl;
}
```

#### Continue Statement
```cpp
// Continue en bucles
for (int i = 1; i <= 10; i++) {
    if (i % 2 == 0) {
        continue;  // Salta números pares
    }
    cout << i << " ";
}
cout << endl;  // Imprime: 1 3 5 7 9
```

#### Nested Loops
```cpp
// Bucles anidados
for (int i = 1; i <= 3; i++) {
    for (int j = 1; j <= 3; j++) {
        cout << "(" << i << "," << j << ") ";
    }
    cout << endl;
}
// Imprime:
// (1,1) (1,2) (1,3)
// (2,1) (2,2) (2,3)
// (3,1) (3,2) (3,3)
```

## 📚 Ejercicios de Práctica

### 🔢 Ejercicio 1: Calculadora de Números Primos
Escribir un programa que:
- Solicite un número al usuario
- Determine si es primo usando un bucle
- Muestre todos los divisores si no es primo

### 📊 Ejercicio 2: Generador de Tablas de Multiplicación
Implementar un programa que:
- Genere tablas de multiplicación del 1 al 10
- Use bucles anidados para formatear la salida
- Permita al usuario elegir qué tabla mostrar

### 🎯 Ejercicio 3: Sistema de Validación de Contraseñas
Crear un programa que:
- Valide contraseñas con múltiples criterios
- Use operadores lógicos para verificar condiciones
- Proporcione feedback específico sobre errores

## 📖 Recursos Adicionales

### 📚 Referencias Bibliográficas
- **"C++ Primer"** por Stanley Lippman - Capítulos 5-6
- **"The C++ Programming Language"** por Bjarne Stroustrup - Capítulo 4
- **"C++ How to Program"** por Deitel & Deitel - Capítulos 4-5

### 🌐 Recursos en Línea
- [C++ Control Structures](https://www.cplusplus.com/doc/tutorial/control/)
- [C++ Loops](https://www.cplusplus.com/doc/tutorial/control/#loops)
- [C++ Switch Statement](https://en.cppreference.com/w/cpp/language/switch)

### 🎥 Videos Recomendados
- **"C++ Control Structures Tutorial"** - The Cherno (YouTube)
- **"C++ Loops Explained"** - Programming with Mosh
- **"C++ Switch Statement"** - CodeBeauty

### 🛠️ Herramientas de Desarrollo
- **IDE**: Visual Studio Code, CLion, Dev-C++
- **Debugger**: GDB para seguimiento de flujo
- **Visualización**: Diagramas de flujo para algoritmos

## 🚀 Compilación y Ejecución

### 🔧 Comandos de Compilación
```bash
# Compilación básica
g++ -o programa programa.cpp

# Con advertencias
g++ -Wall -Wextra -o programa programa.cpp

# Con optimización
g++ -O2 -o programa programa.cpp
```

### 📝 Mejores Prácticas
- Usar llaves {} siempre, incluso para una sola línea
- Mantener bucles simples y legibles
- Evitar bucles infinitos
- Usar break y continue apropiadamente
- Documentar lógica compleja

---

**Duración del Tema**: 2 semanas  
**Evaluación**: Quiz y ejercicios prácticos  
**Prerrequisitos**: Tema 1 (Fundamentos)  
**Última Actualización**: Diciembre 2024 