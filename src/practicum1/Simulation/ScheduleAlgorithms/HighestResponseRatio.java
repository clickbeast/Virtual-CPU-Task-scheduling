package practicum1.Simulation.ScheduleAlgorithms;

import practicum1.DataProcessing.Containers.ProcessInfo;
import practicum1.DataProcessing.Containers.ProcessList;
import practicum1.Simulation.Comparators.ReOrderComparator;
import practicum1.Simulation.Comparators.ShortestTATFirst;
import practicum1.Simulation.Comparators.TimeInterface;

import java.util.PriorityQueue;

public class HighestResponseRatio implements ProcessAlgorithm, TimeInterface {

    private ProcessList result;
    private ProcessList processList;
    private PriorityQueue<ProcessInfo> que;
    private int elapsedTime;

    public HighestResponseRatio(ProcessList processList) {
        this.result = new ProcessList();
        this.processList = processList;
        this.elapsedTime = 0;
        this.que = new PriorityQueue<>(new ShortestTATFirst(this));
    }

    @Override
    public ProcessList run() {

        ProcessInfo exiting;

        while(this.processList.size() != 0 || this.que.size() != 0) {

            if(processList.size() != 0) {
                if (this.que.size() == 0 && elapsedTime < processList.getFirst().getArrivalTime()) {
                    elapsedTime = processList.getFirst().getArrivalTime();
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
            this.result.add(exiting);
        }

        result.sort(new ReOrderComparator());
        return result;
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

