package edu.penzgtu.filters;

public class SimpleMovingAverage implements Filter {
    @Override
    public double[] apply(double[] data, int windowSize) {
        double[] sma = new double[data.length - windowSize + 1];
        for (int i = 0; i < sma.length; i++) {
            double sum = 0;
            for (int j = i; j < i + windowSize; j++) {
                sum += data[j];
            }
            sma[i] = sum / windowSize;
        }
        return sma;
    }
}
