/**
 * @file grade_calculator.cpp
 * @brief Grade Calculator Program - Lab 2 Exercise 1
 * @author Student Name
 * @date December 2024
 * 
 * This program demonstrates control structures by implementing
 * a grade calculator that converts numerical scores to letter grades.
 */

#include <iostream>
#include <iomanip>
#include <limits>
#include <string>

using namespace std;

// Grade boundaries
const int GRADE_A_MIN = 90;
const int GRADE_B_MIN = 80;
const int GRADE_C_MIN = 70;
const int GRADE_D_MIN = 60;
const int MIN_SCORE = 0;
const int MAX_SCORE = 100;

/**
 * @brief Display program header and instructions
 */
void displayHeader() {
    cout << "==========================================" << endl;
    cout << "           Grade Calculator" << endl;
    cout << "    Applied Programming Tools I - Lab 2" << endl;
    cout << "==========================================" << endl;
    cout << endl;
    cout << "This program converts numerical scores to letter grades." << endl;
    cout << "Grading Scale:" << endl;
    cout << "A: 90-100 (4.0)" << endl;
    cout << "B: 80-89  (3.0)" << endl;
    cout << "C: 70-79  (2.0)" << endl;
    cout << "D: 60-69  (1.0)" << endl;
    cout << "F: 0-59   (0.0)" << endl;
    cout << endl;
}

/**
 * @brief Get valid numerical score from user
 * @return Valid score between 0-100
 */
int getValidScore() {
    int score;
    bool validInput = false;
    
    while (!validInput) {
        cout << "Enter numerical score (0-100): ";
        
        if (cin >> score) {
            if (score >= MIN_SCORE && score <= MAX_SCORE) {
                validInput = true;
            } else {
                cout << "Error: Score must be between " << MIN_SCORE 
                     << " and " << MAX_SCORE << "." << endl;
            }
        } else {
            cout << "Error: Please enter a valid number." << endl;
            cin.clear();
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
        }
    }
    
    return score;
}

/**
 * @brief Determine letter grade based on numerical score
 * @param score Numerical score (0-100)
 * @return Letter grade (A, B, C, D, or F)
 */
char getLetterGrade(int score) {
    if (score >= GRADE_A_MIN) {
        return 'A';
    } else if (score >= GRADE_B_MIN) {
        return 'B';
    } else if (score >= GRADE_C_MIN) {
        return 'C';
    } else if (score >= GRADE_D_MIN) {
        return 'D';
    } else {
        return 'F';
    }
}

/**
 * @brief Get grade point value for letter grade
 * @param letterGrade Letter grade (A, B, C, D, or F)
 * @return Grade point value (4.0, 3.0, 2.0, 1.0, or 0.0)
 */
double getGradePoint(char letterGrade) {
    switch (letterGrade) {
        case 'A':
            return 4.0;
        case 'B':
            return 3.0;
        case 'C':
            return 2.0;
        case 'D':
            return 1.0;
        case 'F':
            return 0.0;
        default:
            return 0.0;
    }
}

/**
 * @brief Get grade description based on letter grade
 * @param letterGrade Letter grade
 * @return Description of the grade
 */
string getGradeDescription(char letterGrade) {
    switch (letterGrade) {
        case 'A':
            return "Excellent";
        case 'B':
            return "Good";
        case 'C':
            return "Average";
        case 'D':
            return "Below Average";
        case 'F':
            return "Failing";
        default:
            return "Unknown";
    }
}

/**
 * @brief Display grade results in a formatted table
 * @param score Numerical score
 * @param letterGrade Letter grade
 * @param gradePoint Grade point value
 */
void displayResults(int score, char letterGrade, double gradePoint) {
    cout << endl;
    cout << "==========================================" << endl;
    cout << "              Grade Results" << endl;
    cout << "==========================================" << endl;
    cout << fixed << setprecision(1);
    
    cout << setw(20) << "Numerical Score:" << setw(10) << score << endl;
    cout << setw(20) << "Letter Grade:" << setw(10) << letterGrade << endl;
    cout << setw(20) << "Grade Point:" << setw(10) << gradePoint << endl;
    cout << setw(20) << "Description:" << setw(15) << getGradeDescription(letterGrade) << endl;
    
    cout << "==========================================" << endl;
}

/**
 * @brief Display additional grade information
 * @param score Numerical score
 */
void displayAdditionalInfo(int score) {
    cout << endl;
    cout << "Additional Information:" << endl;
    
    // Calculate percentage
    double percentage = (double)score / MAX_SCORE * 100;
    cout << "Percentage: " << fixed << setprecision(1) << percentage << "%" << endl;
    
    // Determine if passing
    bool isPassing = (score >= GRADE_D_MIN);
    cout << "Status: " << (isPassing ? "Passing" : "Failing") << endl;
    
    // Calculate points needed for next grade
    if (score < GRADE_A_MIN) {
        int pointsNeeded;
        char nextGrade;
        
        if (score < GRADE_B_MIN) {
            pointsNeeded = GRADE_B_MIN - score;
            nextGrade = 'B';
        } else if (score < GRADE_C_MIN) {
            pointsNeeded = GRADE_C_MIN - score;
            nextGrade = 'C';
        } else if (score < GRADE_D_MIN) {
            pointsNeeded = GRADE_D_MIN - score;
            nextGrade = 'D';
        } else {
            pointsNeeded = GRADE_A_MIN - score;
            nextGrade = 'A';
        }
        
        cout << "Points needed for " << nextGrade << ": " << pointsNeeded << endl;
    }
    
    cout << endl;
}

/**
 * @brief Ask user if they want to calculate another grade
 * @return True if user wants to continue, false otherwise
 */
bool askToContinue() {
    char choice;
    cout << "Would you like to calculate another grade? (y/n): ";
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
            int score = getValidScore();
            
            // Calculate grade
            char letterGrade = getLetterGrade(score);
            double gradePoint = getGradePoint(letterGrade);
            
            // Display results
            displayResults(score, letterGrade, gradePoint);
            
            // Display additional information
            displayAdditionalInfo(score);
            
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