package edu.penzgtu.model.filters;

import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesDataItem;
import java.util.Arrays;

public class MedianFilter implements Filter {
    @Override
    public TimeSeries apply(TimeSeries timeSeries, int windowSize) {
        TimeSeries resultSeries = new TimeSeries("Median Filter");
        for (int i = 0; i < timeSeries.getItemCount() - windowSize + 1; i++) {
            double[] window = getWindowValues(timeSeries, i, windowSize);
            Arrays.sort(window);
            double median = window[windowSize / 2];
            resultSeries.addOrUpdate(new TimeSeriesDataItem(timeSeries.getTimePeriod(i + windowSize - 1), median));

        }
        return resultSeries;
    }
    private double[] getWindowValues(TimeSeries timeSeries, int startIndex, int windowSize) {
        double[] window = new double[windowSize];
        for (int i = 0; i < windowSize; i++) {
            TimeSeriesDataItem item = timeSeries.getDataItem(startIndex + i);
            window[i] = item.getValue().doubleValue();
        }
        return window;
    }

}
