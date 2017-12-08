package com.alds.arrays.strings;

import static com.alds.util.Helper.checkNotEmpty;
import static com.alds.util.Helper.checkNotNull;

/**
 * Class that implements modification operations on strings
 */
public class StringModifications {

    //TODO
    public static boolean isOneOrZeroEditDifference(String str1, String str2) {
        return isLessOrEqualThanNEditDifferences(str1, str2, 1);
    }

    private static boolean isLessOrEqualThanNEditDifferences(String str1, String str2, int n) {
        checkNotNull(str1);
        checkNotNull(str2);

        if (Math.abs(str1.length() - str2.length()) > n)
            return false;

        else return countEditDifferences(str1, str2) <= n;
    }

    private static int countEditDifferences(String str1, String str2) {
        return 0;
    }

    /**
     * <p>Task 1.6. Time for implementation and testing :<i>45 min</i></p>
     * <p>Implements a method to perform basic string compression using the counts of repeated characters</p>
     * <p>Performance is amortized <i>O(N)</i>
     * (One while loop (number of iterations <i>N</i>): in some iterations <code>StringBuilder.append</code> is called
     * which could lead to dynamic internal array resizing. Each <code>append</code> is <i>O(1)</i> on average,
     * even though some <code>append</code> take <i>O(N)</i> in worst case).
     * Memory usage is <i>O(2N)</i> (size of initial string + size of StringBuilder(<=<i>N</i>) +
     * size of a new String(<=<i>N</i>))</p>
     * @param str
     * @return
     */
    public static String compressString(String str) {
        checkNotNull(str);
        checkNotEmpty(str);

        StringBuilder builder = compressToStrBuilder(str);

        if (builder.length() >= str.length())
            return str;
        else return builder.toString();

    }

    private static StringBuilder compressToStrBuilder(String str) {
        StringBuilder builder = new StringBuilder();
        int counter = 0, index = 0;
        char prev = str.charAt(index);
        while (true) {
            char current = str.charAt(index);
            validateUpperOrLowerCase(current);
            if (current == prev) {
                counter++;
                index++;
                if (index >= str.length()) {
                    builder.append(prev).append(counter);
                    break;
                } else
                    continue;
            }
            builder.append(prev).append(counter);
            prev = current;
            counter = 0;
        }
        return builder;
    }

    private static void validateUpperOrLowerCase(char character) {
        if (!Character.isAlphabetic(character))
            throw new IllegalArgumentException("Input contains non alphabetic letters");
    }

}
