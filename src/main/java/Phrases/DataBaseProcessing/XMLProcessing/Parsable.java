package Phrases.DataBaseProcessing.XMLProcessing;

import Phrases.DataBaseProcessing.PhraseModel;

public interface Parsable {

    PhraseModel[] parseFromXML(String path);


    PhraseModel parseFromXML(String path, int index);

}