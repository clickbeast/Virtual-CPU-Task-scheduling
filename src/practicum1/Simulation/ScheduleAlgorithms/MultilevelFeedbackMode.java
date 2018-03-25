package practicum1.Simulation.ScheduleAlgorithms;

import practicum1.DataProcessing.Containers.ProcessInfo;
import practicum1.DataProcessing.Containers.ProcessList;

import java.util.PriorityQueue;

public class MultilevelFeedbackMode implements ProcessAlgorithm {

    private ProcessList backup;
    private ProcessList processList;

    private int elapsedTime;

    public MultilevelFeedbackMode(ProcessList processList) {
        this.backup = (ProcessList) processList.clone();
        this.processList = processList;
        this.elapsedTime = 0;
    }

    @Override
    public void run() {

    }

    @Override
    public String getAlgorithmName() {
        return null;
    }
}
