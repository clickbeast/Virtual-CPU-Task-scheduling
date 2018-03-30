package practicum1.Simulation.Comparators;

import practicum1.DataProcessing.Containers.ProcessInfo;

import java.util.Comparator;

public class ShortestTATFirst implements Comparator<ProcessInfo> {

    //comparator om op TAT te sorteren in priorityqueue

    private TimeInterface timeInterface;

    public ShortestTATFirst(TimeInterface timeInterface){
        this.timeInterface = timeInterface;
    }

    @Override
    public int compare(ProcessInfo service1, ProcessInfo service2){

        return (timeInterface.getTime() - service2.getArrivalTime() + service2.getServiceTime())/service2.getServiceTime() -
                (timeInterface.getTime() - service1.getArrivalTime() + service1.getServiceTime())/service1.getServiceTime();
    }
}