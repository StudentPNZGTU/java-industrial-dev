package edu.penzgtu.data;

import org.jfree.data.time.TimeSeries;

import java.io.IOException;

public interface CsvDataSaverImpl {
    void writeChartToCSV(String filename, TimeSeries timeSeries) throws IOException;
}
