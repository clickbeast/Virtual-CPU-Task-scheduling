package practicum1.Simulation.ScheduleAlgorithms;

import practicum1.DataProcessing.Containers.ProcessInfo;
import practicum1.DataProcessing.Containers.ProcessList;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HighestResponseRatio implements ProcessAlgorithm, TimeInterface {

    private ProcessList backup;
    private ProcessList processList;
    private PriorityQueue<ProcessInfo> que;
    private int elapsedTime;

    public HighestResponseRatio(ProcessList processList) {
        this.backup = (ProcessList) processList.clone();
        this.processList = processList;
        this.elapsedTime = 0;
        this.que = new PriorityQueue<>(new ShortestATFirst(this));
    }

    @Override
    public void run() {
        int last = 0;
        ProcessInfo exiting;

        while(this.processList.size() != 0 || this.que.size() != 0) {

            if(processList.size() != 0) {
                if (this.que.size() == 0 && elapsedTime < processList.getFirst().getArrivalTime()) {
                    elapsedTime = processList.getFirst().getArrivalTime();
                    last = processList.getFirst().getId();
                }

                while (elapsedTime >= processList.getFirst().getArrivalTime()) {
                    que.add(processList.removeFirst());
                    if (processList.size() == 0) {
                        break;
                    }
                }
            }

            exiting = this.que.poll();
            elapsedTime += exiting.getServiceTime();
            exiting.setWaitTime(elapsedTime - exiting.getArrivalTime() - exiting.getServiceTime());
            exiting.setTurnAroundTime(elapsedTime - exiting.getArrivalTime());
        }

        boolean found = false;
        int time = 0;
        for(ProcessInfo info: backup){
            if(info.getId() == last){
                time = info.getArrivalTime();
                found = true;
            }
            if(found) time += info.getServiceTime();
        }
        System.out.println(time);
        System.out.println(elapsedTime);

    }

    @Override
    public String getAlgorithmName() {
        return null;
    }

    @Override
    public int getTime(){
        return elapsedTime;
    }
}

class ShortestATFirst implements Comparator<ProcessInfo> {

    private TimeInterface timeInterface;

    public ShortestATFirst(TimeInterface timeInterface){
        this.timeInterface = timeInterface;
    }

    @Override
    public int compare(ProcessInfo service1, ProcessInfo service2){

        return (timeInterface.getTime() - service2.getArrivalTime() + service2.getServiceTime())/service2.getServiceTime() -
                (timeInterface.getTime() - service1.getArrivalTime() + service1.getServiceTime())/service1.getServiceTime();
    }
}

interface TimeInterface{
    int getTime();
}