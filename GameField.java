import java.util.Random;

/**
 * Java level 1.
 * Homework for lesson 5.
 *
 * @author Bogdanov Anton
 * @version dated May 24, 2018.
 */

// Task 2.
class GameField {
    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    private static final char DOT_EMPTY = '.';
    private final int SIZE;
    private final int DOTS_TO_WIN;
    private char[][] field;

    GameField(int size, int dotsToWin) {
        SIZE = size;
        DOTS_TO_WIN = dotsToWin;
        init();
    }

    private void init() {
        field = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = DOT_EMPTY;
            }
        }
    }

    void draw() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + "\t");
        }
        System.out.println("- X");
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + "\t");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(field[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("|");
        System.out.println("Y\n");
    }

    void aiTurn() {
        Random rnd = new Random();
        int x, y, cntHor, cntVer, cntDiaTop, cntDiaBot;

        // Finding the winning turn. Highest priority!
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (field[i][j] == DOT_EMPTY) {
                    field[i][j] = DOT_O;
                    if (checkWin(DOT_O)) {
                        return;
                    } else {
                        field[i][j] = DOT_EMPTY;
                    }
                }
            }
        }

        // Finding the winning turn for the enemy and brake it. High priority!
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (field[i][j] == DOT_EMPTY) {
                    field[i][j] = DOT_X;
                    if (checkWin(DOT_X)) {
                        field[i][j] = DOT_O;
                        return;
                    } else {
                        field[i][j] = DOT_EMPTY;
                    }
                }
            }
        }

        // Check rows and columns for at least 2 "X" from index "0" to "SIZE - 2" inclusive.
        for (int i = 0; (i + 1) < SIZE; i++) {
            cntHor = cntVer = 0;
            for (int j = 0; (j + 1) < SIZE; j++) {
                if (field[i][j] == DOT_X) {
                    cntHor++;
                    if ((cntHor >= 2) && (field[i][j + 1] == DOT_EMPTY)) {
                        field[i][j + 1] = DOT_O;
                        return;
                    }
                } else {
                    cntHor = 0;
                }

                if (field[j][i] == DOT_X) {
                    cntVer++;
                    if ((cntVer >= 2) && (field[j + 1][i] == DOT_EMPTY)) {
                        field[j + 1][i] = DOT_O;
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
                if (field[i][j] == DOT_X) {
                    cntHor++;
                    if ((cntHor >= 2) && (field[i][j - 1] == DOT_EMPTY)) {
                        field[i][j - 1] = DOT_O;
                        return;
                    }
                } else {
                    cntHor = 0;
                }

                if (field[j][i] == DOT_X) {
                    cntVer++;
                    if ((cntVer >= 2) && (field[j - 1][i] == DOT_EMPTY)) {
                        field[j - 1][i] = DOT_O;
                        return;
                    }
                } else {
                    cntVer = 0;
                }
            }
        }

        // Check diagonals
        for (int offset = 0; offset <= (SIZE - DOTS_TO_WIN); offset++) {

            cntDiaTop = cntDiaBot = 0;
            // Check diagonals from top left to bottom right for at least 2 "X" from index "0" to "SIZE - offset - 1" inclusive.
            for (int i = 0; i < (SIZE - offset - 1); i++) {
                if (field[i][i + offset] == DOT_X) {
                    cntDiaTop++;
                    if ((cntDiaTop >= 2) && (field[i + 1][i + offset + 1] == DOT_EMPTY)) {
                        field[i + 1][i + offset + 1] = DOT_O;
                        return;
                    }
                } else {
                    cntDiaTop = 0;
                }

                if (field[i + offset][i] == DOT_X) {
                    cntDiaBot++;
                    if ((cntDiaBot >= 2) && (field[i + offset + 1][i + 1] == DOT_EMPTY)) {
                        field[i + offset + 1][i + 1] = DOT_O;
                        return;
                    }
                } else {
                    cntDiaBot = 0;
                }
            }

            cntDiaTop = cntDiaBot = 0;
            // Check diagonals from bottom right to top left for at least 2 "X" from index "SIZE - 1" to "0 + offset + 1" inclusive.
            for (int i = (SIZE - 1 - offset); i > 0; i--) {
                if (field[i][i + offset] == DOT_X) {
                    cntDiaTop++;
                    if ((cntDiaTop >= 2) && (field[i - 1][i + offset - 1] == DOT_EMPTY)) {
                        field[i - 1][i + offset - 1] = DOT_O;
                        return;
                    }
                } else {
                    cntDiaTop = 0;
                }

                if (field[i + offset][i] == DOT_X) {
                    cntDiaBot++;
                    if ((cntDiaBot >= 2) && (field[i + offset - 1][i - 1] == DOT_EMPTY)) {
                        field[i + offset - 1][i - 1] = DOT_O;
                        return;
                    }
                } else {
                    cntDiaBot = 0;
                }
            }

            cntDiaTop = cntDiaBot = 0;
            // Check diagonals from top right to bottom left for at least 2 "X" from index "0" to "SIZE - offset - 1" inclusive.
            for (int i = 0; i < (SIZE - offset - 1); i++) {
                if (field[i][SIZE - 1 - i - offset] == DOT_X) {
                    cntDiaTop++;
                    if ((cntDiaTop >= 2) && (field[i + 1][SIZE - 2 - i - offset] == DOT_EMPTY)) {
                        field[i + 1][SIZE - 2 - i - offset] = DOT_O;
                        return;
                    }
                } else {
                    cntDiaTop = 0;
                }

                if (field[i + offset][SIZE - 1 - i] == DOT_X) {
                    cntDiaBot++;
                    if ((cntDiaBot >= 2) && (field[i + offset + 1][SIZE - 2 - i] == DOT_EMPTY)) {
                        field[i + offset + 1][SIZE - 2 - i] = DOT_O;
                        return;
                    }
                } else {
                    cntDiaBot = 0;
                }
            }

            cntDiaTop = cntDiaBot = 0;
            // Check diagonals from bottom left to top right for at least 2 "X" from index "SIZE - 1" to "1" inclusive.
            for (int i = (SIZE - 1 - offset); i > 0; i--) {
                if (field[i][SIZE - 1 - i - offset] == DOT_X) {
                    cntDiaTop++;
                    if ((cntDiaTop >= 2) && (field[i - 1][SIZE - i - offset] == DOT_EMPTY)) {
                        field[i - 1][SIZE - i - offset] = DOT_O;
                        return;
                    }
                } else {
                    cntDiaTop = 0;
                }

                if (field[i + offset][SIZE - 1 - i] == DOT_X) {
                    cntDiaBot++;
                    if ((cntDiaBot >= 2) && (field[i + offset - 1][SIZE - i] == DOT_EMPTY)) {
                        field[i + offset - 1][SIZE - i] = DOT_O;
                        return;
                    }
                } else {
                    cntDiaBot = 0;
                }
            }

        }

        do {
            x = rnd.nextInt(SIZE);
            y = rnd.nextInt(SIZE);
        } while (isInvalidCell(x, y));
        field[y][x] = DOT_O;
    }

    void markCell(int x, int y, char dot) {
        field[y][x] = dot;
    }

    boolean isInvalidCell(int x, int y) {
        return (x < 0 || x >= SIZE || y < 0 || y >= SIZE || field[y][x] != DOT_EMPTY);
    }

    boolean checkWin(char dot) {
        int cntHor, cntVer, cntDiaTop, cntDiaBot;

        // Check horizontal and vertical lines.
        for (int i = 0; i < SIZE; i++) {
            cntHor = cntVer = 0;
            for (int j = 0; j < SIZE; j++) {
                if (field[i][j] == dot) {
                    cntHor++;
                    if (cntHor == DOTS_TO_WIN) return true;
                } else {
                    cntHor = 0;
                }

                if (field[j][i] == dot) {
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
                if (field[i][i + offset] == dot) {
                    cntDiaTop++;
                    if (cntDiaTop == DOTS_TO_WIN) return true;
                } else {
                    cntDiaTop = 0;
                }

                if (field[i + offset][i] == dot) {
                    cntDiaBot++;
                    if (cntDiaBot == DOTS_TO_WIN) return true;
                } else {
                    cntDiaBot = 0;
                }
            }

            cntDiaTop = cntDiaBot = 0;
            // Check diagonals from top right to bottom left.
            for (int i = 0; i < (SIZE - offset); i++) {
                if (field[i][SIZE - 1 - i - offset] == dot) {
                    cntDiaTop++;
                    if (cntDiaTop == DOTS_TO_WIN) return true;
                } else {
                    cntDiaTop = 0;
                }

                if (field[i + offset][SIZE - 1 - i] == dot) {
                    cntDiaBot++;
                    if (cntDiaBot == DOTS_TO_WIN) return true;
                } else {
                    cntDiaBot = 0;
                }
            }

        }

        return false;
    }

    boolean isFieldFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (field[i][j] == DOT_EMPTY)
                    return false;
            }
        }
        return true;
    }

}