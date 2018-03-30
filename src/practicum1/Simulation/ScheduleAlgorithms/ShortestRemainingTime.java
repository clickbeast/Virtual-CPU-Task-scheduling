package practicum1.Simulation.ScheduleAlgorithms;

import practicum1.DataProcessing.Containers.ProcessInfo;
import practicum1.DataProcessing.Containers.ProcessList;

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
                //roundrobin manier gebruiken om process 1 jiffy te laten uitvoeren
                //steeds process met kleinste resterende tijd uit queue nemen
                elapsedTime += this.que.peek().serve(1);
                if(this.que.peek().getTimeToServe() <= 0){
                    //wanneer dit process gedaan is, het process naar de resultlijst verplaatsen
                    exiting = this.que.poll();
                    exiting.setTurnAroundTime(elapsedTime - exiting.getArrivalTime());
                    exiting.setWaitTime(elapsedTime - exiting.getArrivalTime() - exiting.getServiceTime());
                    this.result.add(exiting);
                }
            }
            //wanneer er geen process meer staat te wachten, direct aan volgende beginnen
            if (this.que.size() == 0 && elapsedTime < processList.getFirst().getArrivalTime()) {
                elapsedTime = processList.getFirst().getArrivalTime();
            }
            //alle processen die mogelijks kunnen starten aan queue toevoegen
            while (elapsedTime >= processList.getFirst().getArrivalTime()) {
                que.add(processList.removeFirst());
                if(processList.size() == 0){
                    break;
                }
            }
        }

        //laatste van de lijst nog aan sneltempo verwerken
        while (this.que.size() != 0){
            exiting = this.que.poll();
            elapsedTime += exiting.getTimeToServe();
            exiting.setTurnAroundTime(elapsedTime - exiting.getArrivalTime());
            exiting.setWaitTime(elapsedTime - exiting.getArrivalTime() - exiting.getServiceTime());
            this.result.add(exiting);
        }

        this.result.sort(Comparator.comparingInt(ProcessInfo::getServiceTime));
        return result;
    }

    @Override
    public String getAlgorithmName() {
        return "Shortest Remaining Time";
    }
}
