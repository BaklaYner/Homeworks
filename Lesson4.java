import java.util.Random;
import java.util.Scanner;

/**
 * Java level 1.
 * Homework for lesson 4.
 *
 * @author Bogdanov Anton
 * @version dated May 22, 2018
 */

public class Lesson4 {
    private static Scanner scan;
    private static char[][] map;
    private final char DOT_X = 'X';
    private final char DOT_O = 'O';
    private final char DOT_EMPTY = '.';
    private final int SIZE;
    private final int DOTS_TO_WIN;

    private Lesson4(int size, int dotsToWin) {
        SIZE = size;
        DOTS_TO_WIN = dotsToWin;
    }

    public static void main(String[] args) {
        scan = new Scanner(System.in);

        System.out.println("I suggest you play a Tic Tac Toe");
        System.out.println("Enter the preferred size of the playing field:");
        int size = scan.nextInt();
        System.out.println("Enter the preferred count of dots to win:");
        int dots = scan.nextInt();
        System.out.println("Well, let's play the game!");

        Lesson4 game = new Lesson4(size, dots);
        game.play();

        scan.close();
    }

    private void play() {
        initMap();
        drawMap();
        while (true) {
            playerTurn();
            drawMap();
            if (checkWin(DOT_X)) { // Call for Task 2 & 3.
                System.out.println("You win!");
                break;
            } else if (isMapFull()) {
                System.out.println("Sorry, it's a draw!");
                break;
            }
            aiTurn(); // Call for Task 4.
            drawMap();
            if (checkWin(DOT_O)) { // Call for Task 2 & 3.
                System.out.println("AI win!");
                break;
            } else if (isMapFull()) {
                System.out.println("Sorry, it's a draw!");
                break;
            }
        }
        System.out.println("Game over!");
    }

    private void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    private void drawMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + "\t");
        }
        System.out.println("- X");
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + "\t");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("|");
        System.out.println("Y\n");
    }

    private void playerTurn() {
        int x, y;
        do {
            System.out.println("Enter coordinates of your move. They should looks like: X Y");
            x = scan.nextInt() - 1;
            y = scan.nextInt() - 1;
        } while (isInvalidCell(x, y));
        map[y][x] = DOT_X;
    }

    // Task 4.
    private void aiTurn() {
        Random rnd = new Random();
        int x, y, cntHor, cntVer;

        // Finding the winning turn. Highest priority!
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    map[i][j] = DOT_O;
                    if (checkWin(DOT_O)) {
                        return;
                    } else {
                        map[i][j] = DOT_EMPTY;
                    }
                }
            }
        }

        // Finding the winning turn for the enemy and brake it. High priority!
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    map[i][j] = DOT_X;
                    if (checkWin(DOT_X)) {
                        map[i][j] = DOT_O;
                        return;
                    } else {
                        map[i][j] = DOT_EMPTY;
                    }
                }
            }
        }

        // Check rows and columns for at least 2 "X" from index "0" to "SIZE - 2" inclusive.
        for (int i = 0; (i + 1) < SIZE; i++) {
            cntHor = cntVer = 0;
            for (int j = 0; (j + 1) < SIZE; j++) {
                if (map[i][j] == DOT_X) {
                    cntHor++;
                    if ((cntHor >= 2) && (map[i][j + 1] == DOT_EMPTY)) {
                        map[i][j + 1] = DOT_O;
                        return;
                    }
                } else {
                    cntHor = 0;
                }

                if (map[j][i] == DOT_X) {
                    cntVer++;
                    if ((cntVer >= 2) && (map[j + 1][i] == DOT_EMPTY)) {
                        map[j + 1][i] = DOT_O;
                        return;
                    }
                } else {
                    cntVer = 0;
                }
            }
        }

        // Check rows and columns for at least 2 "X" from index "SIZE - 1" to "1" inclusive.
        for (int i = SIZE - 1; i > 0; i--) {
            cntHor = cntVer = 0;
            for (int j = SIZE - 1; j > 0; j--) {
                if (map[i][j] == DOT_X) {
                    cntHor++;
                    if ((cntHor >= 2) && (map[i][j - 1] == DOT_EMPTY)) {
                        map[i][j - 1] = DOT_O;
                        return;
                    }
                } else {
                    cntHor = 0;
                }

                if (map[j][i] == DOT_X) {
                    cntVer++;
                    if ((cntVer >= 2) && (map[j - 1][i] == DOT_EMPTY)) {
                        map[j - 1][i] = DOT_O;
                        return;
                    }
                } else {
                    cntVer = 0;
                }
            }
        }

        // Check diagonals

        do {
            x = rnd.nextInt(SIZE);
            y = rnd.nextInt(SIZE);
        } while (isInvalidCell(x, y));
        map[y][x] = DOT_O;
    }

    private boolean isInvalidCell(int x, int y) {
        return (x < 0 || x >= SIZE || y < 0 || y >= SIZE || map[y][x] != DOT_EMPTY);
    }

    // Task 2 & 3 implemented for ALL values of SIZE and DOTS_TO_WIN variables.
    private boolean checkWin(char dot) {
        int cntHor, cntVer, cntDiaTop, cntDiaBot;

        // Check horizontal and vertical lines.
        for (int i = 0; i < SIZE; i++) {
            cntHor = cntVer = 0;
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == dot) {
                    cntHor++;
                    if (cntHor == DOTS_TO_WIN) return true;
                } else {
                    cntHor = 0;
                }

                if (map[j][i] == dot) {
                    cntVer++;
                    if (cntVer == DOTS_TO_WIN) return true;
                } else {
                    cntVer = 0;
                }
            }
        }

        // Check diagonal lines.
        for (int offset = 0; offset <= (SIZE - DOTS_TO_WIN); offset++) {

            cntDiaTop = cntDiaBot = 0;
            // Check diagonals from top left to bottom right.
            for (int i = 0; i < (SIZE - offset); i++) {
                if (map[i][i + offset] == dot) {
                    cntDiaTop++;
                    if (cntDiaTop == DOTS_TO_WIN) return true;
                } else {
                    cntDiaTop = 0;
                }

                if (map[i + offset][i] == dot) {
                    cntDiaBot++;
                    if (cntDiaBot == DOTS_TO_WIN) return true;
                } else {
                    cntDiaBot = 0;
                }
            }

            cntDiaTop = cntDiaBot = 0;
            // Check diagonals from top right to bottom left.
            for (int i = 0; i < (SIZE - offset); i++) {
                if (map[i][SIZE - 1 - i - offset] == dot) {
                    cntDiaTop++;
                    if (cntDiaTop == DOTS_TO_WIN) return true;
                } else {
                    cntDiaTop = 0;
                }

                if (map[i + offset][SIZE - 1 - i] == dot) {
                    cntDiaBot++;
                    if (cntDiaBot == DOTS_TO_WIN) return true;
                } else {
                    cntDiaBot = 0;
                }
            }

        }

        return false;
    }

    private boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY)
                    return false;
            }
        }
        return true;
    }
}