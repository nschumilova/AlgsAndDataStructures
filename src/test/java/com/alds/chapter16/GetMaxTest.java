package com.alds.chapter16;


import org.junit.Test;

import static org.junit.Assert.*;

public class GetMaxTest {

    @Test
    public void testGetMaxSamePositives() {
        runTest(3, 3, 3);
    }

    @Test
    public void testGetMaxSameNegatives() {
        runTest(-3, -3, -3);
    }

    @Test
    public void testGetMaxDiffPositives() {
        runTest(3, 4, 4);
    }

    @Test
    public void testGetMaxDiffNegatives() {
        runTest(-3, -4, -3);
    }

    @Test
    public void testGetMaxNegativePositive() {
        runTest(-3, 4, 4);
    }

    @Test
    public void testGetMaxNegativeZero() {
        runTest(-3, 0, 0);
    }

    @Test
    public void testGetMaxPositiveZero() {
        runTest(0, 4, 4);
    }

    @Test
    public void testGetMaxBothZeros() {
        runTest(0, 0, 0);
    }

    private void runTest(int first, int second, int result) {
        IntPairContainer container = new IntPairContainer(first, second);
        container.swapPair();
        assertEquals(result, container.getMax());

    }
}
