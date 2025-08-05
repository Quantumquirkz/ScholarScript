/*
 * Function Library
 * Lab 3 - Exercise 1
 * 
 * This program demonstrates various mathematical functions including
 * basic operations, power, factorial, and utility functions.
 */

#include <iostream>
#include <cmath>
#include <iomanip>
using namespace std;

// Function prototypes
int add(int a, int b);
int subtract(int a, int b);
int multiply(int a, int b);
double divide(int a, int b);
int power(int base, int exponent);
int factorial(int n);
int absolute(int n);
int maximum(int a, int b);
int minimum(int a, int b);

int main() {
    int num1, num2, choice;
    
    cout << "=== Function Library Test ===" << endl;
    cout << endl;
    
    // Get input from user
    cout << "Enter first number: ";
    cin >> num1;
    
    cout << "Enter second number: ";
    cin >> num2;
    
    cout << endl;
    cout << "Function Results:" << endl;
    cout << "========================================" << endl;
    
    // Test basic operations
    cout << "Addition: " << num1 << " + " << num2 << " = " << add(num1, num2) << endl;
    cout << "Subtraction: " << num1 << " - " << num2 << " = " << subtract(num1, num2) << endl;
    cout << "Multiplication: " << num1 << " * " << num2 << " = " << multiply(num1, num2) << endl;
    
    // Test division with error handling
    if (num2 != 0) {
        cout << "Division: " << num1 << " / " << num2 << " = " << fixed << setprecision(2) << divide(num1, num2) << endl;
    } else {
        cout << "Division: Cannot divide by zero!" << endl;
    }
    
    // Test power function
    cout << "Power: " << num1 << "^" << num2 << " = " << power(num1, num2) << endl;
    
    // Test factorial (use smaller number to avoid overflow)
    int smaller = (num1 < num2) ? num1 : num2;
    if (smaller >= 0 && smaller <= 12) { // Limit to avoid overflow
        cout << "Factorial: " << smaller << "! = " << factorial(smaller) << endl;
    } else {
        cout << "Factorial: Number too large for calculation" << endl;
    }
    
    // Test utility functions
    cout << "Absolute value of " << num1 << ": " << absolute(num1) << endl;
    cout << "Maximum: max(" << num1 << ", " << num2 << ") = " << maximum(num1, num2) << endl;
    cout << "Minimum: min(" << num1 << ", " << num2 << ") = " << minimum(num1, num2) << endl;
    
    return 0;
}

// Function implementations
int add(int a, int b) {
    return a + b;
}

int subtract(int a, int b) {
    return a - b;
}

int multiply(int a, int b) {
    return a * b;
}

double divide(int a, int b) {
    if (b == 0) {
        return 0; // Return 0 for division by zero
    }
    return static_cast<double>(a) / b;
}

int power(int base, int exponent) {
    if (exponent < 0) {
        return 0; // Return 0 for negative exponents
    }
    
    int result = 1;
    for (int i = 0; i < exponent; i++) {
        result *= base;
    }
    return result;
}

int factorial(int n) {
    if (n < 0) {
        return 0; // Return 0 for negative numbers
    }
    
    if (n == 0 || n == 1) {
        return 1;
    }
    
    int result = 1;
    for (int i = 2; i <= n; i++) {
        result *= i;
    }
    return result;
}

int absolute(int n) {
    return (n < 0) ? -n : n;
}

int maximum(int a, int b) {
    return (a > b) ? a : b;
}

int minimum(int a, int b) {
    return (a < b) ? a : b;
} 