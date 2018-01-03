package Phrases.DataBaseProcessing;

import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Adapter for core transactions.
 * Can be realised by :
 * + MySQL
 * - SQLite
 * - XML
 * - file
 */
public interface Adapter {
    void addPhrase(String type, String phrase);
    List<PhraseModel> listPhrases();
    void deletePhrases(List<PhraseModel> list);
    void deleteId(PhraseModel model);
    void deletePhrase(PhraseModel model);
    void deleteModel(PhraseModel model);
    void shutdown();
    void create();

    static List<PhraseModel> getPhraseModels(ResultSet set, Logger log) {
        try {
            if (set != null) {
                LinkedList<PhraseModel> list = new LinkedList<PhraseModel>();
                while (set.next()) {
                    int id = set.getInt(1);
                    String type = set.getString(2);
                    String phrase = set.getString(3);
                    list.add(new PhraseModel(id, type, phrase));
                }
                set.close();
                return list;
            }
        }
        catch (SQLException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
