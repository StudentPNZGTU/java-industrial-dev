package edu.penzgtu.filters;

import java.util.Arrays;

public class MedianFilter implements Filter {
    @Override
    public double[] apply(double[] data, int windowSize) {
        double[] median = new double[data.length - windowSize + 1];
        for (int i = 0; i < median.length; i++) {
            double[] window = Arrays.copyOfRange(data, i, i + windowSize);
            Arrays.sort(window);
            median[i] = window[windowSize / 2];
        }
        return median;
    }
}
