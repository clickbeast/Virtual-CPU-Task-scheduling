package practicum1.Simulation.ScheduleAlgorithms;

import javafx.scene.layout.Priority;
import practicum1.DataProcessing.Containers.ProcessInfo;
import practicum1.DataProcessing.Containers.ProcessList;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class ShortestJobFirst implements ProcessAlgorithm{

    private ProcessList backup;
    private ProcessList processList;
    private PriorityQueue<ProcessInfo> que;
    private int elapsedTime;

    public ShortestJobFirst(ProcessList processList) {
        this.backup = (ProcessList) processList.clone();
        this.processList = processList;
        this.elapsedTime = 0;
        this.que = new PriorityQueue<>(new ShortestFirst());
    }

    @Override
    public void run() {

//        int last = 0;
        ProcessInfo exiting;

        while(this.processList.size() != 0 || this.que.size() != 0) {

            if (processList.size() != 0) {

                if (this.que.size() == 0 && elapsedTime < processList.getFirst().getArrivalTime()) {
                    elapsedTime = processList.getFirst().getArrivalTime();
//                    last = processList.getFirst().getId();
                }

                while (elapsedTime >= processList.getFirst().getArrivalTime()) {
                    que.add(processList.removeFirst());
                }
            }
            exiting = que.poll();
            elapsedTime += exiting.getServiceTime();
            exiting.setTurnAroundTime(elapsedTime - exiting.getArrivalTime());
            exiting.setWaitTime(elapsedTime - exiting.getWaitTime() - exiting.getServiceTime());
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
        return "shortestjobfirst";
    }

}

class ShortestFirst implements Comparator<ProcessInfo>{

    @Override
    public int compare(ProcessInfo service1, ProcessInfo service2){

        return service1.getServiceTime() - service2.getServiceTime();
    }
}
