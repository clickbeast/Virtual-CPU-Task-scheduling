package practicum1;


import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import practicum1.Simulation.SimulationManager;

import java.net.URL;
import java.util.ResourceBundle;

/*
controls the graphic part of our application

 */
public class MainWindowViewController implements Initializable {

    /**
     *  Variables
     */

    public Scene scene;
    private SimulationManager simulationManager;
    public ChoiceBox choiceBox;
    public Button runButton;


    //TODO beter vanuit code direct in UI Laden niet via fxml
    //XYLineChart
    public LineChart lineChart1;
    public LineChart lineChart2;

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

        displayInfoMessage("Ready");
    }


    public void setSimulationManager(SimulationManager simulationManager) {
        this.simulationManager = simulationManager;
    }

    /**
     *  Layout
     */

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
        displayInfoMessage("run called");
        freezeUI();
        if(choiceBox.getSelectionModel().getSelectedItem() ==  "10000 Processen") {
            this.simulationManager.prepareToSchedule10000();

        }else{
            this.simulationManager.prepareToSchedule50000();
        }

        //TODO
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
        alertInfoLabel.setText(message);
    }




    //TODO reageer op juiste manier op UI INPUT



}
