package practicum1.Simulation.ScheduleAlgorithms;

import practicum1.DataProcessing.Containers.ProcessInfo;
import practicum1.DataProcessing.Containers.ProcessList;

import java.util.Comparator;
import java.util.PriorityQueue;


public class ShortestJobFirst implements ProcessAlgorithm {

    private ProcessList result;
    private ProcessList processList;
    private PriorityQueue<ProcessInfo> que;
    private int elapsedTime;

    public ShortestJobFirst(ProcessList processList) {
        this.result = new ProcessList();
        this.processList = new ProcessList(processList);
        this.elapsedTime = 0;
        this.que = new PriorityQueue<>(Comparator.comparingInt(ProcessInfo::getServiceTime));
    }

    @Override
    public ProcessList run() {

        ProcessInfo exiting;

        while(this.processList.size() != 0 || this.que.size() != 0) {

            if (processList.size() != 0) {

                if (this.que.size() == 0 && elapsedTime < processList.getFirst().getArrivalTime()) {
                    elapsedTime = processList.getFirst().getArrivalTime();
                }

                while (elapsedTime >= processList.getFirst().getArrivalTime()) {
                    que.add(processList.removeFirst());
                    if(processList.size() == 0) break;
                }
            }
            exiting = que.poll();
            elapsedTime += exiting.getServiceTime();
            exiting.setTurnAroundTime(elapsedTime - exiting.getArrivalTime());
            exiting.setWaitTime(elapsedTime - exiting.getArrivalTime() - exiting.getServiceTime());
            this.result.add(exiting);
        }

        this.result.sort(Comparator.comparingInt(ProcessInfo::getServiceTime));
        return result;
    }

    @Override
    public String getAlgorithmName() {
        return "Shortest Job First";
    }

}
