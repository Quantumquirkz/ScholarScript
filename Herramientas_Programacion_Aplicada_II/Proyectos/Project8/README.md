# McMenus Calculator

Aplicación web para calcular descuentos y generar facturas de McDonald's.

## Estructura del Proyecto

El proyecto está dividido en dos partes principales:

- `frontend/`: Aplicación React con TypeScript
- `backend/`: Servidor Spring Boot en Java

## Requisitos

### Frontend
- Node.js 18 o superior
- npm o yarn

### Backend
- Java 17 o superior
- Maven 3.6 o superior

## Configuración

### Backend

1. Navega al directorio del backend:
```bash
cd backend
```

2. Configura las credenciales de correo electrónico en `src/main/resources/application.properties`:
```properties
spring.mail.username=tu_correo@gmail.com
spring.mail.password=tu_contraseña_de_aplicacion
```

3. Compila y ejecuta el backend:
```bash
mvn spring-boot:run
```

El servidor se ejecutará en `http://localhost:8080`.

### Frontend

1. Navega al directorio del frontend:
```bash
cd frontend
```

2. Instala las dependencias:
```bash
npm install
# o
yarn install
```

3. Inicia el servidor de desarrollo:
```bash
npm run dev
# o
yarn dev
```

La aplicación estará disponible en `http://localhost:3000`.

## Características

- Cálculo de descuentos según el día de la semana
- Descuentos especiales para combos
- Generación de facturas
- Envío de facturas por correo electrónico
- Interfaz de usuario moderna y responsiva

## Tecnologías Utilizadas

### Frontend
- React
- TypeScript
- Tailwind CSS
- Shadcn/ui

### Backend
- Spring Boot
- Java 17
- Spring Mail
- Maven 