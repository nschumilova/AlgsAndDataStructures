package com.alds.util;

public class Helper {

    public static void checkNotNull(Object input) {
        if (input == null)
            throw new IllegalArgumentException("Input is null");
    }

    public static void checkNotEmpty(String string) {
        if (string.trim().isEmpty())
            throw new IllegalArgumentException("Input string is empty");
    }

    public static boolean sameObjects(String str1, String str2) {
        return str1 == str2;
    }

    public static void checkMoreOrEqualZero(int value) {
        if (value < 0)
            throw new IllegalArgumentException("Input parameter(" + value + ") must be >=0");
    }
}
