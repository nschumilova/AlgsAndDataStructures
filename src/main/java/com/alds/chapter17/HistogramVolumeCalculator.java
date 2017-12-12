package com.alds.chapter17;

import java.util.Deque;
import java.util.LinkedList;

import static com.alds.util.Helper.checkMoreOrEqualZero;
import static com.alds.util.Helper.checkNotNull;

public class HistogramVolumeCalculator {

    private static final int HIST_WIDTH = 1;
    private static final int HIST_LENGTH = 1;

    /**
     * <p>Task 17.21. Time for implementation and testing :<i>120 min</i></p>
     * Method that calculates volume of water which can fill empty space between histograms.
     * <p>Performance: <i>O(N)</i>  (N - size of heights-array)
     * <br>
     * Memory :  size of additional stack  -> <i>O(N)</i> in worst case (when each histogram's height >= (<=)
     * than previous histogram's height)</p>
     *
     * @param heights array with contains 0 or positive numbers. positive number corresponds
     *                to the histogram's presence and is equal to its height. 0 corresponds to
     *                the histogram's absence
     * @return calculated volume
     */
    public static int calculateVolumeBetweenHistograms(int[] heights) {
        checkNotNull(heights);
        Deque<StackElement> stack = new LinkedList<>();
        for (int index = 0; index < heights.length; index++) {
            checkMoreOrEqualZero(heights[index]);
            if (heights[index] == 0) {
                continue;
            }
            if (!stack.isEmpty() && heights[index] > heights[stack.peek().index])
                subtractHollowHistograms(heights, stack);

            StackElement stackElement = getStackElement(heights, stack, index);
            stack.push(stackElement);
        }

        int sumVolume = 0;
        while (!stack.isEmpty())
            sumVolume += stack.pop().volume;
        return sumVolume;
    }

    private static StackElement getStackElement(int[] heights, Deque<StackElement> stack, int index) {
        int volume = 0;
        int prevIndex, maxIndex, minHeight;
        if (stack.isEmpty()) {
            prevIndex = index;
            maxIndex = index;
            minHeight = heights[index];
        } else {
            prevIndex = stack.peek().index;
            minHeight = getMinValue(heights[stack.peek().index], heights[index]);
            maxIndex = getIndexOfMaxValue(heights, stack.peek().maxIndex, index);
        }
        volume += getVolume(minHeight,
                index == prevIndex ? 0 : (index - prevIndex) - HIST_WIDTH,
                HIST_LENGTH);
        return new StackElement(volume, index, maxIndex);
    }

    private static void subtractHollowHistograms(int[] heights, Deque<StackElement> stack) {
        int delta = 0;
        while (stack.peek().index != stack.peek().maxIndex) {
            delta += getVolume(heights[stack.pop().index], HIST_WIDTH, HIST_LENGTH);
        }
        stack.peek().subtractVolume(delta);
    }

    private static int getVolume(int height, int width, int length) {
        return height * width * length;
    }

    private static int getMinValue(int value1, int value2) {
        return (value1 < value2) ? value1 : value2;
    }

    private static int getMaxValue(int value1, int value2) {
        return (value1 > value2) ? value1 : value2;
    }

    private static int getIndexOfMaxValue(int[] arr, int index1, int index2) {
        if (arr[index1] == arr[index2])
            return getMaxValue(index1, index2);
        return (arr[index1] > arr[index2]) ? index1 : index2;
    }

    private static class StackElement {
        int volume;
        int index;
        int maxIndex;

        public StackElement(int volume, int index, int maxIndex) {
            this.volume = volume;
            this.index = index;
            this.maxIndex = maxIndex;
        }

        public void subtractVolume(int delta) {
            volume -= delta;
        }
    }

}
