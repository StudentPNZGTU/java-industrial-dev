package edu.penzgtu.filters;

public class ExponentialMovingAverage implements Filter {
    @Override
    public double[] apply(double[] data, int windowSize) {
        double[] ema = new double[data.length];
        double alpha = 2.0 / (windowSize + 1);
        ema[0] = data[0];
        for (int i = 1; i < data.length; i++) {
            ema[i] = alpha * data[i] + (1 - alpha) * ema[i - 1];
        }
        return ema;
    }
}
