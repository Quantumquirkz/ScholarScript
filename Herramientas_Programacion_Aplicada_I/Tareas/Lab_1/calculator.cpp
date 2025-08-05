/**
 * @file calculator.cpp
 * @brief Basic Calculator Program - Lab 1 Exercise 2
 * @author Student Name
 * @date December 2024
 * 
 * This program demonstrates arithmetic operations, input validation,
 * and error handling in C++ for Applied Programming Tools I.
 */

#include <iostream>
#include <iomanip>
#include <limits>
#include <string>

using namespace std;

/**
 * @brief Display calculator header and instructions
 */
void displayHeader() {
    cout << "==========================================" << endl;
    cout << "           Simple Calculator" << endl;
    cout << "    Applied Programming Tools I" << endl;
    cout << "==========================================" << endl;
    cout << endl;
    cout << "This calculator performs basic arithmetic operations:" << endl;
    cout << "- Addition (+)" << endl;
    cout << "- Subtraction (-)" << endl;
    cout << "- Multiplication (*)" << endl;
    cout << "- Division (/)" << endl;
    cout << endl;
}

/**
 * @brief Get a valid number from user input
 * @param prompt Message to display to user
 * @return Valid number entered by user
 */
double getValidNumber(const string& prompt) {
    double number;
    bool validInput = false;
    
    while (!validInput) {
        cout << prompt;
        
        if (cin >> number) {
            validInput = true;
        } else {
            cout << "Error: Please enter a valid number." << endl;
            cin.clear(); // Clear error flags
            cin.ignore(numeric_limits<streamsize>::max(), '\n'); // Clear input buffer
        }
    }
    
    return number;
}

/**
 * @brief Perform addition operation
 * @param a First operand
 * @param b Second operand
 * @return Result of addition
 */
double add(double a, double b) {
    return a + b;
}

/**
 * @brief Perform subtraction operation
 * @param a First operand
 * @param b Second operand
 * @return Result of subtraction
 */
double subtract(double a, double b) {
    return a - b;
}

/**
 * @brief Perform multiplication operation
 * @param a First operand
 * @param b Second operand
 * @return Result of multiplication
 */
double multiply(double a, double b) {
    return a * b;
}

/**
 * @brief Perform division operation with error checking
 * @param a First operand (dividend)
 * @param b Second operand (divisor)
 * @param success Reference to boolean indicating success
 * @return Result of division or 0 if division by zero
 */
double divide(double a, double b, bool& success) {
    if (b == 0) {
        success = false;
        return 0;
    }
    success = true;
    return a / b;
}

/**
 * @brief Display calculation results in a formatted table
 * @param num1 First number
 * @param num2 Second number
 */
void displayResults(double num1, double num2) {
    cout << endl;
    cout << "==========================================" << endl;
    cout << "              Results" << endl;
    cout << "==========================================" << endl;
    cout << fixed << setprecision(2);
    
    // Addition
    cout << setw(10) << num1 << " + " << setw(10) << num2 << " = " 
         << setw(12) << add(num1, num2) << endl;
    
    // Subtraction
    cout << setw(10) << num1 << " - " << setw(10) << num2 << " = " 
         << setw(12) << subtract(num1, num2) << endl;
    
    // Multiplication
    cout << setw(10) << num1 << " * " << setw(10) << num2 << " = " 
         << setw(12) << multiply(num1, num2) << endl;
    
    // Division
    bool divisionSuccess;
    double divisionResult = divide(num1, num2, divisionSuccess);
    
    if (divisionSuccess) {
        cout << setw(10) << num1 << " / " << setw(10) << num2 << " = " 
             << setw(12) << divisionResult << endl;
    } else {
        cout << setw(10) << num1 << " / " << setw(10) << num2 << " = " 
             << setw(12) << "UNDEFINED" << endl;
        cout << "Note: Division by zero is not allowed." << endl;
    }
    
    cout << "==========================================" << endl;
}

/**
 * @brief Display additional mathematical information
 * @param num1 First number
 * @param num2 Second number
 */
void displayAdditionalInfo(double num1, double num2) {
    cout << endl;
    cout << "Additional Information:" << endl;
    cout << "Average: " << fixed << setprecision(2) << (num1 + num2) / 2.0 << endl;
    cout << "Maximum: " << max(num1, num2) << endl;
    cout << "Minimum: " << min(num1, num2) << endl;
    cout << "Difference: " << abs(num1 - num2) << endl;
    cout << endl;
}

/**
 * @brief Ask user if they want to perform another calculation
 * @return True if user wants to continue, false otherwise
 */
bool askToContinue() {
    char choice;
    cout << "Would you like to perform another calculation? (y/n): ";
    cin >> choice;
    
    // Clear input buffer
    cin.ignore(numeric_limits<streamsize>::max(), '\n');
    
    return (choice == 'y' || choice == 'Y');
}

/**
 * @brief Display program completion message
 */
void displayCompletion() {
    cout << "==========================================" << endl;
    cout << "    Thank you for using the calculator!" << endl;
    cout << "    Program completed successfully." << endl;
    cout << "==========================================" << endl;
}

/**
 * @brief Main function - program entry point
 * @return Exit status (0 for success)
 */
int main() {
    try {
        displayHeader();
        
        bool continueCalculations = true;
        
        while (continueCalculations) {
            // Get user input
            double number1 = getValidNumber("Enter first number: ");
            double number2 = getValidNumber("Enter second number: ");
            
            // Display results
            displayResults(number1, number2);
            
            // Display additional information
            displayAdditionalInfo(number1, number2);
            
            // Ask if user wants to continue
            continueCalculations = askToContinue();
            
            if (continueCalculations) {
                cout << endl;
                cout << "Starting new calculation..." << endl;
                cout << endl;
            }
        }
        
        displayCompletion();
        return 0;
        
    } catch (const exception& e) {
        cerr << "Error: " << e.what() << endl;
        return 1;
    } catch (...) {
        cerr << "Unknown error occurred." << endl;
        return 1;
    }
} 