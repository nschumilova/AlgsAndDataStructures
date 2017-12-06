package com.alds.arrays.strings;

import static org.junit.Assert.*;
import org.junit.Test;

public class PermutationsPalindromeTest {


    @Test(expected = IllegalArgumentException.class)
    public void testPalindromeWhenInputNull(){
        Permutations.isPalindromePermutation(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPalindromeWhenInputEmpty(){
        Permutations.isPalindromePermutation("    ");
    }

    @Test
    public void testOneLetterIsPalindromePermutation(){
        assertTrue(Permutations.isPalindromePermutation("a"));
    }

    @Test
    public void testPalindromeIsPalindromePermutation(){
        assertTrue(Permutations.isPalindromePermutation("tacocat"));
    }

    @Test
    public void testNotPalindromeIsPalindromePermutation(){
        assertTrue(Permutations.isPalindromePermutation("tactcoa"));
    }

    @Test
    public void testNotPalindromeIsNotPalindromePermutation(){
        assertFalse(Permutations.isPalindromePermutation("helloworld"));
    }

    @Test
    public void testCaseInsensitiveIsPalindromePermutation(){
        assertTrue(Permutations.isPalindromePermutation("TactCoA"));
    }

    @Test
    public void testNoDigitOrAlphabetCharsIsNotPalindromePermutation(){
        assertFalse(Permutations.isPalindromePermutation("!!??!!"));
    }

    @Test
    public void testWithSpecialCharactersIsPalindromePermutation(){
        assertTrue(Permutations.isPalindromePermutation("tact, coa!"));
    }
}
