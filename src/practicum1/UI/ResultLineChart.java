package practicum1.UI;

import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import practicum1.DataProcessing.Containers.GraphData;
import practicum1.DataProcessing.Containers.SimulationResult;

import java.util.Arrays;
import java.util.List;


/**
 * Class that represents a javaFX line Chart
 */
public class ResultLineChart extends LineChart <String, Number> {


    public ResultLineChart(Axis<String> stringAxis, Axis<Number> numberAxis, String title) {
        super(stringAxis, numberAxis);
        this.configureBasics(title);
    }

    public void configureBasics(String title) {
        this.setTitle(title);
    }

    public void addSeries(GraphData grafiekDataBedieningsTijd, String usedAlgorithmName) {

        System.out.println(Arrays.toString(grafiekDataBedieningsTijd.toArray()));

        XYChart.Series series = new XYChart.Series<>();
        series.setName(usedAlgorithmName);


        int percentileCount  = 0;
        for(Double number: grafiekDataBedieningsTijd) {
            series.getData().add(new XYChart.Data(Integer.toString(percentileCount), number));
            percentileCount++;
        }

        this.getData().add(series);
    }

}
