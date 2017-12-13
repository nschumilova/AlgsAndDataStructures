package com.alds.chapter16;

/**
 * <p>Task 16.9. Time for implementation and testing :<i>70 min</i></p>
 * Class that implements arithmetic operations on integer numbers using only sum - operation.
 * Result of each operation is integer number.
 */
public class IntCalculator {


    /**
     * Sums two integers <br>
     * Performance, memory :  <i>O(1)</i> <br>
     *
     * @param value1 first integer
     * @param value2 second integer
     * @return result of the summation
     */
    public static int sum(int value1, int value2) {
        return value1 + value2;
    }

    /**
     * Subtracts one integer from another <br>
     * Performance:  <i>O(abs(subtrahend))</i> <br>
     * Memory :  <i>O(1)</i>
     *
     * @param minuend
     * @param subtrahend
     * @return result of the subtraction
     */
    public static int subtract(int minuend, int subtrahend) {
        int minusSubtrahend = negate(subtrahend);
        return sum(minuend, minusSubtrahend);
    }


    /**
     * Multiplies two integers <br>
     * Performance:  <i>O(min(abs(multiplier1),abs(multiplier2)))</i> <br>
     * Memory :  <i>O(1)</i>
     *
     * @param multiplier1
     * @param multiplier2
     * @return result of multiplication
     */
    public static int multiply(int multiplier1, int multiplier2) {
        int sign = getMultiplySign(multiplier1, multiplier2);

        int abs1 = abs(multiplier1);
        int abs2 = abs(multiplier2);
        int result = 0;
        if (abs1 > abs2) {
            int temp2 = abs2;
            abs2 = abs1;
            abs1 = temp2;
        }
        for (int i = 0; i < abs1; i++)
            result = sum(result, abs2);
        if (sign < 0)
            return negate(result);
        else
            return result;
    }

    /**
     * Divides one integer by another <br>
     * Performance:  <i>O(dividend * divisor /2)</i> <br>
     * Memory :  <i>O(1)</i>
     *
     * @param dividend
     * @param divisor
     * @return result of division
     */
    public static int divide(int dividend, int divisor) {
        if (divisor == 0)
            throw new ArithmeticException("Divide by zero!");
        int absDividend = abs(dividend);
        int absDivisor = abs(divisor);
        int sign = getMultiplySign(dividend, divisor);
        int result = 0;

        while (multiply(result, absDivisor) <= absDividend)
            result = sum(result, 1);
        result = sum(result, -1);
        if (sign > 0)
            return result;
        else
            return negate(result);
    }

    private static int negate(int value) {
        int delta = value == 0 ? 0 : (value > 0 ? -1 : 1);
        int result = 0;
        while (value != 0) {
            value = sum(value, delta);
            result = sum(result, delta);
        }
        return result;
    }

    private static int getMultiplySign(int multiplier1, int multiplier2) {
        if (multiplier1 > 0 && multiplier2 < 0 || multiplier1 < 0 && multiplier2 > 0)
            return -1;
        else
            return 1;
    }

    private static int abs(int value) {
        return (value >= 0) ? value : negate(value);
    }
}
