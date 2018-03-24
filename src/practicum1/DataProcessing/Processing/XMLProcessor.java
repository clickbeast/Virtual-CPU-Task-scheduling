package practicum1.DataProcessing.Processing;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import practicum1.DataProcessing.Containers.ProcessInfo;
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
    public ProcessList generateProcessListBasedOnXML(String fileName) {

        ProcessList processList = new ProcessList();

        //read the xml file
        try {

            File file = new File(fileName);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(file);

            Element root = doc.getDocumentElement();
            NodeList list = root.getElementsByTagName("process");

            for(int i = 0; i < list.getLength(); i++){

                String id = list.item(i).getChildNodes().item(1).getTextContent();
                String arrival = list.item(i).getChildNodes().item(3).getTextContent();
                String service = list.item(i).getChildNodes().item(5).getTextContent();
                processList.add(new ProcessInfo(
                        Integer.parseInt(arrival),
                        Integer.parseInt(service),
                        Integer.parseInt(id)
                ));
            }

            System.out.println("XML file Generated");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return processList;
    }
}
