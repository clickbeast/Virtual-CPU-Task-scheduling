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
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import practicum1.DataProcessing.Containers.SimulationResult;
import practicum1.Simulation.SimulationManager;
import practicum1.UI.ResultLineChart;

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

        //using test list:
        //TODO
        //...

        //clean out current graphs out of the HBOX
        graphView.getChildren().clear();

        //Prepare Axis

        String turnAroundTimeLineChartTitle = "Genormaliseerde omlooptijd in functie van bedieningstijd";
        String waitTimeLineChartTitle = "Wachttijd in functie van bedieningstijd";
        ResultLineChart turnAroundTimeLineChart = new ResultLineChart(createGraphXAxis(),createGraphYAxis("Normalised Turn Around Time"),turnAroundTimeLineChartTitle);
        ResultLineChart waitTimeLineChart = new ResultLineChart(createGraphXAxis(),createGraphYAxis("Wait Time"),waitTimeLineChartTitle);

        this.graphView.getChildren().add(turnAroundTimeLineChart);
        this.graphView.getChildren().add(waitTimeLineChart);

        this.graphView.setVgrow(turnAroundTimeLineChart,Priority.ALWAYS);
        this.graphView.setVgrow(waitTimeLineChart,Priority.ALWAYS);

        //add all chart data
        for(SimulationResult simulationResult: simulationResults) {
            turnAroundTimeLineChart.addSeries(simulationResult.getGrafiekDataOmploopTijd(), simulationResult.getUsedAlgorithmName());
            waitTimeLineChart.addSeries(simulationResult.getGrafiekDataBedieningsTijd(),simulationResult.getUsedAlgorithmName());
        }

    }


    public CategoryAxis createGraphXAxis() {
        final CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Percentile Of Time Required");
        return xAxis;
    }

    public NumberAxis createGraphYAxis(String name) {
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(name);
        return yAxis;
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
        this.runButton.setDisable(false);
    }



    /**
     *  Alerts
     */


    //display info messages in the UI
    public void displayInfoMessage(String message) {
        alertInfoLabel.setText("Info: " + message);
    }



}
