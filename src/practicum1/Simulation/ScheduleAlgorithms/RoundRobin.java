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

        for(ProcessInfo process: processList){

            while (elapsedTime > process.getArrivalTime()){

            }
            
        }
    }

    @Override
    public String getAlgorithmName() {
        return "Round Robin";
    }

}
