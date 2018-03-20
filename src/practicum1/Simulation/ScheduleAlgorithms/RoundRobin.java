package practicum1.Simulation.ScheduleAlgorithms;

import practicum1.DataProcessing.Containers.ProcessList;

public class RoundRobin implements  ProcessAlgorithm{

    private ProcessList processList;


    public RoundRobin(ProcessList processList) {

    }

    @Override
    public void run() {

    }

    @Override
    public String getAlgorithmName() {
        return "Round Robin";
    }

}
