/**
 * Java level 1.
 * Homework for lesson 6.
 *
 * @author Bogdanov Anton
 * @version dated May 28, 2018
 * @link https://github.com/BaklaYner/Homeworks-Java-level-1
 */

// Task 1.
class Animal {

    // Task 3.
    private String name;
    private int maxRunRange, maxSwimRange;
    private double maxJumpHeight;

    Animal(String name, int maxRunRange, int maxSwimRange, double maxJumpHeight) {
        this.name = name;
        this.maxRunRange = maxRunRange;
        this.maxSwimRange = maxSwimRange;
        this.maxJumpHeight = maxJumpHeight;
    }

    String getName() {
        return name;
    }

    // Task 2.
    boolean run(int range) {
        return range <= maxRunRange;
    }

    boolean swim(int range) {
        return range <= maxSwimRange;
    }

    boolean jumpOver(double height) {
        return height <= maxJumpHeight;
    }
}