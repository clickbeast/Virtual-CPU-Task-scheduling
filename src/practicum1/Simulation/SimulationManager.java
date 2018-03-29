package practicum1.Simulation;

import practicum1.MainWindowViewController;
import practicum1.DataProcessing.Containers.ProcessList;
import practicum1.DataProcessing.Processing.ResultProcessor;
import practicum1.DataProcessing.Processing.XMLProcessor;
import practicum1.DataProcessing.Containers.SimulationResult;
import practicum1.Simulation.ScheduleAlgorithms.*;

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

        //VANAF 3de gaan er duidemlijk nog zaken mis

        //define all used algos algirithmn
        ProcessAlgorithm firstComeFirstServe = new FirstComeFirstServe(this.processList);
        results.add(this.runSimulation(firstComeFirstServe));
        ProcessAlgorithm roundRobin2 = new RoundRobin(this.processList, 2);
        results.add(this.runSimulation(roundRobin2));
        ProcessAlgorithm roundRobin8 = new RoundRobin(this.processList, 8);
        results.add(this.runSimulation(roundRobin8));
        ProcessAlgorithm highestResponseRatio = new HighestResponseRatio(this.processList);
        results.add(this.runSimulation(highestResponseRatio));
        ProcessAlgorithm shortestRemainingTime = new ShortestRemainingTime(this.processList);
        results.add(this.runSimulation(shortestRemainingTime));
        ProcessAlgorithm shortestJobFirst = new ShortestJobFirst(this.processList);
        results.add(this.runSimulation(shortestJobFirst));
        ProcessAlgorithm multilevelFeedbackMode = new MultilevelFeedbackMode(this.processList);
        results.add(this.runSimulation(multilevelFeedbackMode));



        viewController.displayInfoMessage("Done Running Algorithms");

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

        ProcessList processListOutcome = processAlgorithm.run();

        //create the a result object based on the given run
        return ResultProcessor.generateSimulationResult(processListOutcome,processAlgorithm.getAlgorithmName());
    }


    public void setMainWindowController(MainWindowViewController mainWindowController) {
        this.viewController = mainWindowController;
    }
}
