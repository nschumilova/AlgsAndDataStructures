package com.alds.stack;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.EmptyStackException;

public class MinStackTest {

    @Test(expected = EmptyStackException.class)
    public void testMinWhenEmptyStack(){
        CustomStack<Integer> stack = new CustomStack<>();
        stack.min();
    }

    @Test
    public void testMinWhenOneElementInStack(){
        CustomStack<Integer> stack = new CustomStack<>();
        stack.push(10);
        assertEquals(10, stack.min().intValue());
    }

    @Test
    public void testMinInTheBottomOfStack(){
        CustomStack<Integer> stack = new CustomStack<>();
        stack.push(7);
        stack.push(8);
        stack.push(9);
        stack.push(10);
        assertEquals(7, stack.min().intValue());
    }

    @Test
    public void testMinAfterInsertionAndDeletionInStack(){
        CustomStack<Integer> stack = new CustomStack<>();
        stack.push(7);
        stack.push(8);
        stack.push(1);
        stack.pop();
        stack.push(2);
        stack.push(3);
        stack.push(0);
        stack.push(100);
        stack.push(101);
        stack.pop();
        assertEquals(0, stack.min().intValue());
    }

}
