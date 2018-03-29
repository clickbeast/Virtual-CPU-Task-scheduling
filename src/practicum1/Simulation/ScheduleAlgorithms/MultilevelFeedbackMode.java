package practicum1.Simulation.ScheduleAlgorithms;

import practicum1.DataProcessing.Containers.ProcessInfo;
import practicum1.DataProcessing.Containers.ProcessList;
import practicum1.Simulation.Comparators.ReOrderComparator;

import java.util.LinkedList;

public class MultilevelFeedbackMode implements ProcessAlgorithm {

    private ProcessList result;
    private ProcessList processList;
    private LinkedList<ProcessInfo> que;
    private LinkedList<ProcessInfo> que2;
    private LinkedList<ProcessInfo> que3;
    private int elapsedTime;

    public MultilevelFeedbackMode(ProcessList processList) {
        this.result = (ProcessList) processList.clone();
        this.processList = processList;
        this.que = new LinkedList<>();
        this.que2 = new LinkedList<>();
        this.que3 = new LinkedList<>();
        this.elapsedTime = 0;
    }

    @Override
    public ProcessList run() {

        ProcessInfo exiting;

        while (processList.size()!= 0 || que.size() !=0 || que2.size() !=0 || que3.size() !=0){

            if(processList.size() != 0) {

                if(this.que.size() == 0 && que2.size() == 0 && que3.size() == 0 && elapsedTime < processList.getFirst().getArrivalTime()){
                    elapsedTime = processList.getFirst().getArrivalTime();
                }

                if (elapsedTime >= processList.getFirst().getArrivalTime()) {
                    que.addLast(processList.removeFirst());
                }
            }

            if(que.size() != 0){
                elapsedTime+=que.getFirst().serve(1);
                pass(que, que2);
            } else if(que2.size() != 0){
                elapsedTime+=que2.getFirst().serve(2);
                pass(que2, que3);
            } else if(que3.size() != 0){
                exiting = que3.removeFirst();
                elapsedTime+=exiting.getTimeToServe();
                exiting.setWaitTime(elapsedTime - exiting.getArrivalTime() - exiting.getServiceTime());
                exiting.setTurnAroundTime(elapsedTime - exiting.getArrivalTime());
                this.result.add(exiting);
            }
        }

        this.result.sort(new ReOrderComparator());
        return this.result;
    }

    private void pass(LinkedList<ProcessInfo> originalQue, LinkedList<ProcessInfo> nextQue){
        ProcessInfo exiting;
        if(originalQue.getFirst().getTimeToServe() > 0) nextQue.addLast(originalQue.removeFirst());
        else{
            exiting = originalQue.removeFirst();
            exiting.setWaitTime(elapsedTime - exiting.getArrivalTime() - exiting.getServiceTime());
            exiting.setTurnAroundTime(elapsedTime - exiting.getArrivalTime());
            this.result.add(exiting);
        }
    }

    @Override
    public String getAlgorithmName() {
        return null;
    }
}
