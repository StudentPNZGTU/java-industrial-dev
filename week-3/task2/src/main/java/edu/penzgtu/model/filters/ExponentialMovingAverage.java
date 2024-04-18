package edu.penzgtu.model.filters;

import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesDataItem;

public class ExponentialMovingAverage implements Filter {
    @Override
    public TimeSeries apply(TimeSeries timeSeries, int windowSize) {
        TimeSeries emaSeries = new TimeSeries("EMA");
        double alpha = 2.0 / (windowSize + 1);
        double ema = 0;
        for (int i = 0; i < timeSeries.getItemCount()-1; i++) {
            TimeSeriesDataItem item = timeSeries.getDataItem(i);
            double value = item.getValue().doubleValue();
            if (i == 0) {
                ema = value;
            } else {
                ema = alpha * value + (1 - alpha) * ema;
            }
            emaSeries.add(item.getPeriod(), ema);
        }
        return emaSeries;
    }
}
