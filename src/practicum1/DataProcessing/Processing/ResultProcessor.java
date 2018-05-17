package practicum1.DataProcessing.Processing;

/*
Processes a completed list of test processes and generates the desired results to display.

 */

import practicum1.DataProcessing.Containers.GraphData;
import practicum1.DataProcessing.Containers.ProcessInfo;
import practicum1.DataProcessing.Containers.ProcessList;
import practicum1.DataProcessing.Containers.SimulationResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ResultProcessor {


    public static SimulationResult generateSimulationResult(ProcessList processList, String algorithmName) {

        //Calculate de gemiddlede tat en turn around time
        System.out.println("Generating simulationresult");

        //processlist sorteren volgens servicetime
        processList.sort(Comparator.comparingInt(ProcessInfo::getServiceTime));

        int partition = processList.size()/100;
        int count = 0;


        //graph outputs
        GraphData waitTimeGraph = new GraphData(algorithmName);
        GraphData turnAroundTimeGraph = new GraphData(algorithmName);

        List<Double> tempWaitTimeValues = new ArrayList<>();
        List<Double> tempTurnAroundValues = new ArrayList<>();

        for(ProcessInfo processInfo: processList) {

            tempWaitTimeValues.add((double) processInfo.getWaitTime());
            tempTurnAroundValues.add(((double) processInfo.getTurnAroundTime())/(double) processInfo.getServiceTime());
            count++;

            //als we aan volgende percentile zitten toevoegen die handel en uitrkenengemiddelnde
            if(count == partition) {
                waitTimeGraph.add(mean(tempWaitTimeValues));
                turnAroundTimeGraph.add(mean(tempTurnAroundValues));

                tempTurnAroundValues.clear();
                tempWaitTimeValues.clear();

                count = 0;
            }
        }
/*
for testing purposes
        System.out.println("SOLUTOION");
        System.out.println(Arrays.toString(tempTurnAroundValues.toArray()));
        System.out.println(Arrays.toString(tempWaitTimeValues.toArray()));
*/

        return new SimulationResult(algorithmName,waitTimeGraph,turnAroundTimeGraph, null,null);

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
