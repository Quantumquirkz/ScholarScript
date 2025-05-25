# Calculadora de Movimiento de Proyectil

Este proyecto consiste en una calculadora de movimiento de proyectil con un frontend en HTML/CSS/JavaScript y un backend en Java.

## Estructura del Proyecto

```
.
├── frontend/
│   ├── index.html
│   ├── js/
│   │   └── main.js
│   └── styles/
│       └── main.css
└── backend/
    ├── pom.xml
    └── src/
        └── main/
            └── java/
                └── com/
                    └── projectile/
                        ├── Main.java
                        ├── ProjectileCalculator.java
                        └── TrajectoryPoint.java
```

## Requisitos

- Java 11 o superior
- Maven
- Navegador web moderno

## Ejecución

### Backend

1. Navega al directorio `backend`:
   ```bash
   cd backend
   ```

2. Compila y ejecuta el proyecto con Maven:
   ```bash
   mvn clean package
   java -jar target/projectile-calculator-1.0-SNAPSHOT.jar
   ```

El servidor backend se iniciará en `http://localhost:4567`.

### Frontend

1. Abre el archivo `frontend/index.html` en tu navegador web.

## Características

- Cálculo de trayectoria de proyectiles
- Visualización de resultados en tiempo real
- Exportación de resultados en formato TXT y PDF
- Interfaz de usuario moderna y responsiva
- Validación de entrada de datos
- Cálculos precisos de física

## Tecnologías Utilizadas

### Frontend
- HTML5
- CSS3 (con Tailwind CSS)
- JavaScript (Vanilla)

### Backend
- Java 11
- Spark Java (servidor web)
- iText (generación de PDF)
- Gson (manejo de JSON)

## API Endpoints

- `POST /api/calculate`: Calcula la trayectoria del proyectil
  - Parámetros: `velocity` (m/s), `angle` (grados)
  - Retorna: JSON con resultados del cálculo

- `POST /api/download/txt`: Genera archivo TXT con resultados
  - Parámetros: `velocity` (m/s), `angle` (grados)
  - Retorna: Archivo TXT descargable

- `POST /api/download/pdf`: Genera archivo PDF con resultados
  - Parámetros: `velocity` (m/s), `angle` (grados)
  - Retorna: Archivo PDF descargable 