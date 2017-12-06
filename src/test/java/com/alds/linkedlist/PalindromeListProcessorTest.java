package com.alds.linkedlist;

import static com.alds.linkedlist.LinkedListHelper.initializeList;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class PalindromeListProcessorTest {

    @Test(expected = IllegalArgumentException.class)
    public void testIsListPalindromeWithNullInput(){
        LinkedListProcessor.isListPalindrome(null);
    }

    @Test(expected = IllegalStateException.class)
    public void testIsListPalindromeWithNullsInList(){
        LinkedList<Character> list = new LinkedList<>();
        initializeList(list, 'a','b',null,'a');
        LinkedListProcessor.isListPalindrome(list);
    }

    @Test
    public void testEmptyListIsPalindrome(){
        LinkedList<Character> list = new LinkedList<>();
        assertTrue(LinkedListProcessor.isListPalindrome(list));
    }

    @Test
    public void testOneElementListIsPalindrome(){
        LinkedList<Character> list = new LinkedList<>();
        list.add('a');
        assertTrue(LinkedListProcessor.isListPalindrome(list));
    }

    @Test
    public void testEvenSizeListIsPalindrome(){
        LinkedList<Character> list = new LinkedList<>();
        initializeList(list, 'a','b','c','c','b','a');
        assertTrue(LinkedListProcessor.isListPalindrome(list));
    }

    @Test
    public void testUnevenSizeListIsPalindrome(){
        LinkedList<Character> list = new LinkedList<>();
        initializeList(list, 'a','b','c','d','c','b','a');
        assertTrue(LinkedListProcessor.isListPalindrome(list));
    }
    @Test
    public void testEvenSizeListIsNotPalindrome(){
        LinkedList<Character> list = new LinkedList<>();
        initializeList(list, 'k','b','c','c','b','a');
        assertFalse(LinkedListProcessor.isListPalindrome(list));
    }

    @Test
    public void testUnevenSizeListIsNotPalindrome(){
        LinkedList<Character> list = new LinkedList<>();
        initializeList(list, 'k','b','c','d','c','b','a');
        assertFalse(LinkedListProcessor.isListPalindrome(list));
    }
}


