
public class Methods {
    //method to get first letter input from user and changing it to caps
    public static void iChange(String guess) {
        guess = guess.toUpperCase(); //changes what user enters to all uppercase
        testTwoPointOne.guessL = guess.charAt(0); //assigns only the first letter of what the user enters as value for variable
    }
    
    //method to check if the letter has already beeen guessed before 
    public static boolean validateC(String phrase, char guessL, char[] alphabet, boolean[] enter, char[] phraseS) {
        testTwoPointOne.tof = false; //resets variable to false
        for (int i = 0; i < 26; i++) { //for loop
            if (guessL == alphabet[i]) { //matches the letter guessed to the alphabet array
                if (enter[i]) { //if the index is true
                    testTwoPointOne.tof = true; //variable becomes true
                }
            }
        }
        return testTwoPointOne.tof; //returns value of tof
    }
    
    //method to check if guess is a consonant
    public static boolean checkVowel(char guessL) {
        boolean check;
        if (guessL == 'A' || guessL == 'E' || guessL == 'I' || guessL == 'O' || guessL == 'U') {
            check = true;
        } else {
            check = false;
        }
        return check;
    }
    
    //method to record the guessed letters
    public static void record(char guessL, boolean[] enter, char[] alphabet) {
        for (int i = 0; i < 26; i++) { //for loop
            if (guessL == alphabet[i]) { //matches the letter guessed to the alphabet array
                enter[i] = true; //changes enter array to true; all marked trues are the letters that have been guessed already
            }
        }
    }
    
    //method to check if the letter guessed is on the gameboard
    public static void gameBoard(String phrase, char[] phraseC, char[] phraseS, char guessL, int[] spinner, int rngSpin) {
        testTwoPointOne.numberC = 0; //assigning value to variable
        for (int i = 0; i < phrase.length(); i++) { //for loop
            if (guessL == phraseC[i]) { //condition, if met, proceed
                phraseS[i] = guessL; //changes the gameboard to reveal guessed letter
                testTwoPointOne.numberC++; //counts number of times the guessed letter appears in the phrase
            }
        }
    }
    
    //method to start a new round if user decides to finish the board by keep guessing letters
    public static boolean boardFinish(String phrase, char[] phraseS) {
        boolean boardF = false; //resets variable value to false
        testTwoPointOne.board = String.copyValueOf(phraseS); //concats all values in char array and assigns it as value for string variable
        if (testTwoPointOne.board.equalsIgnoreCase(phrase)) { //condition, if met, proceed
            System.out.println("The board is now revealed"); //print this
            System.out.println("A new round will now begin"); //print this
            boardF = true; //sets variable as true
        }
        return boardF; //returns value of variable
    }
}
