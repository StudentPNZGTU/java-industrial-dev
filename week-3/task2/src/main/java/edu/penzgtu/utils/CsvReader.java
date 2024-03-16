package edu.penzgtu.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    public static double[] readCSV(String fileName) throws IOException {
        List<Double> inputData = new ArrayList<>();
        InputStream inputStream = CsvReader.class.getResourceAsStream(fileName);
        String string;
        if (inputStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((string = reader.readLine()) != null) {
                double value = Double.parseDouble(string.trim());
                inputData.add(value);
            }
        } else {throw new IOException();}
        double[] dataArray = new double[inputData.size()];
        for (int i = 0; i < inputData.size(); i++) {dataArray[i] = inputData.get(i);}
        return dataArray;
    }
    public static String dataToString(double[] array) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
