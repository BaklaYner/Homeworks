/**
 * Java level 1
 * Homework for lesson 1
 *
 * @author Bogdanov Anton
 * @version dated May 7, 2018
 */
public class Lesson1 {
    // Task 1.
    public static void main(String[] args) {

        // Task 2.
        byte b = 8;
        short s = 16;
        int i = 32;
        long l = 64;
        float f = 32.0f;
        double d = 64.0;
        boolean isBool = true;
        char c = 'Ы';
        String word = "String";

        System.out.println(calculateTask3(8, 6, 4, 2));

        System.out.println(isSumInRange10To20(2, 4));
        System.out.println(isSumInRange10To20(6, 8));
        System.out.println(isSumInRange10To20(10, 12));

        printPosOrNeg(-2);
        printPosOrNeg(2);

        System.out.println(isNegative(b));
        System.out.println(isNegative(-b));

        printHello("Антон");

        isYearLeap(4);
        isYearLeap(8);
        isYearLeap(100);
        isYearLeap(140);
        isYearLeap(400);
        isYearLeap(500);
    }

    // Task 3.
    private static double calculateTask3(int a, int b, int c, int d) {
        return (a * (b + (c / d)));
    }

    // Task 4.
    private static boolean isSumInRange10To20(int a, int b) {
        return ((a + b) >= 10 && (a + b) <= 20);
    }

    // Task 5.
    private static void printPosOrNeg(int i) {
        if (i >= 0)
            System.out.println("Число положительное");
        else
            System.out.println("Число отрицательное");
    }

    // Task 6.
    private static boolean isNegative(int i) {
        return i < 0;
    }

    // Task 7.
    private static void printHello(String name) {
        System.out.println("Привет, " + name + "!");
    }

    // Task 8.
    private static void isYearLeap(int year) {
        if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0))
            System.out.println(year + ": Да, этот год високосный!");
        else
            System.out.println(year + ": Нет, увы, это не високосный год.");
    }
}