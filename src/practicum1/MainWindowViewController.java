package practicum1;


import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import practicum1.DataProcessing.Containers.SimulationResult;
import practicum1.Simulation.SimulationManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/*
controls the graphic part of our application

 */
public class MainWindowViewController implements Initializable {

    //TODO extra;; Display samenvatting on screen if time

    /**
     *  Variables
     */

    public Scene scene;
    private SimulationManager simulationManager;
    public ChoiceBox choiceBox;
    public Button runButton;


    //holds all the graphs
    public VBox graphView;


    //XYLineChart
    //public LineChart turnAroundTimeChart;
    //public LineChart waitTimeChart;
    public Label alertInfoLabel;



    /**
     *  Configure
     *
     */



    //Gets run when the UI has finished loading
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing");
        choiceBox.setItems(FXCollections.observableArrayList("10000 Processen","50000 Processen"));
        choiceBox.getSelectionModel().selectFirst();
        displayInfoMessage("Preparing: Reading XML List");

        //Initialize the graphs

    }


    //gets called when complete setup is done
    public void startFinished() {
        displayInfoMessage("Info: Ready");
    }


    public void setSimulationManager(SimulationManager simulationManager) {
        this.simulationManager = simulationManager;
    }



    /**
     *  Layout
     */

    //TODO
    public void resetGraphView() {


    }


    public void configureGraphsWithData(List<SimulationResult> simulationResults) {

        this.displayInfoMessage("Creating Graphs, please wait.");

        //clean out current graphs out of the HBOX
        graphView.getChildren().clear();

        //Make elements
        //TODO move to nice place


        //fill up the HBOX with the new graph


        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month");

        final LineChart<String,Number> lineChart =
                new LineChart<String,Number>(xAxis,yAxis);

        lineChart.setTitle("Stock Monitoring, 2010");

        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");

        series.getData().add(new XYChart.Data("Jan", 23));
        series.getData().add(new XYChart.Data("Feb", 14));
        series.getData().add(new XYChart.Data("Mar", 15));
        series.getData().add(new XYChart.Data("Apr", 24));
        series.getData().add(new XYChart.Data("May", 34));
        series.getData().add(new XYChart.Data("Jun", 36));
        series.getData().add(new XYChart.Data("Jul", 22));
        series.getData().add(new XYChart.Data("Aug", 45));
        series.getData().add(new XYChart.Data("Sep", 43));
        series.getData().add(new XYChart.Data("Oct", 17));
        series.getData().add(new XYChart.Data("Nov", 29));
        series.getData().add(new XYChart.Data("Dec", 25));


        this.graphView.getChildren().add(lineChart);
        lineChart.getData().add(series);



    }



    //updates all the graphs
    public void updateGraphs() {
        //TODO


    }


    /**
     *  Actions
     *
     */

    //gets called when the user wants to run a specific algorithm
    public void runAction() {
        displayInfoMessage("Starting simulation...");
        freezeUI();
        if(choiceBox.getSelectionModel().getSelectedItem() ==  "10000 Processen") {
            this.simulationManager.prepareToSchedule10000();
        }else{
            this.simulationManager.prepareToSchedule50000();
        }

        List<SimulationResult> simulationResults = this.simulationManager.runAllAlgorithmSimulations();
        this.configureGraphsWithData(simulationResults);

        //TODO
        //create graph based on simulation results.
        displayInfoMessage("Done");

        unFreezeUI();
    }

    //prevent user from using UI, comonly used while running algorithm
    public void freezeUI() {
        this.choiceBox.setDisable(true);
        this.runButton.setDisable(true);
    }

    public void unFreezeUI() {
        this.choiceBox.setDisable(false);
        this.choiceBox.setDisable(false);
    }

    /**
     *  Alerts
     */


    //display info messages in the UI
    public void displayInfoMessage(String message) {
        alertInfoLabel.setText("Info: " + message);
    }




    //TODO reageer op juiste manier op UI INPUT



}
