package com.alds.chapter17;

import org.junit.Test;

import static org.junit.Assert.*;

public class HistogramVolumeCalculatorTest {

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateVolumeWhenInputNull() {
        HistogramVolumeCalculator.calculateVolumeBetweenHistograms(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateVolumeWhenHistHeightIsNegative() {
        HistogramVolumeCalculator.calculateVolumeBetweenHistograms(new int[]{0, -11, 2, 0});
    }

    @Test
    public void testCalculateVolumeWhenInputEmpty() {
        assertEquals(0, HistogramVolumeCalculator.
                calculateVolumeBetweenHistograms(new int[0]));
    }

    @Test
    public void testCalculateVolumeWhenInputHas0Histograms() {
        assertEquals(0, HistogramVolumeCalculator.
                calculateVolumeBetweenHistograms(new int[]{0, 0, 0, 0}));
    }

    @Test
    public void testCalculateVolumeWhenInputHas1Histogram() {
        assertEquals(0, HistogramVolumeCalculator.
                calculateVolumeBetweenHistograms(new int[]{0, 2, 0, 0}));
    }

    @Test
    public void testCalculateVolumeWhenInputHas2Histogram() {
        assertEquals(4, HistogramVolumeCalculator.
                calculateVolumeBetweenHistograms(new int[]{0, 3, 0, 0, 2, 0}));
    }

    @Test
    public void testCalculateVolumeWhenInputHasOneHollowHistogram() {
        assertEquals(26, HistogramVolumeCalculator.
                calculateVolumeBetweenHistograms(
                        new int[]{0, 0, 4, 0, 0, 6, 0, 0, 3, 0, 5, 0, 1, 0, 0, 0}));
    }
    @Test
    public void testCalculateVolumeWhenInputHasThreeHollowHistograms() {
        assertEquals(31, HistogramVolumeCalculator.
                calculateVolumeBetweenHistograms(
                        new int[]{3, 0, 5, 0, 2, 0, 4, 0, 1, 0, 5}));
    }

    @Test
    public void testCalculateVolumeWhenHistogramsHaveEqualHeight() {
        assertEquals(4, HistogramVolumeCalculator.
                calculateVolumeBetweenHistograms(
                        new int[]{0, 2, 0, 2, 2, 0, 2}));
    }
}
