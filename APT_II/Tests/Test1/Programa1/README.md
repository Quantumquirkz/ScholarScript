# Programa 1: Conversión de Temperatura Bohemia

## Descripción
Este programa calcula la conversión de temperaturas desde Fahrenheit a Centígrados y Kelvin, y muestra los resultados en una ventana de diálogo con estilo bohemio.

## Características
- **Entrada:** Temperaturas desde 30°F hasta 100°F con incrementos de 10 grados.
- **Salida:** Muestra una tabla con las conversiones a Centígrados y Kelvin.
- **Interfaz:** Usa `JOptionPane` para mostrar los resultados en una ventana de diálogo.
- **Estilo:** Diseño bohemio con colores cálidos y formato HTML.

## Estructura del Proyecto
- **Carpeta:** `Programa1`
- **Archivo Principal:** `JhuomarProg01.java`
- **Paquete:** `package Programa1;`

## Requisitos
- Java JDK 17 o superior.
- Entorno gráfico (para ejecutar `JOptionPane`).

## Cómo Compilar y Ejecutar
1. **Compilar:**
   ```bash
   javac Programa1/JhuomarProg01.java
   ```

2. **Ejecutar:**
   ```bash
   java -cp . Programa1.JhuomarProg01
   ```

## Notas
- Si usas WSL o un entorno sin GUI, necesitarás configurar un servidor X para ejecutar `JOptionPane`.
- Los errores de compilación relacionados con `javax.swing` y `java.awt` son normales en entornos sin soporte gráfico.

## Autor
- **Universidad:** Universidad Tecnológica de Panamá
- **Grupo:** 1IL-128
- **Estudiante:** Jhuomar Boskoll Barría Quintero | 9-766-196 