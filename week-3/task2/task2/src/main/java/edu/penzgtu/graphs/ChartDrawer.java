package edu.penzgtu.graphs;

import edu.penzgtu.filters.Filter;
import lombok.AllArgsConstructor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.DefaultXYDataset;
import javax.swing.*;

@AllArgsConstructor
public class ChartDrawer {

    private int windowSize;

    private double[] originalData;

    public void plotData(Filter filter) {
        double[] filteredData = filter.apply(originalData, windowSize);
        DefaultXYDataset dataset = new DefaultXYDataset();
        String header = filter.getClass().getName();
        double[][] series = new double[2][originalData.length];
        for (int i = 0; i < originalData.length; i++) {
            series[0][i] = i;
            series[1][i] = originalData[i];
        }
        dataset.addSeries("Data", series);

        series = new double[2][filteredData.length];
        for (int i = 0; i < filteredData.length; i++) {
            series[0][i] = i;
            series[1][i] = filteredData[i];
        }
        dataset.addSeries(header, series);
        JFreeChart chart = ChartFactory.createXYLineChart(
            header,
            "Index",
            "Value",
            dataset
        );
        JFrame frame = new JFrame(header);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ChartPanel(chart));
        frame.pack();
        frame.setVisible(true);
    }
}
