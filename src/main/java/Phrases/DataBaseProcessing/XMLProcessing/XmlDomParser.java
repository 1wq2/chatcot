package Phrases.DataBaseProcessing.XMLProcessing;

import Phrases.DataBaseProcessing.PhraseModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;


public class XmlDomParser implements Parsable {
    private static final Logger log = LogManager.getLogger(XmlDomParser.class);


    public PhraseModel[] parseFromXML(String path) {
        try {
            File inputFile = new File(path);
            if (!inputFile.exists()) {
                log.info("file doesn't exist");
                return null;
            }
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("phrase");
            PhraseModel[] people;
            if (nList.getLength() > 0) {
                people = new PhraseModel[nList.getLength()];
            }
            else {
                people = null;
            }
            for (int temp = 0; temp < nList.getLength(); temp++) {
                if (people != null) {
                    people[temp] = parseFromXML(path, temp);
                }
            }
            return people;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;
    }


    public PhraseModel parseFromXML(String path, int index) {
        try {
            String type = null;
            String phrase = null;
            int id = 0;

            File inputFile = new File(path);
            if (!inputFile.exists()) {
                log.info("file doesn't exists");
                return null;
            }
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("phrase");
            if (index < 0 || index >= nList.getLength()) {
                return null;
            }
            Node nNode = nList.item(index);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                String idString = getXMLArgument(nNode, "id");
                if (idString != null) {
                    id = Integer.valueOf(idString);
                }
                else {
                    id = 0;
                }
                type = getXMLArgument(nNode, "type");
                phrase = getXMLArgument(nNode, "value");
            }
            return new PhraseModel(id, type, phrase);
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return null;
        }
    }



    private static String getXMLArgument(Node nNode, String attribute) {
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode;
            return eElement.getElementsByTagName(attribute).item(0).getTextContent();
        }
        return null;
    }
}