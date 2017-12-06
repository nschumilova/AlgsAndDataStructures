package com.alds.linkedlist;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Custom implementation of singly linked list with basic set of operations
 * @param <T> - data type of list's elements
 */
public class CustomLinkedList<T> {

    private int size;
    private Node<T> head;

    public int size() {
        return size;
    }

    public void addFirst(T value) {
        Node<T> newNode = new Node<>(value, head);
        head = newNode;
        size++;
    }

    public boolean contains(T item) {
        if (size == 0)
            return false;
        Predicate<T> predicate = (val) -> (item == null) ? (val == null) : item.equals(val);
        for (Node<T> node = head; node != null; node = node.next)
            if (predicate.test(node.item))
                return true;
        return false;
    }

    public boolean containsAll(Collection<T> collection){
        if(collection==null)
            throw new IllegalArgumentException("Input collection is null");
        for(T item: collection)
            if (!contains(item))
                return false;
        return true;
    }

    /**
     * <p>Task 2.1. Time for implementation and testing :<i>40 min</i></p>
     * <p>Removes duplicated elements in unsorted linked list.</p>
     * <p>Performance depends on the size of list and equals <i>O(N)</i>.
     * Memory usage is <i>O(N)</i> (size of the list + size of HashSet (<= <i>N</i>))</p>
     */
    public void removeDuplicatesUsingBuffer() {
        if (size <= 1)
            return;
        Set<T> uniqueElements = new HashSet<>();
        boolean containsNulls = false;
        Node<T> current = head;
        Node<T> prev = null;
        while (current!= null) {

            if (current.item == null) {
                if (containsNulls)
                    removeNext(prev);
                else
                    containsNulls = true;
            } else {
                if (uniqueElements.contains(current.item))
                    removeNext(prev);
                else
                    uniqueElements.add(current.item);
            }

            prev = current;
            current = current.next;
        }
    }

    public void removeNext(Node<T> node) {
        if (node != null && node.next != null) {
            node.next = node.next.next;
            size--;
        }
    }

    private static class Node<T> {
        T item;
        Node<T> next;

        public Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }
    }
}
