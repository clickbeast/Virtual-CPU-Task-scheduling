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
import java.util.List;

public class ResultProcessor {


    public static SimulationResult generateSimulationResult(ProcessList processList, String algorithmName) {

        //Calculate de gemiddlede tat en turn around time
        System.out.println("Generating simulationresult");

        GraphData turnAroundTimeValues = new GraphData(algorithmName);
        GraphData waitTimeValues = new GraphData(algorithmName);



        //create list of NTATS and Wait times
        List<Integer> normalisedTurnAroundTimes = new ArrayList<>();
        for(ProcessInfo processInfo: processList) {
            normalisedTurnAroundTimes.add(processInfo.getTurnAroundTime()/processInfo.getServiceTime());
        }

        turnAroundTimeValues = createGraphDataFromValues(normalisedTurnAroundTimes,algorithmName);

        //create list of  Wait times
        List<Integer> waitTimes = new ArrayList<>();
        for(ProcessInfo processInfo: processList) {
            waitTimes.add(processInfo.getWaitTime());
        }

        waitTimeValues = createGraphDataFromValues(waitTimes,algorithmName);



        return new SimulationResult(algorithmName,waitTimeValues,turnAroundTimeValues,null,null);
    }

    private static GraphData createGraphDataFromValues(List<Integer> values, String algorithmName) {

        Collections.sort(values);
        int partition = values.size()/10;
        int count = 1;
        GraphData result = new GraphData(algorithmName);
        Integer meanValue = 0;
        List<Integer> temp = new ArrayList();
        for(Integer value: values) {
            temp.add(value);

            count++;

            //als we aan volgende percentile zitten toevoegen die handel en uitrkenengemiddelnde
            if(count == partition) {
                result.add(mean(temp));
                temp.clear();
                count = 1;
            }
        }

        result.add(meanValue);


        return result;
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
