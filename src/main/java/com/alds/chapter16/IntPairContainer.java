package com.alds.chapter16;

public class IntPairContainer {

    private int first;
    private int second;

    public IntPairContainer(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public void swapPair(){
        first = first^second;
        second = first^second;
        first = first^second;

    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }
}
