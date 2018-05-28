/**
 * Java level 1.
 * Homework for lesson 6.
 *
 * @author Bogdanov Anton
 * @version dated May 28, 2018
 * @link https://github.com/BaklaYner/Homeworks-Java-level-1
 */

// Task 1.
class Cat extends Animal {

    // Task 5.
    Cat(String name, int maxRunRange, double maxJumpHeight) {
        super(name, maxRunRange, Integer.MIN_VALUE, maxJumpHeight);
    }

    // Task 3.
    Cat(String name) {
        this(name, 200, 2);
    }

    // Task 2.
    @Override
    public boolean swim(int range) {
        return false;
    }

    @Override
    public String toString() {
        return "cat \"" + getName() + "\"";
    }
}