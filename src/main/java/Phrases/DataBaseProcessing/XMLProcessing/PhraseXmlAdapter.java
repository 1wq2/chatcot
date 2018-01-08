package Phrases.DataBaseProcessing.XMLProcessing;

import Phrases.DataBaseProcessing.PhraseModel;
import Phrases.DataBaseProcessing.XMLProcessing.XMLConstants;

import static Phrases.DataBaseProcessing.XMLProcessing.XMLConstants.*;

public class PhraseXmlAdapter {

    private PhraseModel model;

    public PhraseXmlAdapter(PhraseModel model) {
        this.model = model;
    }


    public PhraseModel getModel() {
        return model;
    }

    public void setModel(PhraseModel model) {
        this.model = model;
    }

    public String parseToXML(int mask) {
        String result = "";
        if ((mask & XMLBEGIN) == XMLBEGIN){
            result += XMLBegin();
        }
        result += ("  <phrase>\n");
        result += ("      <type>" + this.model.getType() + "</type>\n");
        result += ("      <value>" + this.model.getPhrase() + "</value>\n");
        result += ("  </phrase>\n");
        if ((mask & XMLEND) == XMLEND){
            result += XMLEnd();
        }
        return result;
    }


    public static String parseToXML(PhraseModel[] models, int mask) {
        StringBuilder result = new StringBuilder();
        if ((mask & XMLBEGIN) == XMLBEGIN) {
            result.append(XMLBegin());
        }
        if (models != null) {
            for (PhraseModel model : models) {
                if (model != null) {
                    result.append((new PhraseXmlAdapter(model)).parseToXML(XMLNONE));
                }
            }
        }
        if ((mask & XMLEND) == XMLEND) {
            result.append(XMLEnd());
        }
        return result.toString();
    }
}
