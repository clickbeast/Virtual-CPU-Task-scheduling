package practicum1.UI;

import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import practicum1.DataProcessing.Containers.SimulationResult;

import java.util.List;


/**
 * Class that represents a javaFX line Chart
 */
public class ResultLineChart extends LineChart <String, Number> {


    public ResultLineChart(Axis<String> stringAxis, Axis<Number> numberAxis) {
        super(stringAxis, numberAxis);

        this.configureBasics("bedieningstijd");

    }

    public void configureBasics(String title) {
        this.setTitle(title);
    }


    /**
     * Takes in all results from a simulation and uses the supplied graph data to add an element to this graph.
     * @param simulationResults
     */
    public void addAllResults(List<SimulationResult> simulationResults) {
        for(SimulationResult simulationResult: simulationResults) {
            this.addResult(simulationResult);
        }
    }

    public void addResult(SimulationResult simulationResult) {
        //Add new series
    }

    public void addNewSeries(String title, List<Integer> percentiles) {
        //TODO create series AND ADD
    }


}
