package edu.penzgtu.data;

import com.opencsv.CSVWriter;
import org.jfree.data.time.TimeSeries;

import java.io.FileWriter;
import java.io.IOException;

public class CsvDataSaver implements CsvDataSaverImpl {
    @Override
    public void writeChartToCSV(String filename, TimeSeries timeSeries) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filename))) {
            String[] header = {"Date", "Price"};
            writer.writeNext(header);

            for (int i = 0; i < timeSeries.getItemCount(); i++) {
                long unixTime = timeSeries.getDataItem(i).getPeriod().getLastMillisecond() / 1000L;
                String[] line = {String.valueOf(unixTime), String.valueOf(timeSeries.getValue(i))};
                writer.writeNext(line);
            }
        }
    }
}
