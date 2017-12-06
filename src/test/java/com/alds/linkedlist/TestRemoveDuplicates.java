package com.alds.linkedlist;
import static org.junit.Assert.*;
import static com.alds.linkedlist.LinkedListHelper.*;
import org.junit.Test;

import java.util.Arrays;

public class TestRemoveDuplicates {

    @Test
    public void testRemoveDuplicatesFromEmptyList(){
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.removeDuplicatesUsingBuffer();
        assertEquals(0, list.size());
    }

    @Test
    public void testRemoveDuplicatesWithNulls(){
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        initializeList(list, 55,null,0,null);
        list.removeDuplicatesUsingBuffer();
        assertEquals(3, list.size());
        assertTrue(list.containsAll(Arrays.asList(55,null,0)));
    }

    @Test
    public void testRemoveDuplicatesInListWithNoDuplicates(){

        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        initializeList(list, 0,8,4,7,1);
        list.removeDuplicatesUsingBuffer();
        assertEquals(5, list.size());
        assertTrue(list.containsAll(Arrays.asList(0,8,4,7,1)));
    }
    @Test
    public void testRemoveDuplicatesInListWithDuplicates(){

        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        initializeList(list, 0,2,1,1,0);
        list.removeDuplicatesUsingBuffer();
        assertEquals(3, list.size());
        assertTrue(list.containsAll(Arrays.asList(0,1,2)));
    }


}
