package com.alds.test;

import java.util.*;


import static org.junit.Assert.*;

import org.junit.Test;

public class DivisionPairsOf9DigitsTest {

    @Test
    public void testDivisionPairsFound() {
        List<DivisionContainer> containers = PermutationProcessor.getDivisionPairsOf9DigitsPermutation();
        assertFalse(containers.isEmpty());
    }

    @Test
    public void testDivisionPairsContainAll9Digits(){
        List<DivisionContainer> containers = PermutationProcessor.getDivisionPairsOf9DigitsPermutation();
        Set<Integer> digits = new HashSet<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
        for(DivisionContainer container : containers){
            assertEquals(digits, splitIntToDigits(container.getDividend()));
            assertEquals(digits, splitIntToDigits(container.getDivisor()));
        }
    }

    @Test
    public void testDivisionPairsDivideWithoutRemainder(){
        List<DivisionContainer> containers = PermutationProcessor.getDivisionPairsOf9DigitsPermutation();
        for(DivisionContainer container : containers){
            assertEquals(0, container.getDividend()%container.getDivisor());
        }
    }

    private Set<Integer> splitIntToDigits(Integer value) {
        Set<Integer> digits = new HashSet<>();
        String str = value.toString();
        for (int i = 0; i < str.length(); i++) {
            digits.add(Character.getNumericValue(str.charAt(i)));
        }
        return digits;
    }
}
