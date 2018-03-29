package practicum1.DataProcessing.Processing;

/*
Processes a completed list of test processes and generates the desired results to display.

 */

import javafx.scene.layout.Priority;
import practicum1.DataProcessing.Containers.GraphData;
import practicum1.DataProcessing.Containers.ProcessInfo;
import practicum1.DataProcessing.Containers.ProcessList;
import practicum1.DataProcessing.Containers.SimulationResult;

import java.util.*;

public class ResultProcessor {


    public static SimulationResult generateSimulationResult(ProcessList processList, String algorithmName) {

        //Calculate de gemiddlede tat en turn around time
        System.out.println("Generating simulationresult");


        //sort processlist on service time
        Collections.sort(processList , Comparator.comparingInt(ProcessInfo::getServiceTime));

        int partition = processList.size()/100;
        int count = 0;


        //graph outputs
        GraphData waitTimeGraph = new GraphData(algorithmName);
        GraphData turnAroundTimeGraph = new GraphData(algorithmName);
        LinkedList<Double> offsets = new LinkedList<>();

        List<Double> tempWaitTimeValues = new ArrayList<>();
        List<Double> tempTurnAroundValues = new ArrayList<>();

        double start = -1, mean = 0;

        for(ProcessInfo processInfo: processList) {

            if(start == -1) start = processInfo.getServiceTime();

            mean += processInfo.getServiceTime();
            tempWaitTimeValues.add((double) processInfo.getWaitTime());
            tempTurnAroundValues.add(((double) processInfo.getTurnAroundTime())/(double) processInfo.getServiceTime());
            count++;

            //als we aan volgende percentile zitten toevoegen die handel en uitrkenengemiddelnde
            if(count == partition) {

                mean = mean/count;
                mean-=start;

                offsets.add(mean);
                waitTimeGraph.add(mean(tempWaitTimeValues));
                turnAroundTimeGraph.add(mean(tempTurnAroundValues));

                tempTurnAroundValues.clear();
                tempWaitTimeValues.clear();

                count = 0;
                start = -1;
            }
        }

        System.out.println("SOLUTOION");
        System.out.println(Arrays.toString(tempTurnAroundValues.toArray()));
        System.out.println(Arrays.toString(tempWaitTimeValues.toArray()));


        return new SimulationResult(algorithmName,waitTimeGraph,turnAroundTimeGraph, offsets,null,null);

    }

    private static Double mean(List<Double> temp) {
        Double sum = 0.0;
        if(!temp.isEmpty()) {
            for (Double mark : temp) {
                sum += mark;
            }
            return sum / temp.size();
        }
        return sum;
    }


}
