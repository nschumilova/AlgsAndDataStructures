package com.alds.searchsort;

import static org.junit.Assert.*;
import org.junit.Test;

public class SearchInSortedShiftedArrayTest {

    @Test(expected = IllegalArgumentException.class)
    public void testSearchWhenNullInput(){
        SearchProcessor.findIndexInSortedAndShiftedArray(null,9);
    }

    @Test
    public void testSearchWithNoDuplicatesFound(){
        int[] arr = {5,6,7,8,9,0,1,2,3};
        assertEquals(7, SearchProcessor.findIndexInSortedAndShiftedArray(arr,2));
    }

    @Test
    public void testSearchWithNoDuplicatesNotFound(){
        int[] arr = {5,6,7,8,9,0,1,2,3};
        assertEquals(-1, SearchProcessor.findIndexInSortedAndShiftedArray(arr,4));
    }

    @Test
    public void testSearchWithDuplicatesFound(){
        int[] arr = {5,5,5,5,5,5,5,5,5,5,0,1,2,3,5};
        assertEquals(12, SearchProcessor.findIndexInSortedAndShiftedArray(arr,2));
    }

    @Test
    public void testSearchWithDuplicatesNotFound(){
        int[] arr = {5,5,5,5,5,5,5,5,5,5,0,1,2,3,5};
        assertEquals(-1, SearchProcessor.findIndexInSortedAndShiftedArray(arr,4));
    }
    @Test
    public void testSearchWithAllDuplicatesFound(){
        int[] arr = {5,5,5,5,5,5,5,5};
        assertNotEquals(-1, SearchProcessor.findIndexInSortedAndShiftedArray(arr,5));
    }
    @Test
    public void testSearchWithAllDuplicatesNotFound(){
        int[] arr = {5,5,5,5,5,5,5,5,5};
        assertEquals(-1, SearchProcessor.findIndexInSortedAndShiftedArray(arr,4));
    }
}
