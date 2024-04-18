package edu.penzgtu.view;

import edu.penzgtu.data.ChartManager;
import edu.penzgtu.data.CsvDataSaver;
import edu.penzgtu.errors.DataLoadException;
import edu.penzgtu.utils.ResourceReader;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.TimeSeriesCollection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChartDrawer extends JFrame {

    private static final Logger LOGGER = LogManager.getLogger(ChartDrawer.class);

    public ChartDrawer(String header, ChartManager manager) {
        super(header);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        TimeSeriesCollection dataset = new TimeSeriesCollection(manager.getApiChart());
        dataset.addSeries(manager.getFilteredChart());

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                manager.getNameFilteredChart(),
                "UnixTime",
                "Price",
                dataset
        );
        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(800, 600));
        setContentPane(panel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                String file = "";
                try {
                    file = ResourceReader.readConfig("config.properties").getProperty("path.csv");
                    new CsvDataSaver().writeChartToCSV(file, manager.getApiChart());
                    LOGGER.info("Saved chart to {}", file);
                } catch (IOException ex) {
                    LOGGER.error("Error reading data from file: {}", file);
                    throw new DataLoadException(ex);
                } catch (NullPointerException ex) {
                    LOGGER.error("Error reading the configuration. The Path is: {}", file);
                    throw new DataLoadException(ex);
                }
            }
        });
    }
}
