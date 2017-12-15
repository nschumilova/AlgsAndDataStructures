package com.alds.chapter16;


import com.alds.util.GeometryHelper;
import javafx.geometry.Point2D;
import javafx.scene.shape.Line;

import java.util.*;

import static com.alds.util.Helper.checkNotNull;

/**
 * Analyzes array by given criteria
 */
public class ArrayAnalyzer {
    private static final int ROUNDING_SCALE = 5;

    /**
     * <p>Task 16.24. Time for implementation and testing :<i>20 min</i></p>
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

    /**
     * <p>Task 16.14. Time for implementation and testing :<i>180 min</i></p>
     * Finds a line which contains maximum number of non-duplicate points
     * Performance :  <i>O(N^2)</i> , where N is the total number of points
     * Memory :  for each iteration i=[0..N] a map of maximum size of<i>O(N-i)</i> will be created
     *
     * @param points - array of  points on 2D-plane
     * @return - a line with maximum number of points if such was found
     */
    public static Optional<Line> getLineWithMaxPointNumber(Point2D[] points) {
        checkNotNull(points);

        if (points.length < 2)
            return Optional.empty();

        LineContainer lineWithMaxPoints = null;
        for (int i = 0; i < points.length; i++) {
            Map<Double, List<LineContainer>> iterationSlopeMap = new HashMap<>();
            LineContainer iterationLine = null;
            for (int j = i + 1; j < points.length; j++) {

                Double slope = GeometryHelper.getSlope(points[i], points[j], Optional.of(ROUNDING_SCALE));
                if (!points[i].equals(points[j]) && //lines don't have same coordinates
                        (lineWithMaxPoints == null ||//no line was found previously
                                !(lineWithMaxPoints.slope.doubleValue() == slope.doubleValue() &&
                                        GeometryHelper.lineContainsPoint(lineWithMaxPoints.point1,
                                                lineWithMaxPoints.slope, points[i])))) //different slopes, or previous line contains new point
                {
                    iterationLine = getBestLine(iterationLine,
                            addLineContainer(iterationSlopeMap, points[i], points[j], i, j, slope));

                }
            }

            lineWithMaxPoints = getBestLine(lineWithMaxPoints, iterationLine);
        }
        return Optional.ofNullable(lineWithMaxPoints == null ? null : lineWithMaxPoints.getLine());
    }

    private static LineContainer getBestLine(LineContainer line1, LineContainer line2) {
        if (line1 != null && line2 != null) {
            return line1.numberOfPoints > line2.numberOfPoints ? line1 : line2;
        } else if (line1 != null)
            return line1;
        else
            return line2;
    }

    private static LineContainer addLineContainer(Map<Double, List<LineContainer>> slopeMap,
                                                  Point2D point1,
                                                  Point2D point2, int index1, int index2,
                                                  Double slope) {
        LineContainer newLine = null;
        if (!slopeMap.containsKey(slope)) {
            List<LineContainer> list = new ArrayList<>();
            newLine = new LineContainer(point1, point2, index1, index2, slope);
            list.add(newLine);
            slopeMap.put(slope, list);
        } else {
            for (LineContainer container : slopeMap.get(slope))
                if (GeometryHelper.lineContainsPoint(container.point1, container.slope, point1)) {
                    container.incrementPoints(index1, index2);
                    newLine = container;
                    break;
                }
        }
        return newLine;
    }

    private static class LineContainer {
        Point2D point1;
        Point2D point2;
        int numberOfPoints;
        Double slope;
        Set<Integer> indexes = new HashSet<>();

        LineContainer(Point2D point1, Point2D point2, int index1, int index2, Double slope) {
            this.point1 = point1;
            this.point2 = point2;
            this.slope = slope;
            this.numberOfPoints = 2;
            indexes.add(index1);
            indexes.add(index2);
        }

        void incrementPoints(int index1, int index2) {
            numberOfPoints++;
            indexes.add(index1);
            indexes.add(index2);
        }

         Line getLine() {
            return new Line(point1.getX(), point1.getY(), point2.getX(), point2.getY());
        }
    }
}
