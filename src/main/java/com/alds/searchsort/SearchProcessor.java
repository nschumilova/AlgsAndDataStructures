package com.alds.searchsort;

import static com.alds.util.Helper.checkNotNull;

/**
 * Class that contains implementations of search algorithms
 */
public class SearchProcessor {

    /**
     * <p>Task 10.3. Time for implementation and testing :<i>60 min</i></p>
     * Searches index of an element in sorted(asc) array which is shifted  by unknown number
     * Performance: from <i>O(logN)</i> to <i>O(N)</i>
     * Memory: <i>O(1)</i>
     *
     * @param arr   - sorted and shifted array
     * @param value - value to search
     * @return found index or -1 if array does not contain element
     */
    public static int findIndexInSortedAndShiftedArray(int[] arr, int value) {
        checkNotNull(arr);
        return findRecursive(arr, value, 0, arr.length - 1);
    }

    private static int findRecursive(int[] arr, int value, int low, int high) {
        if (low > high)
            return -1;
        int mid = (low + high) / 2;
        if (arr[mid] == value)
            return mid;
        if (arr[mid] < arr[high]) {
            if (arr[mid] < value && arr[high] >= value)
                return findRecursive(arr, value, mid + 1, high);
            else
                return findRecursive(arr, value, low, mid - 1);
        } else if (arr[mid] > arr[low]) {
            if (arr[mid] > value && arr[low] <= value)
                return findRecursive(arr, value, low, mid - 1);
            else
                return findRecursive(arr, value, mid + 1, high);
        } else if (arr[mid] == arr[high] || arr[mid] == arr[low]) {
            int shift = 1;
            while (midBetweenLowHigh(low, high, mid + shift)) {
                if (arr[mid] != arr[mid + shift])
                    break;
                shift = getNextShift(shift);
            }
            if (shift < 0)
                return findRecursive(arr, value, low, mid + shift);
            else
                return findRecursive(arr, value, mid + shift, high);
        }
        return -1;
    }

    private static boolean midBetweenLowHigh(int low, int high, int mid) {
        return (mid <= high && mid >= low);
    }

    private static int getNextShift(int shift) {
        int val = Math.abs(shift) + 1;
        int sign = 0 - Integer.signum(shift);
        return val * sign;
    }
}
