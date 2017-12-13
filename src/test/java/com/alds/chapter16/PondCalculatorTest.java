package com.alds.chapter16;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PondCalculatorTest {

    @Test(expected = IllegalArgumentException.class)
    public void testCountPondsWhenInputNull() {
        PondCalculator.countPondsSizes(null);
    }

    @Test
    public void testCountPondsWhenInputEmpty() {
        assertEquals(0, PondCalculator.countPondsSizes(new int[0][0]).size());
    }

    @Test
    public void testCountPondsWhenInputContainsNoPonds() {
        assertEquals(0, PondCalculator.countPondsSizes(new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}).size());
    }

    @Test
    public void testCountPondsWhenInputContainsNoHeightsAboveWater() {
        assertLists(Arrays.asList(9),
                PondCalculator.countPondsSizes(
                        new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
    }

    @Test
    public void testCountPondsWhenInputContainsOnePond() {
        assertLists(Arrays.asList(1),
                PondCalculator.countPondsSizes(
                        new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}));
    }

    @Test
    public void testCountPondsWhenInputContainsSeveralPonds() {
        assertLists(Arrays.asList(1, 2, 4),
                PondCalculator.countPondsSizes(
                        new int[][]{{0, 2, 1, 0}, {0, 1, 0, 1}, {1, 1, 0, 1}, {0, 1, 0, 1}}));
    }

    private void assertLists(List<Integer> expected, List<Integer> actual) {
        actual.sort(Integer::compareTo);
        assertEquals(expected, actual);
    }


}
