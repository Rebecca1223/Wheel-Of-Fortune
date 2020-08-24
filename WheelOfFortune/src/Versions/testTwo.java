package Versions;


import javax.swing.JOptionPane;
import java.util.Random; //importing RNG method

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

        Object[] options = {"OK"};
        int n = JOptionPane.showOptionDialog(null, "Message here ",null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (n==0) {
            System.out.println ("this works");
        }

 */
/**
 *
 * @author acceb
 */
public class testTwo extends javax.swing.JFrame {

    public static Object[] options = {"OK"};
    public static char alphabet[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    public static String[] phrases = {"PHILLY CHEESE STEAK", "SAUSAGE AND EGGDS", "BOWL OF RAMEN", "BAKED POTATO", "CHICKEN WINGS", "BUCKET OF CHICKEN", "ONION RINGS", "BIRTHDAY CAKE", "CARROT CAKE", "SPONGE CAKE", "BURGER AND FRIES", "FISH AND CHIPS", "BARBEQUE CHIPS", "TOMATO CHIPS", "TURKEY DINNER", "PIZZA AND DIP", "FRIED RICE", "BUBBLE TEA", "HOT CHOCOLATE", "MEXICAN BURRITO", "HARD SHELL TACO", "CHICKEN NUGGETS", "CHOCOLATE FUDGE", "STRAWBERRY WAFFLE", "EGG TARTS"};
    public static int[] spinner = {500, 520, 540, 560, 580, 600, 620, 640, 660, 680, 700, 720, 740, 760, 780, 800, 820, 840, 860, 880, 900, 0, 0, 10, 3000};
    public static boolean enter[] = new boolean[26]; //boolean array used when checking if letter has been guessed already
    public static Random randGen = new Random(); //random number generator declaration
    public static char[] phraseC;
    public static char[] phraseS;

    public static int rngPhrase, userTotal = 0, computerTotal = 0, rngSpin, type, gain, numberC;
    public static String phrase, guess, display = "", board, reveal;
    public static char guessL;
    public static boolean tof;

    /**
     * Creates new form testTwo
     */
    public testTwo() {
        initComponents();
        GuessConsonant.setEnabled(false);
        GuessVowel.setEnabled(false);
        GuessPhrase.setEnabled(false);
        Submit.setEnabled(false);

        Wheel.setEditable(false);
        Guess.setEditable(false);
        UserMoney.setEditable(false);
        CompMoney.setEditable(false);
        Phrase.setEditable(false);
        Instructions.setEditable(false);
    }

    //method to get first letter input from user and changing it to caps
    public static void iChange(String guess) {
        guess = guess.toUpperCase(); //changes what user enters to all uppercase
        guessL = guess.charAt(0); //assigns only the first letter of what the user enters as value for variable
    }

    //method to check if the letter has already beeen guessed before 
    public static boolean validateC(String phrase, char guessL, char[] alphabet, boolean[] enter, char[] phraseS) {
        tof = false; //resets variable to false
        for (int i = 0; i < 26; i++) { //for loop
            if (guessL == alphabet[i]) { //matches the letter guessed to the alphabet array
                if (enter[i]) { //if the index is true
                    tof = true; //variable becomes true
                }
            }
        }
        return tof; //returns value of tof
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
        numberC = 0; //assigning value to variable
        for (int i = 0; i < phrase.length(); i++) { //for loop
            if (guessL == phraseC[i]) { //condition, if met, proceed
                phraseS[i] = guessL; //changes the gameboard to reveal guessed letter
                numberC++; //counts number of times the guessed letter appears in the phrase
            }
        }
    }

    //method to start a new round if user decides to finish the board by keep guessing letters
    public static boolean boardFinish(String phrase, char[] phraseS) {
        boolean boardF = false; //resets variable value to false
        board = String.copyValueOf(phraseS); //concats all values in char array and assigns it as value for string variable
        if (board.equalsIgnoreCase(phrase)) { //condition, if met, proceed
            System.out.println("The board is now revealed"); //print this
            System.out.println("A new round will now begin"); //print this
            boardF = true; //sets variable as true
        }
        return boardF; //returns value of variable
    }

    public static void userPlay() {
        UserMoney.setText(Integer.toString(userTotal));
        CompMoney.setText(Integer.toString(computerTotal));

        rngSpin = randGen.nextInt(25);
        if (0 <= rngSpin && rngSpin <= 20) { //condition, if met, proceed
            Wheel.setText("You landed on $" + spinner[rngSpin] + " amount!");
            System.out.println("You landed on $" + spinner[rngSpin] + " amount!"); //Print this
        } else if (rngSpin == 21 || rngSpin == 22) { //condition, if met, proceed
            Wheel.setText("You went bankrupt");
            userTotal = 0;
            UserMoney.setText(Integer.toString(userTotal));
            computer();
            System.out.println("You went bankrupt"); //Print this
//      break;
        } else if (rngSpin == 23) { //condition, if met, proceed
            Wheel.setText("You landed on skip a turn");
            computer();
            System.out.println("You landed on skip a turn"); //Print this
//      break;
        } else if (rngSpin == 24) { //condition, if met, proceed
            Wheel.setText("You landed on a top dollar value of $" + spinner[24] + " amount!");
            System.out.println("You landed on a top dollar value of $" + spinner[24] + " amount!"); //Print this
        }

        type = 0;
        Instructions.setText("Would you like to buy a vowel, guess a consonant, or guess the phrase?");
        if (userTotal > 249) {
            GuessVowel.setEnabled(true);
            GuessConsonant.setEnabled(true);
            GuessPhrase.setEnabled(true);
        } else {
            GuessConsonant.setEnabled(true);
            GuessPhrase.setEnabled(true);
        }
    }

    public static void computer() {
        rngSpin = randGen.nextInt(25);
        if (rngSpin == 21 || rngSpin == 22) {
            computerTotal = 0;
            int n = JOptionPane.showOptionDialog(null, "The computer went bankrupted", null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            if (n == 0) {
                CompMoney.setText(Integer.toString(computerTotal));
                userPlay();
            }
        } else if (rngSpin == 23) {
            int n = JOptionPane.showOptionDialog(null, "The computer skipped its turn", null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            if (n == 0) {
                userPlay();
            }
        } else if ((0 <= rngSpin && rngSpin <= 20) || rngSpin == 24) {
            int compChoice = randGen.nextInt(4) + 1;
            if (1 <= compChoice && compChoice < 4) {
                int cLetter = randGen.nextInt(5) + 1; //assigns RNG number as value for variable
                if (cLetter == 5 && computerTotal > 249) {
                    computerTotal = computerTotal - 250;
                    CompMoney.setText(Integer.toString(computerTotal));
                    int rngVowel = randGen.nextInt(5);
                    char[] vowels = {'A', 'E', 'I', 'O', 'U'};
                    char vowel = vowels[rngVowel];
                    display = "The computer chose to buy a vowel. \n It wishes to buy: " + vowel + "\n";
                    tof = validateC(phrase, vowel, alphabet, enter, phraseS);
                    if (tof) { //condition, if tof is true, proceed
                        display = display + "That letter has already been guessed. \n The Computer lost its turn \n\n Your turn!";
                        int n = JOptionPane.showOptionDialog(null, display, null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                        if (n == 0) {
                            userPlay();
                        }
                    } else {
                        record(vowel, enter, alphabet); //uses record method
                        gameBoard(phrase, phraseC, phraseS, vowel, spinner, rngSpin);

                        if (numberC > 0) { //condition, if met, proceed
                            display = display + "There are " + numberC + " of those letters!";
                            int n = JOptionPane.showOptionDialog(null, display, null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                            if (n == 0) {
                                if (boardFinish(phrase, phraseS)) {
                                    int x = JOptionPane.showOptionDialog(null, "Game Over", null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                                    if (x == 0) {
                                        //go to end game screen
                                        System.out.println("Game over");
                                    }
                                } else {
                                    reveal = String.valueOf(phraseS);
                                    Phrase.setText(reveal);
                                    computer();
                                }
                            }
                        } else if (numberC == 0) {  //condition, if met, proceed
                            System.out.println("Sorry, that letter does not appear in this phrase"); //Print this
                            display = display + "That letter does not appear in this phrase. \n\n Your turn!";
                            int n = JOptionPane.showOptionDialog(null, display, null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                            if (n == 0) {
                                userPlay();
                            }
                        }
                    }
                } else {
                    display = "The Wheel landed on: $" + spinner[rngSpin] + "\n The Computer chooses to guess a consonant \n";
                    int rngAlpha = randGen.nextInt(21); //assigns RNG number as value for variable
                    char consonant[] = {'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z'}; //char array declaration and stores all consonant in it
                    char guessC = consonant[rngAlpha];
                    display = display + "The Computer guesses: " + guessC + "\n";

                    tof = validateC(phrase, guessC, alphabet, enter, phraseS); //stores return value of validateC method as value for variable
                    if (tof) { //condition, if tof is true, proceed
                        display = display + "That letter has already been guessed. \n The Computer lost its turn \n\n Your turn!";
                        int n = JOptionPane.showOptionDialog(null, display, null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                        if (n == 0) {
                            userPlay();
                        }
                    } else {
                        record(guessC, enter, alphabet); //uses record method
                        gameBoard(phrase, phraseC, phraseS, guessC, spinner, rngSpin);

                        if (numberC > 0) { //condition, if met, proceed
                            gain = numberC * spinner[rngSpin];
                            display = display + "There are " + numberC + " of those letters! \n The Computer gained " + gain + " dollars!";;
                            int n = JOptionPane.showOptionDialog(null, display, null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                            if (n == 0) {
                                computerTotal = computerTotal + gain;
                                CompMoney.setText(Integer.toString(computerTotal));
                                if (boardFinish(phrase, phraseS)) {
                                    int x = JOptionPane.showOptionDialog(null, "Game Over", null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                                    if (x == 0) {
                                        //go to end game screen
                                        System.out.println("Game over");
                                    }
                                } else {
                                    reveal = String.valueOf(phraseS);
                                    Phrase.setText(reveal);
                                    computer();
                                }
                            }
                        } else if (numberC == 0) {  //condition, if met, proceed
                            System.out.println("Sorry, that letter does not appear in this phrase"); //Print this
                            display = display + "That letter does not appear in this phrase. \n\n Your turn!";
                            int n = JOptionPane.showOptionDialog(null, display, null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                            if (n == 0) {
                                userPlay();
                            }
                        }
                    }
                }
            } else if (compChoice == 4) {
                int rngPhraseC = randGen.nextInt(25); //stores RNG number as value for variable
                String compPhrase = phrases[rngPhraseC]; //variable declaration and stores index rngPhraseC of phrases array as value for variable
                display = "The Computer chose to guess the phrase \n It guesses: " + compPhrase + "\n";
                if (compPhrase.equalsIgnoreCase(phrase)) {
                    display = display + "It was the correct phrase! \n The computer gained $1000 \n\n Game Over";
                    computerTotal = computerTotal + 1000;
                    int n = JOptionPane.showOptionDialog(null, display, null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                    if (n == 0) {
                        //go to end game screen
                        System.out.println("Game Over");
                    }
                } else {
                    display = display + "It was the incorrect phrase. \n Your turn!";
                    int n = JOptionPane.showOptionDialog(null, display, null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                    if (n == 0) {
                        userPlay();
                    }
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Play = new javax.swing.JButton();
        Submit = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        CompMoney = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        Wheel = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        Phrase = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        UserMoney = new javax.swing.JTextField();
        Instructions = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Guess = new javax.swing.JTextField();
        GuessVowel = new javax.swing.JButton();
        GuessConsonant = new javax.swing.JButton();
        GuessPhrase = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Play.setText("Play");
        Play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayActionPerformed(evt);
            }
        });

        Submit.setText("Submit Guess");
        Submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitActionPerformed(evt);
            }
        });

        jLabel5.setText("The Computer currently have ($):");

        jLabel6.setText("Wheel:");

        jLabel1.setText("Your phrase is:");

        jLabel2.setText("You currently have ($):");

        jLabel4.setText("Enter your guess here:");

        GuessVowel.setText("Buy a Vowel");
        GuessVowel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuessVowelActionPerformed(evt);
            }
        });

        GuessConsonant.setText("Guess a Consonant");
        GuessConsonant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuessConsonantActionPerformed(evt);
            }
        });

        GuessPhrase.setText("Guess the Phrase");
        GuessPhrase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuessPhraseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Phrase, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Wheel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Instructions)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Guess))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(37, 37, 37))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(UserMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CompMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(GuessVowel)
                        .addGap(126, 126, 126)
                        .addComponent(GuessConsonant)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(GuessPhrase))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(231, 231, 231)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(239, 239, 239)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Play)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(225, 225, 225)
                                .addComponent(Submit)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Play)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Wheel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Phrase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UserMoney, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CompMoney, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Instructions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GuessVowel)
                    .addComponent(GuessConsonant)
                    .addComponent(GuessPhrase))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Guess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Submit)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlayActionPerformed
        for (int i = 0; i < 25; i++) { //for loop
            enter[i] = false; //sets all indexes of the array to false
        }
        rngPhrase = randGen.nextInt(24); //stores the RNG number as value for variable
        phrase = phrases[rngPhrase];
        phraseC = new char[phrase.length()]; //stores the board
        phraseS = new char[phrase.length()]; //stores the original phrase

        for (int i = 0; i < phrase.length(); i++) { //for loop
            phraseC[i] = phrase.charAt(i); //splits the phrase and stores each character to the indexes of array
            phraseS[i] = phrase.charAt(i); //splits the phrase and stores each character to the indexes of array
        }

        for (int i = 0; i < phrase.length(); i++) { //for loop
            if (phraseS[i] != ' ') { //condition, if met proceed
                phraseS[i] = '#'; //changes the phrase (in char array) to a series of hashtags
            }
        }

        String displayPhrase = String.copyValueOf(phraseS);
        Phrase.setText(displayPhrase);

        UserMoney.setText(Integer.toString(userTotal));
        CompMoney.setText(Integer.toString(computerTotal));

        userPlay();

        System.out.println(phrase);
    }//GEN-LAST:event_PlayActionPerformed

    private void GuessVowelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuessVowelActionPerformed
        Instructions.setText("Enter your guess below and press Submit");
        GuessVowel.setEnabled(false);
        GuessConsonant.setEnabled(false);
        GuessPhrase.setEnabled(false);
        Guess.setEditable(true);
        Submit.setEnabled(true);

        type = 1;
    }//GEN-LAST:event_GuessVowelActionPerformed

    private void GuessConsonantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuessConsonantActionPerformed
        Instructions.setText("Enter your guess below and press Submit");
        GuessVowel.setEnabled(false);
        GuessConsonant.setEnabled(false);
        GuessPhrase.setEnabled(false);
        Guess.setEditable(true);
        Submit.setEnabled(true);

        type = 2;
    }//GEN-LAST:event_GuessConsonantActionPerformed

    private void GuessPhraseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuessPhraseActionPerformed
        Instructions.setText("Enter your guess below and press Submit");
        GuessVowel.setEnabled(false);
        GuessConsonant.setEnabled(false);
        GuessPhrase.setEnabled(false);
        Guess.setEditable(true);
        Submit.setEnabled(true);

        type = 3;
    }//GEN-LAST:event_GuessPhraseActionPerformed

    private void SubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitActionPerformed
        guess = Guess.getText();

        Guess.setText("");
        Guess.setEditable(false);
        Submit.setEnabled(false);

        iChange(guess);

        if (type == 1) {
            tof = validateC(phrase, guessL, alphabet, enter, phraseS); //assigns return value of validateC method as value for variable
            userTotal = userTotal - 250;
            UserMoney.setText(Integer.toString(userTotal));

            if (checkVowel(guessL) == false) {
                Instructions.setText("That is not a vowel! You lost your turn..");
                computer();
            } else if (tof) { //condition, if met, proceed
                System.out.println("That letter has already been guessed"); //Print this
                Instructions.setText("That letter has already been guessed");
                computer();
            } else {
                record(guessL, enter, alphabet); //uses record method
                gameBoard(phrase, phraseC, phraseS, guessL, spinner, rngSpin); //uses gameboard method

                if (numberC > 0) { //condition, if met, proceed
                    System.out.println("Congrats, there are " + numberC + " of those letters!"); //Print this
                    display = "Congrats, there are " + numberC + " of those letters!";
                    int n = JOptionPane.showOptionDialog(null, display, null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                    if (n == 0) {
                        if (boardFinish(phrase, phraseS)) {
                            int x = JOptionPane.showOptionDialog(null, "Game Over", null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                            if (x == 0) {
                                //go to end game screen
                                System.out.println("Game over");
                            }
                        } else {
                            reveal = String.valueOf(phraseS);
                            Phrase.setText(reveal);
                            userPlay();
                        }
                    }
                } else if (numberC == 0) {  //condition, if met, proceed
                    System.out.println("Sorry, that letter does not appear in this phrase"); //Print this
                    display = "Sorry, that letter does not appear in this phrase";
                    int n = JOptionPane.showOptionDialog(null, display, null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                    if (n == 0) {
                        computer();
                    }
                }
            }
        } else if (type == 2) {
            tof = validateC(phrase, guessL, alphabet, enter, phraseS); //assigns return value of validateC method as value for variable

            if (checkVowel(guessL)) {
                Instructions.setText("That is not a consonant! You lost your turn..");
                computer();
            } else if (tof) { //condition, if met, proceed
                System.out.println("That letter has already been guessed"); //Print this
                Instructions.setText("That letter has already been guessed");
                computer();
            } else {
                record(guessL, enter, alphabet); //uses record method
                gameBoard(phrase, phraseC, phraseS, guessL, spinner, rngSpin); //uses gameboard method
                if (numberC > 0) { //condition, if met, proceed
                    System.out.println("Congrats, there are " + numberC + " of those letters!"); //Print this
                    gain = numberC * spinner[rngSpin]; //value assignment to variable
                    display = "Congrats, there are " + numberC + " of those letters! \n You gained " + gain + " dollars!";
                    int n = JOptionPane.showOptionDialog(null, display, null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                    if (n == 0) {
                        userTotal = userTotal + gain;
                        UserMoney.setText(Integer.toString(userTotal));
                        if (boardFinish(phrase, phraseS)) {
                            int x = JOptionPane.showOptionDialog(null, "Game Over", null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                            if (x == 0) {
                                //go to end game screen
                                System.out.println("Game over");
                            }
                        } else {
                            reveal = String.valueOf(phraseS);
                            Phrase.setText(reveal);
                            userPlay();
                        }
                    }
                } else if (numberC == 0) {  //condition, if met, proceed
                    System.out.println("Sorry, that letter does not appear in this phrase"); //Print this
                    display = "Sorry, that letter does not appear in this phrase";
                    int n = JOptionPane.showOptionDialog(null, display, null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                    if (n == 0) {
                        computer();
                    }
                }
            }
        } else if (type == 3) {
            if (guess.equalsIgnoreCase(phrase)) {
                display = "Congrats, you have guessed the correct phrase!";
                userTotal = userTotal + 1000;
                int n = JOptionPane.showOptionDialog(null, display, null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                if (n == 0) {
                    //go to end game screen
                    System.out.println("Game over");
                }
            } else {
                display = "That is not the correct phrase!";
                int n = JOptionPane.showOptionDialog(null, display, null, JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                if (n == 0) {
                    computer();
                }
            }
        }
    }//GEN-LAST:event_SubmitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(testTwo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(testTwo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(testTwo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(testTwo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new testTwo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JTextField CompMoney;
    private static javax.swing.JTextField Guess;
    private static javax.swing.JButton GuessConsonant;
    private static javax.swing.JButton GuessPhrase;
    private static javax.swing.JButton GuessVowel;
    private static javax.swing.JTextField Instructions;
    private static javax.swing.JTextField Phrase;
    private static javax.swing.JButton Play;
    private static javax.swing.JButton Submit;
    private static javax.swing.JTextField UserMoney;
    private static javax.swing.JTextField Wheel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
