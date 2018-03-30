package practicum1.Simulation.ScheduleAlgorithms;


import practicum1.DataProcessing.Containers.ProcessInfo;
import practicum1.DataProcessing.Containers.ProcessList;

import java.util.Comparator;


public class FirstComeFirstServe implements ProcessAlgorithm {

    private ProcessList processList;
    private int elapsedTime;

    public FirstComeFirstServe(ProcessList processList) {
        this.processList = new ProcessList(processList);
        this.elapsedTime = 0;
    }

    @Override
    public ProcessList run() {

        for(ProcessInfo process: processList){

            if(elapsedTime > process.getArrivalTime()){
                process.setWaitTime(elapsedTime - process.getArrivalTime());
            } else{
                elapsedTime = process.getArrivalTime();
                process.setWaitTime(0);
            }
            process.setTurnAroundTime(process.getServiceTime() + process.getWaitTime());
            elapsedTime += process.getServiceTime();
        }
        this.processList.sort(Comparator.comparingInt(ProcessInfo::getServiceTime));
        return this.processList;
    }

    @Override
    public String getAlgorithmName() {
        return "First Come First Serve";
    }
}
