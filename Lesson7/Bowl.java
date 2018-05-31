package Lesson7;

/**
 * Java level 1.
 * Homework for lesson 7.
 *
 * @author Bogdanov Anton.
 * @version dated May 31, 2018.
 * @link https://github.com/BaklaYner/Homeworks-Java-level-1
 */

class Bowl {
    private final int VOLUME;
    private int foodQuantity;

    Bowl(int volume, int foodQuantity) {
        VOLUME = volume;
        if (foodQuantity <= VOLUME) {
            this.foodQuantity = foodQuantity;
        } else {
            this.foodQuantity = VOLUME;
        }
    }

    int getVOLUME() {
        return VOLUME;
    }

    int getFoodQuantity() {
        return foodQuantity;
    }

    // Task 2 & 4.
    boolean decreaseFood(int quantity) {
        if (quantity <= foodQuantity) {
            foodQuantity -= quantity;
            return true;
        }
        return false;
    }

    // Task 6.
    void increaseFood() {
        if (foodQuantity <= (VOLUME - 50)) {
            foodQuantity += 50;
        } else if (foodQuantity < VOLUME) {
            foodQuantity = VOLUME;
        }
    }

    @Override
    public String toString() {
        return "Bowl volume: " + VOLUME + ", quantity of food: " + foodQuantity;
    }
}