package practicum1.Simulation.ScheduleAlgorithms;

import practicum1.DataProcessing.Containers.ProcessInfo;
import practicum1.DataProcessing.Containers.ProcessList;

import java.util.LinkedList;

public class RoundRobin implements  ProcessAlgorithm{

    private ProcessList processList;
    private LinkedList<ProcessInfo> que;
    private int elapsedTime;
    private int q;

    public RoundRobin(ProcessList processList, int q) {
        this.processList = processList;
        this.elapsedTime = 0;
        this.que = new LinkedList<>();
        this.q = q;
    }

    @Override
    public void run() {

        ProcessInfo next = null;
        ProcessInfo executing;
        int served;

        while(this.processList.size() != 0 || this.que.size() != 0){

            if(next == null) next = this.processList.removeFirst();

            if(next.getArrivalTime() > elapsedTime && this.que.size() == 0){

                elapsedTime = next.getArrivalTime() + q;
                this.que.addLast(next);
                next = null;
            }
            else{
                if(next.getArrivalTime() < elapsedTime) {
                    this.que.addLast(next);
                }

                executing = this.que.removeFirst();
                served = (executing.getTimeToServe()%q)+1;
                executing.serve(q);
                elapsedTime+=served;
                if(executing.getTimeToServe() <= 0) this.que.addLast(executing);
            }
        }
    }

    @Override
    public String getAlgorithmName() {
        return "Round Robin";
    }

}
