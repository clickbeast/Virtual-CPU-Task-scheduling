package practicum1.Simulation;

import practicum1.DataProcessing.Containers.ProcessList;
import practicum1.DataProcessing.Containers.SimulationResult;
import practicum1.DataProcessing.Processing.ResultProcessor;
import practicum1.DataProcessing.Processing.XMLProcessor;
import practicum1.MainWindowViewController;
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

        //define all used algos algirithmn
        ProcessAlgorithm firstComeFirstServe = new FirstComeFirstServe(this.processList);
        viewController.displayInfoMessage("Starting first come first serve");
        results.add(this.runSimulation(firstComeFirstServe));
        viewController.displayInfoMessage("Done first come first serve");
        ProcessAlgorithm roundRobin2 = new RoundRobin(this.processList, 2);

        viewController.displayInfoMessage("Starting round robin, q = 2");
        results.add(this.runSimulation(roundRobin2));
        viewController.displayInfoMessage("Done round robin, q = 2");
        ProcessAlgorithm roundRobin8 = new RoundRobin(this.processList, 8);
        viewController.displayInfoMessage("Starting round robin, q = 8");
        results.add(this.runSimulation(roundRobin8));
        viewController.displayInfoMessage("Done round robin, q = 8");

        ProcessAlgorithm shortestJobFirst = new ShortestJobFirst(this.processList);
        viewController.displayInfoMessage("Starting shortest job first");
        results.add(this.runSimulation(shortestJobFirst));
        viewController.displayInfoMessage("Done shortest job first");

        ProcessAlgorithm shortestRemainingTime = new ShortestRemainingTime(this.processList);
        viewController.displayInfoMessage("Starting shortest remaining time first");
        results.add(this.runSimulation(shortestRemainingTime));
        viewController.displayInfoMessage("Done shortest remaining time first");

        ProcessAlgorithm highestResponseRatio = new HighestResponseRatio(this.processList);
        viewController.displayInfoMessage("Starting highest response ratio");
        results.add(this.runSimulation(highestResponseRatio));
        viewController.displayInfoMessage("Done highest response ratio");

        ProcessAlgorithm multilevelFeedbackMode = new MultilevelFeedbackMode(this.processList);
        viewController.displayInfoMessage("Starting multilevel feedback");
        results.add(this.runSimulation(multilevelFeedbackMode));
        viewController.displayInfoMessage("Done multilevel feedback");

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
