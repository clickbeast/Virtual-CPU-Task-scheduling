package practicum1.DataProcessing.Containers;

import java.util.ArrayList;


//List of 100 values for generating an XY Graph

public class GraphData extends ArrayList<Integer> {


    private String title;

    public GraphData(String title) {
        super(10);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
