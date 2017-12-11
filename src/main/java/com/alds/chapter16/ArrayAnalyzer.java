package com.alds.chapter16;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.alds.util.Helper.checkNotNull;

/**
 * Analyzes array by given criteria
 */
public class ArrayAnalyzer {

    /**
     *<p>Task 16.24. Time for implementation and testing :<i>20 min</i></p>
     * Finds pairs of integers in array, the sum of which is equal to the given number
     * Performance :  <i>O(N)</i>
     * Memory : <i>O(N)</i> (size of list of pairs + size of set)
     *
     * @param arr - array of integers
     * @param sum - given value of sum
     * @return - list of pairs
     */
    public static List<IntPairContainer> findPairsByTheirSum(int[] arr, int sum) {
        checkNotNull(arr);

        List<IntPairContainer> list = new ArrayList<>(arr.length / 2);
        Set<Integer> set = new HashSet<>();
        for (int val : arr) {
            int pair = sum - val;
            if (set.contains(pair)) {
                list.add(new IntPairContainer(val, pair));
                set.remove(pair);
            } else
                set.add(val);
        }
        return list;
    }
}
