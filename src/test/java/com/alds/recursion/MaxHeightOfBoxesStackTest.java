package com.alds.recursion;


import static org.junit.Assert.*;

import org.junit.Test;

public class MaxHeightOfBoxesStackTest {

    @Test(expected = IllegalArgumentException.class)
    public void testMaxHeightWhenNullInput() {
        RecursionProcessor.getMaxHeightOfBoxesStack(null);
    }

    @Test
    public void testMaxHeightWhenEmptyStack() {
        int height = RecursionProcessor.getMaxHeightOfBoxesStack(new Box[0]);
        assertEquals(0,height);
    }
    @Test
    public void testMaxHeightWhenOnlyOneBox() {
        int height = RecursionProcessor.getMaxHeightOfBoxesStack(new Box[]{new Box(3, 2, 1)});
        assertEquals(3,height);
    }

    @Test
    public void testMaxHeightWhenDuplicateBoxes() {
        Box box = new Box(3, 2, 1);
        int height = RecursionProcessor.getMaxHeightOfBoxesStack(new Box[]{box,box});
        assertEquals(3,height);
    }

    @Test
    public void testMaxHeightWhenAllBoxesBuildOneStack() {
        int height = RecursionProcessor.getMaxHeightOfBoxesStack(new Box[]{
                new Box(7, 17, 27),
                new Box(6, 16, 26),
                new Box(8, 18, 28),
                new Box(10, 20, 30),
                new Box(9, 19, 29)
        });
        assertEquals(40,height);
    }

    @Test
    public void testMaxHeightWhenAllBoxesBuildTwoStacks() {
        int height = RecursionProcessor.getMaxHeightOfBoxesStack(new Box[]{
                new Box(100, 4, 40),
                new Box(99, 3, 39),
                new Box(98, 2, 38),
                new Box(6, 16, 26),
                new Box(7, 17, 27),
                new Box(8, 18, 28),
                new Box(9, 19, 29),
                new Box(10, 20, 30)

        });
        assertEquals(297,height);
    }
}
