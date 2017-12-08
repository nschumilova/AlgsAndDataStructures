package com.alds.stack;

/**
 * Stack that stores elements in sorted order.(Min element is always on top).
 *
 * @param <T>
 */
public class SortedStack<T extends Comparable<T>> extends CustomStack<T> {


    /**
     * <p>Task 3.5. Time for implementation and testing :<i>30 min</i></p>
     * Method that pushes new element into stack.
     * Performance complexity is <i>O(N)</i> in the worst case (when new element is the largest)
     * and <i>O(1)</i> in the best case (when new element is the smallest).
     * Memory usage is  <i>O(1)</i> in the best case and <i>O(N)</i> in the worst case</p>
     *
     */
    @Override
    public void push(T item) {
        CustomStack<T> auxStack = new CustomStack<>();
        while (!this.isEmpty() && item.compareTo(this.peek()) > 0)
            auxStack.push(this.pop());
        auxStack.push(item);
        while (!auxStack.isEmpty())
            super.push(auxStack.pop());
    }

    @Override
    public T min() {
        throw new UnsupportedOperationException();
    }
}
