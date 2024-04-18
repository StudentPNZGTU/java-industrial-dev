package edu.penzgtu.model.filters;

import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesDataItem;

public class SimpleMovingAverage implements Filter {
    @Override
    public TimeSeries apply(TimeSeries timeSeries, int windowSize) {
        TimeSeries resultSeries = new TimeSeries("Moving Average");
        for (int i = 0; i < timeSeries.getItemCount() - windowSize + 1; i++) {
            double sum = 0;
            for (int j = i; j < i + windowSize; j++) {
                TimeSeriesDataItem item = timeSeries.getDataItem(j);
                sum += item.getValue().doubleValue();
            }
            double sma = sum / windowSize;
            resultSeries.addOrUpdate(new TimeSeriesDataItem(timeSeries.getTimePeriod(i + windowSize - 1), sma));
        }
        return resultSeries;
    }
}
