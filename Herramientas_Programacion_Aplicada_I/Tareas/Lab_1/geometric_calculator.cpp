/*
 * Geometric Calculator
 * Lab 1 - Exercise 5
 * 
 * This program calculates area and perimeter/circumference for different geometric shapes.
 */

#include <iostream>
#include <iomanip>
#include <cmath>
using namespace std;

int main() {
    double rectLength, rectWidth, circleRadius;
    double triangleBase, triangleHeight;
    double rectArea, rectPerimeter, circleArea, circleCircumference;
    double triangleArea, trianglePerimeter;
    
    cout << "=== Geometric Calculator ===" << endl;
    cout << endl;
    
    // Get rectangle dimensions
    cout << "Enter rectangle length: ";
    cin >> rectLength;
    
    cout << "Enter rectangle width: ";
    cin >> rectWidth;
    
    // Get circle radius
    cout << "Enter circle radius: ";
    cin >> circleRadius;
    
    // Get triangle dimensions
    cout << "Enter triangle base: ";
    cin >> triangleBase;
    
    cout << "Enter triangle height: ";
    cin >> triangleHeight;
    
    // Calculate rectangle properties
    rectArea = rectLength * rectWidth;
    rectPerimeter = 2 * (rectLength + rectWidth);
    
    // Calculate circle properties
    const double PI = 3.14159265359;
    circleArea = PI * circleRadius * circleRadius;
    circleCircumference = 2 * PI * circleRadius;
    
    // Calculate triangle properties
    triangleArea = 0.5 * triangleBase * triangleHeight;
    // For perimeter, we need the third side (hypotenuse for right triangle)
    double hypotenuse = sqrt(triangleBase * triangleBase + triangleHeight * triangleHeight);
    trianglePerimeter = triangleBase + triangleHeight + hypotenuse;
    
    // Display results
    cout << endl;
    cout << "Results:" << endl;
    cout << "========================================" << endl;
    cout << fixed << setprecision(2);
    
    cout << "Rectangle:" << endl;
    cout << "  Length: " << rectLength << endl;
    cout << "  Width: " << rectWidth << endl;
    cout << "  Area: " << rectArea << endl;
    cout << "  Perimeter: " << rectPerimeter << endl;
    cout << endl;
    
    cout << "Circle:" << endl;
    cout << "  Radius: " << circleRadius << endl;
    cout << "  Area: " << circleArea << endl;
    cout << "  Circumference: " << circleCircumference << endl;
    cout << endl;
    
    cout << "Triangle:" << endl;
    cout << "  Base: " << triangleBase << endl;
    cout << "  Height: " << triangleHeight << endl;
    cout << "  Area: " << triangleArea << endl;
    cout << "  Perimeter: " << trianglePerimeter << endl;
    cout << endl;
    
    // Summary table
    cout << "Summary Table:" << endl;
    cout << "========================================" << endl;
    cout << setw(15) << "Shape" << setw(15) << "Area" << setw(15) << "Perimeter" << endl;
    cout << "----------------------------------------" << endl;
    cout << setw(15) << "Rectangle" << setw(15) << rectArea << setw(15) << rectPerimeter << endl;
    cout << setw(15) << "Circle" << setw(15) << circleArea << setw(15) << circleCircumference << endl;
    cout << setw(15) << "Triangle" << setw(15) << triangleArea << setw(15) << trianglePerimeter << endl;
    
    return 0;
} 