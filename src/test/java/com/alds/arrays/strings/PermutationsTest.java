package com.alds.arrays.strings;


import static org.junit.Assert.*;
import org.junit.Test;

public class PermutationsTest {

    @Test(expected = IllegalArgumentException.class)
    public void testPermutationNullInput(){
        Permutations.isPermutation("abc", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBothEmptyStrings(){
        Permutations.isPermutation("  ", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOneEmptyString(){
        assertFalse(Permutations.isPermutation("abc", ""));
    }

    @Test
    public void testDiffStringsLengthsIsNotPermutation(){
        assertFalse(Permutations.isPermutation("abc", "cabd"));
    }

    @Test
    public void testSameStringsIsPermutation(){
        assertTrue(Permutations.isPermutation(new String("helloworld"), new String("helloworld")));
    }

    @Test
    public void testDifferentStringsIsPermutation(){
        assertTrue(Permutations.isPermutation("helloworld", "olrelhlodw"));
    }

    @Test
    public void testDifferentStringsIsNotPermutation(){
        assertFalse(Permutations.isPermutation("helloworld", "olrelabcwd"));
    }
}
