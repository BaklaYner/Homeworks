import java.util.Random;
import java.util.Scanner;

/**
 * Java level 1.
 * Homework for lesson 4.
 *
 * @author Bogdanov Anton
 * @version dated May 20, 2018
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

    // Task 4 partially. Block horizontal and vertical threats, but not block diagonal.
    private void aiTurn() {
        Random rnd = new Random();
        int x, y, cntRow, cntCol;

        // Check rows and columns for at least 2 "X" from index "0" to "SIZE - 2" inclusive.
        for (int i = 0; (i + 1) < SIZE; i++) {
            cntRow = cntCol = 0;
            for (int j = 0; (j + 1) < SIZE; j++) {
                if (map[i][j] == DOT_X) {
                    cntRow++;
                    if ((cntRow >= 2) && (map[i][j + 1] == DOT_EMPTY)) {
                        map[i][j + 1] = DOT_O;
                        return;
                    }
                } else {
                    cntRow = 0;
                }

                if (map[j][i] == DOT_X) {
                    cntCol++;
                    if ((cntCol >= 2) && (map[j + 1][i] == DOT_EMPTY)) {
                        map[j + 1][i] = DOT_O;
                        return;
                    }
                } else {
                    cntCol = 0;
                }
            }
        }

        // Check rows and columns for at least 2 "X" from index "SIZE - 1" to "1" inclusive.
        for (int i = SIZE - 1; i > 0; i--) {
            cntRow = cntCol = 0;
            for (int j = SIZE - 1; j > 0; j--) {
                if (map[i][j] == DOT_X) {
                    cntRow++;
                    if ((cntRow >= 2) && (map[i][j - 1] == DOT_EMPTY)) {
                        map[i][j - 1] = DOT_O;
                        return;
                    }
                } else {
                    cntRow = 0;
                }

                if (map[j][i] == DOT_X) {
                    cntCol++;
                    if ((cntCol >= 2) && (map[j - 1][i] == DOT_EMPTY)) {
                        map[j - 1][i] = DOT_O;
                        return;
                    }
                } else {
                    cntCol = 0;
                }
            }
        }

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
        int hor, ver, diaTop, diaBot;

        // Check horizontal and vertical lines.
        for (int i = 0; i < SIZE; i++) {
            hor = ver = 0;
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == dot) {
                    hor++;
                    if (hor == DOTS_TO_WIN) return true;
                } else {
                    hor = 0;
                }

                if (map[j][i] == dot) {
                    ver++;
                    if (ver == DOTS_TO_WIN) return true;
                } else {
                    ver = 0;
                }
            }
        }

        // Check diagonal lines.
        for (int offset = 0; offset <= (SIZE - DOTS_TO_WIN); offset++) {
            diaTop = diaBot = 0;
            // Check diagonals from top left to bottom right.
            for (int i = 0; i < (SIZE - offset); i++) {
                if (map[i][i + offset] == dot) {
                    diaTop++;
                    if (diaTop == DOTS_TO_WIN) return true;
                } else {
                    diaTop = 0;
                }

                if (map[i + offset][i] == dot) {
                    diaBot++;
                    if (diaBot == DOTS_TO_WIN) return true;
                } else {
                    diaBot = 0;
                }
            }

            // Check diagonals from top right to bottom left.
            for (int i = 0; i < (SIZE - offset); i++) {
                if (map[i][SIZE - 1 - i - offset] == dot) {
                    diaTop++;
                    if (diaTop == DOTS_TO_WIN) return true;
                } else {
                    diaTop = 0;
                }

                if (map[i + offset][SIZE - 1 - i] == dot) {
                    diaBot++;
                    if (diaBot == DOTS_TO_WIN) return true;
                } else {
                    diaBot = 0;
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