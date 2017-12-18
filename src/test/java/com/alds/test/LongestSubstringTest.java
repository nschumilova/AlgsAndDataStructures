package com.alds.test;


import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

public class LongestSubstringTest {

    @Test(expected = IllegalArgumentException.class)
    public void testLongestSubstringWhenInputNull() {
        LongestSubstringFinder.find(null);
    }

    @Test
    public void testLongestSubstringWhenInputEmpty() {
        assertFalse(LongestSubstringFinder.find(new HashSet<>()).isPresent());
    }

    @Test
    public void testLongestSubstringWhenInputHasOneString() {
        assertEquals("ABCDE",
                LongestSubstringFinder.find(new HashSet<>(Arrays.asList("ABCDE"))).get());
    }

    @Test
    public void testLongestSubstringHasOneLetter() {
        assertEquals("E",
                LongestSubstringFinder.
                        find(new HashSet<>(Arrays.asList("ABCDEFG", "HIGKLEM", "EQRSTUV", "WXYE"))).get());
    }

    @Test
    public void testLongestSubstringWhenTwoIntersections() {
        assertEquals("EFG",
                LongestSubstringFinder.
                        find(new HashSet<>(Arrays.asList("ANOBCDEFG", "NOHIGKLEFGM", "EFGNOQRSTUV", "WXYNOEFG"))).get());
    }

    @Test
    public void testLongestSubstringNoIntersectionsInAllWords() {
        assertFalse(
                LongestSubstringFinder.
                        find(new HashSet<>(Arrays.asList("ABCDEFG","HIGKLMNOP","QRSTUVW","XYZ"))).isPresent());
    }

    @Test
    public void testLongestSubstringNoIntersectionsInSomeWord() {
        assertFalse(
                LongestSubstringFinder.
                        find(new HashSet<>(Arrays.asList("ABCDEFG","ABCHIGK","LMNOP","QRSTUV","XYZABC"))).isPresent());
    }
}
