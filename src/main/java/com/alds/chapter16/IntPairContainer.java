package com.alds.chapter16;

/**
 * Class that contains two integers and operations which can be implemented on them
 */
public class IntPairContainer {

    private int first;
    private int second;

    public IntPairContainer(int first, int second) {
        this.first = first;
        this.second = second;
    }

    /**
     * <p>Task 16.1. Time for implementation and testing :<i>10 min</i></p>
     * Swaps two integers in the container without using temporal variable
     * Performance, memory :  <i>O(1)</i>
     */
    public void swapPair() {
        first = first ^ second;
        second = first ^ second;
        first = first ^ second;

    }

    /**
     * <p>Task 16.7. Time for implementation and testing :<i>15 min</i></p>
     * Finds maximum value between two values in the container without using
     * comparison operators
     * Performance, memory :  <i>O(1)</i>
     *
     * @return maximum value
     */
    public int getMax() {
        int k = (first - second) >>> 31;//==1(if first < second), ==0 otherwise
        return second * k + first * (k ^ 1);
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntPairContainer that = (IntPairContainer) o;

        if (first != that.first) return false;
        return second == that.second;
    }

    @Override
    public int hashCode() {
        int result = first;
        result = 31 * result + second;
        return result;
    }
}
