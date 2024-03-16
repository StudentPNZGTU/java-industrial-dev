package edu.penzgtu;

import edu.penzgtu.filters.ExponentialMovingAverage;
import edu.penzgtu.filters.MedianFilter;
import edu.penzgtu.filters.SimpleMovingAverage;
import edu.penzgtu.graphs.ChartDrawer;
import edu.penzgtu.utils.CsvReader;

import java.util.InputMismatchException;
import java.util.Properties;
import java.io.IOException;
import java.util.Scanner;

import static edu.penzgtu.utils.ConfigReader.readConfig;

public class Main {
    public static void main(String[] args) {
        double[] data;
        try {
            Properties properties = readConfig("config.properties");
            data = CsvReader.readCSV(properties.getProperty("path.csv"));
        } catch (IOException e) {throw new RuntimeException(e);}

        System.out.println("The data from the file has been read:");
        System.out.print(CsvReader.dataToString(data));

        ChartDrawer chartDrawer = new ChartDrawer(inputInteger(), data);
        chartDrawer.plotData(new MedianFilter());
        chartDrawer.plotData(new ExponentialMovingAverage());
        chartDrawer.plotData(new SimpleMovingAverage());
    }

    public static int inputInteger() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\nint: ");
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer");
                scanner.nextLine();
            }
        }
    }
}
