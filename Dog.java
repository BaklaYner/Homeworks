/**
 * Java level 1.
 * Homework for lesson 6.
 *
 * @author Bogdanov Anton
 * @version dated May 28, 2018
 * @link https://github.com/BaklaYner/Homeworks-Java-level-1
 */

// Task 1.
class Dog extends Animal {

    // Task 5.
    Dog(String name, int maxRunRange, int maxSwimRange, double maxJumpHeight) {
        super(name, maxRunRange, maxSwimRange, maxJumpHeight);
    }

    // Task 3.
    Dog(String name) {
        this(name, 500, 10, 0.5);
    }

    @Override
    public String toString() {
        return "dog \"" + getName() + "\"";
    }
}