package practicum1.Simulation;

import practicum1.MainWindowViewController;
import practicum1.DataProcessing.Containers.ProcessList;
import practicum1.DataProcessing.Processing.ResultProcessor;
import practicum1.DataProcessing.Processing.XMLProcessor;
import practicum1.DataProcessing.Containers.SimulationResult;
import practicum1.Simulation.ScheduleAlgorithms.FirstComeFirstServe;
import practicum1.Simulation.ScheduleAlgorithms.ProcessAlgorithm;
import practicum1.Simulation.ScheduleAlgorithms.RoundRobin;

import java.util.ArrayList;
import java.util.List;


/*
    This is the central managment class that allows us to run certain algorthms based on input files and generate test reuslt
    based in this

 */
public class SimulationManager {


    //access UI
    private MainWindowViewController viewController;


    //Processes
    private ProcessList processList;


    public SimulationManager() {
        System.out.println("Creating Simulation Manager");
    }



    public void resetSimulationManager() {
        if(processList != null) {
            processList.clear();
        }
    }



    public void prepareToSchedule10000() {
        processList = new XMLProcessor().generateProcessListBasedOnXML("processen10000.xml");
    }

    public void prepareToSchedule20000() {
        processList = new XMLProcessor().generateProcessListBasedOnXML("processen20000.xml");
    }

    public void prepareToSchedule50000() {
        processList = new XMLProcessor().generateProcessListBasedOnXML("processen50000.xml");
    }



    public List<SimulationResult> runAllAlgorithmSimulations () {

        System.out.println("Preparing to run all algorithms on loaded processes");
        viewController.displayInfoMessage("Beginning to run Algorithms.");

        List<SimulationResult> results = new ArrayList<>();


        //define the algirithmn
        ProcessAlgorithm roundRobin = new RoundRobin(this.processList, 2);
        ProcessAlgorithm fcfs = new FirstComeFirstServe(this.processList);

        results.add(this.runSimulation(roundRobin));
        results.add(this.runSimulation(fcfs));

        System.out.println("DONE running algorithms on loaded processes");

        viewController.displayInfoMessage("Done running Algorrithms");

        return results;
    }


    /**
     *
     * @param processAlgorithm  An instance of an algirthm that is about to be used on the current scheduled processes
     *
     * @return SuimulationResult: Holds all the simulation date , like TAT, data for  creating graphs in UI, ETC.
     */
    public SimulationResult runSimulation(ProcessAlgorithm processAlgorithm) {
        System.out.println("Running algorithm: " + processAlgorithm.getAlgorithmName());
        viewController.displayInfoMessage("Running Algorithm: " + processAlgorithm.getAlgorithmName());
        processAlgorithm.run();
        //create the a result object based on the given run
        return ResultProcessor.generateSimulationResult(this.processList,processAlgorithm.getAlgorithmName());

    }


    public void setMainWindowController(MainWindowViewController mainWindowController) {
        this.viewController = mainWindowController;
    }
}
