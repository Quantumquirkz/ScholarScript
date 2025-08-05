/**
 * @file menu_calculator.cpp
 * @brief Menu-Driven Calculator - Lab 2 Exercise 3
 * @author Student Name
 * @date December 2024
 * 
 * This program demonstrates control structures by implementing
 * a menu-driven calculator with various arithmetic operations.
 */

#include <iostream>
#include <iomanip>
#include <cmath>
#include <limits>
#include <string>

using namespace std;

// Menu options
enum MenuOption {
    ADDITION = 1,
    SUBTRACTION = 2,
    MULTIPLICATION = 3,
    DIVISION = 4,
    MODULUS = 5,
    POWER = 6,
    SQUARE_ROOT = 7,
    EXIT = 8
};

/**
 * @brief Display program header and instructions
 */
void displayHeader() {
    cout << "==========================================" << endl;
    cout << "         Menu-Driven Calculator" << endl;
    cout << "    Applied Programming Tools I - Lab 2" << endl;
    cout << "==========================================" << endl;
    cout << endl;
    cout << "This calculator performs various mathematical operations." << endl;
    cout << endl;
}

/**
 * @brief Display the main menu
 */
void displayMenu() {
    cout << "Available Operations:" << endl;
    cout << "1. Addition (+)" << endl;
    cout << "2. Subtraction (-)" << endl;
    cout << "3. Multiplication (*)" << endl;
    cout << "4. Division (/)" << endl;
    cout << "5. Modulus (%)" << endl;
    cout << "6. Power (^)" << endl;
    cout << "7. Square Root (√)" << endl;
    cout << "8. Exit" << endl;
    cout << endl;
}

/**
 * @brief Get valid menu choice from user
 * @return Valid menu option (1-8)
 */
MenuOption getMenuChoice() {
    int choice;
    bool validChoice = false;
    
    while (!validChoice) {
        cout << "Enter your choice (1-8): ";
        
        if (cin >> choice) {
            if (choice >= 1 && choice <= 8) {
                validChoice = true;
            } else {
                cout << "Error: Please enter a number between 1 and 8." << endl;
            }
        } else {
            cout << "Error: Please enter a valid number." << endl;
            cin.clear();
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
        }
    }
    
    return static_cast<MenuOption>(choice);
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
            cin.clear();
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
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
 * @brief Perform modulus operation with error checking
 * @param a First operand
 * @param b Second operand
 * @param success Reference to boolean indicating success
 * @return Result of modulus or 0 if invalid
 */
int modulus(int a, int b, bool& success) {
    if (b == 0) {
        success = false;
        return 0;
    }
    success = true;
    return a % b;
}

/**
 * @brief Perform power operation
 * @param base Base number
 * @param exponent Exponent
 * @return Result of power operation
 */
double power(double base, double exponent) {
    return pow(base, exponent);
}

/**
 * @brief Perform square root operation with error checking
 * @param number Number to find square root of
 * @param success Reference to boolean indicating success
 * @return Square root or 0 if invalid
 */
double squareRoot(double number, bool& success) {
    if (number < 0) {
        success = false;
        return 0;
    }
    success = true;
    return sqrt(number);
}

/**
 * @brief Get operation symbol for display
 * @param operation Menu option
 * @return String representation of operation
 */
string getOperationSymbol(MenuOption operation) {
    switch (operation) {
        case ADDITION: return "+";
        case SUBTRACTION: return "-";
        case MULTIPLICATION: return "*";
        case DIVISION: return "/";
        case MODULUS: return "%";
        case POWER: return "^";
        case SQUARE_ROOT: return "√";
        default: return "?";
    }
}

/**
 * @brief Display calculation results
 * @param operation Menu option
 * @param a First operand
 * @param b Second operand (if applicable)
 * @param result Calculation result
 * @param success Whether operation was successful
 */
void displayResult(MenuOption operation, double a, double b, double result, bool success) {
    cout << endl;
    cout << "==========================================" << endl;
    cout << "              Result" << endl;
    cout << "==========================================" << endl;
    cout << fixed << setprecision(4);
    
    if (success) {
        switch (operation) {
            case SQUARE_ROOT:
                cout << "√(" << a << ") = " << result << endl;
                break;
            default:
                cout << a << " " << getOperationSymbol(operation) << " " << b << " = " << result << endl;
                break;
        }
    } else {
        cout << "Error: Invalid operation!" << endl;
        switch (operation) {
            case DIVISION:
                cout << "Division by zero is not allowed." << endl;
                break;
            case MODULUS:
                cout << "Modulus by zero is not allowed." << endl;
                break;
            case SQUARE_ROOT:
                cout << "Cannot calculate square root of negative number." << endl;
                break;
            default:
                cout << "Please check your input." << endl;
                break;
        }
    }
    
    cout << "==========================================" << endl;
}

/**
 * @brief Perform calculation based on menu choice
 * @param operation Selected menu option
 */
void performCalculation(MenuOption operation) {
    double a, b, result;
    int intA, intB, intResult;
    bool success = true;
    
    switch (operation) {
        case ADDITION:
            a = getValidNumber("Enter first number: ");
            b = getValidNumber("Enter second number: ");
            result = add(a, b);
            displayResult(operation, a, b, result, true);
            break;
            
        case SUBTRACTION:
            a = getValidNumber("Enter first number: ");
            b = getValidNumber("Enter second number: ");
            result = subtract(a, b);
            displayResult(operation, a, b, result, true);
            break;
            
        case MULTIPLICATION:
            a = getValidNumber("Enter first number: ");
            b = getValidNumber("Enter second number: ");
            result = multiply(a, b);
            displayResult(operation, a, b, result, true);
            break;
            
        case DIVISION:
            a = getValidNumber("Enter dividend: ");
            b = getValidNumber("Enter divisor: ");
            result = divide(a, b, success);
            displayResult(operation, a, b, result, success);
            break;
            
        case MODULUS:
            intA = static_cast<int>(getValidNumber("Enter first integer: "));
            intB = static_cast<int>(getValidNumber("Enter second integer: "));
            intResult = modulus(intA, intB, success);
            displayResult(operation, intA, intB, intResult, success);
            break;
            
        case POWER:
            a = getValidNumber("Enter base: ");
            b = getValidNumber("Enter exponent: ");
            result = power(a, b);
            displayResult(operation, a, b, result, true);
            break;
            
        case SQUARE_ROOT:
            a = getValidNumber("Enter number: ");
            result = squareRoot(a, success);
            displayResult(operation, a, 0, result, success);
            break;
            
        case EXIT:
            cout << "Exiting calculator..." << endl;
            break;
    }
}

/**
 * @brief Ask user if they want to continue
 * @return True if user wants to continue, false otherwise
 */
bool askToContinue() {
    char choice;
    cout << endl;
    cout << "Continue? (y/n): ";
    cin >> choice;
    
    // Clear input buffer
    cin.ignore(numeric_limits<streamsize>::max(), '\n');
    
    return (choice == 'y' || choice == 'Y');
}

/**
 * @brief Display program completion message
 */
void displayCompletion() {
    cout << endl;
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
        
        do {
            displayMenu();
            MenuOption choice = getMenuChoice();
            
            if (choice == EXIT) {
                continueCalculations = false;
            } else {
                performCalculation(choice);
                continueCalculations = askToContinue();
            }
            
            if (continueCalculations) {
                cout << endl;
                cout << "Returning to menu..." << endl;
                cout << endl;
            }
            
        } while (continueCalculations);
        
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