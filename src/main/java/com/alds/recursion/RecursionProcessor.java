package com.alds.recursion;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.*;

import static com.alds.util.Helper.checkNotNull;

/**
 * Class that contains implementations of recursion algorithms
 */
public class RecursionProcessor {


    /**
     * <p>Task 8.6. Time for implementation and testing :<i>20 min</i></p>
     * Method  that implements hanoy towers problem.
     * <p>Performance depends on the size of the source stack and equals <i>O(2^N)</i>
     * (2 because the third recursive call takes O(1)).
     * Memory usage is <i>O(log(N/3))</i></p>
     *
     * @param sourceStack      - stack which contains disks sorted from bottom to top (desc)
     * @param destinationStack - stack where the disks must be put
     * @param auxStack         - auxiliary stack
     * @param <T>              - type of elements in stacks
     */
    public static <T> void moveHanoyTowers(Deque<T> sourceStack,
                                           Deque<T> destinationStack,
                                           Deque<T> auxStack) {
        checkNotNull(sourceStack);
        checkNotNull(destinationStack);
        checkNotNull(auxStack);
        if (sourceStack.size() > 0) {
            moveHanoyRecursevly(sourceStack, destinationStack, auxStack, sourceStack.size());
        }
    }

    private static <T> void moveHanoyRecursevly(Deque<T> sourceStack,
                                                Deque<T> destinationStack,
                                                Deque<T> auxStack,
                                                int size) {
        if (size == 1)
            destinationStack.push(sourceStack.pop());
        else {
            moveHanoyRecursevly(sourceStack, auxStack, destinationStack, size - 1);
            moveHanoyRecursevly(sourceStack, destinationStack, auxStack, 1);
            moveHanoyRecursevly(auxStack, destinationStack, sourceStack, size - 1);
        }

    }

    /**
     * <p>Task 8.7. Time for implementation and testing :<i>30 min</i></p>
     * Method  that creates all permutations of word's letters.
     * <p>Performance depends on the number of word's letters: <i>O(2^N)</i>.
     * Memory usage is <i>O(N + log(N)+K)</i>, where K is the number of permutations</p>
     *
     * @param string - word
     * @return list of all permutations
     */
    public static Set<String> getCharPermutations(String string) {
        checkNotNull(string);
        Set<String> set = new HashSet<>();
        if (string.isEmpty()) {
            return set;
        }
        List<StringBuilder> sbList = new LinkedList<>();
        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            switchChars(chars, 0, i);
            sbList.addAll(getCharsRecursively(chars, 0));
        }
        for (StringBuilder sb : sbList) {
            set.add(sb.toString());
        }
        return set;
    }

    private static void switchChars(char[] chars, int ind1, int ind2) {
        if (ind1 != ind2) {
            char ch1 = chars[ind1];
            chars[ind1] = chars[ind2];
            chars[ind2] = ch1;
        }
    }

    private static List<StringBuilder> getCharsRecursively(char[] chars, int index) {
        if (index == chars.length - 1) {
            List<StringBuilder> list = new LinkedList<>();
            StringBuilder sb = new StringBuilder();
            sb.append(chars[index]);
            list.add(sb);
            return list;
        } else {
            char chr = chars[index];
            List<StringBuilder> list = getCharsRecursively(chars, index + 1);
            ListIterator<StringBuilder> iterator = list.listIterator();
            while (iterator.hasNext()) {
                StringBuilder next = iterator.next();
                StringBuilder sb = new StringBuilder();
                sb.append(chr);
                sb.append(next);
                next.append(chr);
                iterator.add(sb);
            }
            return list;
        }
    }

    /**
     * <p>Task 8.13. Time for implementation and testing :<i>30 min</i></p>
     * Finds max height of boxes which can be inserted one into another,
     * provided that the box which is inserted is less by all parameters(height, width, length)
     * than the box where the insertion occurs
     * <p>Performance : sorting <i>O(N log N)</i> +  finding paths: <i>O(N*K)</i>,
     * where N is the size of input array of boxes and K
     * is the number of boxes which are inserted one into another
     * Memory usage is <i>O(N + K)</i>, where N stands for the size of additional array,
     * which contains computed heights, and K stands for the number of boxes which are inserted one into another</p>
     *
     * @param array - array of all boxes
     * @return max height of inserted boxes
     */
    public static int getMaxHeightOfBoxesStack(Box[] array) {
        checkNotNull(array);

        Arrays.sort(array, Comparator.comparingInt(Box::getHeight).reversed());
        int maxHeight = 0;
        int[] foundHeights = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            int height = getMaxHeightOfBoxesStackRecursively(array, i, foundHeights);
            if (maxHeight < height)
                maxHeight = height;
        }
        return maxHeight;
    }

    private static int getMaxHeightOfBoxesStackRecursively(Box[] array, int currentIndex, int[] foundHeights) {

        if (foundHeights[currentIndex] != 0)
            return foundHeights[currentIndex];
        int maxHeight = array[currentIndex].getHeight();
        for (int nextIndex = currentIndex + 1; nextIndex < array.length; nextIndex++) {
            if (boxInsertionPossible(array[currentIndex], array[nextIndex])) {
                int height = array[currentIndex].getHeight() +
                        getMaxHeightOfBoxesStackRecursively(array, currentIndex + 1, foundHeights);
                if (maxHeight < height)
                    maxHeight = height;
            }

        }
        foundHeights[currentIndex] = maxHeight;
        return maxHeight;
    }

    private static boolean boxInsertionPossible(Box outerBox, Box innerBox) {
        return outerBox.getHeight() > innerBox.getHeight() &&
                outerBox.getWidth() > innerBox.getWidth() &&
                outerBox.getLength() > innerBox.getLength();
    }
}
