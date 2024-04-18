package edu.penzgtu.data;

import com.opencsv.exceptions.CsvException;
import edu.penzgtu.utils.CsvUtils;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class CsvDataFetcher implements CsvDataFetcherImpl {
    @Override
    public void loadDataFromCSV(String filename, TimeSeries timeSeries)
        throws IOException, CsvException, NullPointerException {
        List<String[]> dataCSV = new CsvUtils(filename).readAllData();
        for (int i = 1; i < dataCSV.size(); i++) {
            String[] row = dataCSV.get(i);
            Second second = new Second(new Date(Long.parseLong(row[0])*1000));
            timeSeries.add(second, Double.parseDouble(row[1]));
        }
    }
}
