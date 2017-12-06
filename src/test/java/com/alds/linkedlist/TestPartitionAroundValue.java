package com.alds.linkedlist;

import static org.junit.Assert.*;
import static com.alds.linkedlist.CutomLinkedListHelper.*;

import org.junit.Test;

import java.util.Arrays;

public class TestPartitionAroundValue {


    @Test(expected = IllegalArgumentException.class)
    public void testRemoveDuplicatesWhenNoComparatorSet(){
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        initializeList(list, 2, 1);
        list.partitionAroundValue(1, null);
        assertEquals(0, list.size());
    }
    @Test
    public void testPartitionWithValueIncluded() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        initializeList(list, 1, 2, 10, 5, 8, 5, 3);
        list.partitionAroundValue(5, Integer::compareTo);
        CustomLinkedList<Integer> sublist = list.subList(0, 4);
        assertEquals(5,sublist.size());
        assertEquals(2, list.countItemOccurences(5));
        assertTrue(sublist.containsAll(Arrays.asList(1, 2, 5, 3)));
    }

    @Test
    public void testPartitionWithValueExcluded() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        initializeList(list, 1, 2, 10, 8, 3);
        list.partitionAroundValue(5, Integer::compareTo);
        CustomLinkedList<Integer> sublist = list.subList(0, 2);
        assertEquals(3,sublist.size());
        assertTrue(sublist.containsAll(Arrays.asList(1, 2, 3)));
    }
}
