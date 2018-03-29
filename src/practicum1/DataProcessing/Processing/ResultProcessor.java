package practicum1.DataProcessing.Processing;

/*
Processes a completed list of test processes and generates the desired results to display.

 */

import javafx.scene.layout.Priority;
import practicum1.DataProcessing.Containers.GraphData;
import practicum1.DataProcessing.Containers.ProcessInfo;
import practicum1.DataProcessing.Containers.ProcessList;
import practicum1.DataProcessing.Containers.SimulationResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ResultProcessor {


    public static SimulationResult generateSimulationResult(ProcessList processList, String algorithmName) {

        //Calculate de gemiddlede tat en turn around time
        System.out.println("Generating simulationresult");

        //sort processlist on service time
        Collections.sort(processList , Comparator.comparingInt(ProcessInfo::getServiceTime));

        int partition = processList.size()/10;
        int count = 1;

        //@jonas 10 values for each percinile for the wait graph and the turn around graph

        //graph outputs
        GraphData waitTimeGraph = new GraphData(algorithmName);
        GraphData turnAroundTimeGraph = new GraphData(algorithmName);

        List<Integer> tempWaitTimeValues = new ArrayList();
        List<Integer> tempTurnAroundValues = new ArrayList();

        for(ProcessInfo processInfo: processList) {

            tempWaitTimeValues.add(processInfo.getWaitTime());
            tempTurnAroundValues.add(processInfo.getTurnAroundTime()/processInfo.getServiceTime());

            count++;

            //als we aan volgende percentile zitten toevoegen die handel en uitrkenengemiddelnde
            if(count == partition) {
                waitTimeGraph.add(mean(tempWaitTimeValues));
                turnAroundTimeGraph.add(mean(tempTurnAroundValues));

                tempTurnAroundValues.clear();
                tempWaitTimeValues.clear();

                count = 1;
            }
        }

        return new SimulationResult(algorithmName,waitTimeGraph,turnAroundTimeGraph,null,null);

    }




    private static Integer mean(List<Integer> temp) {
        Integer sum = 0;
        if(!temp.isEmpty()) {
            for (Integer mark : temp) {
                sum += mark;
            }
            return sum / temp.size();
        }
        return sum;
    }


}
