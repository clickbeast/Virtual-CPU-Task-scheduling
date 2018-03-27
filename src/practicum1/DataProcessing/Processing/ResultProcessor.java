package practicum1.DataProcessing.Processing;

/*
Processes a completed list of test processes and generates the desired results to display.

 */

import practicum1.DataProcessing.Containers.GraphData;
import practicum1.DataProcessing.Containers.ProcessList;
import practicum1.DataProcessing.Containers.SimulationResult;

public class ResultProcessor {


    public ResultProcessor() {

    }


    public static SimulationResult generateSimulationResult(ProcessList processList, String algorithmName) {

        //Calculate de gemiddlede tat en turn around time

        System.out.println("- - - - - -");

        System.out.println("Generating simulationresult");



        //DIT herhalen we 2 keer voor beide tat en wait time
            //bundel de resultaten volgens percentielen  -> elke keer 10 bij elkaar
            //DAN van die bundel NEEM JE GEMUIDDELDE -> dat is waarde op de YAS
            //DIE GHEMIDDELDES IN VOLGORDE IN EEN LIJST

        //DIE 10 nummer lijst word telkens toevoegd aan de line chart voor elke algo...  OKAY...

        return null;
    }

}
