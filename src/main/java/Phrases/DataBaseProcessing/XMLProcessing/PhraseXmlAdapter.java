package Phrases.DataBaseProcessing.XMLProcessing;

import Phrases.DataBaseProcessing.PhraseModel;

import static Phrases.DataBaseProcessing.XMLProcessing.XmlConstants.*;

public class PhraseXmlAdapter {

    public static String parseToXML(PhraseModel model, int mask) {
        StringBuilder result = new StringBuilder();
        if ((mask & XMLBEGIN) == XMLBEGIN){
            result.append(XMLBegin());
        }
        if (model != null) {
            result.append("    <phrase>\n");
            result.append("        <id>").append(model.getId()).append("</id>\n");
            result.append("        <type>").append(model.getType()).append("</type>\n");
            result.append("        <value>").append(model.getPhrase()).append("</value>\n");
            result.append("    </phrase>\n");
        }
        if ((mask & XMLEND) == XMLEND){
            result.append(XMLEnd());
        }
        return result.toString();
    }


    public static String parseToXML(PhraseModel[] models, int mask) {
        StringBuilder result = new StringBuilder();
        if ((mask & XMLBEGIN) == XMLBEGIN) {
            result.append(XMLBegin());
        }
        if (models != null) {
            for (PhraseModel model : models) {
                if (model != null) {
                    result.append(parseToXML(model, XMLNONE));
                }
            }
        }
        if ((mask & XMLEND) == XMLEND) {
            result.append(XMLEnd());
        }
        return result.toString();
    }
}
