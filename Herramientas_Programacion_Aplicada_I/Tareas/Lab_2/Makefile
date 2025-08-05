# Makefile for Lab 2: Control Structures
# Applied Programming Tools I

# Compiler settings
CXX = g++
CXXFLAGS = -Wall -Wextra -std=c++11
DEBUG_FLAGS = -g -O0
RELEASE_FLAGS = -O2

# Target programs
TARGETS = grade_calculator guessing_game menu_calculator

# Default target
all: $(TARGETS)

# Compile grade calculator
grade_calculator: grade_calculator.cpp
	$(CXX) $(CXXFLAGS) -o grade_calculator grade_calculator.cpp

# Compile guessing game
guessing_game: guessing_game.cpp
	$(CXX) $(CXXFLAGS) -o guessing_game guessing_game.cpp

# Compile menu calculator
menu_calculator: menu_calculator.cpp
	$(CXX) $(CXXFLAGS) -o menu_calculator menu_calculator.cpp

# Compile with debugging information
debug: CXXFLAGS += $(DEBUG_FLAGS)
debug: $(TARGETS)

# Compile with optimization
release: CXXFLAGS += $(RELEASE_FLAGS)
release: $(TARGETS)

# Run all programs
run: $(TARGETS)
	@echo "=== Running Grade Calculator ==="
	@echo "85" | ./grade_calculator
	@echo ""
	@echo "=== Running Guessing Game ==="
	@echo "2" | ./guessing_game
	@echo ""
	@echo "=== Running Menu Calculator ==="
	@echo "1" | ./menu_calculator

# Test programs with sample input
test: $(TARGETS)
	@echo "Running tests..."
	@echo "Test 1: Grade Calculator"
	@echo "85" | ./grade_calculator > test_output.txt
	@echo "Test 2: Guessing Game"
	@echo "2" | ./guessing_game >> test_output.txt
	@echo "Test 3: Menu Calculator"
	@echo "1" | ./menu_calculator >> test_output.txt
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