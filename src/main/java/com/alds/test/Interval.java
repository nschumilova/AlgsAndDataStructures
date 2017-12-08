package com.alds.test;

import java.time.LocalDate;

public class Interval {
    private LocalDate start;
    private LocalDate end;

    public Interval(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public boolean intersects(Interval interval)
    {
        return end.isAfter(interval.start) && end.isBefore(interval.end)||
                interval.end.isAfter(start) && interval.end.isBefore(end)||
                start.isBefore(interval.start)&& interval.end.isBefore(end) ||
                interval.start.isBefore(start)&& end.isBefore(interval.end)||
                start.isEqual(interval.start) && end.isEqual(interval.start);
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Interval interval = (Interval) o;

        if (!start.equals(interval.start)) return false;
        return end.equals(interval.end);
    }

    @Override
    public int hashCode() {
        int result = start.hashCode();
        result = 31 * result + end.hashCode();
        return result;
    }
}
