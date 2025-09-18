# Expression Evaluator Project

## Project Overview
A comprehensive expression evaluator that can parse, validate, and evaluate mathematical expressions using stacks and various data structures. The project demonstrates the practical application of stacks, parsing algorithms, and expression trees in C++.

## Project Specifications

### Core Features
- Mathematical expression parsing and validation
- Infix, postfix, and prefix notation support
- Expression tree construction and evaluation
- Support for variables and functions
- Error handling and syntax validation
- Command-line interface with interactive mode
- File input/output for expression processing

### Technical Requirements
- **Language**: C++17 or higher
- **Dependencies**: Standard C++ libraries only
- **Platforms**: Linux, Windows, macOS
- **Build Tool**: CMake or Makefile
- **Data Structures**: Stacks, Trees, Hash Tables

## Project Structure
```
expression-evaluator/
├── src/
│   ├── ExpressionParser.h
│   ├── ExpressionParser.cpp
│   ├── Stack.h
│   ├── Stack.cpp
│   ├── ExpressionTree.h
│   ├── ExpressionTree.cpp
│   ├── Tokenizer.h
│   ├── Tokenizer.cpp
│   ├── Evaluator.h
│   ├── Evaluator.cpp
│   ├── VariableManager.h
│   ├── VariableManager.cpp
│   └── main.cpp
├── tests/
│   ├── test_parser.cpp
│   ├── test_stack.cpp
│   └── test_evaluator.cpp
├── CMakeLists.txt
├── Makefile
└── README.md
```

## Detailed Specifications

### 1. Token System

#### Token.h
```cpp
#ifndef TOKEN_H
#define TOKEN_H

#include <string>
#include <variant>

enum class TokenType {
    NUMBER,
    OPERATOR,
    VARIABLE,
    FUNCTION,
    LEFT_PARENTHESIS,
    RIGHT_PARENTHESIS,
    COMMA,
    UNKNOWN
};

struct Token {
    TokenType type;
    std::string value;
    int precedence;
    bool isLeftAssociative;
    
    Token(TokenType t, const std::string& v, int p = 0, bool left = true)
        : type(t), value(v), precedence(p), isLeftAssociative(left) {}
    
    bool isOperator() const {
        return type == TokenType::OPERATOR;
    }
    
    bool isNumber() const {
        return type == TokenType::NUMBER;
    }
    
    bool isVariable() const {
        return type == TokenType::VARIABLE;
    }
    
    bool isFunction() const {
        return type == TokenType::FUNCTION;
    }
    
    bool isParenthesis() const {
        return type == TokenType::LEFT_PARENTHESIS || 
               type == TokenType::RIGHT_PARENTHESIS;
    }
};

#endif // TOKEN_H
```

### 2. Stack Implementation

#### Stack.h
```cpp
#ifndef STACK_H
#define STACK_H

#include <vector>
#include <stdexcept>

template<typename T>
class Stack {
private:
    std::vector<T> data;
    
public:
    Stack() = default;
    ~Stack() = default;
    
    // Copy constructor and assignment
    Stack(const Stack& other) : data(other.data) {}
    Stack& operator=(const Stack& other) {
        if (this != &other) {
            data = other.data;
        }
        return *this;
    }
    
    // Move constructor and assignment
    Stack(Stack&& other) noexcept : data(std::move(other.data)) {}
    Stack& operator=(Stack&& other) noexcept {
        if (this != &other) {
            data = std::move(other.data);
        }
        return *this;
    }
    
    // Stack operations
    void push(const T& item) {
        data.push_back(item);
    }
    
    void push(T&& item) {
        data.push_back(std::move(item));
    }
    
    void pop() {
        if (isEmpty()) {
            throw std::runtime_error("Stack is empty");
        }
        data.pop_back();
    }
    
    T& top() {
        if (isEmpty()) {
            throw std::runtime_error("Stack is empty");
        }
        return data.back();
    }
    
    const T& top() const {
        if (isEmpty()) {
            throw std::runtime_error("Stack is empty");
        }
        return data.back();
    }
    
    bool isEmpty() const {
        return data.empty();
    }
    
    size_t size() const {
        return data.size();
    }
    
    void clear() {
        data.clear();
    }
    
    // Iterator support
    auto begin() { return data.begin(); }
    auto end() { return data.end(); }
    auto begin() const { return data.begin(); }
    auto end() const { return data.end(); }
};

#endif // STACK_H
```

### 3. Expression Parser

#### ExpressionParser.h
```cpp
#ifndef EXPRESSION_PARSER_H
#define EXPRESSION_PARSER_H

#include "Tokenizer.h"
#include "Stack.h"
#include <vector>
#include <string>
#include <unordered_map>

class ExpressionParser {
private:
    std::unordered_map<std::string, int> operatorPrecedence;
    std::unordered_map<std::string, bool> operatorAssociativity;
    std::unordered_map<std::string, int> functionArity;
    
    void initializeOperators();
    bool isOperator(const std::string& token) const;
    bool isFunction(const std::string& token) const;
    bool isNumber(const std::string& token) const;
    bool isVariable(const std::string& token) const;
    bool isLeftParenthesis(const std::string& token) const;
    bool isRightParenthesis(const std::string& token) const;
    
    std::vector<Token> tokenizeExpression(const std::string& expression);
    std::vector<Token> infixToPostfix(const std::vector<Token>& infixTokens);
    double evaluatePostfix(const std::vector<Token>& postfixTokens, 
                          const std::unordered_map<std::string, double>& variables = {});
    
public:
    ExpressionParser();
    
    // Main evaluation method
    double evaluate(const std::string& expression, 
                   const std::unordered_map<std::string, double>& variables = {});
    
    // Convert between notations
    std::string infixToPostfix(const std::string& infixExpression);
    std::string infixToPrefix(const std::string& infixExpression);
    std::string postfixToInfix(const std::string& postfixExpression);
    
    // Validation
    bool isValidExpression(const std::string& expression) const;
    std::vector<std::string> getVariables(const std::string& expression) const;
    
    // Advanced features
    void addFunction(const std::string& name, int arity);
    void removeFunction(const std::string& name);
    std::vector<std::string> getSupportedFunctions() const;
};

#endif // EXPRESSION_PARSER_H
```

#### ExpressionParser.cpp (Key Methods)
```cpp
#include "ExpressionParser.h"
#include <cmath>
#include <algorithm>
#include <sstream>

ExpressionParser::ExpressionParser() {
    initializeOperators();
}

void ExpressionParser::initializeOperators() {
    // Arithmetic operators
    operatorPrecedence["+"] = 1;
    operatorPrecedence["-"] = 1;
    operatorPrecedence["*"] = 2;
    operatorPrecedence["/"] = 2;
    operatorPrecedence["^"] = 3;
    operatorPrecedence["%"] = 2;
    
    // Associativity (true = left associative)
    operatorAssociativity["+"] = true;
    operatorAssociativity["-"] = true;
    operatorAssociativity["*"] = true;
    operatorAssociativity["/"] = true;
    operatorAssociativity["^"] = false; // Right associative
    operatorAssociativity["%"] = true;
    
    // Mathematical functions
    functionArity["sin"] = 1;
    functionArity["cos"] = 1;
    functionArity["tan"] = 1;
    functionArity["log"] = 1;
    functionArity["ln"] = 1;
    functionArity["sqrt"] = 1;
    functionArity["abs"] = 1;
    functionArity["max"] = 2;
    functionArity["min"] = 2;
    functionArity["pow"] = 2;
}

std::vector<Token> ExpressionParser::infixToPostfix(const std::vector<Token>& infixTokens) {
    std::vector<Token> postfix;
    Stack<Token> operatorStack;
    
    for (const auto& token : infixTokens) {
        if (token.isNumber() || token.isVariable()) {
            postfix.push_back(token);
        }
        else if (token.isFunction()) {
            operatorStack.push(token);
        }
        else if (token.type == TokenType::LEFT_PARENTHESIS) {
            operatorStack.push(token);
        }
        else if (token.type == TokenType::RIGHT_PARENTHESIS) {
            while (!operatorStack.isEmpty() && 
                   operatorStack.top().type != TokenType::LEFT_PARENTHESIS) {
                postfix.push_back(operatorStack.top());
                operatorStack.pop();
            }
            
            if (!operatorStack.isEmpty()) {
                operatorStack.pop(); // Remove left parenthesis
            }
            
            // Handle function
            if (!operatorStack.isEmpty() && 
                operatorStack.top().isFunction()) {
                postfix.push_back(operatorStack.top());
                operatorStack.pop();
            }
        }
        else if (token.isOperator()) {
            while (!operatorStack.isEmpty() && 
                   operatorStack.top().isOperator() &&
                   ((operatorAssociativity[token.value] && 
                     operatorPrecedence[token.value] <= operatorPrecedence[operatorStack.top().value]) ||
                    (!operatorAssociativity[token.value] && 
                     operatorPrecedence[token.value] < operatorPrecedence[operatorStack.top().value]))) {
                postfix.push_back(operatorStack.top());
                operatorStack.pop();
            }
            operatorStack.push(token);
        }
    }
    
    // Pop remaining operators
    while (!operatorStack.isEmpty()) {
        postfix.push_back(operatorStack.top());
        operatorStack.pop();
    }
    
    return postfix;
}

double ExpressionParser::evaluatePostfix(const std::vector<Token>& postfixTokens,
                                       const std::unordered_map<std::string, double>& variables) {
    Stack<double> operandStack;
    
    for (const auto& token : postfixTokens) {
        if (token.isNumber()) {
            operandStack.push(std::stod(token.value));
        }
        else if (token.isVariable()) {
            auto it = variables.find(token.value);
            if (it != variables.end()) {
                operandStack.push(it->second);
            } else {
                throw std::runtime_error("Undefined variable: " + token.value);
            }
        }
        else if (token.isOperator()) {
            if (operandStack.size() < 2) {
                throw std::runtime_error("Insufficient operands for operator: " + token.value);
            }
            
            double b = operandStack.top();
            operandStack.pop();
            double a = operandStack.top();
            operandStack.pop();
            
            double result;
            if (token.value == "+") {
                result = a + b;
            } else if (token.value == "-") {
                result = a - b;
            } else if (token.value == "*") {
                result = a * b;
            } else if (token.value == "/") {
                if (b == 0) {
                    throw std::runtime_error("Division by zero");
                }
                result = a / b;
            } else if (token.value == "^") {
                result = std::pow(a, b);
            } else if (token.value == "%") {
                result = std::fmod(a, b);
            } else {
                throw std::runtime_error("Unknown operator: " + token.value);
            }
            
            operandStack.push(result);
        }
        else if (token.isFunction()) {
            if (operandStack.size() < 1) {
                throw std::runtime_error("Insufficient operands for function: " + token.value);
            }
            
            double operand = operandStack.top();
            operandStack.pop();
            
            double result;
            if (token.value == "sin") {
                result = std::sin(operand);
            } else if (token.value == "cos") {
                result = std::cos(operand);
            } else if (token.value == "tan") {
                result = std::tan(operand);
            } else if (token.value == "log") {
                result = std::log10(operand);
            } else if (token.value == "ln") {
                result = std::log(operand);
            } else if (token.value == "sqrt") {
                result = std::sqrt(operand);
            } else if (token.value == "abs") {
                result = std::abs(operand);
            } else {
                throw std::runtime_error("Unknown function: " + token.value);
            }
            
            operandStack.push(result);
        }
    }
    
    if (operandStack.size() != 1) {
        throw std::runtime_error("Invalid expression: too many operands");
    }
    
    return operandStack.top();
}

double ExpressionParser::evaluate(const std::string& expression,
                                const std::unordered_map<std::string, double>& variables) {
    try {
        auto tokens = tokenizeExpression(expression);
        auto postfixTokens = infixToPostfix(tokens);
        return evaluatePostfix(postfixTokens, variables);
    } catch (const std::exception& e) {
        throw std::runtime_error("Evaluation error: " + std::string(e.what()));
    }
}
```

### 4. Expression Tree

#### ExpressionTree.h
```cpp
#ifndef EXPRESSION_TREE_H
#define EXPRESSION_TREE_H

#include "Token.h"
#include <memory>
#include <unordered_map>

class ExpressionTreeNode {
public:
    Token token;
    std::unique_ptr<ExpressionTreeNode> left;
    std::unique_ptr<ExpressionTreeNode> right;
    
    ExpressionTreeNode(const Token& t) 
        : token(t), left(nullptr), right(nullptr) {}
    
    bool isLeaf() const {
        return left == nullptr && right == nullptr;
    }
    
    double evaluate(const std::unordered_map<std::string, double>& variables = {}) const;
};

class ExpressionTree {
private:
    std::unique_ptr<ExpressionTreeNode> root;
    
    std::unique_ptr<ExpressionTreeNode> buildTreeFromPostfix(const std::vector<Token>& postfixTokens);
    void printTreeHelper(const ExpressionTreeNode* node, int depth = 0) const;
    
public:
    ExpressionTree();
    explicit ExpressionTree(const std::string& expression);
    
    void buildFromExpression(const std::string& expression);
    double evaluate(const std::unordered_map<std::string, double>& variables = {}) const;
    void printTree() const;
    std::string toInfix() const;
    std::string toPostfix() const;
    std::string toPrefix() const;
    
    bool isEmpty() const {
        return root == nullptr;
    }
};

#endif // EXPRESSION_TREE_H
```

### 5. Variable Manager

#### VariableManager.h
```cpp
#ifndef VARIABLE_MANAGER_H
#define VARIABLE_MANAGER_H

#include <unordered_map>
#include <string>
#include <vector>

class VariableManager {
private:
    std::unordered_map<std::string, double> variables;
    std::vector<std::string> variableHistory;
    
public:
    VariableManager() = default;
    
    // Variable operations
    void setVariable(const std::string& name, double value);
    double getVariable(const std::string& name) const;
    bool hasVariable(const std::string& name) const;
    void removeVariable(const std::string& name);
    void clearVariables();
    
    // Batch operations
    void setVariables(const std::unordered_map<std::string, double>& vars);
    std::unordered_map<std::string, double> getAllVariables() const;
    
    // Utility methods
    std::vector<std::string> getVariableNames() const;
    size_t getVariableCount() const;
    void listVariables() const;
    
    // History management
    void saveState();
    void restoreState();
    std::vector<std::string> getHistory() const;
    
    // File operations
    bool saveToFile(const std::string& filename) const;
    bool loadFromFile(const std::string& filename);
};

#endif // VARIABLE_MANAGER_H
```

## User Interface

### Main Application
```cpp
#include "ExpressionParser.h"
#include "VariableManager.h"
#include "ExpressionTree.h"
#include <iostream>
#include <iomanip>

class ExpressionEvaluatorApp {
private:
    ExpressionParser parser;
    VariableManager variableManager;
    
    void displayMenu() const;
    void evaluateExpression();
    void manageVariables();
    void convertNotation();
    void buildExpressionTree();
    void batchEvaluation();
    void help() const;
    
public:
    void run();
};

void ExpressionEvaluatorApp::displayMenu() const {
    std::cout << "\n=== EXPRESSION EVALUATOR ===" << std::endl;
    std::cout << "1. Evaluate expression" << std::endl;
    std::cout << "2. Manage variables" << std::endl;
    std::cout << "3. Convert notation" << std::endl;
    std::cout << "4. Build expression tree" << std::endl;
    std::cout << "5. Batch evaluation" << std::endl;
    std::cout << "6. Help" << std::endl;
    std::cout << "0. Exit" << std::endl;
    std::cout << "===========================" << std::endl;
    std::cout << "Enter your choice: ";
}

void ExpressionEvaluatorApp::evaluateExpression() {
    std::string expression;
    std::cout << "Enter expression: ";
    std::cin.ignore();
    std::getline(std::cin, expression);
    
    try {
        auto variables = variableManager.getAllVariables();
        double result = parser.evaluate(expression, variables);
        
        std::cout << "Result: " << std::fixed << std::setprecision(6) 
                  << result << std::endl;
                  
        // Show postfix notation
        std::string postfix = parser.infixToPostfix(expression);
        std::cout << "Postfix: " << postfix << std::endl;
        
    } catch (const std::exception& e) {
        std::cout << "Error: " << e.what() << std::endl;
    }
}

int main() {
    std::cout << "===========================================" << std::endl;
    std::cout << "        EXPRESSION EVALUATOR v1.0         " << std::endl;
    std::cout << "===========================================" << std::endl;
    
    try {
        ExpressionEvaluatorApp app;
        app.run();
    } catch (const std::exception& e) {
        std::cerr << "Fatal error: " << e.what() << std::endl;
        return 1;
    }
    
    std::cout << "Thank you for using Expression Evaluator!" << std::endl;
    return 0;
}
```

## Advanced Features

### Function Support
```cpp
class FunctionEvaluator {
private:
    std::unordered_map<std::string, std::function<double(const std::vector<double>&)>> functions;
    
public:
    FunctionEvaluator() {
        initializeBuiltInFunctions();
    }
    
    void initializeBuiltInFunctions() {
        functions["sin"] = [](const std::vector<double>& args) {
            if (args.size() != 1) throw std::runtime_error("sin requires 1 argument");
            return std::sin(args[0]);
        };
        
        functions["cos"] = [](const std::vector<double>& args) {
            if (args.size() != 1) throw std::runtime_error("cos requires 1 argument");
            return std::cos(args[0]);
        };
        
        functions["max"] = [](const std::vector<double>& args) {
            if (args.size() < 2) throw std::runtime_error("max requires at least 2 arguments");
            return *std::max_element(args.begin(), args.end());
        };
        
        functions["min"] = [](const std::vector<double>& args) {
            if (args.size() < 2) throw std::runtime_error("min requires at least 2 arguments");
            return *std::min_element(args.begin(), args.end());
        };
    }
    
    double evaluateFunction(const std::string& name, const std::vector<double>& args) {
        auto it = functions.find(name);
        if (it != functions.end()) {
            return it->second(args);
        }
        throw std::runtime_error("Unknown function: " + name);
    }
    
    void addFunction(const std::string& name, 
                    std::function<double(const std::vector<double>&)> func) {
        functions[name] = func;
    }
};
```

### Error Handling and Validation
```cpp
class ExpressionValidator {
public:
    struct ValidationResult {
        bool isValid;
        std::string errorMessage;
        size_t errorPosition;
    };
    
    static ValidationResult validateExpression(const std::string& expression) {
        ValidationResult result = {true, "", 0};
        
        // Check for empty expression
        if (expression.empty()) {
            result.isValid = false;
            result.errorMessage = "Empty expression";
            return result;
        }
        
        // Check parentheses balance
        int balance = 0;
        for (size_t i = 0; i < expression.length(); ++i) {
            if (expression[i] == '(') {
                balance++;
            } else if (expression[i] == ')') {
                balance--;
                if (balance < 0) {
                    result.isValid = false;
                    result.errorMessage = "Unmatched closing parenthesis";
                    result.errorPosition = i;
                    return result;
                }
            }
        }
        
        if (balance != 0) {
            result.isValid = false;
            result.errorMessage = "Unmatched opening parenthesis";
            return result;
        }
        
        // Check for invalid characters
        for (size_t i = 0; i < expression.length(); ++i) {
            char c = expression[i];
            if (!std::isdigit(c) && !std::isalpha(c) && 
                c != '+' && c != '-' && c != '*' && c != '/' && 
                c != '^' && c != '%' && c != '(' && c != ')' && 
                c != '.' && c != ' ' && c != ',') {
                result.isValid = false;
                result.errorMessage = "Invalid character: " + std::string(1, c);
                result.errorPosition = i;
                return result;
            }
        }
        
        return result;
    }
};
```

## Cross-Platform Considerations

### CMakeLists.txt
```cmake
cmake_minimum_required(VERSION 3.10)
project(ExpressionEvaluator)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# Compiler flags
if(MSVC)
    set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} /W4")
else()
    set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -Wall -Wextra -Wpedantic")
endif()

# Source files
set(SOURCES
    src/ExpressionParser.cpp
    src/Stack.cpp
    src/ExpressionTree.cpp
    src/Tokenizer.cpp
    src/Evaluator.cpp
    src/VariableManager.cpp
    src/main.cpp
)

# Create executable
add_executable(${PROJECT_NAME} ${SOURCES})

# Include directories
target_include_directories(${PROJECT_NAME} PRIVATE src)

# Testing
enable_testing()
add_subdirectory(tests)
```

## Testing Strategy

### Unit Tests
```cpp
#include <gtest/gtest.h>
#include "ExpressionParser.h"
#include "Stack.h"

class ExpressionParserTest : public ::testing::Test {
protected:
    ExpressionParser parser;
};

TEST_F(ExpressionParserTest, BasicArithmetic) {
    EXPECT_DOUBLE_EQ(parser.evaluate("2 + 3"), 5.0);
    EXPECT_DOUBLE_EQ(parser.evaluate("10 - 4"), 6.0);
    EXPECT_DOUBLE_EQ(parser.evaluate("3 * 4"), 12.0);
    EXPECT_DOUBLE_EQ(parser.evaluate("15 / 3"), 5.0);
}

TEST_F(ExpressionParserTest, ComplexExpressions) {
    EXPECT_DOUBLE_EQ(parser.evaluate("2 + 3 * 4"), 14.0);
    EXPECT_DOUBLE_EQ(parser.evaluate("(2 + 3) * 4"), 20.0);
    EXPECT_DOUBLE_EQ(parser.evaluate("2 ^ 3"), 8.0);
}

TEST_F(ExpressionParserTest, Variables) {
    std::unordered_map<std::string, double> vars = {{"x", 5.0}, {"y", 3.0}};
    EXPECT_DOUBLE_EQ(parser.evaluate("x + y", vars), 8.0);
    EXPECT_DOUBLE_EQ(parser.evaluate("x * y - 2", vars), 13.0);
}

TEST_F(ExpressionParserTest, Functions) {
    EXPECT_NEAR(parser.evaluate("sin(0)"), 0.0, 1e-10);
    EXPECT_NEAR(parser.evaluate("cos(0)"), 1.0, 1e-10);
    EXPECT_DOUBLE_EQ(parser.evaluate("sqrt(16)"), 4.0);
}

TEST_F(ExpressionParserTest, NotationConversion) {
    EXPECT_EQ(parser.infixToPostfix("2 + 3"), "2 3 +");
    EXPECT_EQ(parser.infixToPostfix("(2 + 3) * 4"), "2 3 + 4 *");
}
```

## Performance Requirements
- Parse expressions up to 1000 characters
- Evaluate expressions in < 100ms for typical expressions
- Support up to 100 variables
- Memory usage < 50MB for maximum expression size
- Handle nested parentheses up to 50 levels deep

## Extension Ideas
- Support for complex numbers
- Matrix expressions
- Calculus operations (derivatives, integrals)
- Graph plotting capabilities
- Web interface using Emscripten
- Mobile application
- Plugin system for custom functions
- Expression optimization and simplification

## Deliverables
1. Complete source code with proper class structure
2. CMake and Makefile build configurations
3. Comprehensive unit tests
4. Documentation (README, code comments)
5. Sample expression files for testing
6. Build and run scripts for all platforms
7. User manual with examples

## Success Criteria
- All expression operations work correctly
- Error handling prevents crashes
- File I/O operations work reliably
- Code compiles and runs on all target platforms
- Unit tests achieve 90% code coverage
- Performance meets specified requirements
- User interface is intuitive and responsive
