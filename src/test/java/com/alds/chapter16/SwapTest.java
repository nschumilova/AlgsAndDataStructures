package com.alds.chapter16;

import org.junit.Test;

import static  org.junit.Assert.*;
public class SwapTest {

    @Test
    public void testSwapSamePositivesValues(){
        runTest(3,3);
    }

    @Test
    public void testSwapSameNegativeValues(){
        runTest(-3,-3);
    }

    @Test
    public void testSwapDiffPositiveValues(){
        runTest(3,4);
    }

    @Test
    public void testSwapDiffNegativeValues(){
        runTest(-3,-4);
    }

    @Test
    public void testSwapNegativePositiveValues(){
        runTest(-3,4);
    }

    private void runTest(int firstBefore, int secondBefore){
        IntPairContainer container = new IntPairContainer(firstBefore,secondBefore);
        container.swapPair();
        assertEquals(secondBefore, container.getFirst());
        assertEquals(firstBefore, container.getSecond());
    }
}
