package Phrases;

import Phrases.PhrasesAdapter.PhraseModel;
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
}
