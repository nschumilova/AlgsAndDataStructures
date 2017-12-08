package com.alds.linkedlist;

import static com.alds.helper.LinkedListHelper.initializeList;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.LinkedList;
import java.util.function.BiFunction;

public class NumbersListProcessorTest {

    LinkedList<Integer> first = new LinkedList<>();
    LinkedList<Integer> second = new LinkedList<>();

    @Test(expected = IllegalArgumentException.class)
    public void testSumInReversedOrderWhenInputNull(){
        testNullLists(LinkedListProcessor::sumNumbersInReversedOrder);
    }
    @Test
    public void testSumInReversedOrderEmptyLists(){
        testEmptyLists(LinkedListProcessor::sumNumbersInReversedOrder);
    }
    @Test
    public void testSumInReversedOrderSameLengthNumbers(){
        initializeList(first,7,1,6);
        initializeList(second,5,9,4);
        LinkedList<Integer> result = new LinkedList<>();
        initializeList(result, 2,1,1,1);
        testSumLength(LinkedListProcessor::sumNumbersInReversedOrder, result);
    }
    @Test
    public void testSumInReversedOrderDiffLengthNumbers(){
        initializeList(first,7,1,8);
        initializeList(second,4);
        LinkedList<Integer> result = new LinkedList<>();
        initializeList(result, 1,2,8);
        testSumLength(LinkedListProcessor::sumNumbersInReversedOrder, result);
    }



    @Test(expected = IllegalArgumentException.class)
    public void testSumInForwardOrderWhenInputNull(){
        testNullLists(LinkedListProcessor::sumNumbersInForwardOrder);
    }
    @Test
    public void testSumInForwardOrderEmptyLists(){
        testEmptyLists(LinkedListProcessor::sumNumbersInForwardOrder);
    }
    @Test
    public void testSumInForwardOrderSameLengthNumbers(){
        initializeList(first,6,1,7);
        initializeList(second,4,9,5);
        LinkedList<Integer> result = new LinkedList<>();
        initializeList(result, 1,1,1,2);
        testSumLength(LinkedListProcessor::sumNumbersInForwardOrder, result);
    }
    @Test
    public void testSumInForwardOrderDiffLengthNumbers(){
        initializeList(first,7,1,8);
        initializeList(second,3);
        LinkedList<Integer> result = new LinkedList<>();
        initializeList(result, 1,0,1,8);
        testSumLength(LinkedListProcessor::sumNumbersInForwardOrder, result);
    }


    private void testNullLists(BiFunction<LinkedList<Integer>, LinkedList<Integer>,LinkedList<Integer>> func){
        func.apply(null, null);
    }
    private void testEmptyLists(BiFunction<LinkedList<Integer>, LinkedList<Integer>,LinkedList<Integer>> func){
        assertTrue(func.apply(first, second).isEmpty());
    }
    private void testSumLength(BiFunction<LinkedList<Integer>, LinkedList<Integer>,LinkedList<Integer>> func,
                                   LinkedList<Integer> expected){
        LinkedList<Integer> result = func.apply(first, second);
        assertEquals(expected,result);
    }
}
