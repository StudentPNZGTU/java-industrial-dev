package edu.penzgtu;

import edu.penzgtu.controller.CryptoController;
import edu.penzgtu.data.ChartManager;
import edu.penzgtu.errors.DataLoadException;
import edu.penzgtu.model.api.CryptoCompare;
import edu.penzgtu.model.filters.ExponentialMovingAverage;
import edu.penzgtu.model.filters.MedianFilter;
import edu.penzgtu.model.filters.SimpleMovingAverage;
import edu.penzgtu.utils.ResourceReader;
import edu.penzgtu.view.ChartDrawer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        CryptoController cryptoController = new CryptoController();

        int userInput = cryptoController.userChoice();
        ArrayList<ChartManager> chartManagers = new ArrayList<>();

        chartManagers.add(new ChartManager(new ExponentialMovingAverage(), 7));
        chartManagers.add(new ChartManager(new MedianFilter(), 10));
        chartManagers.add(new ChartManager(new SimpleMovingAverage(), 7));

        if (userInput == 1) {
            Properties properties;
            try {
                properties = ResourceReader.readConfig("config.properties");
            } catch (IOException e) {
                LOGGER.error("Error reading data from file: config.properties");
                throw new DataLoadException(e);
            }
            String file = properties.getProperty("path.csv");
            for (ChartManager chartManager : chartManagers) {
                cryptoController.loadDataFromFile(file, chartManager);
            }
        } else {
            CryptoCompare cryptoCompare = new CryptoCompare(
                    cryptoController.scanValue("currency 1:", String.class),
                    cryptoController.scanValue("currency 2:", String.class)
            );
            for (ChartManager chartManager : chartManagers) {
                cryptoController.loadDataFromInternet(chartManager, cryptoCompare);
            }
        }
        ArrayList<ChartDrawer> chartDrawers = new ArrayList<>();

        for (int i = 0; i < chartManagers.size(); i++) {
            ChartManager chartManager = chartManagers.get(i);
            chartDrawers.add(new ChartDrawer(chartManager.getNameFilteredChart(), chartManager));
            chartDrawers.get(i).pack();
            chartDrawers.get(i).setVisible(true);
        }
    }
}
