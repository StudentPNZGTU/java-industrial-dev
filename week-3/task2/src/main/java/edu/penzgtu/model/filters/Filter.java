package edu.penzgtu.model.filters;

import org.jfree.data.time.TimeSeries;

public interface Filter {
    TimeSeries apply(TimeSeries timeSeries, int windowSize);
}
