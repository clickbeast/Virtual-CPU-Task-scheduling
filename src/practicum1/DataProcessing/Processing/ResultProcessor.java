package practicum1.DataProcessing.Processing;

/*
Processes a completed list of test processes and generates the desired results to display.

 */

import practicum1.DataProcessing.Containers.GraphData;
import practicum1.DataProcessing.Containers.ProcessList;
import practicum1.DataProcessing.Containers.SimulationResult;

public class ResultProcessor {


    public ResultProcessor() {

    }


    public static SimulationResult generateSimulationResult(ProcessList processList, String algorithmName) {

        //Calculate de gemiddlede tat en turn around time


        //Generate the graph data

        //TODO generate graphdata, op basis van process list

        GraphData bedieningstijd = new GraphData(algorithmName);
        GraphData turnAroundTime = new GraphData(algorithmName);

        SimulationResult simulationResult = new SimulationResult(algorithmName,bedieningstijd,turnAroundTime,processList.getMeanTurnAroundTime(),processList.getMeanWaitTime());

        return simulationResult;
    }

}
