# Calculadora de Movimiento de Proyectil

## Descripción
Este proyecto es una aplicación web que calcula y visualiza el movimiento de un proyectil en el aire. Permite a los usuarios ingresar la velocidad inicial y el ángulo de lanzamiento para obtener cálculos detallados de la trayectoria, incluyendo tiempo de vuelo, altura máxima y distancia recorrida.

## Características Principales
- Cálculo preciso del movimiento de proyectiles
- Visualización gráfica de la trayectoria
- Tabla detallada de datos de la trayectoria
- Exportación de resultados en formatos TXT y PDF
- Interfaz de usuario intuitiva y responsiva
- Validación de datos en tiempo real

## Requisitos del Sistema

### Backend (Java)
- Java 11 o superior
- Maven 3.6 o superior
- Dependencias:
  - Spark Java (servidor web)
  - Gson (manejo de JSON)
  - iText (generación de PDF)
  - SLF4J (logging)

### Frontend
- Navegador web moderno (Chrome, Firefox, Safari, Edge)
- Conexión a Internet (para cargar CDNs de Tailwind CSS y Chart.js)

## Estructura del Proyecto
```
projectile-calculator/
├── backend/
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/
│   │       │       └── projectile/
│   │       │           ├── Main.java
│   │       │           ├── ProjectileCalculator.java
│   │       │           └── TrajectoryPoint.java
│   │       └── resources/
│   │           └── logback.xml
│   └── pom.xml
└── frontend/
    ├── index.html
    ├── js/
    │   └── main.js
    └── styles/
        └── main.css
```

## Instalación y Configuración

### Backend
1. Navegar al directorio del backend:
```bash
cd backend
```

2. Compilar el proyecto:
```bash
mvn clean package
```

3. Ejecutar el servidor:
```bash
java -jar target/projectile-calculator-1.0-SNAPSHOT.jar
```

El servidor se iniciará en `http://localhost:4567`

### Frontend
1. El frontend se sirve automáticamente desde el backend
2. Acceder a la aplicación en el navegador:
```
http://localhost:4567
```

## Uso de la Aplicación

### Entrada de Datos
1. **Velocidad Inicial**
   - Ingrese la velocidad en metros por segundo (m/s)
   - Debe ser un valor positivo
   - Ejemplo: 50 m/s

2. **Ángulo de Lanzamiento**
   - Ingrese el ángulo en grados
   - Debe estar entre 0° y 90°
   - Ejemplo: 45°

### Resultados
La aplicación mostrará:
1. **Gráfica de Trayectoria**
   - Visualización en tiempo real
   - Eje X: Distancia horizontal
   - Eje Y: Altura
   - Puntos interactivos con información detallada

2. **Datos Principales**
   - Tiempo total de vuelo
   - Tiempo hasta altura máxima
   - Altura máxima alcanzada
   - Distancia máxima recorrida

3. **Tabla de Trayectoria**
   - Tiempo en cada punto
   - Altura
   - Posición horizontal
   - Velocidad horizontal
   - Velocidad vertical

### Exportación de Resultados
1. **Formato TXT**
   - Click en "Descargar TXT"
   - Archivo con formato tabulado
   - Incluye todos los datos calculados

2. **Formato PDF**
   - Click en "Descargar PDF"
   - Documento formateado con gráficos
   - Incluye tabla de trayectoria

## Fórmulas Utilizadas

### Cálculos Principales
1. **Tiempo Total de Vuelo**
   ```
   t = (2 * v₀ * sin(θ)) / g
   ```
   donde:
   - v₀ = velocidad inicial
   - θ = ángulo de lanzamiento
   - g = aceleración de la gravedad (9.81 m/s²)

2. **Tiempo hasta Altura Máxima**
   ```
   t = (v₀ * sin(θ)) / g
   ```

3. **Altura Máxima**
   ```
   h = (v₀² * sin²(θ)) / (2 * g)
   ```

4. **Distancia Máxima**
   ```
   d = (v₀² * sin(2θ)) / g
   ```

## Manejo de Errores
- Validación de entrada en tiempo real
- Mensajes de error claros y descriptivos
- Verificación de conexión con el servidor
- Manejo de excepciones en cálculos

## Contribución
1. Fork el repositorio
2. Crear una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abrir un Pull Request

## Licencia
Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

## Contacto
Para preguntas o sugerencias, por favor abrir un issue en el repositorio. 