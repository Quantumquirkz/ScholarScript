/*
 * Multiplication Table Generator
 * Lab 2 - Exercise 3
 * 
 * This program generates multiplication tables using loops.
 */

#include <iostream>
#include <iomanip>
using namespace std;

int main() {
    int number;
    char continueChoice;
    
    do {
        cout << "=== Multiplication Table Generator ===" << endl;
        cout << endl;
        
        // Get input from user
        cout << "Enter a number: ";
        cin >> number;
        
        cout << endl;
        cout << "Multiplication Table for " << number << ":" << endl;
        cout << "========================================" << endl;
        
        // Generate multiplication table using for loop
        for (int i = 1; i <= 10; i++) {
            cout << setw(2) << number << " x " << setw(2) << i << " = " 
                 << setw(3) << (number * i) << endl;
        }
        
        cout << endl;
        
        // Ask if user wants to generate another table
        cout << "Generate another table? (y/n): ";
        cin >> continueChoice;
        
        cout << endl;
        
    } while (continueChoice == 'y' || continueChoice == 'Y');
    
    cout << "Thank you for using the Multiplication Table Generator!" << endl;
    
    return 0;
} 