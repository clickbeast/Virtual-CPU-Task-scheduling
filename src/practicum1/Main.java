package practicum1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practicum1.DataProcessing.Containers.ProcessList;
import practicum1.DataProcessing.Processing.XMLProcessor;
import practicum1.Simulation.ScheduleAlgorithms.ProcessAlgorithm;
import practicum1.Simulation.ScheduleAlgorithms.RoundRobin;
import practicum1.Simulation.ScheduleAlgorithms.ShortestJobFirst;
import practicum1.Simulation.ScheduleAlgorithms.ShortestRemainingTime;
import practicum1.Simulation.SimulationManager;

public class Main extends Application {

    private SimulationManager simulationManager;


    //keep a reference to the main  window controller
    public MainWindowViewController mainwindow;


    /**
     *  SETUP
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.setupSimulationManager();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));

        //provide the controller with a reference of the simulationManager
        loader.setControllerFactory( c -> {
            if(c == MainWindowViewController.class) {
                MainWindowViewController mc = new MainWindowViewController();
                mc.setSimulationManager(this.simulationManager);
                mainwindow = mc;
                return mc;
            }else{
                try {
                    return c.newInstance();
                }catch (Exception exc){
                    throw new RuntimeException(exc);
                }
            }
        });


        Parent flowPane = loader.load();

        primaryStage.setTitle("Scheduler");
        //primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(flowPane, 1000, 700));
        primaryStage.show();
        this.simulationManager.setMainWindowController(mainwindow);
        mainwindow.scene = primaryStage.getScene();
        this.mainwindow.startFinished();



        /**
         * TESTING
         * - - - - - - - -
         *
         * Use below to test setting up objects etc.
         * - - - - - - - -
         */

        System.out.println("Testing generating processList");
        ProcessList processList = new XMLProcessor().generateProcessListBasedOnXML("processen10000.xml");
        ProcessAlgorithm roundRobin = new ShortestRemainingTime(processList);
        roundRobin.run();
        System.out.println("checkme");
    }


    //Sets up the simulationManager that controllers simulations
    public void setupSimulationManager() {
        this.simulationManager = new SimulationManager();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
