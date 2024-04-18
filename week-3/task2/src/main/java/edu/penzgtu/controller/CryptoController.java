package edu.penzgtu.controller;

import com.opencsv.exceptions.CsvException;
import edu.penzgtu.data.*;
import edu.penzgtu.errors.DataLoadException;
import edu.penzgtu.model.api.CryptoCompare;
import edu.penzgtu.model.api.CryptoResponse;
import edu.penzgtu.utils.CsvUtils;
import edu.penzgtu.view.ConsolePrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

public class CryptoController {
    private static final Logger LOGGER = LogManager.getLogger(CsvUtils.class);

    public void loadDataFromFile(String filename, ChartManager chartManager) {
        try  {
            new CsvDataFetcher().loadDataFromCSV(filename, chartManager.getApiChart());
            LOGGER.info("Successfully read data from file: {}", filename);
        } catch (IOException e) {
            LOGGER.error("Error reading data from file: {}", filename);
            throw new DataLoadException(e);
        } catch (CsvException e) {
            LOGGER.error("Data error in the file: {}", filename);
            throw new DataLoadException(e);
        } catch (NullPointerException e) {
            LOGGER.error("Error reading the configuration. The Path is: {}", filename);
            throw new DataLoadException(e);
        }

        chartManager.fetchDataFilterFromChartAPI();
    }

    public void loadDataFromInternet(ChartManager chartManager, CryptoCompare cryptoCompare) {

        boolean isCurrencyPair = false;
        while (!isCurrencyPair) {
            isCurrencyPair = true;
            if (!cryptoCompare.validateCurrency(cryptoCompare.getCurrency1())) {
                cryptoCompare.setCurrency1(pairNamingError(cryptoCompare.getCurrency1()));
                isCurrencyPair = false;
            }
            if (!cryptoCompare.validateCurrency(cryptoCompare.getCurrency2())) {
                cryptoCompare.setCurrency2(pairNamingError(cryptoCompare.getCurrency2()));
                isCurrencyPair = false;
            }
        }
        LOGGER.debug(ConsolePrinter.resultInputUserChoice(cryptoCompare));

        SwingUtilities.invokeLater(() -> {
            Thread thread = new Thread(() -> {
                try {
                    while (true) {
                        RequestDataFetcher requestDataFetcher = new RequestDataFetcher();
                        requestDataFetcher.fetchApiJsonString(cryptoCompare.buildRequestUrl());
                        for (CryptoResponse timeCurr: requestDataFetcher.parseJsonToResponseList()) {
                            chartManager.fetchDataFromRequest(timeCurr);
                            chartManager.fetchDataFilterFromChartAPI();
                            LOGGER.debug(new StringBuilder()
                                    .append("Fetched data from API json string:\n")
                                    .append("time=").append(timeCurr.getTime())
                                    .append("\tprice=").append(timeCurr.getOpen())
                            );
                        }
                        // We receive two request bodies per minute
                        Thread.sleep(120000);
                    }
                } catch (InterruptedException e) {
                    LOGGER.error("Thread interrupted: {}", e.getMessage());
                }
            });
            thread.start();
        });
    }

    public String pairNamingError(String currency) {
        LOGGER.error("invalid parameter: {}", currency);
        return scanValue("Currency:", String.class);
    }

    public int userChoice() {
        ConsolePrinter.printUserChoice();
        int choice = scanValue("", Integer.class);

        if (choice == 1 || choice == 2) {
            return choice;
        }
        LOGGER.info("Please enter the correct number");
        return userChoice();
    }

    public <T> T scanValue(String prompt, Class<T> type) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);

        while (true) {
            try {
                if (type == Integer.class) {
                    return type.cast(scanner.nextInt());
                } else if (type == String.class) {
                    return type.cast(scanner.nextLine());
                } else {throw new IllegalArgumentException("Unsupported type");}
            } catch (Exception e) {
                LOGGER.info("Invalid input. Please enter a valid {}", type.getSimpleName());
                scanner.nextLine();
            }
        }
    }
}
