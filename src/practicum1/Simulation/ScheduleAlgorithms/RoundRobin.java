package practicum1.Simulation.ScheduleAlgorithms;

import practicum1.DataProcessing.Containers.ProcessInfo;
import practicum1.DataProcessing.Containers.ProcessList;

import java.util.LinkedList;

public class RoundRobin implements  ProcessAlgorithm{

    private ProcessList backup;
    private ProcessList processList;
    private LinkedList<ProcessInfo> que;
    private int elapsedTime;
    private int q;

    public RoundRobin(ProcessList processList, int q) {
        this.backup = (ProcessList) processList.clone();
        this.processList = processList;
        this.elapsedTime = 0;
        this.que = new LinkedList<>();
        this.q = q;
    }

    @Override
    public void run() {

        int last = 0;

        while(this.processList.size() != 0 || this.que.size() != 0){

            if(processList.size() != 0) {

                if(this.que.size() == 0 && elapsedTime < processList.getFirst().getArrivalTime()){
                    elapsedTime = processList.getFirst().getArrivalTime();
                    last = processList.getFirst().getId();
                }

                if (elapsedTime >= processList.getFirst().getArrivalTime()) {
                    que.addLast(processList.removeFirst());
                }
            }

            elapsedTime+=que.getFirst().serve(q);
            if(que.getFirst().getTimeToServe() > 0) que.addLast(que.removeFirst());
            else que.removeFirst();
        }

        boolean found = false;
        int time = 0;
        for(ProcessInfo processInfo: backup){
            if (found) time += processInfo.getServiceTime();
            if(processInfo.getId() == last){
                found = true;
                time = processInfo.getArrivalTime() + processInfo.getServiceTime();
            }
        }
        System.out.println(time);
        System.out.println(elapsedTime);

    }

    @Override
    public String getAlgorithmName() {
        return "Round Robin";
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
