package com.alds.stack;

import java.util.EmptyStackException;

/**
 * Custom implementation of a stack with additional <i>min()</i>- method
 *
 * @param <T>
 */
public class CustomStack<T extends Comparable<T>> {

    private Node<T> top = null;


    private static class Node<T extends Comparable<T>> {
        T currentElement;
        T min;
        Node<T> next;

        Node(T element) {
            currentElement = element;
            min = element;
        }
    }

    public T pop() {
        checkNotEmpty();
        T item = top.currentElement;
        top = top.next;
        return item;
    }

    public void push(T item) {
        Node<T> t = new Node<T>(item);
        if (top!=null && t.min.compareTo(top.min) > 0)
            t.min = top.min;
        t.next = top;
        top = t;
    }

    public T peek() {
        checkNotEmpty();
        return top.currentElement;
    }

    public boolean isEmpty() {
        return top == null;
    }

    /**
     * <p>Task 3.2. Time for implementation and testing :<i>30 min</i></p>
     * Method that returns minimum element in a stack.
     * Performance complexity is <i>O(1)</i>.
     *
     * @return minimum element in a stack
     */
    public T min() {
        checkNotEmpty();
        return top.min;
    }

    private void checkNotEmpty() {
        if (top == null) throw new EmptyStackException();
    }
}
