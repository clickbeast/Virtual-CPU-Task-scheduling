package practicum1.DataProcessing.Containers;


import sun.awt.image.ImageWatched;

import java.util.LinkedList;

/*
    Holds all necessary info of an algorithm simulation; including all processes with TAT and wait time
Gemiddelde TAT , en wait time, and usefull graph data, THIS is the object that gets passed to the UI  of our app
   for each run simulation of an algorithm, after that the UI will make an appropiate graph.
 */
public class SimulationResult {

    private GraphData grafiekDataBedieningsTijd;
    private GraphData grafiekDataOmploopTijd;
    private LinkedList<Double> offsets;
    //TODO
    private Double totalAverageTAT;
    private Double totalAverageWaitTime;

    //name of the used algorithm
    private String title;

    public SimulationResult(String title, GraphData grafiekDataBedieningsTijd, GraphData grafiekDataOmploopTijd, LinkedList<Double> offsets, Double totalAverageTAT, Double totalAverageWaitTime) {
        this.grafiekDataBedieningsTijd = grafiekDataBedieningsTijd;
        this.grafiekDataOmploopTijd = grafiekDataOmploopTijd;
        this.offsets = offsets;
        this.totalAverageTAT = totalAverageTAT;
        this.totalAverageWaitTime = totalAverageWaitTime;
        this.title = title;
    }

    public GraphData getGrafiekDataBedieningsTijd() {
        return grafiekDataBedieningsTijd;
    }

    public GraphData getGrafiekDataOmploopTijd() {
        return grafiekDataOmploopTijd;
    }

    public Double getTotalAverageTAT() {
        return totalAverageTAT;
    }

    public Double getTotalAverageWaitTime() {
        return totalAverageWaitTime;
    }

    public String getUsedAlgorithmName() {
        return title;
    }
}
