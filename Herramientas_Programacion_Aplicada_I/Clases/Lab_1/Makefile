# Makefile for Lab 1: Introduction to C++
# Applied Programming Tools I

# Compiler settings
CXX = g++
CXXFLAGS = -Wall -Wextra -std=c++11
DEBUG_FLAGS = -g -O0
RELEASE_FLAGS = -O2

# Target programs
TARGETS = hello_world calculator temperature

# Default target
all: $(TARGETS)

# Compile hello_world program
hello_world: hello_world.cpp
	$(CXX) $(CXXFLAGS) -o hello_world hello_world.cpp

# Compile calculator program
calculator: calculator.cpp
	$(CXX) $(CXXFLAGS) -o calculator calculator.cpp

# Compile temperature converter program
temperature: temperature.cpp
	$(CXX) $(CXXFLAGS) -o temperature temperature.cpp

# Compile with debugging information
debug: CXXFLAGS += $(DEBUG_FLAGS)
debug: $(TARGETS)

# Compile with optimization
release: CXXFLAGS += $(RELEASE_FLAGS)
release: $(TARGETS)

# Run all programs
run: $(TARGETS)
	@echo "=== Running Hello World Program ==="
	@echo "John" | ./hello_world
	@echo ""
	@echo "=== Running Calculator Program ==="
	@echo "10" | ./calculator
	@echo "5" | ./calculator
	@echo ""
	@echo "=== Running Temperature Converter ==="
	@echo "25" | ./temperature

# Test programs with sample input
test: $(TARGETS)
	@echo "Running tests..."
	@echo "Test 1: Hello World"
	@echo "John" | ./hello_world > test_output.txt
	@echo "Test 2: Calculator"
	@echo "10" | ./calculator >> test_output.txt
	@echo "5" | ./calculator >> test_output.txt
	@echo "Test 3: Temperature"
	@echo "25" | ./temperature >> test_output.txt
	@echo "Tests completed. Check test_output.txt for results."

# Clean compiled files
clean:
	rm -f $(TARGETS) *.o test_output.txt

# Install dependencies (if needed)
install:
	@echo "Checking for required tools..."
	@which $(CXX) || (echo "Error: $(CXX) not found. Please install GCC/G++." && exit 1)
	@echo "✓ Compiler found: $(shell $(CXX) --version | head -1)"

# Show help
help:
	@echo "Available targets:"
	@echo "  all       - Compile all programs (default)"
	@echo "  debug     - Compile with debugging information"
	@echo "  release   - Compile with optimization"
	@echo "  run       - Compile and run all programs"
	@echo "  test      - Run tests with sample input"
	@echo "  clean     - Remove compiled files"
	@echo "  install   - Check and install dependencies"
	@echo "  help      - Show this help message"

# Phony targets
.PHONY: all debug release run test clean install help 