package practicum1.Simulation.ScheduleAlgorithms;


/*
    Defines an algorithm that can be run on a list of incoming processes

 */
public interface ProcessAlgorithm {


    /**
     * Required: Handle each process in the process list according to the algorthm and add the required new data
     * That is generayed when running the algorthm: The wait and the turn around time.
     */
    public void run();

    //returns the name of the used algorthmn
    public String getAlgorithmName();

}
