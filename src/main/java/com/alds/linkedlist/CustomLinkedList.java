package com.alds.linkedlist;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Custom implementation of singly linked list with basic set of operations
 *
 * @param <T> - data type of list's elements
 */
public class CustomLinkedList<T> {

    private int size;
    private Node<T> head;

    public int size() {
        return size;
    }

    public void addFirst(T value) {
        addFirst(new Node<>(value, head));
    }

    public boolean contains(T item) {
        if (size == 0)
            return false;
        Predicate<T> predicate = getComparisonPredicate(item);
        for (Node<T> node = head; node != null; node = node.next)
            if (predicate.test(node.item))
                return true;
        return false;
    }

    public int countItemOccurences(T item) {
        int count = 0;
        if (size > 0) {
            Predicate<T> predicate = getComparisonPredicate(item);
            for (Node<T> node = head; node != null; node = node.next)
                if (predicate.test(node.item))
                    count++;
        }
        return count;
    }

    public boolean containsAll(Collection<T> collection) {
        if (collection == null)
            throw new IllegalArgumentException("Input collection is null");
        for (T item : collection)
            if (!contains(item))
                return false;
        return true;
    }

    public CustomLinkedList<T> subList(int start, int end) {
        validateIndex(start);
        validateIndex(end);
        if (start > end) {
            start = start ^ end;
            end = start ^ end;
            start = start ^ end;
        }
        CustomLinkedList<T> subList = new CustomLinkedList<>();
        subList.size = (end - start) + 1;
        subList.head = copySublist(start, end);
        return subList;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Index " + index + " is not in the range [0;" + size + "]");

    }

    /**
     * <p>Task 2.1. Time for implementation and testing :<i>40 min</i></p>
     * <p>Removes duplicated elements in unsorted linked list.</p>
     * <p>Performance depends on the size of list and equals <i>O(N)</i>.
     * Memory usage is <i>O(2N)</i> (size of the list + size of HashSet (<= <i>N</i>))</p>
     */
    public void removeDuplicatesUsingBuffer() {
        if (size <= 1)
            return;
        Set<T> uniqueElements = new HashSet<>();
        boolean containsNulls = false;
        Node<T> current = head;
        Node<T> prev = null;
        while (current != null) {

            if (current.item == null) {
                if (containsNulls) {
                    removeNext(prev);
                    current = prev;
                } else
                    containsNulls = true;
            } else {
                if (uniqueElements.contains(current.item)) {
                    removeNext(prev);
                    current = prev;
                } else
                    uniqueElements.add(current.item);
            }

            prev = current;
            current = current.next;
        }
    }

    /**
     * <p>Task 2.4. Time for implementation and testing :<i>90 min</i></p>
     * Method that implements partitioning of a list around some value so that all nodes less than value
     * come before all nodes greater or equal to value.
     * <p>Performance depends on the size of the list and equals <i>O(N)</i> (defined by the list traversal).
     * Memory usage is <i>O(N)</i> (size of the list )</p>
     *
     * @param value
     * @param comparator
     */
    public void partitionAroundValue(T value, Comparator<T> comparator) {
        if (comparator == null || value == null)
            throw new IllegalArgumentException("Input parameters are not initialized");
        if (size <= 1)
            return;
        Node<T> insertionPointer = new Node<>(null, head);
        Node<T> prevNode = null;
        Node<T> currentNode = head;
        while (currentNode != null) {
            int compare = comparator.compare(currentNode.item, value);
            if (compare <= 0) {
                Node<T> newInsertionPointer;
                //if insertion position will not change
                if (insertionPointer.next != currentNode) {
                    Node<T> removedNode = removeNext(prevNode);
                    insertAfter(insertionPointer, removedNode);
                    currentNode = prevNode;
                    newInsertionPointer = removedNode;

                } else
                    newInsertionPointer = currentNode;
                if (compare != 0)
                    insertionPointer = newInsertionPointer;

            }
            prevNode = currentNode;
            currentNode = currentNode.next;
        }
    }

    private Node<T> getNode(int index) {
        int counter = 0;
        Node node = head;
        while (counter != index)
            node = head.next;
        return node;
    }

    private Node<T> copySublist(int start, int end) {
        Node startNode = getNode(start);
        Node root = new Node(startNode.item, null);
        Node current = root;
        while (start < end) {
            startNode = startNode.next;
            Node nextNode = new Node(startNode.item, null);
            current.next = nextNode;
            current = current.next;
            start++;
        }
        return root;
    }

    private Predicate<T> getComparisonPredicate(T item) {
        return (val) -> (item == null) ? (val == null) : item.equals(val);
    }

    private Node<T> removeNext(Node<T> node) {
        Node<T> removedNode = null;
        if (node != null && node.next != null) {
            removedNode = node.next;
            node.next = node.next.next;
            removedNode.next = null;
            size--;
        }
        return removedNode;
    }

    private void insertAfter(Node<T> predecessor, Node<T> nodeToInsert) {
        if (nodeToInsert != null) {
            Node<T> oldNext = predecessor.next;
            predecessor.next = nodeToInsert;
            nodeToInsert.next = oldNext;
            if (oldNext == head)
                head = nodeToInsert;
            size++;
        }
    }

    private void addFirst(Node<T> newNode) {
        head = newNode;
        size++;
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
