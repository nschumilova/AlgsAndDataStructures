package com.alds.recursion;


import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecursionPermutationTest {


    @Test(expected = IllegalArgumentException.class)
    public void testPermutationWhenInputNull() {
        RecursionProcessor.getCharPermutations(null);
    }

    @Test
    public void testPermutationWhenInputEmpty() {
        assertTrue(RecursionProcessor.getCharPermutations("").isEmpty());
    }

    @Test
    public void testPermutationWhenInputHasOneElement() {
        Set<String> set = RecursionProcessor.getCharPermutations("a");
        assertEquals(1,set.size());
        assertEquals("a",set.iterator().next());
    }

    @Test
    public void testPermutationWhenInputHasSeveralElements() {
        Set<String> expected = new HashSet<>();
        expected.addAll(Arrays.asList("abc", "acb", "bac", "bca", "cab","cba"));
        Set<String> set = RecursionProcessor.getCharPermutations("abc");
        assertEquals(expected, set);
    }

    @Test
    public void testPermutationWhenInputHasDuplicates() {
        Set<String> expected = new HashSet<>();
        expected.addAll(Arrays.asList("aac", "aca", "caa"));
        Set<String> set = RecursionProcessor.getCharPermutations("aac");
        assertEquals(expected, set);
    }

}
