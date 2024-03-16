package edu.penzgtu.filters;

public interface Filter {
    double[] apply(double[] data, int windowSize);
}
