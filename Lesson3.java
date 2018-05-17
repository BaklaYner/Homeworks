import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Java level 1.
 * Homework for lesson 3.
 *
 * @author Bogdanov Anton
 * @version dated May 17, 2018
 */
public class Lesson3 {
    private static Scanner scanner;
    private static Random random = new Random();

    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        System.out.println("I suggest you play one of two games.");
        int userChoice = getUserInput("Enter 1, for playing \"Guess the number\"\n" +
                "Enter 2, for playing \"Guess the product\" : ", 1, 2);
        switch (userChoice) {
            case 1:
                //Call for task 1.
                playGuessTheNumber();
                break;
            case 2:
                //Call for task 2.
                playGuessTheWord();
                break;
        }

        scanner.close();
    }

    // Task 1.
    static void playGuessTheNumber() {
        int numOfAttempts = 3;
        int range = 10;
        int userChoice;
        String message;

        do {
            int answer = random.nextInt(range);
            int attempt;
            System.out.println("\nHello! I made a number between 0 and " + (range - 1) + " inclusive");
            System.out.println("You should guess it. You have: " + numOfAttempts + " attempts");

            for (attempt = 1; attempt <= numOfAttempts; attempt++) {
                message = "\nAttempt №\"" + attempt + "\". Please enter your guess (number between 0" +
                        " and " + (range - 1) + ") :";
                int userAnswer = getUserInput(message, 0, (range - 1));

                if (userAnswer == answer) {
                    System.out.println("Excellent! You are right!");
                    break;
                } else if (userAnswer > answer) {
                    System.out.println("The predicted number is less than " + userAnswer + "!");
                } else {
                    System.out.println("The hidden number is bigger than " + userAnswer + "!");
                }

                System.out.println("You have only " + (numOfAttempts - attempt) + " attempts left");
            }
            if (attempt > numOfAttempts) {
                System.out.println("You do not have more attempts, unfortunately you lost!");
            }

            message = "Repeat the game again? 1 - yes / 0 - no: ";
            userChoice = getUserInput(message, 0, 1);
        } while (userChoice == 1);
    }

    // Task 2.
    static void playGuessTheWord() {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry",
                "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear",
                "pepper", "pineapple", "pumpkin", "potato"};
        String hint;
        System.out.println("We have a list of products: ");
        System.out.println(Arrays.toString(words));
        System.out.println(" and we forgot to buy one of them, you should guess which one!");
        String answer = words[random.nextInt(words.length)];

        do {
            System.out.println("Please enter your guess: ");
            String userAnswer = scanner.next();
            if (userAnswer.equalsIgnoreCase(answer)) {
                System.out.println("Excellent! You're right!");
                break;
            } else {
                System.out.println("You're wrong");
                hint = createTooltip(userAnswer, answer);
                System.out.println("Hint: Forgotten product - " + hint);
            }
        } while (true);
    }

    //Method for creating tooltip for Task 2.
    private static String createTooltip(String userAnswer, String answer) {
        StringBuilder builder = new StringBuilder(15);
        int border = (userAnswer.length() > answer.length()) ? answer.length() : userAnswer.length();
        for (int i = 0; i < border; i++) {
            if (userAnswer.charAt(i) == answer.charAt(i)) {
                builder.append(answer.charAt(i));
            } else {
                builder.append('#');
            }
        }
        for (int i = border; i < builder.capacity(); i++) {
            builder.append('#');
        }
        return String.valueOf(builder);
    }

    //Method for print "message" and request user input between "start" and "end" inclusive.
    private static int getUserInput(String message, int start, int end) {
        int userChoice = -1;
        String ignore;
        do {
            System.out.println(message);
            if (scanner.hasNextInt()) {
                userChoice = scanner.nextInt();
            } else {
                ignore = scanner.nextLine();
            }
        } while (userChoice < start || userChoice > end);
        return userChoice;
    }
}