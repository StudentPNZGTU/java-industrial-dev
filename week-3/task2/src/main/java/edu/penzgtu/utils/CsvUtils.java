package edu.penzgtu.utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CsvUtils {

    private final String file;

    public CsvUtils(String filename) {
        this.file = filename;
    }
    public List<String[]> readAllData() throws IOException, CsvException {
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(file)).build()) {
            return reader.readAll();
        }
    }
}