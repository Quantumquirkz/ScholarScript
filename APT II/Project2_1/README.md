# Calculadora Geométrica

## Descripción
Calculadora geométrica desarrollada en JavaFX que permite calcular áreas y volúmenes de diferentes figuras geométricas con una interfaz moderna y elegante en tema oscuro. La aplicación proporciona cálculos detallados con visualización de fórmulas matemáticas utilizando MathJax.

## Características
- **Interfaz Moderna**: Diseño elegante con tema oscuro y acentos en verde neón
- **Cálculos de Área**:
  - Rectángulo
  - Triángulo
- **Cálculos de Volumen**:
  - Cono
  - Cilindro
  - Esfera
- **Visualización Matemática**: Fórmulas y procedimientos renderizados con MathJax
- **Validación de Entrada**: Manejo robusto de errores y validación de datos
- **Responsive Design**: Interfaz adaptable con scroll vertical

## Requisitos del Sistema
- Java Development Kit (JDK) 17 o superior
- JavaFX SDK 17 o superior
- Conexión a Internet (para la carga de MathJax)

## Instalación

### 1. Clonar el Repositorio
```bash
git clone [URL_DEL_REPOSITORIO]
cd calculadora-geometrica
```

### 2. Configuración del Proyecto
El proyecto utiliza Maven como gestor de dependencias. Asegúrese de tener Maven instalado en su sistema.

```bash
mvn clean install
```

### 3. Ejecutar la Aplicación
```bash
mvn javafx:run
```

## Guía de Uso

### Cálculo de Áreas

#### Rectángulo
1. Seleccione la pestaña "Áreas"
2. En la sección "Área del Rectángulo":
   - Ingrese la base
   - Ingrese la altura
   - Presione "Calcular"
3. El resultado se mostrará junto con el procedimiento detallado

#### Triángulo
1. En la misma pestaña "Áreas":
   - Ingrese la base
   - Ingrese la altura
   - Presione "Calcular"
2. Visualice el resultado y el procedimiento

### Cálculo de Volúmenes

#### Cono
1. Seleccione la pestaña "Volúmenes"
2. En la sección "Volumen del Cono":
   - Ingrese el radio
   - Ingrese la altura
   - Presione "Calcular"
3. El resultado y procedimiento se mostrarán automáticamente

#### Cilindro
1. En la sección "Volumen del Cilindro":
   - Ingrese el radio
   - Ingrese la altura
   - Presione "Calcular"

#### Esfera
1. En la sección "Volumen de la Esfera":
   - Ingrese el radio
   - Presione "Calcular"

## Características Técnicas

### Fórmulas Implementadas

#### Áreas
- Rectángulo: \( A = base \times altura \)
- Triángulo: \( A = \frac{base \times altura}{2} \)

#### Volúmenes
- Cono: \( V = \frac{\pi r^2 h}{3} \)
- Cilindro: \( V = \pi r^2 h \)
- Esfera: \( V = \frac{4}{3}\pi r^3 \)

### Manejo de Errores
- Validación de entrada numérica
- Soporte para decimales con punto o coma
- Mensajes de error descriptivos
- Prevención de valores negativos o cero

### Interfaz de Usuario
- Tema oscuro optimizado para reducir la fatiga visual
- Controles intuitivos y responsivos
- Visualización clara de resultados
- Procedimientos matemáticos paso a paso

## Estructura del Proyecto
```
src/
├── main/
│   ├── java/
│   │   └── geometria/
│   │       ├── Main.java
│   │       └── MainController.java
│   └── resources/
│       └── geometria/
│           ├── main.fxml
│           └── styles.css
```

## Tecnologías Utilizadas
- **JavaFX**: Framework de interfaz gráfica
- **FXML**: Definición de interfaces de usuario
- **CSS**: Estilización y tema oscuro
- **MathJax**: Renderizado de fórmulas matemáticas

## Contribución
Si desea contribuir al proyecto:
1. Fork el repositorio
2. Cree una rama para su función (`git checkout -b feature/AmazingFeature`)
3. Commit sus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abra un Pull Request

## Licencia
Este proyecto está licenciado bajo la Licencia MIT - vea el archivo [LICENSE](LICENSE) para más detalles.

## Contacto
[Tu Nombre] - [tu.email@ejemplo.com]

## Agradecimientos
- JavaFX Community
- MathJax Team
- Contribuidores del proyecto

---
Desarrollado con ❤️ por [Tu Nombre/Organización] 