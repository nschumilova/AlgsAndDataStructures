package com.alds.searchsort;


import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class GropuByAnagramTest {


    @Test(expected = IllegalArgumentException.class)
    public void testGroupByAnagramWhenNullInput() {
        SearchProcessor.groupByAnagrams(null);
    }

    @Test
    public void testGroupByAnagramDoesNotChangeContent() {
        String[] initialArr = new String[]{"a", "bcd", "ca", "f", "cdb", "ac", "bdc"};
        String[] copy = Arrays.copyOf(initialArr, initialArr.length);
        SearchProcessor.groupByAnagrams(copy);
        Arrays.sort(initialArr);
        Arrays.sort(copy);
        assertTrue(Arrays.equals(initialArr, copy));
    }

    @Test
    public void testGroupByAnagramWithNoDuplicates() {
        String[] initialArr = new String[]{"a", "bcd", "ca", "f", "cdb", "ac", "bdc"};
        SearchProcessor.groupByAnagrams(initialArr);

        assertEquals(3,getNumOfAdjacentElementsInArray(initialArr,
                new HashSet<>(Arrays.asList("bcd", "cdb", "bdc"))::contains));
        assertEquals(2,getNumOfAdjacentElementsInArray(initialArr,
                new HashSet<>(Arrays.asList("ca", "ac"))::contains));
    }

    @Test
    public void testGroupByAnagramWithDuplicates() {
        String[] initialArr = new String[]{"aaa", "bcd", "aaa", "f", "cdb", "ac", "bdc", "aaa"};
        SearchProcessor.groupByAnagrams(initialArr);

        assertEquals(3,getNumOfAdjacentElementsInArray(initialArr,
                new HashSet<>(Arrays.asList("bcd", "cdb", "bdc"))::contains));
        assertEquals(3,getNumOfAdjacentElementsInArray(initialArr,
                "aaa"::equals));
    }

    private int getNumOfAdjacentElementsInArray(String[] array, Predicate<String> filter) {

        int[] indexes = IntStream.range(0, array.length)
                .filter(i ->
                        filter.test(array[i]))
                .sorted()
                .toArray();
        int number = 1;
        for (int i = 1; i < indexes.length; i++)
            if (indexes[i] - indexes[i - 1] == 1)
                number++;
        return number;

    }


}
