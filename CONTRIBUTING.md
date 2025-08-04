# 🤝 Guía de Contribución

¡Gracias por tu interés en contribuir a ScholarScript! Este documento proporciona las pautas para contribuir al proyecto de manera efectiva.

## 📋 Tabla de Contenidos

- [Cómo Contribuir](#cómo-contribuir)
- [Configuración del Entorno](#configuración-del-entorno)
- [Convenciones de Código](#convenciones-de-código)
- [Proceso de Pull Request](#proceso-de-pull-request)
- [Reporte de Bugs](#reporte-de-bugs)
- [Solicitud de Funcionalidades](#solicitud-de-funcionalidades)
- [Preguntas Frecuentes](#preguntas-frecuentes)

## 🚀 Cómo Contribuir

### 🔧 Tipos de Contribuciones

Aceptamos los siguientes tipos de contribuciones:

- **🐛 Bug Fixes**: Corrección de errores en el código existente
- **✨ Nuevas Funcionalidades**: Adición de nuevas características
- **📚 Documentación**: Mejoras en la documentación
- **🧪 Tests**: Adición o mejora de pruebas
- **🎨 Mejoras de UI/UX**: Mejoras en la interfaz de usuario
- **⚡ Optimizaciones**: Mejoras en el rendimiento
- **🔒 Seguridad**: Correcciones de vulnerabilidades

### 📋 Requisitos Previos

Antes de contribuir, asegúrate de tener:

- [Git](https://git-scm.com/) instalado
- Un editor de código (VS Code, IntelliJ, etc.)
- Conocimientos básicos del lenguaje de programación correspondiente
- Comprensión del flujo de trabajo de Git

## 🛠️ Configuración del Entorno

### 🔄 Fork y Clone

1. **Fork el repositorio**
   ```bash
   # Ve a https://github.com/Quantumquirk31/ScholarScript
   # Haz clic en "Fork" en la esquina superior derecha
   ```

2. **Clone tu fork**
   ```bash
   git clone https://github.com/TU_USUARIO/ScholarScript.git
   cd ScholarScript
   ```

3. **Configura el upstream**
   ```bash
   git remote add upstream https://github.com/Quantumquirk31/ScholarScript.git
   ```

### 🔧 Configuración por Lenguaje

#### Para C++ (APT_I)
```bash
# Instalar compilador
sudo apt-get install g++  # Ubuntu/Debian
brew install gcc          # macOS

# Verificar instalación
g++ --version
```

#### Para Java (APT_II)
```bash
# Instalar JDK
sudo apt-get install openjdk-11-jdk  # Ubuntu/Debian
brew install openjdk@11              # macOS

# Verificar instalación
java --version
javac --version
```

#### Para Python (Collaborative_Systems)
```bash
# Crear entorno virtual
python -m venv venv
source venv/bin/activate  # Linux/macOS
# venv\Scripts\activate   # Windows

# Instalar dependencias
pip install -r requirements.txt
```

## 📝 Convenciones de Código

### 🔤 Estándares de Nomenclatura

#### C++
- **Clases**: PascalCase (`MiClase`)
- **Funciones**: camelCase (`miFuncion`)
- **Variables**: camelCase (`miVariable`)
- **Constantes**: UPPER_SNAKE_CASE (`MAX_SIZE`)
- **Archivos**: snake_case (`mi_archivo.cpp`)

#### Java
- **Clases**: PascalCase (`MiClase`)
- **Métodos**: camelCase (`miMetodo`)
- **Variables**: camelCase (`miVariable`)
- **Constantes**: UPPER_SNAKE_CASE (`MAX_SIZE`)
- **Paquetes**: lowercase (`com.example.proyecto`)

#### Python
- **Clases**: PascalCase (`MiClase`)
- **Funciones**: snake_case (`mi_funcion`)
- **Variables**: snake_case (`mi_variable`)
- **Constantes**: UPPER_SNAKE_CASE (`MAX_SIZE`)
- **Archivos**: snake_case (`mi_archivo.py`)

### 📁 Estructura de Archivos

Cada contribución debe seguir esta estructura:

```
proyecto/
├── README.md              # Documentación del proyecto
├── src/                   # Código fuente
├── tests/                 # Tests unitarios
├── docs/                  # Documentación adicional
├── examples/              # Ejemplos de uso
└── requirements.txt       # Dependencias (si aplica)
```

### 💬 Comentarios y Documentación

- **Comentarios**: Explicar el "por qué", no el "qué"
- **Documentación**: Mantener README actualizado
- **Javadoc/Doxygen**: Documentar funciones públicas
- **Ejemplos**: Proporcionar ejemplos de uso

## 🔄 Proceso de Pull Request

### 📋 Antes de Crear un PR

1. **Sincroniza tu fork**
   ```bash
   git fetch upstream
   git checkout main
   git merge upstream/main
   ```

2. **Crea una rama para tu contribución**
   ```bash
   git checkout -b feature/nueva-funcionalidad
   # o
   git checkout -b fix/correccion-bug
   ```

3. **Haz tus cambios**
   - Escribe código limpio y bien documentado
   - Añade tests si es necesario
   - Actualiza la documentación

4. **Commits significativos**
   ```bash
   git add .
   git commit -m "feat: añadir nueva funcionalidad X"
   git commit -m "fix: corregir bug en función Y"
   git commit -m "docs: actualizar documentación"
   ```

### 🚀 Creando el Pull Request

1. **Push a tu fork**
   ```bash
   git push origin feature/nueva-funcionalidad
   ```

2. **Crea el Pull Request**
   - Ve a tu fork en GitHub
   - Haz clic en "New Pull Request"
   - Selecciona la rama correcta
   - Completa la plantilla del PR

### 📋 Plantilla del Pull Request

```markdown
## 📋 Descripción
Breve descripción de los cambios realizados.

## 🎯 Tipo de Cambio
- [ ] Bug fix
- [ ] Nueva funcionalidad
- [ ] Mejora de documentación
- [ ] Refactorización
- [ ] Test

## 🧪 Tests
- [ ] Tests unitarios añadidos/pasando
- [ ] Tests de integración añadidos/pasando
- [ ] Manual testing realizado

## 📚 Documentación
- [ ] README actualizado
- [ ] Comentarios añadidos al código
- [ ] Documentación técnica actualizada

## 🔍 Checklist
- [ ] Código sigue las convenciones del proyecto
- [ ] Tests pasan localmente
- [ ] Documentación actualizada
- [ ] No hay conflictos de merge
```

## 🐛 Reporte de Bugs

### 📋 Información Requerida

Al reportar un bug, incluye:

1. **Descripción del problema**
2. **Pasos para reproducir**
3. **Comportamiento esperado**
4. **Comportamiento actual**
5. **Información del sistema**
   - Sistema operativo
   - Versión del lenguaje
   - Versión del proyecto

### 📝 Plantilla de Bug Report

```markdown
## 🐛 Descripción del Bug
Descripción clara y concisa del problema.

## 🔄 Pasos para Reproducir
1. Ve a '...'
2. Haz clic en '...'
3. Desplázate hacia abajo hasta '...'
4. Ve el error

## ✅ Comportamiento Esperado
Descripción de lo que debería suceder.

## ❌ Comportamiento Actual
Descripción de lo que está sucediendo.

## 📱 Información del Sistema
- OS: [ej. Ubuntu 20.04]
- Versión: [ej. 1.0.0]
- Compilador: [ej. g++ 9.4.0]

## 📸 Capturas de Pantalla
Si aplica, añade capturas de pantalla.

## 📋 Contexto Adicional
Cualquier otra información relevante.
```

## ✨ Solicitud de Funcionalidades

### 📋 Información Requerida

Al solicitar una nueva funcionalidad, incluye:

1. **Descripción de la funcionalidad**
2. **Caso de uso**
3. **Alternativas consideradas**
4. **Impacto en el proyecto**

### 📝 Plantilla de Feature Request

```markdown
## ✨ Descripción de la Funcionalidad
Descripción clara de la nueva funcionalidad deseada.

## 🎯 Caso de Uso
Explicación de por qué esta funcionalidad es útil.

## 🔄 Alternativas Consideradas
Descripción de alternativas que has considerado.

## 📊 Impacto
Cómo esta funcionalidad afectaría al proyecto.

## 📋 Contexto Adicional
Cualquier otra información relevante.
```

## ❓ Preguntas Frecuentes

### 🤔 ¿Cómo empiezo a contribuir?
1. Revisa los issues abiertos
2. Elige uno que te interese
3. Comenta en el issue que quieres trabajar en él
4. Sigue las pautas de este documento

### 🔧 ¿Qué hago si tengo problemas con la configuración?
1. Revisa la documentación del proyecto
2. Busca en issues similares
3. Crea un issue con la etiqueta "help wanted"

### 📝 ¿Cómo sé si mi contribución es buena?
- Sigue las convenciones de código
- Incluye tests apropiados
- Actualiza la documentación
- Responde a los comentarios del review

### ⏰ ¿Cuánto tiempo toma revisar un PR?
- Depende de la complejidad
- Generalmente 1-3 días hábiles
- PRs simples pueden ser revisados más rápido

## 📞 Contacto

Si tienes preguntas sobre contribuir:

- **Issues**: Usa GitHub Issues para bugs y feature requests
- **Discussions**: Usa GitHub Discussions para preguntas generales
- **Email**: [Tu email aquí]

## 🙏 Agradecimientos

Gracias a todos los contribuidores que han ayudado a hacer ScholarScript mejor:

- [Lista de contribuidores]

---

**Nota**: Al contribuir, aceptas que tus contribuciones serán licenciadas bajo la misma licencia del proyecto (MIT). 