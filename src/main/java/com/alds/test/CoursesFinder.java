package com.alds.test;

import java.util.*;

import static com.alds.util.Helper.checkNotNull;

public class CoursesFinder {

    /**
     * Finds max number of non overlapping intervals
     *
     * <p>Performance: sorting O(log N)+ nested loop(O(N^2)), but since
     *  theree is a hashset of visited intervals some branches will not be checked
     *  (for example, if 1->2->3 was visited, then 2->3 will not be visited). Hence,
     *  the nested loop will equal to (O(K), where K is the length of the list with non overlapping intervals</p>
     * @param intervals  input array of intervals
     * @return list which contains max number of non overlapping intervals
     */
    public List<Interval> getMaxNonOverlappingIntervals(Interval[] intervals) {
        checkNotNull(intervals);

        Arrays.sort(intervals, (Comparator<Interval>) (o1, o2) -> o1.getStart().compareTo(o2.getStart()));

        Deque<Interval> maxStack = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        for (int i = 0; i < intervals.length; i++) {

            if (!visited.contains(i)) {
                Deque<Interval> stack = new LinkedList<>();
                stack.push(intervals[i]);
                visited.add(i);
                for (int j = i + 1; j < intervals.length; j++) {
                    if (stack.peek().intersects(intervals[j]))
                        continue;
                    else {
                        stack.push(intervals[j]);
                        visited.add(i);
                    }
                }
                if (maxStack.size() < stack.size())
                    maxStack = stack;
            }
        }
        return (LinkedList<Interval>) maxStack;
    }

}
