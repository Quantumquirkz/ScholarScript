# Programa2 - Calculadora de Operaciones Básicas

Este programa Java implementa una calculadora con dos operaciones principales: suma de números enteros y cálculo del volumen de un cono.

## Características

- Menú interactivo en consola
- Dos operaciones disponibles:
  1. Suma de dos números enteros
  2. Cálculo del volumen de un cono
- Manejo de errores para entradas inválidas
- Reportes formateados de resultados

## Estructura del Proyecto

```
Programa2/
├── JhuomarProg02.java    # Programa principal con el menú
├── Operaciones.java      # Clase con las operaciones matemáticas
└── README.md            # Este archivo
```

## Requisitos

- Java Development Kit (JDK) 8 o superior
- Compilador de Java (javac)

## Compilación

Para compilar el programa, ejecute el siguiente comando en la terminal:

```bash
javac Programa2/Operaciones.java Programa2/JhuomarProg02.java
```

## Ejecución

Para ejecutar el programa, use el siguiente comando:

```bash
java -cp . Programa2.JhuomarProg02
```

## Uso del Programa

1. Al iniciar, se mostrará un menú con tres opciones:
   - 1: Sumar dos números enteros
   - 2: Calcular volumen de un cono
   - 3: Salir

2. Para la suma:
   - Ingrese dos números enteros
   - El programa mostrará un reporte con los valores y el resultado

3. Para el volumen del cono:
   - Ingrese el radio y la altura del cono
   - El programa calculará y mostrará el volumen

4. Para salir:
   - Seleccione la opción 3

## Manejo de Errores

El programa maneja los siguientes casos de error:
- Entrada de caracteres no numéricos
- Valores inválidos para las operaciones
- Errores de entrada/salida

## Notas

- Los resultados del volumen del cono se calculan usando la fórmula: V = (1/3)πr²h
- Los reportes incluyen todos los valores ingresados y el resultado final 