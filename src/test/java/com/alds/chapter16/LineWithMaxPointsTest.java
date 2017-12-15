package com.alds.chapter16;


import com.alds.util.GeometryHelper;
import javafx.geometry.Point2D;
import javafx.scene.shape.Line;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class LineWithMaxPointsTest {

    @Test(expected = IllegalArgumentException.class)
    public void testGetLineWhenInputNull() {
        ArrayAnalyzer.getLineWithMaxPointNumber(null);
    }

    @Test
    public void testGetLineWhenInputEmpty() {
        assertFalse(ArrayAnalyzer.getLineWithMaxPointNumber(new Point2D[0]).isPresent());
    }

    @Test
    public void testGetLineWhenInputContainsOnePoint() {
        assertFalse(ArrayAnalyzer.getLineWithMaxPointNumber(new Point2D[]{new Point2D(0, 0)}).isPresent());
    }

    @Test
    public void testGetLineWhenInputContainsTwoPoints() {
        List<Point2D> points = Arrays.asList(new Point2D(0, 0), new Point2D(1, 1));
        Optional<Line> line = ArrayAnalyzer.getLineWithMaxPointNumber(new Point2D[]{points.get(0), points.get(1)});
        assertTrue(lineContainsPoints(line.get(), points));
    }

    @Test
    public void testGetLineWhenInputContainsSeveralPointsOnOneLine() {
        List<Point2D> points1 = Arrays.asList(new Point2D(0, 0),
                new Point2D(1, 1),
                new Point2D(2,2),
                new Point2D(3,3));

        List<Point2D> points2 = Arrays.asList(new Point2D(31, 40),
                new Point2D(-1, 2),
                new Point2D(3,7),
                new Point2D(100,101));
        List<Point2D> input = new ArrayList<>(points2);
        input.addAll(points1);
        Collections.shuffle(input);

        Optional<Line> line = ArrayAnalyzer.getLineWithMaxPointNumber(input.toArray(new Point2D[input.size()]));
        assertTrue(lineContainsPoints(line.get(), points1));
    }

    @Test
    public void testGetLineWhenInputContainsParallelLines() {
        List<Point2D> points1 = Arrays.asList(new Point2D(0, 0),
                new Point2D(1, 1),
                new Point2D(2,2),
                new Point2D(3,3));

        List<Point2D> points2 = Arrays.asList(new Point2D(0, 1),
                new Point2D(1, 2),
                new Point2D(2,3),
                new Point2D(4,0));
        List<Point2D> input = new ArrayList<>(points2);
        input.addAll(points1);
        Collections.shuffle(input);

        Optional<Line> line = ArrayAnalyzer.getLineWithMaxPointNumber(input.toArray(new Point2D[input.size()]));
        assertTrue(lineContainsPoints(line.get(), points1));
    }

    @Test
    public void testGetLineWhenInputContainsVerticalLine() {
        List<Point2D> points1 = Arrays.asList(new Point2D(0, 0),
                new Point2D(0, 1),
                new Point2D(0,2),
                new Point2D(0,3));

        List<Point2D> points2 = Arrays.asList(new Point2D(1, 1),
                new Point2D(2, 3),
                new Point2D(3,7),
                new Point2D(100,123));
        List<Point2D> input = new ArrayList<>(points2);
        input.addAll(points1);
        Collections.shuffle(input);

        Optional<Line> line = ArrayAnalyzer.getLineWithMaxPointNumber(input.toArray(new Point2D[input.size()]));
        assertTrue(lineContainsPoints(line.get(), points1));
    }

    @Test
    public void testGetLineWhenInputContainsHorizontalLine() {
        List<Point2D> points1 = Arrays.asList(new Point2D(0, 0),
                new Point2D(1, 0),
                new Point2D(2,0),
                new Point2D(3,0));

        List<Point2D> points2 = Arrays.asList(new Point2D(1, 1),
                new Point2D(2, 3),
                new Point2D(3,7),
                new Point2D(100,123));
        List<Point2D> input = new ArrayList<>(points2);
        input.addAll(points1);
        Collections.shuffle(input);

        Optional<Line> line = ArrayAnalyzer.getLineWithMaxPointNumber(input.toArray(new Point2D[input.size()]));
        assertTrue(lineContainsPoints(line.get(), points1));
    }

    @Test
    public void testGetLineWhenInputContainsOnlyDuplicates() {
        assertFalse(ArrayAnalyzer.getLineWithMaxPointNumber(
                new Point2D[]{new Point2D(1,1),new Point2D(1,1),new Point2D(1,1)}).isPresent());
    }

    @Test
    public void testGetLineWhenInputContainsSomeDuplicates() {
        List<Point2D> points1 = Arrays.asList(new Point2D(0, 0),
                new Point2D(0, 1),
                new Point2D(0,2),
                new Point2D(0,3));

        List<Point2D> points2 = Arrays.asList(new Point2D(0, 0),
                new Point2D(0, 0),
                new Point2D(3,7),
                new Point2D(100,123));
        List<Point2D> input = new ArrayList<>(points2);
        input.addAll(points1);
        Collections.shuffle(input);

        Optional<Line> line = ArrayAnalyzer.getLineWithMaxPointNumber(input.toArray(new Point2D[input.size()]));
        assertTrue(lineContainsPoints(line.get(), points1));
    }

    private boolean lineContainsPoints(Line line, List<Point2D> points) {
        for (Point2D point : points) {
            if (!GeometryHelper.lineContainsPoint(line, point))
                return false;
        }
        return true;
    }

}
