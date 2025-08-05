# 📝 Tarea 1: Fundamentos de Programación en C++

## 📋 Descripción de la Tarea
Esta tarea evalúa la comprensión de los conceptos fundamentales de programación en C++, incluyendo variables, tipos de datos, operadores, entrada/salida y estructuras básicas de control.

## 🎯 Objetivos de Aprendizaje
- Demostrar dominio de la sintaxis básica de C++
- Aplicar conceptos de tipos de datos y variables
- Implementar operaciones aritméticas y lógicas
- Utilizar estructuras de entrada y salida
- Resolver problemas prácticos usando programación

## 📚 Temas Evaluados
- Variables y tipos de datos
- Operadores aritméticos y lógicos
- Entrada y salida estándar
- Estructuras de control básicas
- Formateo de salida

## 🛠️ Problemas a Resolver

### 📝 Problema 1: Calculadora de Interés Compuesto (25 puntos)
**Descripción**: Crear un programa que calcule el interés compuesto para una inversión.

**Requisitos**:
- Solicitar al usuario: capital inicial, tasa de interés anual, tiempo en años
- Calcular el monto final usando la fórmula: A = P(1 + r/n)^(nt)
- Mostrar resultados formateados con 2 decimales
- Validar que todos los valores sean positivos

**Entrada de Ejemplo**:
```
Capital inicial: 1000
Tasa de interés anual (%): 5.5
Tiempo (años): 3
```

**Salida Esperada**:
```
=== Calculadora de Interés Compuesto ===
Capital inicial: $1000.00
Tasa de interés: 5.50%
Tiempo: 3 años
Monto final: $1174.24
Interés ganado: $174.24
```

### 📝 Problema 2: Conversor de Unidades (25 puntos)
**Descripción**: Implementar un conversor que permita convertir entre diferentes unidades de medida.

**Requisitos**:
- Menú con opciones: longitud, peso, temperatura
- Para longitud: metros, pies, pulgadas, centímetros
- Para peso: kilogramos, libras, onzas, gramos
- Para temperatura: Celsius, Fahrenheit, Kelvin
- Validar entrada del usuario

**Ejemplo de Interfaz**:
```
=== Conversor de Unidades ===
1. Longitud
2. Peso
3. Temperatura
4. Salir
Seleccione opción: 1

=== Conversor de Longitud ===
1. Metros a Pies
2. Pies a Metros
3. Centímetros a Pulgadas
4. Pulgadas a Centímetros
Seleccione conversión: 1
Ingrese valor en metros: 5
Resultado: 16.40 pies
```

### 📝 Problema 3: Analizador de Números (25 puntos)
**Descripción**: Crear un programa que analice propiedades matemáticas de un número.

**Requisitos**:
- Solicitar un número entero positivo
- Determinar si es par o impar
- Verificar si es primo
- Calcular la suma de sus dígitos
- Encontrar sus divisores
- Calcular su factorial (si es ≤ 12)

**Salida de Ejemplo**:
```
=== Analizador de Números ===
Ingrese un número: 28

Análisis del número 28:
- Es par
- No es primo
- Suma de dígitos: 10
- Divisores: 1, 2, 4, 7, 14, 28
- Número de divisores: 6
```

### 📝 Problema 4: Generador de Patrones (25 puntos)
**Descripción**: Implementar un generador de patrones geométricos usando caracteres.

**Requisitos**:
- Menú con diferentes patrones: triángulo, rectángulo, diamante
- Solicitar dimensiones al usuario
- Usar caracteres ASCII para crear patrones
- Validar dimensiones (máximo 20)

**Ejemplo de Patrones**:
```
Triángulo (altura 5):
    *
   ***
  *****
 *******
*********

Rectángulo (5x8):
********
*      *
*      *
*      *
********

Diamante (altura 7):
   *
  ***
 *****
*******
 *****
  ***
   *
```

## 📊 Criterios de Evaluación

### 🎯 Funcionalidad (40%)
- Todos los programas compilan sin errores
- Funcionamiento correcto según especificaciones
- Manejo adecuado de casos límite
- Validación de entrada apropiada

### 📝 Calidad del Código (30%)
- Estructura clara y organizada
- Nomenclatura consistente y descriptiva
- Comentarios explicativos donde sea necesario
- Uso apropiado de tipos de datos

### 🔧 Eficiencia (20%)
- Algoritmos eficientes
- Uso apropiado de estructuras de control
- Optimización de operaciones
- Manejo eficiente de memoria

### 📚 Documentación (10%)
- Comentarios en el código
- Documentación de funciones
- Explicación de algoritmos complejos
- README con instrucciones de uso

## 📋 Instrucciones de Entrega

### 📁 Estructura de Archivos
```
Tarea_1/
├── Problema1/
│   ├── interes_compuesto.cpp
│   └── README.md
├── Problema2/
│   ├── conversor_unidades.cpp
│   └── README.md
├── Problema3/
│   ├── analizador_numeros.cpp
│   └── README.md
├── Problema4/
│   ├── generador_patrones.cpp
│   └── README.md
└── README.md (este archivo)
```

### 📝 Formato de Entrega
- Código fuente en archivos `.cpp`
- Documentación en archivos `README.md`
- Comentarios en español
- Nombres de variables y funciones descriptivos

### 🔧 Compilación
```bash
# Para cada problema
g++ -std=c++17 -o programa programa.cpp
./programa
```

## 📚 Recursos Adicionales

### 📖 Referencias
- **"C++ Primer"** - Capítulos 1-3
- **"The C++ Programming Language"** - Capítulo 2
- Documentación de C++: cppreference.com

### 🌐 Enlaces Útiles
- [C++ Tutorial](https://www.cplusplus.com/doc/tutorial/)
- [C++ Operators](https://en.cppreference.com/w/cpp/language/operator_precedence)
- [C++ Input/Output](https://en.cppreference.com/w/cpp/io)

### 🛠️ Herramientas Recomendadas
- **IDE**: Visual Studio Code, CLion, Dev-C++
- **Compilador**: GCC/G++ 7.0 o superior
- **Debugger**: GDB para depuración

## ⏰ Fechas Importantes

- **Fecha de Publicación**: Semana 3
- **Fecha de Entrega**: Semana 5
- **Duración Estimada**: 8-10 horas
- **Peso en la Evaluación**: 15% de la nota final

## 📞 Soporte y Consultas

### 🕐 Horarios de Consulta
- **Lunes**: 14:00 - 16:00
- **Miércoles**: 10:00 - 12:00
- **Viernes**: 16:00 - 18:00

### 📧 Contacto
- **Email**: profesor@universidad.edu
- **Canal de Discord**: #tareas-hpa-i
- **Foro**: Campus Virtual

---

**Nota**: Esta tarea es individual. Cualquier caso de plagio será reportado según las políticas académicas de la universidad.

**Última Actualización**: Diciembre 2024 