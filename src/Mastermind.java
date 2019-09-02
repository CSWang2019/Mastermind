import java.util.Random;
import java.util.Scanner;

public class Mastermind {
    private final StringBuilder COLORS = new StringBuilder("RGBY");
    private final int secretLength = 4;
    private StringBuilder secret;
    private StringBuilder guess;
    private int life = 15;
    private boolean validGuess = true;
    private boolean isWin = false;

    private Mastermind() {
        System.out.println("Welcome to Mastermind!");
        System.out.println("There are 4 colors: Red, Green, Blue, and Yellow.");
        System.out.println("Please guess arbitrary 4 colors like \"RRGB.\" ");
        System.out.println("We will tell you colors of exact or partial correct until you hit the answer!");
        secret = new StringBuilder();
        guess = new StringBuilder();
        generateSecret();
        System.out.println("remain life: " + life);
    }

    private void generateSecret() {
        Random rand = new Random();
        for (int i = 0; i < secretLength; i++) {
            secret.append(COLORS.charAt(rand.nextInt(COLORS.length())));
        }
        //System.out.println(secret);
    }

    private void getGuess() {//get and check
        System.out.printf("Please enter %d colors:\n", secretLength);
        guess.delete(0,guess.length());
        Scanner input = new Scanner(System.in);
        StringBuilder str = new StringBuilder(input.nextLine());
        if (!checkGuessValidity(str)) {
            return;
        }
        guess.append(str);
        System.out.println(guess);
    }

    private boolean processGuess() {
        int exactCorrect = 0;
        int partialCorrect = 0;
        StringBuilder answer = new StringBuilder(secret);
        for (int i = 0; i < secretLength; i++) {
            if (guess.charAt(i) == secret.charAt(i)) {
                guess.setCharAt(i, 'x');
                answer.setCharAt(i, 'y');
                exactCorrect++;
            }
        }
        for (int i = 0; i < secretLength; i++) {
            for (int j = 0; j < secretLength; j++) {
                if (guess.charAt(i) == answer.charAt(j)) {
                    guess.setCharAt(i, 'x');
                    answer.setCharAt(j, 'y');
                    partialCorrect++;
                }
            }
        }
        System.out.println("exact correct: " + exactCorrect + "\tpartial correct: " + partialCorrect);

        if (exactCorrect == 4) {
            return true;
        } else {
            life--;
            System.out.println("remain life: " + life);
            return false;
        }

    }

    private boolean checkGuessValidity(StringBuilder str) {//check length and color and change to uppercase
        validGuess = true;
        if (str.length() != secretLength) {
            System.out.printf("Input length should be %d.\n", secretLength);
            validGuess = false;
            return false;
        }
        for (int i = 0; i < secretLength; i++) {
            if (!Character.isLetter(str.charAt(i))) {
                System.out.println("Please enter letters. eq.RGBB ");
                validGuess = false;
                return false;
            }
            if (Character.isLowerCase(str.charAt(i))) {
                str.setCharAt(i, Character.toUpperCase(str.charAt(i)));
            }
        }
        boolean invalidLetter = true;
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < COLORS.length(); j++) {
                if (COLORS.charAt(j) == str.charAt(i)) {
                    invalidLetter = false;
                }
            }
            if (invalidLetter) {
                System.out.println("Please enter letters of color. eq.RGBB.");
                validGuess = false;
                return false;
            }
            invalidLetter = true;
        }
        validGuess = true;
        return true;
    }

    public static void main(String[] args) {
        Mastermind master = new Mastermind();

        while (!master.isWin) {
            master.getGuess();
            if (master.validGuess) {
                master.isWin = master.processGuess();
                if(master.isWin)
                {
                    System.out.println("YOU WIN!");
                    break;
                }
            }
            if (master.life == 0) {
                System.out.println("YOU LOSE!");
                break;
            }

        }


    }
}
