package com.alds.chapter16;


import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class FindPairsBySumTest {

    @Test(expected = IllegalArgumentException.class)
    public void testFindPairsWhenUnputNull() {
        ArrayAnalyzer.findPairsByTheirSum(null, 9);
    }

    @Test
    public void testFindPairsWhenArrEmpty() {
        assertTrue(ArrayAnalyzer.findPairsByTheirSum(new int[0], 9).isEmpty());

    }

    @Test
    public void testFindNoPairsWhenArrNotEmpty() {
        assertTrue(ArrayAnalyzer.findPairsByTheirSum(new int[]{1, 2, 3, 4, 5}, 100).isEmpty());
    }

    @Test
    public void testFindPairsWhenArrNotEmpty() {
        Set<IntPairContainer> result = new HashSet<>();
        result.add(new IntPairContainer(0, 4));
        result.add(new IntPairContainer(1, 3));
        List<IntPairContainer> pairs = ArrayAnalyzer.findPairsByTheirSum(new int[]{0, 5, 2, 3, 1, 4}, 4);

        assertEquals(2, pairs.size());

        for (IntPairContainer container : pairs) {
            result.remove(container);
            container.swapPair();
            result.remove(container);
        }
        assertTrue(result.isEmpty());
    }
}
