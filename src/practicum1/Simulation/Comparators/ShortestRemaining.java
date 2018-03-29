package practicum1.Simulation.Comparators;

import practicum1.DataProcessing.Containers.ProcessInfo;

import java.util.Comparator;

public class ShortestRemaining implements Comparator<ProcessInfo> {

    @Override
    public int compare(ProcessInfo service1, ProcessInfo service2){

        return service1.getTimeToServe() - service2.getTimeToServe();
    }
}