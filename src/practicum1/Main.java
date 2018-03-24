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

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Practicum 1: Simon Vermeir & Jonas Vinck");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        ProcessList processList = new XMLProcessor().generateProcessListBasedOnXML("processen10000.xml");
        ProcessAlgorithm roundRobin = new RoundRobin(processList, 2);
        roundRobin.run();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
