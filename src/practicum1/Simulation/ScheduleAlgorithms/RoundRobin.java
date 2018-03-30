package practicum1.Simulation.ScheduleAlgorithms;

import practicum1.DataProcessing.Containers.ProcessInfo;
import practicum1.DataProcessing.Containers.ProcessList;

import java.util.Comparator;
import java.util.LinkedList;

public class RoundRobin implements  ProcessAlgorithm{

    private ProcessList result;
    private ProcessList processList;
    private LinkedList<ProcessInfo> que;
    private int elapsedTime;
    private int q;

    public RoundRobin(ProcessList processList, int q) {
        this.result = new ProcessList();
        this.processList = new ProcessList(processList);
        this.elapsedTime = 0;
        this.que = new LinkedList<>();
        this.q = q;
    }

    @Override
    public ProcessList run() {

        ProcessInfo exiting;

        while(this.processList.size() != 0 || this.que.size() != 0){

            if(processList.size() != 0) {

                if(this.que.size() == 0 && elapsedTime < processList.getFirst().getArrivalTime()){
                    elapsedTime = processList.getFirst().getArrivalTime();
                }

                while (elapsedTime >= processList.getFirst().getArrivalTime()) {
                    que.addLast(processList.removeFirst());
                    if(processList.size() == 0) break;
                }
            }

            elapsedTime+=que.getFirst().serve(q);
            if(que.getFirst().getTimeToServe() > 0) que.addLast(que.removeFirst());
            else{
                exiting = que.removeFirst();
                exiting.setWaitTime(elapsedTime - exiting.getArrivalTime() - exiting.getServiceTime());
                exiting.setTurnAroundTime(elapsedTime - exiting.getArrivalTime());
                this.result.add(exiting);
            }
        }

        this.result.sort(Comparator.comparingInt(ProcessInfo::getServiceTime));
        return result;
    }

    @Override
    public String getAlgorithmName() {
        return "Round Robin, q= " + q;
    }

    private void print(){

        System.out.println("elapsed time: " + this.elapsedTime);

        for(ProcessInfo processInfo: this.que){

            System.out.println("----------------------------------");

            System.out.println("id: " + processInfo.getId());
            System.out.println("service time: " + processInfo.getServiceTime());
            System.out.println("time to serve " + processInfo.getTimeToServe());
        }
        System.out.println("===============================");
    }
}
