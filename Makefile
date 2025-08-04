# Makefile para ScholarScript
# Automatización de tareas comunes del proyecto

.PHONY: help clean build test docs install setup

# Variables
PYTHON = python3
PIP = pip3
GCC = g++
JAVAC = javac
MAVEN = mvn

# Colores para output
RED = \033[0;31m
GREEN = \033[0;32m
YELLOW = \033[1;33m
BLUE = \033[0;34m
NC = \033[0m # No Color

# Ayuda
help:
	@echo "$(BLUE)ScholarScript - Comandos disponibles:$(NC)"
	@echo "$(GREEN)make setup$(NC)     - Configurar el entorno de desarrollo"
	@echo "$(GREEN)make install$(NC)   - Instalar dependencias"
	@echo "$(GREEN)make build$(NC)     - Compilar todos los proyectos"
	@echo "$(GREEN)make test$(NC)      - Ejecutar tests"
	@echo "$(GREEN)make clean$(NC)     - Limpiar archivos generados"
	@echo "$(GREEN)make docs$(NC)      - Generar documentación"
	@echo "$(GREEN)make format$(NC)    - Formatear código"
	@echo "$(GREEN)make lint$(NC)      - Verificar estilo de código"

# Configuración inicial
setup:
	@echo "$(YELLOW)Configurando entorno de desarrollo...$(NC)"
	@echo "$(BLUE)Verificando herramientas...$(NC)"
	@which $(PYTHON) || (echo "$(RED)Python no encontrado$(NC)" && exit 1)
	@which $(GCC) || (echo "$(RED)GCC no encontrado$(NC)" && exit 1)
	@which $(JAVAC) || (echo "$(RED)Java no encontrado$(NC)" && exit 1)
	@which $(MAVEN) || (echo "$(RED)Maven no encontrado$(NC)" && exit 1)
	@echo "$(GREEN)✓ Todas las herramientas están instaladas$(NC)"

# Instalar dependencias
install:
	@echo "$(YELLOW)Instalando dependencias...$(NC)"
	@cd Collaborative_Systems && $(PIP) install -r requirements.txt
	@echo "$(GREEN)✓ Dependencias instaladas$(NC)"

# Compilar proyectos
build:
	@echo "$(YELLOW)Compilando proyectos...$(NC)"
	@echo "$(BLUE)Compilando APT_I (C++)...$(NC)"
	@find APT_I -name "*.cpp" -exec $(GCC) -o {}.out {} \;
	@echo "$(BLUE)Compilando APT_II (Java)...$(NC)"
	@find APT_II -name "*.java" -exec $(JAVAC) {} \;
	@echo "$(GREEN)✓ Compilación completada$(NC)"

# Ejecutar tests
test:
	@echo "$(YELLOW)Ejecutando tests...$(NC)"
	@echo "$(BLUE)Tests de Python...$(NC)"
	@cd Collaborative_Systems && $(PYTHON) -m pytest tests/ -v
	@echo "$(GREEN)✓ Tests completados$(NC)"

# Limpiar archivos generados
clean:
	@echo "$(YELLOW)Limpiando archivos generados...$(NC)"
	@find . -name "*.o" -delete
	@find . -name "*.out" -delete
	@find . -name "*.class" -delete
	@find . -name "__pycache__" -type d -exec rm -rf {} +
	@find . -name "*.pyc" -delete
	@find . -name "build" -type d -exec rm -rf {} +
	@find . -name "dist" -type d -exec rm -rf {} +
	@find . -name "*.egg-info" -type d -exec rm -rf {} +
	@echo "$(GREEN)✓ Limpieza completada$(NC)"

# Generar documentación
docs:
	@echo "$(YELLOW)Generando documentación...$(NC)"
	@echo "$(BLUE)Documentación de Python...$(NC)"
	@cd Collaborative_Systems && $(PYTHON) -m pydoc -w .
	@echo "$(GREEN)✓ Documentación generada$(NC)"

# Formatear código
format:
	@echo "$(YELLOW)Formateando código...$(NC)"
	@echo "$(BLUE)Formateando Python...$(NC)"
	@cd Collaborative_Systems && $(PYTHON) -m black .
	@echo "$(GREEN)✓ Código formateado$(NC)"

# Verificar estilo de código
lint:
	@echo "$(YELLOW)Verificando estilo de código...$(NC)"
	@echo "$(BLUE)Linting Python...$(NC)"
	@cd Collaborative_Systems && $(PYTHON) -m flake8 .
	@echo "$(GREEN)✓ Linting completado$(NC)"

# Ejecutar proyectos específicos
run-apt1:
	@echo "$(YELLOW)Ejecutando APT_I...$(NC)"
	@find APT_I -name "*.out" -exec {} \;

run-apt2:
	@echo "$(YELLOW)Ejecutando APT_II...$(NC)"
	@cd APT_II && $(MAVEN) exec:java

run-collaborative:
	@echo "$(YELLOW)Ejecutando Collaborative Systems...$(NC)"
	@cd Collaborative_Systems && $(PYTHON) server.py

# Backup del proyecto
backup:
	@echo "$(YELLOW)Creando backup...$(NC)"
	@tar -czf scholarscript_backup_$(shell date +%Y%m%d_%H%M%S).tar.gz \
		--exclude='*.o' --exclude='*.out' --exclude='*.class' \
		--exclude='__pycache__' --exclude='*.pyc' \
		--exclude='build' --exclude='dist' --exclude='*.egg-info' \
		--exclude='.git' .
	@echo "$(GREEN)✓ Backup creado$(NC)"

# Estadísticas del proyecto
stats:
	@echo "$(YELLOW)Estadísticas del proyecto:$(NC)"
	@echo "$(BLUE)Líneas de código:$(NC)"
	@find . -name "*.cpp" -o -name "*.java" -o -name "*.py" | xargs wc -l
	@echo "$(BLUE)Archivos por tipo:$(NC)"
	@echo "C++: $(shell find . -name "*.cpp" | wc -l)"
	@echo "Java: $(shell find . -name "*.java" | wc -l)"
	@echo "Python: $(shell find . -name "*.py" | wc -l)"
	@echo "Markdown: $(shell find . -name "*.md" | wc -l)"

# Verificar estructura del proyecto
check-structure:
	@echo "$(YELLOW)Verificando estructura del proyecto...$(NC)"
	@test -d APT_I || (echo "$(RED)✗ APT_I no encontrado$(NC)" && exit 1)
	@test -d APT_II || (echo "$(RED)✗ APT_II no encontrado$(NC)" && exit 1)
	@test -d Data_Structures || (echo "$(RED)✗ Data_Structures no encontrado$(NC)" && exit 1)
	@test -d Collaborative_Systems || (echo "$(RED)✗ Collaborative_Systems no encontrado$(NC)" && exit 1)
	@test -f README.md || (echo "$(RED)✗ README.md no encontrado$(NC)" && exit 1)
	@test -f LICENSE || (echo "$(RED)✗ LICENSE no encontrado$(NC)" && exit 1)
	@echo "$(GREEN)✓ Estructura del proyecto correcta$(NC)"

# Comando por defecto
all: setup install build test docs
	@echo "$(GREEN)✓ Todas las tareas completadas$(NC)" 