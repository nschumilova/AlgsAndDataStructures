package com.alds.test;

import static com.alds.util.Helper.checkNotNull;

public class FibonacciChecker {

    public static boolean isPartOfFibonacci(String string) {

        checkNotNull(string);
        String trimmed = string.trim();
        if (trimmed.isEmpty())
            return false;
        int first = -1;
        int second = -1;
        for (int i = 0; i < trimmed.length(); i++) {
            char ch = trimmed.charAt(i);
            if (!Character.isDigit(ch))
                return false;
            while (true) {
                int next = getFibonacciAfter(first, second);
                int chNumeric = Character.getNumericValue(ch);

                first = second;
                second = next;
            }
        }

        return false;
    }

    private static int getFibonacciAfter(int first, int second) {
        if (first < 0)
            if (second < 0)
                return 0;
            else return 1;
        else return first + second;
    }
}
