package Phrases.DataBaseProcessing.XMLProcessing;

import Phrases.DataBaseProcessing.Adapter;
import Phrases.DataBaseProcessing.PhraseModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static Phrases.DataBaseProcessing.XMLProcessing.XMLConstants.XMLBEGIN;
import static Phrases.DataBaseProcessing.XMLProcessing.XMLConstants.XMLEND;
import static Phrases.DataBaseProcessing.XMLProcessing.XMLConstants.XMLNONE;

public class XMLAdapter implements Adapter{

    private Parsable parser;

    private String path;
    private String archivePath;

    private static final String prefix = "temp";

    /**
     * Creating XML Editor for XML file with this path, uses this parser and save result into this archive
     *
     * @param path        the path of the XML file for store and editing File
     * @param parser      the Object of parser for reading XML file
     * @param archivePath the path of archive for zipping XML file
     */
    public XMLAdapter(String path, Parsable parser, String archivePath) {
        this.path = path;
        this.parser = parser;
        this.archivePath = archivePath;
    }


    public void add(PhraseModel phrase, int index) {
        PhraseModel phrases[] = parser.parseFromXML(path);
        if (phrases != null) {
            if (index < 0 || index > phrases.length) {
                return;
            }
        }
        try {
            FileWriter fileWriter = new FileWriter(path, false);
            fileWriter.write(PhraseXmlAdapter.parseToXML(null, XMLBEGIN));
            PhraseXmlAdapter adapter = new PhraseXmlAdapter(null);
            if (phrases != null && index <= phrases.length) {
                for (int i = 0; i < index; i++) {
                    adapter.setModel(phrases[i]);
                    fileWriter.append(adapter.parseToXML(XMLNONE));
                }
            }
            adapter.setModel(phrase);
            fileWriter.append(adapter.parseToXML(XMLNONE));
            if (phrases != null && index < phrases.length) {
                for (int i = index; i < phrases.length; i++) {
                    adapter.setModel(phrases[i]);
                    fileWriter.append(adapter.parseToXML(XMLNONE));
                }
            }
            fileWriter.append(PhraseXmlAdapter.parseToXML(null, XMLEND));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addInBegin(PhraseModel phrase) {
        this.add(phrase, 0);
    }

    /**
     * adding this Person in the end of the XML file
     *
     * @param phrase the Person, that was been adding in the end of the XML file
     */
    public void addInEnd(PhraseModel phrase) {
        if (parser.parseFromXML(path) != null) {
            this.add(phrase, parser.parseFromXML(path).length);
        } else {
            this.add(phrase, 0);
        }
    }

    /**
     * deleting Person in XML file on the this position
     *
     * @param index the index of Person for deleting
     * @return true - if deleting ending successfully
     * false - if deleting don't ending successfully
     */
    public boolean delete(int index) {
        try {
            PhraseModel phrases[] = parser.parseFromXML(path);
            PhraseXmlAdapter adapter = new PhraseXmlAdapter(null);
            if (phrases != null && index >= 0 && index < phrases.length) {
                FileWriter fileWriter = new FileWriter(prefix + path, false);
                fileWriter.write(PhraseXmlAdapter.parseToXML(null, XMLBEGIN));
                if (index >= phrases.length) {
                    return false;
                }
                for (int i = 0; i < index; i++) {
                    adapter.setModel(phrases[i]);
                    fileWriter.append(adapter.parseToXML(XMLNONE));
                }
                for (int i = index + 1; i < phrases.length; i++) {
                    adapter.setModel(phrases[i]);
                    fileWriter.append(adapter.parseToXML(XMLNONE));
                }
                fileWriter.append(PhraseXmlAdapter.parseToXML(null, XMLEND));
                fileWriter.flush();
                fileWriter.close();

                File file = new File(prefix + path);
                File oldFile = new File(path);
                if (oldFile.delete()) {
                    if (!file.renameTo(oldFile)) {
                        System.out.println("Can't rename");
                    }
                } else {
                    System.out.println("Can't delete");
                }
                return true;
            } else {
                if (phrases == null) {
                    File file = new File(path);
                    file.delete();
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void edit(PhraseModel phrase, int index) {
        if (this.delete(index)) {
            this.add(phrase, index);
        }
    }

    /**
     * getting Person from file on this index
     *
     * @param index the index of Person, that was getting
     * @return the Person on this position in File
     */
    public PhraseModel get(int index) {
        return parser.parseFromXML(path, index);
    }

    /**
     * getting arrayList from XML file
     *
     * @return arrayList of all Users in XML file
     */
    public LinkedList<PhraseModel> getList() {
        LinkedList<PhraseModel> list = new LinkedList<PhraseModel>();
        PhraseModel phrase;
        int index = 0;
        do {
            phrase = parser.parseFromXML(path, index++);
            if (phrase != null) {
                list.add(phrase);
            }
        } while (phrase != null);
        return list;
    }


    public int findIndexByPhrase(String otherPhrase) {
        PhraseModel phrase;
        int index = 0;
        do {
            phrase = parser.parseFromXML(path, index);
            if (phrase != null) {
                if (phrase.getPhrase().equals(otherPhrase)) {
                    return index;
                }
            }
            index++;
        } while (phrase != null);
        return -1;
    }

    public int findIndexByType(String otherType) {
        PhraseModel phrase;
        int index = 0;
        do {
            phrase = parser.parseFromXML(path, index);
            if (phrase != null) {
                if (phrase.getType().equals(otherType)) {
                    return index;
                }
            }
            index++;
        } while (phrase != null);
        return -1;
    }


    /**
     * the count of Users into this XML File
     *
     * @return count of Users into XML File
     */
    public int length() {
        PhraseModel[] phrases = parser.parseFromXML(path);
        if (phrases != null) {
            return phrases.length;
        } else {
            return 0;
        }
    }

    /**
     * transform into String all Users from XMl file
     *
     * @return the result of transform
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        PhraseModel[] phrases = parser.parseFromXML(path);
        if (phrases != null) {
            for (PhraseModel phrase : parser.parseFromXML(path)) {
                if (phrase != null) {
                    s.append(phrase.toString());
                }
            }
        }
        return s.toString();
    }



    /**
     * getting the current path of the XML file
     *
     * @return the current path of the XML file
     */
    public String getPath() {
        return path;
    }

    /**
     * setting the current path of the XML file
     *
     * @param path the current path of the XML file
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * setting the new parser of the XML file
     *
     * @param parser new parser of the XML file
     */
    public void setParser(Parsable parser) {
        this.parser = parser;
    }

    /**
     * getting path of zip archive of this XML file
     *
     * @return zip path of the XML file
     */
    public String getArchivePath() {
        return archivePath;
    }

    /**
     * setting path of zip archive of this XML file
     *
     * @param archivePath zip path of the XML file
     */
    public void setArchivePath(String archivePath) {
        this.archivePath = archivePath;
    }

    @Override
    public void addPhrase(String type, String phrase) {
        this.addInEnd(new PhraseModel(type, phrase));
    }

    @Override
    public List<PhraseModel> listPhrases() {
        return getList();
    }

    @Override
    public void deletePhrases(List<PhraseModel> list) {
        for (PhraseModel model : list) {
            delete(findIndexByPhrase(model.getPhrase()));
        }
    }

    @Override
    public void deleteId(PhraseModel model) {

    }

    @Override
    public void deletePhrase(PhraseModel model) {
        delete(findIndexByPhrase(model.getPhrase()));
    }

    @Override
    public void deleteModel(PhraseModel model) {
        deletePhrase(model);
    }

    @Override
    public void shutdown() {

    }

    @Override
    public void create() {
        try {
            FileWriter fileWriter = new FileWriter(path, false);
            fileWriter.write(PhraseXmlAdapter.parseToXML(null, XMLBEGIN));
            fileWriter.write(PhraseXmlAdapter.parseToXML(null, XMLEND));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
