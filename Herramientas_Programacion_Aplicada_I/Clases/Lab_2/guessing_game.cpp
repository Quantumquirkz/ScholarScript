/**
 * @file guessing_game.cpp
 * @brief Number Guessing Game - Lab 2 Exercise 2
 * @author Student Name
 * @date December 2024
 * 
 * This program demonstrates control structures by implementing
 * a number guessing game with multiple difficulty levels.
 */

#include <iostream>
#include <cstdlib>
#include <ctime>
#include <limits>
#include <string>

using namespace std;

// Game difficulty settings
struct Difficulty {
    int minNumber;
    int maxNumber;
    int maxAttempts;
    string name;
};

// Difficulty levels
const Difficulty EASY = {1, 50, 10, "Easy"};
const Difficulty MEDIUM = {1, 100, 7, "Medium"};
const Difficulty HARD = {1, 200, 5, "Hard"};

/**
 * @brief Display program header and instructions
 */
void displayHeader() {
    cout << "==========================================" << endl;
    cout << "         Number Guessing Game" << endl;
    cout << "    Applied Programming Tools I - Lab 2" << endl;
    cout << "==========================================" << endl;
    cout << endl;
    cout << "I will think of a number, and you have to guess it!" << endl;
    cout << "I'll give you hints after each guess." << endl;
    cout << endl;
}

/**
 * @brief Display difficulty selection menu
 */
void displayDifficultyMenu() {
    cout << "Select difficulty:" << endl;
    cout << "1. " << EASY.name << " (" << EASY.minNumber << "-" << EASY.maxNumber 
         << ", " << EASY.maxAttempts << " attempts)" << endl;
    cout << "2. " << MEDIUM.name << " (" << MEDIUM.minNumber << "-" << MEDIUM.maxNumber 
         << ", " << MEDIUM.maxAttempts << " attempts)" << endl;
    cout << "3. " << HARD.name << " (" << HARD.minNumber << "-" << HARD.maxNumber 
         << ", " << HARD.maxAttempts << " attempts)" << endl;
}

/**
 * @brief Get difficulty choice from user
 * @return Selected difficulty level
 */
Difficulty getDifficultyChoice() {
    int choice;
    bool validChoice = false;
    
    while (!validChoice) {
        displayDifficultyMenu();
        cout << "Enter your choice (1-3): ";
        
        if (cin >> choice) {
            switch (choice) {
                case 1:
                    return EASY;
                case 2:
                    return MEDIUM;
                case 3:
                    return HARD;
                default:
                    cout << "Error: Please enter 1, 2, or 3." << endl;
            }
        } else {
            cout << "Error: Please enter a valid number." << endl;
            cin.clear();
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
        }
    }
    
    return EASY; // Default fallback
}

/**
 * @brief Generate a random number within the given range
 * @param min Minimum value (inclusive)
 * @param max Maximum value (inclusive)
 * @return Random number between min and max
 */
int generateRandomNumber(int min, int max) {
    return min + (rand() % (max - min + 1));
}

/**
 * @brief Get valid guess from user
 * @param min Minimum valid value
 * @param max Maximum valid value
 * @return Valid guess within the range
 */
int getValidGuess(int min, int max) {
    int guess;
    bool validInput = false;
    
    while (!validInput) {
        cout << "Enter your guess (" << min << "-" << max << "): ";
        
        if (cin >> guess) {
            if (guess >= min && guess <= max) {
                validInput = true;
            } else {
                cout << "Error: Guess must be between " << min << " and " << max << "." << endl;
            }
        } else {
            cout << "Error: Please enter a valid number." << endl;
            cin.clear();
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
        }
    }
    
    return guess;
}

/**
 * @brief Compare guess with target number and provide feedback
 * @param guess User's guess
 * @param target Target number to guess
 * @return 0 if correct, -1 if too low, 1 if too high
 */
int compareGuess(int guess, int target) {
    if (guess == target) {
        return 0;
    } else if (guess < target) {
        return -1;
    } else {
        return 1;
    }
}

/**
 * @brief Display feedback based on guess comparison
 * @param result Comparison result (-1, 0, or 1)
 * @param attemptsRemaining Number of attempts left
 */
void displayFeedback(int result, int attemptsRemaining) {
    switch (result) {
        case 0:
            cout << "Congratulations! You guessed it correctly!" << endl;
            break;
        case -1:
            cout << "Too low! Try a higher number." << endl;
            break;
        case 1:
            cout << "Too high! Try a lower number." << endl;
            break;
    }
    
    if (result != 0 && attemptsRemaining > 0) {
        cout << "Attempts remaining: " << attemptsRemaining << endl;
    }
}

/**
 * @brief Play a single game round
 * @param difficulty Selected difficulty level
 * @return True if player won, false if lost
 */
bool playGame(const Difficulty& difficulty) {
    // Generate random number
    int targetNumber = generateRandomNumber(difficulty.minNumber, difficulty.maxNumber);
    int attempts = 0;
    int maxAttempts = difficulty.maxAttempts;
    
    cout << endl;
    cout << "I'm thinking of a number between " << difficulty.minNumber 
         << " and " << difficulty.maxNumber << "..." << endl;
    cout << "You have " << maxAttempts << " attempts to guess it." << endl;
    cout << endl;
    
    while (attempts < maxAttempts) {
        attempts++;
        int attemptsRemaining = maxAttempts - attempts;
        
        // Get user's guess
        int guess = getValidGuess(difficulty.minNumber, difficulty.maxNumber);
        
        // Compare and provide feedback
        int result = compareGuess(guess, targetNumber);
        displayFeedback(result, attemptsRemaining);
        
        // Check if correct
        if (result == 0) {
            cout << "You won in " << attempts << " attempt(s)!" << endl;
            return true;
        }
        
        cout << endl;
    }
    
    // Player ran out of attempts
    cout << "Game Over! You ran out of attempts." << endl;
    cout << "The number was: " << targetNumber << endl;
    return false;
}

/**
 * @brief Display game statistics
 * @param gamesPlayed Total number of games played
 * @param gamesWon Number of games won
 */
void displayStatistics(int gamesPlayed, int gamesWon) {
    cout << endl;
    cout << "==========================================" << endl;
    cout << "              Game Statistics" << endl;
    cout << "==========================================" << endl;
    cout << "Games Played: " << gamesPlayed << endl;
    cout << "Games Won: " << gamesWon << endl;
    cout << "Games Lost: " << (gamesPlayed - gamesWon) << endl;
    
    if (gamesPlayed > 0) {
        double winRate = (double)gamesWon / gamesPlayed * 100;
        cout << "Win Rate: " << winRate << "%" << endl;
    }
    
    cout << "==========================================" << endl;
}

/**
 * @brief Ask user if they want to play another game
 * @return True if user wants to continue, false otherwise
 */
bool askToPlayAgain() {
    char choice;
    cout << endl;
    cout << "Would you like to play another game? (y/n): ";
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
    cout << "    Thank you for playing!" << endl;
    cout << "    Program completed successfully." << endl;
    cout << "==========================================" << endl;
}

/**
 * @brief Main function - program entry point
 * @return Exit status (0 for success)
 */
int main() {
    try {
        // Seed random number generator
        srand(static_cast<unsigned int>(time(0)));
        
        displayHeader();
        
        int gamesPlayed = 0;
        int gamesWon = 0;
        bool continuePlaying = true;
        
        while (continuePlaying) {
            // Get difficulty choice
            Difficulty difficulty = getDifficultyChoice();
            
            // Play the game
            bool won = playGame(difficulty);
            gamesPlayed++;
            
            if (won) {
                gamesWon++;
            }
            
            // Display statistics
            displayStatistics(gamesPlayed, gamesWon);
            
            // Ask if user wants to play again
            continuePlaying = askToPlayAgain();
            
            if (continuePlaying) {
                cout << endl;
                cout << "Starting new game..." << endl;
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