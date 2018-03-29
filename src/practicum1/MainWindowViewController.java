package practicum1;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import practicum1.DataProcessing.Containers.GraphData;
import practicum1.DataProcessing.Containers.SimulationResult;
import practicum1.Simulation.SimulationManager;
import practicum1.UI.LogarithmicAxis;
import practicum1.UI.ResultLineChart;

import java.net.URL;
import java.util.ArrayList;
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
    public ChoiceBox displayChoice;
    private boolean displayTAT, displayWait, started = false;
    public Button runButton;

    private List<SimulationResult> simulationResults;


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
        choiceBox.setItems(FXCollections.observableArrayList("10000 Processen","20000 processen", "50000 Processen"));
        choiceBox.getSelectionModel().selectFirst();
        displayChoice.setItems(FXCollections.observableArrayList("TAT","Wait", "beide"));
        displayChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                switch ((int) newValue){
                    case 0:
                        displayTAT = true;
                        displayWait = false;
                        break;
                    case 1:
                        displayTAT = false;
                        displayWait = true;
                        break;
                    default:
                        displayTAT = true;
                        displayWait = true;
                        break;
                }
                try{
                    configureGraphsWithData();
                } catch (NullPointerException e){
                    if(started) {
                        new Alert(
                                Alert.AlertType.INFORMATION,
                                "Geen data om weer te geven. Run eerst een simmulatie.",
                                ButtonType.OK
                        ).showAndWait();
                    }
                }
            }
        });
        displayChoice.getSelectionModel().selectLast();
        displayInfoMessage("Preparing: Reading XML List");
        started = true;

    }


    //gets called when complete setup is done
    public void startFinished() {
        displayInfoMessage("Ready");
    }


    public void setSimulationManager(SimulationManager simulationManager) {
        this.simulationManager = simulationManager;
    }


    /**
     *  Layout
     */


    public void configureGraphsWithData() {



        this.displayInfoMessage("Creating Graphs, please wait.");

        //clean out current graphs out of the VBOX
        graphView.getChildren().clear();

        //Set Graph titles
        String turnAroundTimeLineChartTitle = "Genormaliseerde omlooptijd in functie van bedieningstijd";
        String waitTimeLineChartTitle = "Wachttijd in functie van bedieningstijd";
        final LogarithmicAxis yAxis2 = new LogarithmicAxis();
        yAxis2.setLabel("Normalised Turn Around Time");

        ResultLineChart turnAroundTimeLineChart = null, waitTimeLineChart = null;
        if(displayTAT) {
            turnAroundTimeLineChart = new ResultLineChart(createGraphXAxis(), yAxis2, turnAroundTimeLineChartTitle);
            this.graphView.getChildren().add(turnAroundTimeLineChart);
            //make graph fill windows
            this.graphView.setVgrow(turnAroundTimeLineChart,Priority.ALWAYS);
        }
        if (displayWait) {
            waitTimeLineChart = new ResultLineChart(createGraphXAxis(),createGraphYAxis("Wait Time"),waitTimeLineChartTitle);
            this.graphView.getChildren().add(waitTimeLineChart);
            //make graph fill windows
            this.graphView.setVgrow(waitTimeLineChart,Priority.ALWAYS);

        }

        //add all chart data
        for(SimulationResult simulationResult: simulationResults) {
            if(displayTAT){
                turnAroundTimeLineChart.addSeries(simulationResult.getGrafiekDataOmploopTijd(), simulationResult.getUsedAlgorithmName());
            }
            if(displayWait){
                waitTimeLineChart.addSeries(simulationResult.getGrafiekDataBedieningsTijd(),simulationResult.getUsedAlgorithmName());
            }
        }



    }


    public void configureGraphTest() {
//        graphView.getChildren().clear();
//
//        //Set Graph titles
//        String turnAroundTimeLineChartTitle = "Genormaliseerde omlooptijd in functie van bedieningstijd";
//        String waitTimeLineChartTitle = "Wachttijd in functie van bedieningstijd";
//
//        ResultLineChart turnAroundTimeLineChart = new ResultLineChart(createGraphXAxis(),createGraphYAxis("Normalised Turn Around Time"),turnAroundTimeLineChartTitle);
//        ResultLineChart waitTimeLineChart = new ResultLineChart(createGraphXAxis(),createGraphYAxis("Wait Time"),waitTimeLineChartTitle);
//
//        this.graphView.getChildren().add(turnAroundTimeLineChart);
//        this.graphView.getChildren().add(waitTimeLineChart);
//
//
//        //make graphs fill windows
//        this.graphView.setVgrow(turnAroundTimeLineChart,Priority.ALWAYS);
//        this.graphView.setVgrow(waitTimeLineChart,Priority.ALWAYS);
//
//        GraphData values = new GraphData("bonjour");
//        values.add(1);
//        values.add(3);
//        values.add(4);
//        values.add(7);
//        values.add(6);
//        values.add(10);
//        values.add(12);
//        values.add(13);
//        values.add(14);
//        values.add(15);
//
//        GraphData values2 = new GraphData("bonjour");
//        values2.add(3);
//        values2.add(7);
//        values2.add(18);
//        values2.add(7);
//        values2.add(5);
//        values2.add(2);
//        values2.add(12);
//        values2.add(3);
//        values2.add(14);
//        values2.add(20);
//
//
//        turnAroundTimeLineChart.addSeries(values,"TEst series");
//        waitTimeLineChart.addSeries(values, "con");
//        turnAroundTimeLineChart.addSeries(values2,"TEst series");
//        waitTimeLineChart.addSeries(values2, "con");

    }

    //creates an xaxis fot oneo of the graphs
    public CategoryAxis createGraphXAxis() {
        final CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Percentile Of Time Required");
        return xAxis;
    }

    //creates an yaxis for one of the graphs
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

        this.simulationResults = this.simulationManager.runAllAlgorithmSimulations();
        this.configureGraphsWithData();


        displayInfoMessage("Simulation Finished");
        unFreezeUI();
    }


    //change what the user wants to see
    public void changeDisplay() {
        displayInfoMessage("Starting simulation...");
        freezeUI();
        if(choiceBox.getSelectionModel().getSelectedItem() ==  "10000 Processen") {
            this.simulationManager.prepareToSchedule10000();
        }
        else if(choiceBox.getSelectionModel().getSelectedItem() ==  "20000 Processen") {
            this.simulationManager.prepareToSchedule20000();
        }else{
            this.simulationManager.prepareToSchedule50000();
        }

        simulationResults = this.simulationManager.runAllAlgorithmSimulations();
        this.configureGraphsWithData();


        displayInfoMessage("Simulation Finished");
        unFreezeUI();
    }


    //prevent user from using UI, commonly used while running algorithm
    public void freezeUI() {
        this.choiceBox.setDisable(true);

        this.runButton.setDisable(true);
    }

    //allow user to use UI again
    public void unFreezeUI() {
        this.choiceBox.setDisable(false);
        this.runButton.setDisable(false);
    }



    /**
     *  Alerts
     */


    //display info messages in the UI while running the app
    public void displayInfoMessage(String message) {
        alertInfoLabel.setText("Info: " + message);
    }



}
