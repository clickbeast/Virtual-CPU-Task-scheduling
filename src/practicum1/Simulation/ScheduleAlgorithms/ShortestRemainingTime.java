package practicum1.Simulation.ScheduleAlgorithms;

import practicum1.DataProcessing.Containers.ProcessInfo;
import practicum1.DataProcessing.Containers.ProcessList;

import java.util.Comparator;
import java.util.PriorityQueue;

public class ShortestRemainingTime implements ProcessAlgorithm {

    private ProcessList backup;
    private ProcessList processList;
    private PriorityQueue<ProcessInfo> que;
    private int elapsedTime;

    public ShortestRemainingTime(ProcessList processList) {
        this.backup = (ProcessList) processList.clone();
        this.processList = processList;
        this.elapsedTime = 0;
        this.que = new PriorityQueue<>(new ShortestRemaining());
    }

    @Override
    public void run() {
//        int last = 0;
        ProcessInfo exiting;

        while(this.processList.size() != 0) {

            while (elapsedTime < processList.getFirst().getArrivalTime() && this.que.size() != 0) {
                elapsedTime += this.que.peek().serve(1);
                if(this.que.peek().getTimeToServe() <= 0){
                    exiting = this.que.poll();
                    exiting.setTurnAroundTime(elapsedTime - exiting.getArrivalTime());
                    exiting.setWaitTime(elapsedTime - exiting.getArrivalTime() - exiting.getServiceTime());
                }
            }

            if (this.que.size() == 0 && elapsedTime < processList.getFirst().getArrivalTime()) {
                elapsedTime = processList.getFirst().getArrivalTime();
//                last = processList.getFirst().getId();
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
        }
/*
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
*/
    }

    @Override
    public String getAlgorithmName() {
        return null;
    }
}

class ShortestRemaining implements Comparator<ProcessInfo> {

    @Override
    public int compare(ProcessInfo service1, ProcessInfo service2){

        return service1.getTimeToServe() - service2.getTimeToServe();
    }
}