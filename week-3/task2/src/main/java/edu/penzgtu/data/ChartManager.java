package edu.penzgtu.data;

import edu.penzgtu.model.api.CryptoResponse;
import edu.penzgtu.model.filters.Filter;
import lombok.Getter;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;

import java.util.Date;

public class ChartManager {
    @Getter
    private final TimeSeries apiChart;
    @Getter
    private final TimeSeries filteredChart;
    private final Filter filter;

    private final int windowSize;

    public ChartManager(Filter filterAlgorithm, int windowSize) {
        this.filter = filterAlgorithm;
        this.windowSize = windowSize;
        this.apiChart = new TimeSeries("Cryptocurrency");
        this.filteredChart = new TimeSeries(getNameFilteredChart());
    }

    public void fetchDataFromRequest(CryptoResponse timeCurr) {
        this.apiChart.add(new Second(new Date(timeCurr.getTime() * 1000)), timeCurr.getOpen());
    }

    public void fetchDataFilterFromChartAPI() {
        this.filteredChart.clear();
        this.filteredChart.addAndOrUpdate(this.filter.apply(apiChart, this.windowSize));
    }

    public String getNameFilteredChart() {
        return this.filter.getClass().getSimpleName();
    }
}
