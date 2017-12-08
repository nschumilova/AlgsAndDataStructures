package com.alds.searchsort;

import java.util.*;

import static com.alds.util.Helper.checkNotNull;

/**
 * Class that contains implementations of search algorithms
 */
public class SearchProcessor {

    /**
     * <p>Task 10.3. Time for implementation and testing :<i>60 min</i></p>
     * Searches index of an element in sorted(asc) array which is shifted  by unknown number
     * Performance: from <i>O(log N)</i> to <i>O(N)</i>
     * Memory: depth of recursion from <i>O(log N)</i> to <i>O(N)</i>
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

    /**
     * <p>Task 10.2. Time for implementation and testing :<i>60 min</i></p>
     * Reorders elements in the array so that anagrams are grouped together.
     * Performance: from <i>O(N * K log K)</i> where N - is the size of the input array
     * and K is the number of letters in a string
     * Memory: because of additional map <i>O(2N)</i>
     * (2 - because when array has no anagrams => for each element
     * will be created two items in the map: key and value)
     *
     * @param array - array which can contain anagrams
     */
    public static void groupByAnagrams(String[] array) {
        checkNotNull(array);
        Map<String, List<String>> map = new HashMap<>();

        for (String string : array) {
            char[] chars = string.toCharArray();
            Arrays.sort(chars);
            String sorted = String.valueOf(chars);
            if (!map.containsKey(sorted))
                map.put(sorted, new LinkedList<>());
            map.get(sorted).add(string);
        }
        int counter = 0;
        for (String sorted : map.keySet()) {
            for (String initialString : map.get(sorted)) {
                array[counter] = initialString;
                counter++;
            }
        }

    }
}
