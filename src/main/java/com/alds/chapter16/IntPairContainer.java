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

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }
}
