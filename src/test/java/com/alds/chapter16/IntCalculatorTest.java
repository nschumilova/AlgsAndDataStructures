package com.alds.chapter16;


import org.junit.Test;

import static org.junit.Assert.*;
public class IntCalculatorTest {

    @Test
    public void testSumBothPositives(){
        assertEquals(3,IntCalculator.sum(1,2));
    }
    @Test
    public void testSumBothNegatives(){
        assertEquals(-3,IntCalculator.sum(-1,-2));
    }
    @Test
    public void testSumPositiveNegative(){
        assertEquals(1,IntCalculator.sum(-1,2));
    }

    @Test
    public void testSumPositiveZero(){
        assertEquals(1,IntCalculator.sum(1,0));
    }

    @Test
    public void testSumNegativeZero(){
        assertEquals(-1,IntCalculator.sum(-1,0));
    }

    @Test
    public void testSumBothZero(){
        assertEquals(0,IntCalculator.sum(0,0));
    }

    @Test
    public void testSubtractBothPositivesResultPositive(){
        assertEquals(1,IntCalculator.subtract(5,4));
    }
    @Test
    public void testSubtractBothPositivesResultNegative(){
        assertEquals(-1,IntCalculator.subtract(4,5));
    }
    @Test
    public void testSubtractBothNegativesResultPositive(){
        assertEquals(1,IntCalculator.subtract(-4,-5));
    }
    @Test
    public void testSubtractBothNegativesResultNegative(){
        assertEquals(-1,IntCalculator.subtract(-5,-4));
    }
    @Test
    public void testSubtractZero(){
        assertEquals(5,IntCalculator.subtract(5,0));
    }
    @Test
    public void testSubtractFromZero(){
        assertEquals(-5,IntCalculator.subtract(0,5));
    }
    @Test
    public void testSubtractBothZeros(){
        assertEquals(0,IntCalculator.subtract(0,0));
    }

    @Test
    public void testMultiplyBothPositives(){
        assertEquals(2,IntCalculator.multiply(1,2));
    }
    @Test
    public void testMultiplyBothNegatives(){
        assertEquals(2,IntCalculator.multiply(-1,-2));
    }
    @Test
    public void testMultiplyPositiveNegative(){
        assertEquals(-2,IntCalculator.multiply(-1,2));
    }

    @Test
    public void testMultiplyPositiveByZero(){
        assertEquals(0,IntCalculator.multiply(1,0));
    }

    @Test
    public void testMultiplyNegativeByZero(){
        assertEquals(0,IntCalculator.multiply(-1,0));
    }

    @Test
    public void testMultiplyBothZero(){
        assertEquals(0,IntCalculator.multiply(0,0));
    }

    @Test
    public void testDivideBothPositivesWithoutRemainder(){
        assertEquals(3,IntCalculator.divide(6,2));
    }
    @Test
    public void testDivideBothPositivesWithRemainder(){
        assertEquals(1,IntCalculator.divide(6,4));
    }
    @Test
    public void testDivideBothNegativesWithoutRemainder(){
        assertEquals(3,IntCalculator.divide(-6,-2));
    }
    @Test
    public void testDivideBothNegativesWithRemainder(){
        assertEquals(1,IntCalculator.divide(-6,-4));
    }

    @Test
    public void testDividePositiveByNegative(){
        assertEquals(-1,IntCalculator.divide(6,-4));
    }

    @Test
    public void testDivideZeroByPositive(){
        assertEquals(0,IntCalculator.divide(0,4));
    }
    @Test
    public void testDivideZeroByNegative(){
        assertEquals(0,IntCalculator.divide(0,-4));
    }
    @Test(expected = ArithmeticException.class)
    public void testDivideByZero(){
        IntCalculator.divide(4,0);
    }

}
