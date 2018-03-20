package practicum1.DataProcessing.Processing;


import org.w3c.dom.Document;
import practicum1.DataProcessing.Containers.ProcessList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/*
    Main task: Process a supplied xml document and generate an approptiate list of process Objects that can be handled later


 */
public class XMLProcessor {

    //initialise class
    public XMLProcessor() {

    }

    //TODO fully read file and fully generate list
    public ProcessList generateProcessListBasedOnXML(String fileName, int capacity) {

        ProcessList processList = new ProcessList(10000);

        //read the xml file
        try {

            File file = new File(fileName);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(file);


            //TODO Read document recursivly and give processList


        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("XML file generated");

        return processList;
    }


}
