package game;

/**
 * A simple counter class that maintains an integer count value.
 * The counter can be incremented, decremented, and queried for its current
 * value.
 */
public class Counter {
    private int count;

    /**
     * Creates a counter starting from 0.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * Add number to current count.
     *
     * @param number the value to add
     */
    public void increase(int number) {
        this.count = this.count + number;
    }

    /**
     * Subtract number from current count.
     *
     * @param number the value to subtract
     */
    public void decrease(int number) {
        this.count = this.count - number;
    }

    /**
     * Get current count.
     *
     * @return the current count value
     */
    public int getValue() {
        return this.count;
    }
}