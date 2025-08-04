/**
 * @file temperature.cpp
 * @brief Temperature Converter Program - Lab 1 Exercise 3
 * @author Student Name
 * @date December 2024
 * 
 * This program demonstrates temperature conversion between Celsius and Fahrenheit,
 * including input validation and formatted output for Applied Programming Tools I.
 */

#include <iostream>
#include <iomanip>
#include <limits>
#include <string>

using namespace std;

// Temperature conversion constants
const double CELSIUS_TO_FAHRENHEIT_RATIO = 9.0 / 5.0;
const double CELSIUS_TO_FAHRENHEIT_OFFSET = 32.0;
const double FAHRENHEIT_TO_CELSIUS_RATIO = 5.0 / 9.0;
const double FAHRENHEIT_TO_CELSIUS_OFFSET = 32.0;

// Temperature scale constants
const double ABSOLUTE_ZERO_CELSIUS = -273.15;
const double ABSOLUTE_ZERO_FAHRENHEIT = -459.67;

/**
 * @brief Display program header and instructions
 */
void displayHeader() {
    cout << "==========================================" << endl;
    cout << "         Temperature Converter" << endl;
    cout << "    Applied Programming Tools I" << endl;
    cout << "==========================================" << endl;
    cout << endl;
    cout << "This program converts temperatures between:" << endl;
    cout << "- Celsius (°C) to Fahrenheit (°F)" << endl;
    cout << "- Fahrenheit (°F) to Celsius (°C)" << endl;
    cout << endl;
    cout << "Temperature ranges:" << endl;
    cout << "- Celsius: " << ABSOLUTE_ZERO_CELSIUS << "°C to +∞" << endl;
    cout << "- Fahrenheit: " << ABSOLUTE_ZERO_FAHRENHEIT << "°F to +∞" << endl;
    cout << endl;
}

/**
 * @brief Get conversion type from user
 * @return 1 for Celsius to Fahrenheit, 2 for Fahrenheit to Celsius
 */
int getConversionType() {
    int choice;
    bool validChoice = false;
    
    while (!validChoice) {
        cout << "Select conversion type:" << endl;
        cout << "1. Celsius to Fahrenheit" << endl;
        cout << "2. Fahrenheit to Celsius" << endl;
        cout << "Enter your choice (1 or 2): ";
        
        if (cin >> choice && (choice == 1 || choice == 2)) {
            validChoice = true;
        } else {
            cout << "Error: Please enter 1 or 2." << endl;
            cin.clear();
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
        }
    }
    
    return choice;
}

/**
 * @brief Get valid temperature input from user
 * @param scale Temperature scale ("Celsius" or "Fahrenheit")
 * @return Valid temperature value
 */
double getValidTemperature(const string& scale) {
    double temperature;
    bool validInput = false;
    
    while (!validInput) {
        cout << "Enter temperature in " << scale << ": ";
        
        if (cin >> temperature) {
            // Check if temperature is above absolute zero
            double absoluteZero = (scale == "Celsius") ? ABSOLUTE_ZERO_CELSIUS : ABSOLUTE_ZERO_FAHRENHEIT;
            
            if (temperature >= absoluteZero) {
                validInput = true;
            } else {
                cout << "Error: Temperature cannot be below absolute zero (" 
                     << absoluteZero << (scale == "Celsius" ? "°C" : "°F") << ")." << endl;
            }
        } else {
            cout << "Error: Please enter a valid number." << endl;
            cin.clear();
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
        }
    }
    
    return temperature;
}

/**
 * @brief Convert Celsius to Fahrenheit
 * @param celsius Temperature in Celsius
 * @return Temperature in Fahrenheit
 */
double celsiusToFahrenheit(double celsius) {
    return (celsius * CELSIUS_TO_FAHRENHEIT_RATIO) + CELSIUS_TO_FAHRENHEIT_OFFSET;
}

/**
 * @brief Convert Fahrenheit to Celsius
 * @param fahrenheit Temperature in Fahrenheit
 * @return Temperature in Celsius
 */
double fahrenheitToCelsius(double fahrenheit) {
    return (fahrenheit - FAHRENHEIT_TO_CELSIUS_OFFSET) * FAHRENHEIT_TO_CELSIUS_RATIO;
}

/**
 * @brief Get temperature description based on Celsius value
 * @param celsius Temperature in Celsius
 * @return Description of the temperature
 */
string getTemperatureDescription(double celsius) {
    if (celsius < -20) return "Very Cold";
    else if (celsius < 0) return "Cold";
    else if (celsius < 15) return "Cool";
    else if (celsius < 25) return "Mild";
    else if (celsius < 35) return "Warm";
    else if (celsius < 45) return "Hot";
    else return "Very Hot";
}

/**
 * @brief Display conversion results
 * @param originalTemp Original temperature
 * @param convertedTemp Converted temperature
 * @param fromScale Original scale
 * @param toScale Target scale
 */
void displayResults(double originalTemp, double convertedTemp, 
                   const string& fromScale, const string& toScale) {
    cout << endl;
    cout << "==========================================" << endl;
    cout << "           Conversion Results" << endl;
    cout << "==========================================" << endl;
    cout << fixed << setprecision(2);
    
    cout << setw(15) << originalTemp << " " << fromScale << " = "
         << setw(15) << convertedTemp << " " << toScale << endl;
    
    // Display temperature description if converting to Celsius
    if (toScale == "Celsius") {
        string description = getTemperatureDescription(convertedTemp);
        cout << "Temperature description: " << description << endl;
    }
    
    cout << "==========================================" << endl;
}

/**
 * @brief Display additional temperature information
 * @param celsius Temperature in Celsius (for reference)
 * @param fahrenheit Temperature in Fahrenheit (for reference)
 */
void displayAdditionalInfo(double celsius, double fahrenheit) {
    cout << endl;
    cout << "Additional Information:" << endl;
    cout << "Kelvin: " << fixed << setprecision(2) << (celsius + 273.15) << " K" << endl;
    cout << "Rankine: " << (fahrenheit + 459.67) << " °R" << endl;
    
    // Display reference temperatures
    cout << endl;
    cout << "Reference Temperatures:" << endl;
    cout << "Water freezing point: 0°C / 32°F" << endl;
    cout << "Water boiling point: 100°C / 212°F" << endl;
    cout << "Human body temperature: 37°C / 98.6°F" << endl;
    cout << "Room temperature: 20°C / 68°F" << endl;
    cout << endl;
}

/**
 * @brief Ask user if they want to perform another conversion
 * @return True if user wants to continue, false otherwise
 */
bool askToContinue() {
    char choice;
    cout << "Would you like to perform another conversion? (y/n): ";
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
    cout << "    Thank you for using the converter!" << endl;
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
        
        bool continueConversions = true;
        
        while (continueConversions) {
            // Get conversion type
            int conversionType = getConversionType();
            cout << endl;
            
            double originalTemp, convertedTemp;
            string fromScale, toScale;
            
            if (conversionType == 1) {
                // Celsius to Fahrenheit
                originalTemp = getValidTemperature("Celsius");
                convertedTemp = celsiusToFahrenheit(originalTemp);
                fromScale = "°C";
                toScale = "°F";
            } else {
                // Fahrenheit to Celsius
                originalTemp = getValidTemperature("Fahrenheit");
                convertedTemp = fahrenheitToCelsius(originalTemp);
                fromScale = "°F";
                toScale = "°C";
            }
            
            // Display results
            displayResults(originalTemp, convertedTemp, fromScale, toScale);
            
            // Display additional information
            double celsius = (conversionType == 1) ? originalTemp : convertedTemp;
            double fahrenheit = (conversionType == 1) ? convertedTemp : originalTemp;
            displayAdditionalInfo(celsius, fahrenheit);
            
            // Ask if user wants to continue
            continueConversions = askToContinue();
            
            if (continueConversions) {
                cout << endl;
                cout << "Starting new conversion..." << endl;
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