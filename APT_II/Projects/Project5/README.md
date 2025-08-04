# Calculadora de Caída Libre

Este proyecto es una aplicación web desarrollada en Java (Spring Boot) y JavaScript que permite calcular el tiempo total de caída de un objeto desde una altura determinada, considerando la gravedad de la Tierra o Marte.

## Enunciado original
> Confeccionar un programa en Java que realice 4 métodos, para asignar valores de entrada y métodos para calcular el tiempo total de caída de un objeto según fórmulas de caída libre.

## Descripción
- El backend está implementado con Spring Boot y expone un endpoint `/calcular` que recibe la altura y el planeta (Tierra o Marte) y devuelve el tiempo de caída y la gravedad utilizada.
- El frontend es una interfaz web moderna que permite al usuario ingresar la altura y seleccionar el planeta, mostrando los resultados de manera clara y visual.

## Métodos implementados (según el enunciado)
1. **Asignar altura:** El usuario ingresa la altura desde la interfaz web.
2. **Asignar planeta:** El usuario selecciona el planeta (Tierra o Marte).
3. **Calcular gravedad:** El backend asigna la gravedad según el planeta seleccionado.
4. **Calcular tiempo de caída:** El backend calcula el tiempo total de caída usando la fórmula:
   \[
   t = \sqrt{\frac{2h}{g}}
   \]
   donde `h` es la altura y `g` la gravedad.

## ¿Cómo ejecutar el proyecto?
1. **Clona el repositorio y entra a la carpeta del proyecto.**
2. **Compila y ejecuta el backend:**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
3. **Abre tu navegador y accede a:**
   ```
   http://localhost:8080/index.html
   ```

## Estructura del proyecto
- `src/main/resources/static/` → Archivos del frontend (`index.html`, `styles.css`, `script.js`)
- `src/main/java/com/caidalibre/` → Código Java del backend
- `src/main/java/com/caidalibre/controller/CalculadoraController.java` → Lógica principal del cálculo

## Tecnologías usadas
- Java 11
- Spring Boot
- HTML5, CSS3, Bootstrap 5
- JavaScript (fetch API)

## Autor
- [Tu nombre aquí]

---
¡Listo para calcular caídas libres en la Tierra y Marte! 🚀 