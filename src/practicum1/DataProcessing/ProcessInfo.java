package practicum1.DataProcessing;


/*
    Class that contains info about an incoming process, when working with the process, TAT and wait time can be added
    after a testrun

 */
public class ProcessInfo {

    private int turnAroundTime;
    private int waitTime;
    private int arrivalTime;
    private int serviveTime;
    private int id;

    public ProcessInfo(int arrivalTime, int serviveTime, int id) {
        this.arrivalTime = arrivalTime;
        this.serviveTime = serviveTime;
        this.id = id;
    }


    public int getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime(int turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiveTime() {
        return serviveTime;
    }

    public int getId() {
        return id;
    }
}
