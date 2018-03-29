package practicum1.Simulation.ScheduleAlgorithms;

import practicum1.DataProcessing.Containers.ProcessInfo;
import practicum1.DataProcessing.Containers.ProcessList;
import practicum1.Simulation.Comparators.ShortestRemaining;

import java.util.Comparator;
import java.util.PriorityQueue;

public class ShortestRemainingTime implements ProcessAlgorithm {

    private ProcessList result;
    private ProcessList processList;
    private PriorityQueue<ProcessInfo> que;
    private int elapsedTime;

    public ShortestRemainingTime(ProcessList processList) {
        this.result = new ProcessList();
        this.processList = new ProcessList(processList);
        this.elapsedTime = 0;
        this.que = new PriorityQueue<>(Comparator.comparingInt(ProcessInfo::getTimeToServe));
    }

    @Override
    public ProcessList run() {
        ProcessInfo exiting;

        while(this.processList.size() != 0) {

            while (elapsedTime < processList.getFirst().getArrivalTime() && this.que.size() != 0) {
                elapsedTime += this.que.peek().serve(1);
                if(this.que.peek().getTimeToServe() <= 0){
                    exiting = this.que.poll();
                    exiting.setTurnAroundTime(elapsedTime - exiting.getArrivalTime());
                    exiting.setWaitTime(elapsedTime - exiting.getArrivalTime() - exiting.getServiceTime());
                    this.result.add(exiting);
                }
            }

            if (this.que.size() == 0 && elapsedTime < processList.getFirst().getArrivalTime()) {
                elapsedTime = processList.getFirst().getArrivalTime();
            }

            while (elapsedTime >= processList.getFirst().getArrivalTime()) {
                que.add(processList.removeFirst());
                if(processList.size() == 0){
                    break;
                }
            }
        }
        while (this.que.size() != 0){
            exiting = this.que.poll();
            elapsedTime += exiting.getTimeToServe();
            exiting.setTurnAroundTime(elapsedTime - exiting.getArrivalTime());
            exiting.setWaitTime(elapsedTime - exiting.getArrivalTime() - exiting.getServiceTime());
            this.result.add(exiting);
        }

        result.sort(Comparator.comparingInt(ProcessInfo::getArrivalTime));
        return result;
    }

    @Override
    public String getAlgorithmName() {
        return "Shortest Remaining Time";
    }
}
