package Phrases.DataBaseProcessing.XMLProcessing;

import Phrases.DataBaseProcessing.PhraseModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;


/**
 * Created by Alexey on 15.04.2017.
 * The DOM parser for XML file. Uses as tool in XMLEditor
 */
public class XmlDomParser implements Parsable {

    /**
     * Parse XML file for getting array of people, saving in file
     * @param path the path of xml file for parse
     * @return the array of people in xml file (path)
     */
    public PhraseModel[] parseFromXML(String path) {
        try {
            File inputFile = new File(path);
            if (!inputFile.exists()){
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
        }
        return null;
    }

    /**
     * Parse XML file for getting user with @param index from this xml file
     * @param path the path of xml file for parsing
     * @param index the index of getting user
     * @return the user with @param index
     */
    public PhraseModel parseFromXML(String path, int index) {
        try {
            String type = null;
            String phrase = null;

            File inputFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("phrase");
            if (index < 0 || index >= nList.getLength()){
                return null;
            }
            Node nNode = nList.item(index);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                type = getXMLArgument(nNode, "type");
                phrase = getXMLArgument(nNode, "value");
            }
            return new PhraseModel(type, phrase);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     * private method for getting value of attribute from Node in XML file
     * @param nNode the Node in XML file
     * @param attribute the name of attribute to getting it value
     * @return the value of this argument
     */
    private static String getXMLArgument(Node nNode, String attribute) {
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode;
            return eElement.getElementsByTagName(attribute).item(0).getTextContent();
        }
        return null;
    }
}