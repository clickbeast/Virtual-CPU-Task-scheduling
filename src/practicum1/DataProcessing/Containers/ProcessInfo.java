package practicum1.DataProcessing.Containers;


/*
    Class that contains info about an incoming process, when working with the process, TAT and wait time can be added
    after a testrun

 */
public class ProcessInfo {

    private int turnAroundTime;
    private int waitTime;
    private int arrivalTime;
    private int serviceTime;
    private int timeToServe;
    private int id;

    public ProcessInfo(int arrivalTime, int serviceTime, int id) {
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.timeToServe = serviceTime;
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

    public int getServiceTime() {
        return serviceTime;
    }

    public int getTimeToServe() {
        return timeToServe;
    }

    public int getId() {
        return id;
    }

    public void serve(int q){
        this.timeToServe -= q;
    }
}
