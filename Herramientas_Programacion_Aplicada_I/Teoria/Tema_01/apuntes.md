# 📚 Tema 1: Fundamentos de Programación en C++

## 📋 Objetivos del Tema
- Comprender los conceptos básicos de programación
- Dominar la sintaxis fundamental de C++
- Entender el modelo de memoria y tipos de datos
- Aplicar operadores y expresiones correctamente

## 🎯 Conceptos Clave

### 🔧 Estructura de un Programa C++
```cpp
#include <iostream>  // Directiva de preprocesador
using namespace std; // Declaración de namespace

int main() {         // Función principal
    // Código del programa
    cout << "Hola Mundo!" << endl;
    return 0;        // Valor de retorno
}
```

### 📊 Tipos de Datos Fundamentales

#### Tipos Enteros
```cpp
int numero = 42;           // 4 bytes, -2^31 a 2^31-1
short pequeno = 123;       // 2 bytes, -2^15 a 2^15-1
long grande = 1234567890L; // 4+ bytes
long long enorme = 1234567890123456789LL; // 8 bytes
```

#### Tipos de Punto Flotante
```cpp
float decimal = 3.14f;     // 4 bytes, 7 dígitos de precisión
double preciso = 3.14159265359; // 8 bytes, 15 dígitos
long double muy_preciso = 3.14159265359L; // 10+ bytes
```

#### Tipos de Carácter
```cpp
char letra = 'A';          // 1 byte, -128 a 127
unsigned char byte = 255;  // 1 byte, 0 a 255
wchar_t ancho = L'Ω';      // 2+ bytes, caracteres Unicode
```

#### Tipo Booleano
```cpp
bool verdadero = true;     // true o false
bool falso = false;
```

### ⚡ Operadores

#### Operadores Aritméticos
```cpp
int a = 10, b = 3;
int suma = a + b;      // 13
int resta = a - b;     // 7
int producto = a * b;  // 30
int cociente = a / b;  // 3
int residuo = a % b;   // 1
```

#### Operadores de Asignación
```cpp
int x = 5;
x += 3;   // x = x + 3
x -= 2;   // x = x - 2
x *= 4;   // x = x * 4
x /= 2;   // x = x / 2
x %= 3;   // x = x % 3
```

#### Operadores de Incremento/Decremento
```cpp
int n = 5;
int pre_incremento = ++n;  // n = 6, pre_incremento = 6
int post_incremento = n++; // post_incremento = 6, n = 7
int pre_decremento = --n;  // n = 6, pre_decremento = 6
int post_decremento = n--; // post_decremento = 6, n = 5
```

### 🔄 Entrada y Salida

#### Salida Estándar
```cpp
#include <iostream>
using namespace std;

cout << "Texto simple" << endl;
cout << "Número: " << 42 << endl;
cout << "Decimal: " << 3.14 << endl;
cout << "Carácter: " << 'A' << endl;
```

#### Entrada Estándar
```cpp
int numero;
string texto;
char caracter;

cout << "Ingrese un número: ";
cin >> numero;

cout << "Ingrese texto: ";
cin >> texto;  // Lee hasta el primer espacio

cout << "Ingrese un carácter: ";
cin >> caracter;
```

#### Formateo de Salida
```cpp
#include <iomanip>

cout << fixed << setprecision(2);  // Decimales fijos
cout << setw(10) << "Texto";       // Ancho de campo
cout << left << "Izquierda";       // Alineación izquierda
cout << right << "Derecha";        // Alineación derecha
```

### 📝 Variables y Constantes

#### Declaración de Variables
```cpp
// Declaración simple
int edad;
string nombre;
double altura;

// Declaración con inicialización
int edad = 25;
string nombre = "Juan";
double altura = 1.75;

// Declaración múltiple
int x = 1, y = 2, z = 3;
```

#### Constantes
```cpp
// Constantes literales
const double PI = 3.14159265359;
const int MAX_ESTUDIANTES = 100;
const string UNIVERSIDAD = "UTP";

// Constantes con #define (preprocesador)
#define GRAVEDAD 9.81
#define VERSION "1.0"
```

### 🧮 Expresiones y Precedencia

#### Precedencia de Operadores
```cpp
// Orden de precedencia (de mayor a menor)
// 1. () [] -> .
// 2. ++ -- (postfix)
// 3. ++ -- (prefix) + - ! ~ (type) * & sizeof
// 4. * / %
// 5. + -
// 6. << >>
// 7. < <= > >=
// 8. == !=
// 9. &
// 10. ^
// 11. |
// 12. &&
// 13. ||
// 14. ?:
// 15. = += -= *= /= %= <<= >>= &= ^= |=
// 16. ,

int resultado = 2 + 3 * 4;  // resultado = 14, no 20
int resultado2 = (2 + 3) * 4;  // resultado2 = 20
```

### 🔍 Conversión de Tipos

#### Conversión Implícita
```cpp
int entero = 5;
double decimal = entero;  // Conversión automática

double pi = 3.14159;
int aproximacion = pi;    // Truncamiento a 3
```

#### Conversión Explícita (Cast)
```cpp
double decimal = 3.14159;
int entero = static_cast<int>(decimal);  // 3

int numero = 65;
char letra = static_cast<char>(numero);  // 'A'
```

### 🛡️ Buenas Prácticas

#### Nomenclatura
```cpp
// Variables: camelCase
int edadEstudiante;
string nombreCompleto;
double promedioNotas;

// Constantes: UPPER_CASE
const int MAX_ESTUDIANTES = 100;
const double PI = 3.14159;

// Funciones: camelCase
void calcularPromedio();
int obtenerEdad();
```

#### Inicialización
```cpp
// Siempre inicializar variables
int contador = 0;
string nombre = "";
double promedio = 0.0;

// Evitar variables no inicializadas
int x;  // Mal: valor indefinido
int y = 0;  // Bien: valor definido
```

#### Comentarios
```cpp
// Comentario de una línea

/*
 * Comentario de múltiples líneas
 * para explicar código complejo
 */

// Comentario de documentación
/**
 * @brief Calcula el área de un círculo
 * @param radio El radio del círculo
 * @return El área calculada
 */
double calcularAreaCirculo(double radio);
```

## 📚 Ejercicios de Práctica

### 🔢 Ejercicio 1: Calculadora Básica
Escribir un programa que:
- Solicite dos números al usuario
- Realice las operaciones básicas (+, -, *, /)
- Muestre los resultados formateados

### 📊 Ejercicio 2: Conversión de Temperatura
Implementar un programa que:
- Convierta entre Celsius y Fahrenheit
- Use constantes para las fórmulas
- Valide la entrada del usuario

### 🎯 Ejercicio 3: Cálculo de Promedio
Crear un programa que:
- Solicite 3 calificaciones
- Calcule el promedio
- Determine si aprueba (>= 60)

## 📖 Recursos Adicionales

### 📚 Lectura Recomendada
- **"C++ Primer"** - Capítulos 1-3
- **"The C++ Programming Language"** - Capítulo 2
- **Documentación de C++**: cppreference.com

### 🌐 Enlaces Útiles
- [C++ Tutorial](https://www.cplusplus.com/doc/tutorial/)
- [C++ Reference](https://en.cppreference.com/)
- [GCC Documentation](https://gcc.gnu.org/onlinedocs/)

---

**Duración del Tema**: 2 semanas  
**Evaluación**: Quiz y ejercicios prácticos  
**Prerrequisitos**: Ninguno  
**Última Actualización**: Diciembre 2024 