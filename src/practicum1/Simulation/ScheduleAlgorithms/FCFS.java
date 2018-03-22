package practicum1.Simulation.ScheduleAlgorithms;


import practicum1.DataProcessing.Containers.ProcessInfo;
import practicum1.DataProcessing.Containers.ProcessList;

//!!!!! : doet SIMON al.


/*

Example of
 */
public class FCFS implements ProcessAlgorithm {

    private ProcessList processList;
    private int elapsedTime;

    public FCFS(ProcessList processList) {

        this.processList = processList;
        this.elapsedTime = 0;
    }

    @Override
    public void run() {

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
    }

    @Override
    public String getAlgorithmName() {
        return "First Come First Serve";
    }
}
