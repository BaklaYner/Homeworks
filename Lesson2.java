import java.util.Arrays;

/**
 * Java level 1.
 * Homework for lesson 2.
 *
 * @author Bogdanov Anton
 * @version dated May 12, 2018
 */
public class Lesson2 {
    public static void main(String[] args) {
        // Call for task 1.
        invertArray();
        invertArray2();

        // Call for task 2.
        fillArray();
        fillArray2();

        // Call for task 3.
        doubleElementsLess6();
        doubleElementsLess6_2();

        // Call for task 4.
        fillDiagonals();

        // Call for task 5.
        searchMinMax();

        // Call for task 6.
        int[] a = {};
        int[] b = {1};
        int[] c = {1, 2};
        int[] d = {1, 1, 1, 2, 1};
        int[] e = {10, 10};
        int[] f = {2, 1, 1, 2, 1};
        System.out.println(Arrays.toString(a));
        System.out.println(checkBalance(a));
        System.out.println(Arrays.toString(b));
        System.out.println(checkBalance(b));
        System.out.println(Arrays.toString(c));
        System.out.println(checkBalance(c));
        System.out.println(Arrays.toString(d));
        System.out.println(checkBalance(d));
        System.out.println(Arrays.toString(e));
        System.out.println(checkBalance(e));
        System.out.println(Arrays.toString(f));
        System.out.println(checkBalance(f));
        System.out.println();

        // Call for task 7.
        int[] g = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(Arrays.toString(g));
        shiftElementsBy(g, -3);
        System.out.println(Arrays.toString(g));
        shiftElementsBy(g, 4);
        System.out.println(Arrays.toString(g));
        shiftElementsBy(g, 0);
        System.out.println(Arrays.toString(g));

    }

    // Task 1 (version 1).
    private static void invertArray() {
        int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = 1;
            } else {
                arr[i] = 0;
            }
        }
        System.out.println(Arrays.toString(arr));
        System.out.println();
    }

    // Task 1 (version 2).
    private static void invertArray2() {
        int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] == 0 ? 1 : 0;
        }
        System.out.println(Arrays.toString(arr));
        System.out.println();
    }

    // Task 2 (version 1).
    private static void fillArray() {
        int[] arr = new int[8];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i * 3;
        }
        System.out.println(Arrays.toString(arr));
        System.out.println();
    }

    // Task 2 (version 2).
    private static void fillArray2() {
        int[] arr = new int[8];
        arr[0] = 0;
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i - 1] + 3;
        }
        System.out.println(Arrays.toString(arr));
        System.out.println();
    }

    // Task 3 (version 1).
    private static void doubleElementsLess6() {
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] < 6 ? arr[i] * 2 : arr[i];
        }
        System.out.println(Arrays.toString(arr));
        System.out.println();
    }

    // Task 3 (version 2).
    private static void doubleElementsLess6_2() {
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        }
        System.out.println(Arrays.toString(arr));
        System.out.println();
    }

    // Task 4.
    private static void fillDiagonals() {
        int size = 7;
        int[][] arr = new int[size][size];
        System.out.println(Arrays.deepToString(arr)); // Вот использование метода Arrays.deepToString для многомерных массивов
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (j == i || j == (arr[i].length - 1 - i)) {
                    arr[i][j] = 1;
                }
            }
        }
        for (int[] anArr : arr) {
            System.out.println(Arrays.toString(anArr));
        }
        System.out.println();
    }

    // Task 5.
    private static void searchMinMax() {
        int size = 1 + (int) (Math.random() * 20); // Генерация рандомного размера массива от 1 до 20
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101); // Заполнение массива рандомными числами от 0 до 100
        }
        System.out.println(Arrays.toString(arr));

        int min, max = min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) min = arr[i];
            if (arr[i] > max) max = arr[i];
        }
        System.out.println("MinVal = " + min);
        System.out.println("MaxVal = " + max);
        System.out.println();
    }

    // Task 6.
    private static boolean checkBalance(int[] arr) {
        for (int border = 1; border < arr.length; border++) {
            int sumFirst = 0, sumSecond = 0;
            for (int j = 0; j < border; j++) {
                sumFirst += arr[j];
            }
            for (int j = border; j < arr.length; j++) {
                sumSecond += arr[j];
            }
            if (sumFirst == sumSecond) return true;
        }
        return false;
    }

    // Task 7.
    private static void shiftElementsBy(int[] arr, int count) {
        if (count >= arr.length || count <= -(arr.length)) { // Оптимизация (Чтобы не делать полный круг)
            count %= arr.length;
        }
        if (arr.length < 2 || count == 0) return;
        if (Math.abs(count) > (arr.length / 2)) { // Оптимизация (При длине массива 10, смещение на 7 равноценно смещению на -3)
            count = ((arr.length - Math.abs(count)) * (Math.abs(count) / -count));
        }

        int temp;
        while (count < 0) {
            temp = arr[0];
            for (int i = 0; i < arr.length - 1; i++) { // Эту часть лучше бы сделать через System.arraycopy()
                arr[i] = arr[i + 1];
            }
            arr[arr.length - 1] = temp;
            count++;
        }
        while (count > 0) {
            temp = arr[arr.length - 1];
            for (int i = arr.length - 1; i > 0; i--) {
                arr[i] = arr[i - 1];
            }
            arr[0] = temp;
            count--;
        }
    }
}