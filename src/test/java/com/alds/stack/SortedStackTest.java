package com.alds.stack;


import static org.junit.Assert.*;
import org.junit.Test;

public class SortedStackTest {

    @Test
    public void testStackSortedWhenInsertionInReversedOrder(){
        CustomStack<Integer> stack = new SortedStack<>();
        stack.push(0);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertTrue(isSorted(stack));
    }

    @Test
    public void testStackSortedWhenInsertionInForwardOrder(){
        CustomStack<Integer> stack = new SortedStack<>();
        stack.push(3);
        stack.push(2);
        stack.push(1);
        stack.push(0);
        assertTrue(isSorted(stack));
    }

    @Test
    public void testStackSortedWhenInsertionInArbitraryOrder(){
        CustomStack<Integer> stack = new SortedStack<>();
        stack.push(3);
        stack.push(8);
        stack.push(5);
        stack.push(6);
        stack.push(2);
        stack.push(1);
        stack.push(10);
        stack.push(0);
        stack.push(17);
        assertTrue(isSorted(stack));
    }

    @Test
    public void testStackSortedWhenInsertionContainsDuplicatesOrder(){
        CustomStack<Integer> stack = new SortedStack<>();
        stack.push(1);
        stack.push(8);
        stack.push(5);
        stack.push(1);
        stack.push(2);
        stack.push(1);
        stack.push(10);
        stack.push(0);
        stack.push(17);
        assertTrue(isSorted(stack));
    }


    private boolean isSorted(CustomStack<Integer> stack){
        int prev = stack.pop();
        while(stack.isEmpty()){
            int current = stack.pop();
            if(current<prev)
                return false;
            prev = current;
        }
        return true;
    }

}
