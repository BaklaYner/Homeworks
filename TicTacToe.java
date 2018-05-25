import java.util.Scanner;

/**
 * Java level 1.
 * Homework for lesson 5.
 *
 * @author Bogdanov Anton
 * @version dated May 24, 2018
 */

// Task 2.
public class TicTacToe {
    private Scanner scan;
    private GameField field;

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.start();
    }

    private void init() {
        System.out.println("I suggest you play a Tic Tac Toe");
        System.out.println("Enter the preferred size of the playing field:");
        int size = scan.nextInt();
        System.out.println("Enter the preferred count of dots to win:");
        int dots = scan.nextInt();
        System.out.println("Well, let's play the game!");

        field = new GameField(size, dots);
        field.draw();
    }

    void start() {
        scan = new Scanner(System.in);

        init();
        while (true) {
            playerTurn();
            field.draw();
            if (field.checkWin(GameField.DOT_X)) {
                System.out.println("You win!");
                break;
            } else if (field.isFieldFull()) {
                System.out.println("Sorry, it's a draw!");
                break;
            }
            field.aiTurn();
            field.draw();
            if (field.checkWin(GameField.DOT_O)) {
                System.out.println("AI win!");
                break;
            } else if (field.isFieldFull()) {
                System.out.println("Sorry, it's a draw!");
                break;
            }
        }
        System.out.println("Game over!");

        scan.close();
    }

    private void playerTurn() {
        int x, y;
        do {
            System.out.println("Enter coordinates of your move. They should looks like: X Y");
            x = scan.nextInt() - 1;
            y = scan.nextInt() - 1;
        } while (field.isInvalidCell(x, y));
        field.markCell(x, y, GameField.DOT_X);
    }

}