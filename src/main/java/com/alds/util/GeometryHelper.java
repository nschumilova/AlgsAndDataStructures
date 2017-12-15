package com.alds.util;

import javafx.geometry.Point2D;
import javafx.scene.shape.Line;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class GeometryHelper {

    public static Double getSlope(Point2D point1, Point2D point2, Optional<Integer> roundScale) {
        Double slope = (point2.getY() - point1.getY()) / (point2.getX() - point1.getX());
        if (!(slope.isInfinite() || slope.isNaN()) && roundScale.isPresent())
            return new BigDecimal(slope).setScale(roundScale.get(), RoundingMode.HALF_UP).doubleValue();
        else return slope;
    }

    public static boolean lineContainsPoint(Point2D linePoint, Double lineSlope, Point2D point) {
        if (lineSlope.isNaN() || lineSlope.isInfinite())
            return linePoint.getX() == point.getX();
        return (point.getY() - linePoint.getY()) == (lineSlope * (point.getX() - linePoint.getX()));
    }

    public static boolean lineContainsPoint(Line line, Point2D point) {
        Point2D point1 = new Point2D(line.getStartX(), line.getStartY());
        Point2D point2 = new Point2D(line.getEndX(), line.getEndY());
        Double slope = getSlope(point1, point2, Optional.empty());
        return lineContainsPoint(point1, slope, point);
    }
}
