package Lesson7;

/**
 * Java level 1.
 * Homework for lesson 7.
 *
 * @author Bogdanov Anton.
 * @version dated May 31, 2018.
 * @link https://github.com/BaklaYner/Homeworks-Java-level-1
 */

class Cat {
    private String name;

    int getAppetite() {
        return appetite;
    }

    private int appetite;
    private boolean satiety;

    Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    // Task 3 & 4.
    void eat(Bowl bowl) {
        if (!satiety) {
            satiety = bowl.decreaseFood(appetite);
        }
    }

    boolean isSatisfied() {
        return satiety;
    }

    @Override
    public String toString() {
        return "cat \"" + name + "\" with appetite - " + appetite;
    }
}