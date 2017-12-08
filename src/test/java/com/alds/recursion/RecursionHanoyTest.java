package com.alds.recursion;

import static org.junit.Assert.*;

import com.alds.helper.LinkedListHelper;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class RecursionHanoyTest {

    @Test(expected = IllegalArgumentException.class)
    public void testHanoyWhenInputNull() {
        RecursionProcessor.moveHanoyTowers(null, null, null);
    }

    @Test
    public void testHanoyWhenSourceEmpty() {
        Deque<Integer> source = new LinkedList<>();
        Deque<Integer> destination = new LinkedList<>();
        Deque<Integer> auxiliary = new LinkedList<>();
        RecursionProcessor.moveHanoyTowers(source, destination, auxiliary);
        assertTrue(destination.isEmpty());
        assertTrue(source.isEmpty());
        assertTrue(auxiliary.isEmpty());
    }

    @Test
    public void testHanoyWhenSourceHasOneElement() {
        Deque<Integer> source = new LinkedList<>();
        source.push(1);
        Deque<Integer> destination = new LinkedList<>();
        Deque<Integer> auxiliary = new LinkedList<>();
        RecursionProcessor.moveHanoyTowers(source, destination, auxiliary);
        assertTrue(destination.pop() == 1);
        assertTrue(destination.isEmpty());
        assertTrue(source.isEmpty());
        assertTrue(auxiliary.isEmpty());
    }

    @Test
    public void testHanoyWhenSourceHasSeveralElement() {
        List<Integer> expected = new LinkedList<>();
        LinkedListHelper.initializeList(expected, 4, 3, 2, 1, 0);
        Deque<Integer> source = new LinkedList<>();
        pushAllToStack(source, expected);
        Deque<Integer> destination = new LinkedList<>();
        Deque<Integer> auxiliary = new LinkedList<>();
        RecursionProcessor.moveHanoyTowers(source, destination, auxiliary);
        List<Integer> result = new LinkedList<>();
        popAllFromStack(destination, result);
        assertEquals(expected, result);
        assertTrue(source.isEmpty());
        assertTrue(auxiliary.isEmpty());
    }

    private void pushAllToStack(Deque<Integer> stack, List<Integer> list) {
        list.stream().forEach(stack::push);
    }

    private void popAllFromStack(Deque<Integer> stack, List<Integer> list) {
        while (!stack.isEmpty())
            list.add(0,stack.pop());
    }
}
