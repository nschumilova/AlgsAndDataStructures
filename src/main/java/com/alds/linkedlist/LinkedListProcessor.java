package com.alds.linkedlist;

import javax.xml.stream.events.Characters;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import static com.alds.util.Helper.checkNotNull;

public class LinkedListProcessor {
    private static final int BASIS = 10;

    /**
     * <p>Task 2.5. Time for implementation and testing :<i>60 min</i></p>
     * Function that adds two numbers represented as a linked list, where each node contains a single digit.
     * The digits are stored in reverse order.
     * <p>Performance depends on the size of the largest list and equals <i>O(max(N1, N2)</i> (defined by the lists traversal).
     * Memory usage is <i>O(max(N1, N2)</i> (size of the first list + size of the second list + size of the result)</p>
     *
     * @param firstNumber  - first number to add
     * @param secondNumber - second number to add
     * @return - reversed ordered number represented as a linked list
     */
    public static LinkedList<Integer> sumNumbersInReversedOrder(LinkedList<Integer> firstNumber, LinkedList<Integer> secondNumber) {
        checkNotNull(firstNumber);
        checkNotNull(secondNumber);
        Iterator<Integer> firstIterator = firstNumber.iterator();
        Iterator<Integer> secondIterator = secondNumber.iterator();

        LinkedList<Integer> result = new LinkedList<>();
        int prevPositionSum = 0;
        while (firstIterator.hasNext() || secondIterator.hasNext()) {
            int sum = prevPositionSum;
            if (firstIterator.hasNext())
                sum += firstIterator.next();
            if (secondIterator.hasNext())
                sum += secondIterator.next();
            result.add(sum % BASIS);
            prevPositionSum = sum / BASIS;
        }
        return result;
    }

    /**
     * <p>Task 2.6. Time for implementation and testing :<i>65 min</i></p>
     * Function that checks if a linked list is a palindrome
     * <p>Performance depend on the size of a list and is equal to <i>O(N)</i> (defined by the list traversal).
     * Memory usage depend on the depth of recursion and equals to  <i>O(N/2)</i></p>
     *
     * @param list - list which checked to contain palindrome
     * @return - <code>true</code> if the list is palindrome and <code>false</code> otherwise
     */
    public static boolean isListPalindrome(LinkedList<Character> list) {

        checkNotNull(list);
        int size = list.size();
        if (size <= 1)
            return true;
        PalindromeTraversalHolder.middle = Math.round(size / 2f);
        PalindromeTraversalHolder.listSizeEven = size % 2 == 0;
        Iterator<Character> iterator = list.iterator();
        return checkPalindrome(iterator, 1).isPalindrome;
    }

    private static PalindromeTraversalHolder checkPalindrome(Iterator<Character> iterator, int position) {
        Character value = getNext(iterator);
        PalindromeTraversalHolder newHolder = new PalindromeTraversalHolder();

        if (position == PalindromeTraversalHolder.middle) {
            if (PalindromeTraversalHolder.listSizeEven)
                newHolder.isPalindrome = value.equals(getNext(iterator));
            else
                newHolder.isPalindrome = true;
        } else {
            PalindromeTraversalHolder internalHolder = checkPalindrome(iterator, position + 1);
            newHolder.isPalindrome = internalHolder.isPalindrome && value.equals(getNext(iterator));
        }

        return newHolder;
    }

    private static Character getNext(Iterator<Character> iterator) {
        Character next = iterator.next();
        if (next == null)
            throw new IllegalStateException("List contains null values");
        return next;
    }

    private static class PalindromeTraversalHolder {
        static int middle = 0;
        static boolean listSizeEven;
        boolean isPalindrome = true;

    }

    //TODO
    public static LinkedList<Integer> sumNumbersInForwardOrder(LinkedList<Integer> firstNumber, LinkedList<Integer> secondNumber) {
        checkNotNull(firstNumber);
        checkNotNull(secondNumber);
        return null;
    }

    private int forwardSum(ListIterator<Integer> result, Iterator<Integer> first, Iterator<Integer> second, int position) {
        if (!first.hasNext() && !second.hasNext())
            return 0;
        return 0;
    }
}
