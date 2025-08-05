/*
 * Number Classifier
 * Lab 2 - Exercise 1
 * 
 * This program classifies numbers based on various criteria including
 * sign, parity, and number of digits.
 */

#include <iostream>
#include <cmath>
using namespace std;

int main() {
    int number;
    
    cout << "=== Number Classification ===" << endl;
    cout << endl;
    
    // Get input from user
    cout << "Enter a number: ";
    cin >> number;
    
    cout << endl;
    cout << "Classification:" << endl;
    cout << "----------------------------------------" << endl;
    
    // Classify by sign
    cout << "- Sign: ";
    if (number > 0) {
        cout << "Positive";
    } else if (number < 0) {
        cout << "Negative";
    } else {
        cout << "Zero";
    }
    cout << endl;
    
    // Classify by parity (even/odd)
    cout << "- Parity: ";
    if (number % 2 == 0) {
        cout << "Even";
    } else {
        cout << "Odd";
    }
    cout << endl;
    
    // Classify by number of digits
    cout << "- Digits: ";
    if (number == 0) {
        cout << "Single digit";
    } else {
        int absNumber = abs(number);
        int digitCount = 1;
        
        while (absNumber >= 10) {
            absNumber /= 10;
            digitCount++;
        }
        
        if (digitCount == 1) {
            cout << "Single digit";
        } else if (digitCount == 2) {
            cout << "Double digit";
        } else if (digitCount == 3) {
            cout << "Triple digit";
        } else {
            cout << digitCount << "-digit number";
        }
    }
    cout << endl;
    
    // Classify by range
    cout << "- Range: ";
    if (number == 0) {
        cout << "Zero";
    } else if (abs(number) >= 1 && abs(number) <= 9) {
        cout << "1-9";
    } else if (abs(number) >= 10 && abs(number) <= 99) {
        cout << "10-99";
    } else if (abs(number) >= 100 && abs(number) <= 999) {
        cout << "100-999";
    } else if (abs(number) >= 1000 && abs(number) <= 9999) {
        cout << "1000-9999";
    } else {
        cout << "10000+";
    }
    cout << endl;
    
    // Additional classifications
    cout << "- Properties: ";
    bool hasProperties = false;
    
    // Check if it's a perfect square
    int sqrtNum = sqrt(abs(number));
    if (sqrtNum * sqrtNum == abs(number)) {
        cout << "Perfect square";
        hasProperties = true;
    }
    
    // Check if it's a perfect cube
    int cubeRoot = cbrt(abs(number));
    if (cubeRoot * cubeRoot * cubeRoot == abs(number)) {
        if (hasProperties) cout << ", ";
        cout << "Perfect cube";
        hasProperties = true;
    }
    
    // Check if it's a power of 2
    if (number > 0 && (number & (number - 1)) == 0) {
        if (hasProperties) cout << ", ";
        cout << "Power of 2";
        hasProperties = true;
    }
    
    if (!hasProperties) {
        cout << "None";
    }
    cout << endl;
    
    return 0;
} 