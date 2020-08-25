/*
* modified 20200824
* date     20200824
* @author  Group 3 (Rebecca, Jeffery, Maximilian, Imad, Gracie)
* @version 2.0
* @see    Assignment 4 Culminating
*/

public class Methods {
    
    /*
    Rebecca
    Purpose: Get the first letter input from user and changing it to caps
    Pre-Con: An input is received from the user
    Post-Con: Takes the first letter of the input and capitalize it
     */
    public static void iChange(String guess) {
        guess = guess.toUpperCase(); //changes what user enters to all uppercase
        WheelOfFortune.guessL = guess.charAt(0); //assigns only the first letter of what the user enters as value for variable
    }
    
    /*
    Rebecca
    Purpose: Checks if the letter has already been guessed before
    Pre-Con: An input is received from the user
    Post-Con: Returns true if the letter has already been guessed before
     */
    public static boolean validateC(String phrase, char guessL, char[] alphabet, boolean[] enter, char[] phraseS) {
        WheelOfFortune.tof = false; //resets variable to false
        for (int i = 0; i < 26; i++) { 
            if (guessL == alphabet[i]) { //matches the letter guessed to the alphabet array
                if (enter[i]) { //if the index is true
                    WheelOfFortune.tof = true; //variable becomes true
                }
            }
        }
        return WheelOfFortune.tof; //returns value of tof
    }
    
    /*
    Rebecca
    Purpose: Checks whether a letter is a consonant or a vowel
    Pre-Con: An input is received from the user
    Post-Con: Returns true if it is a vowel and false if it is not
     */
    public static boolean checkVowel(char guessL) {
        boolean check;
        if (guessL == 'A' || guessL == 'E' || guessL == 'I' || guessL == 'O' || guessL == 'U') {
            check = true;
        } else {
            check = false;
        }
        return check;
    }
    
    /*
    Rebecca
    Purpose: Records the guessed letters
    Pre-Con: An letter guess from user or computer
    Post-Con: Changes the char array of the phrase to true once the letter has been guessed
     */
    public static void record(char guessL, boolean[] enter, char[] alphabet) {
        for (int i = 0; i < 26; i++) { 
            if (guessL == alphabet[i]) { //matches the letter guessed to the alphabet array
                enter[i] = true; //changes enter array to true; all marked trues are the letters that have been guessed already
            }
        }
    }
    
    /*
    Rebecca
    Purpose: Checks if the letter guessed is on the gameboard and reveal the geussed letters in the phrase
    Pre-Con: An guess is made by the user or computer
    Post-Con: Counts how many of the letters are in the phrase and reveal them 
     */
    public static void gameBoard(String phrase, char[] phraseC, char[] phraseS, char guessL, int[] spinner, int rngSpin) {
        WheelOfFortune.numberC = 0; 
        for (int i = 0; i < phrase.length(); i++) { 
            if (guessL == phraseC[i]) { 
                phraseS[i] = guessL; //changes the gameboard to reveal guessed letter
                WheelOfFortune.numberC++; //counts number of times the guessed letter appears in the phrase
            }
        }
    }
    
    /*
    Rebecca
    Purpose: Ends the game if all the letters in the phrase are guessed
    Pre-Con: An guess is made by the user or computer
    Post-Con: Returns true if all letters are revealed
     */
    public static boolean boardFinish(String phrase, char[] phraseS) {
        boolean boardF = false; //resets variable value to false
        WheelOfFortune.board = String.copyValueOf(phraseS); //concats all values in char array and assigns it as value for string variable
        if (WheelOfFortune.board.equalsIgnoreCase(phrase)) { 
            boardF = true; 
        }
        return boardF; 
    }
}
