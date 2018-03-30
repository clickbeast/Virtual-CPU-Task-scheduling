package practicum1.Simulation.ScheduleAlgorithms;

import practicum1.DataProcessing.Containers.ProcessInfo;
import practicum1.DataProcessing.Containers.ProcessList;

import java.util.Comparator;
import java.util.LinkedList;

public class MultilevelFeedbackMode implements ProcessAlgorithm {

    private ProcessList result;
    private ProcessList processList;
    private LinkedList<ProcessInfo> que;
    private LinkedList<ProcessInfo> que2;
    private LinkedList<ProcessInfo> que3;
    private int elapsedTime;

    public MultilevelFeedbackMode(ProcessList processList) {

        this.result = new ProcessList();
        this.processList = new ProcessList(processList);
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

                //wanneer geen processen staan te wachten, direct aan volgende beginnen
                if(this.que.size() == 0 && que2.size() == 0 && que3.size() == 0 && elapsedTime < processList.getFirst().getArrivalTime()){
                    elapsedTime = processList.getFirst().getArrivalTime();
                }

                //alle wachtende processen aan de queue toevoegen
                while (elapsedTime >= processList.getFirst().getArrivalTime()) {
                    que.addLast(processList.removeFirst());
                    if(processList.size() == 0){
                        break;
                    }
                }
            }

            if(que.size() != 0){
                elapsedTime+=que.getFirst().serve(40);
                pass(que, que2);
            } else if(que2.size() != 0){
                elapsedTime+=que2.getFirst().serve(80);
                pass(que2, que3);
            } else if(que3.size() != 0){
                //first come first server implementatie
                exiting = que3.removeFirst();
                elapsedTime+=exiting.getTimeToServe();
                exiting.setWaitTime(elapsedTime - exiting.getArrivalTime() - exiting.getServiceTime());
                exiting.setTurnAroundTime(elapsedTime - exiting.getArrivalTime());
                this.result.add(exiting);
            }
        }

        this.result.sort(Comparator.comparingInt(ProcessInfo::getServiceTime));
        return this.result;
    }

    private void pass(LinkedList<ProcessInfo> originalQue, LinkedList<ProcessInfo> nextQue){
        //process doorgeven tussen 2 queues of proess naar result lijst verplaatsen
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
        return "Multilevel Feedback Mode";
    }
}
