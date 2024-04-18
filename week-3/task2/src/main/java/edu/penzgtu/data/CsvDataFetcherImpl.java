package edu.penzgtu.data;

import com.opencsv.exceptions.CsvException;
import org.jfree.data.time.TimeSeries;

import java.io.IOException;

public interface CsvDataFetcherImpl {
    void loadDataFromCSV(String filename, TimeSeries timeSeries) throws IOException, CsvException;
}
