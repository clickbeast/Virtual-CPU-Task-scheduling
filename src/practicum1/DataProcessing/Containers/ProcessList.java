package practicum1.DataProcessing.Containers;

import java.util.LinkedList;

public class ProcessList extends LinkedList<ProcessInfo> {

    public ProcessList() {
        super();
    }

    public ProcessList(ProcessList processList) {
        super();
        for(ProcessInfo processInfo: processList){
            this.add(new ProcessInfo(processInfo));
        }
    }


    //TODO make calculations mean work

    public Double getMeanTurnAroundTime() {

        return null;
    }

    public Double getMeanWaitTime() {

        return null;
    }

}
