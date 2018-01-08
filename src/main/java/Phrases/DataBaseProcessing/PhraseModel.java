package Phrases.DataBaseProcessing;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PhraseModel {
    private static final Logger log = LogManager.getLogger(PhraseModel.class);

    private int id;
    private String type;
    private String phrase;

    public PhraseModel(int id, String type, String phrase) {
        this.id = id;
        this.type = type;
        this.phrase = phrase;
    }

    public PhraseModel(String type, String phrase) {
        this(0, type, phrase);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhraseModel that = (PhraseModel) o;

        if (id != that.id) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (phrase != null ? !phrase.equals(that.phrase) : that.phrase != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (phrase != null ? phrase.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return id + " : " + type + " : " + phrase;
    }
}
