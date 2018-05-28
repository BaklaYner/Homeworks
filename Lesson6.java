/**
 * Java level 1.
 * Homework for lesson 6.
 *
 * @author Bogdanov Anton
 * @version dated May 28, 2018
 * @link https://github.com/BaklaYner/Homeworks-Java-level-1
 */

public class Lesson6 {
    public static void main(String[] args) {
        Animal[] animals = {new Cat("Barsik"), new Cat("Murzik", 300, 3),
                new Dog("Bobik"), new Dog("Tuzik", 600, 20, 2)};

        for (Animal animal : animals) {
            System.out.println("\nThe result of calling the run(250) method on " + animal + ": "
                    + animal.run(250));
            System.out.println("The result of calling the run(550) method on " + animal + ": "
                    + animal.run(550));
            System.out.println("The result of calling the swim(0) method on " + animal + ": "
                    + animal.swim(0));
            System.out.println("The result of calling the swim(15) method on " + animal + ": "
                    + animal.swim(15));
            System.out.println("The result of calling the jumpOver(1.5) method on " + animal + ": "
                    + animal.jumpOver(1.5));
            System.out.println("The result of calling the jumpOver(2.5) method on " + animal + ": "
                    + animal.jumpOver(2.5));
        }
    }
}