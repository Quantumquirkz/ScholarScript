/**
 * @file hello_world.cpp
 * @brief Hello World Program - Lab 1 Exercise 1
 * @author Student Name
 * @date December 2024
 * 
 * This program demonstrates basic C++ input/output operations
 * and program structure for Applied Programming Tools I.
 */

#include <iostream>
#include <string>
#include <ctime>
#include <iomanip>

using namespace std;

/**
 * @brief Get current date and time as a formatted string
 * @return Formatted date and time string
 */
string getCurrentDateTime() {
    time_t now = time(0);
    tm* ltm = localtime(&now);
    
    char buffer[80];
    strftime(buffer, sizeof(buffer), "%Y-%m-%d %H:%M:%S", ltm);
    return string(buffer);
}

/**
 * @brief Display welcome message and program information
 */
void displayWelcome() {
    cout << "==========================================" << endl;
    cout << "    Welcome to C++ Programming!" << endl;
    cout << "    Applied Programming Tools I" << endl;
    cout << "==========================================" << endl;
    cout << endl;
}

/**
 * @brief Get user's name with input validation
 * @return User's name as string
 */
string getUserName() {
    string name;
    bool validInput = false;
    
    while (!validInput) {
        cout << "Please enter your name: ";
        getline(cin, name);
        
        // Check if name is not empty and contains only letters and spaces
        if (!name.empty() && name.find_first_not_of("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ") == string::npos) {
            validInput = true;
        } else {
            cout << "Error: Please enter a valid name (letters and spaces only)." << endl;
        }
    }
    
    return name;
}

/**
 * @brief Display personalized greeting
 * @param name User's name
 */
void displayGreeting(const string& name) {
    cout << endl;
    cout << "Hello, " << name << "! Welcome to the world of programming." << endl;
    cout << "You're about to embark on an exciting journey with C++." << endl;
    cout << endl;
}

/**
 * @brief Display current system information
 */
void displaySystemInfo() {
    cout << "System Information:" << endl;
    cout << "Current date and time: " << getCurrentDateTime() << endl;
    cout << "C++ Standard: C++11" << endl;
    cout << "Compiler: GCC/G++" << endl;
    cout << endl;
}

/**
 * @brief Display program completion message
 */
void displayCompletion() {
    cout << "==========================================" << endl;
    cout << "    Program completed successfully!" << endl;
    cout << "    Thank you for using our program." << endl;
    cout << "==========================================" << endl;
}

/**
 * @brief Main function - program entry point
 * @return Exit status (0 for success)
 */
int main() {
    try {
        // Display welcome message
        displayWelcome();
        
        // Get user's name
        string userName = getUserName();
        
        // Display personalized greeting
        displayGreeting(userName);
        
        // Display system information
        displaySystemInfo();
        
        // Display completion message
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