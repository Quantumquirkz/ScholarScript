/*
 * Student Grade Calculator
 * Lab 1 - Exercise 4
 * 
 * This program calculates and displays student grades based on three test scores.
 * It determines the average score and assigns a letter grade.
 */

#include <iostream>
#include <string>
#include <iomanip>
using namespace std;

int main() {
    string studentName;
    double test1, test2, test3, average;
    char letterGrade;
    
    cout << "=== Student Grade Calculator ===" << endl;
    cout << endl;
    
    // Get student information
    cout << "Enter student name: ";
    getline(cin, studentName);
    
    cout << "Enter test 1 score: ";
    cin >> test1;
    
    cout << "Enter test 2 score: ";
    cin >> test2;
    
    cout << "Enter test 3 score: ";
    cin >> test3;
    
    // Calculate average
    average = (test1 + test2 + test3) / 3.0;
    
    // Determine letter grade
    if (average >= 90) {
        letterGrade = 'A';
    } else if (average >= 80) {
        letterGrade = 'B';
    } else if (average >= 70) {
        letterGrade = 'C';
    } else if (average >= 60) {
        letterGrade = 'D';
    } else {
        letterGrade = 'F';
    }
    
    // Display results
    cout << endl;
    cout << "Grade Report for " << studentName << ":" << endl;
    cout << "----------------------------------------" << endl;
    cout << "Test 1: " << test1 << endl;
    cout << "Test 2: " << test2 << endl;
    cout << "Test 3: " << test3 << endl;
    cout << "Average: " << fixed << setprecision(1) << average << endl;
    cout << "Letter Grade: " << letterGrade << endl;
    
    // Additional feedback
    cout << endl;
    if (letterGrade == 'A') {
        cout << "Excellent work! Keep it up!" << endl;
    } else if (letterGrade == 'B') {
        cout << "Good work! You're doing well." << endl;
    } else if (letterGrade == 'C') {
        cout << "Satisfactory. Consider reviewing the material." << endl;
    } else if (letterGrade == 'D') {
        cout << "Needs improvement. Please seek additional help." << endl;
    } else {
        cout << "Failing grade. Immediate action required." << endl;
    }
    
    return 0;
} 