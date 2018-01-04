package Phrases.DataBaseProcessing.SQLite;

import Phrases.DataBaseProcessing.Adapter;
import Phrases.DataBaseProcessing.PhraseModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SQLiteAdapter implements Adapter {
    private static final Logger log = LogManager.getLogger(SQLiteAdapter.class);

    private SQLiteUtil util;

    private final String schemeName = SQLiteUtil.getScheme();
    private final String tableName = "phrases";

    private final String idColumn = "id";
    private final String typeColumn = "type";
    private final String phraseColumn = "phrase";


    public SQLiteAdapter() {
        util = SQLiteUtil.getInstance();
        log.info("creating MySQL Adapter");
    }

    /**
     * adding new phrase in DataBase
     * @param type - type of phrase
     * @param phrase - phrase
     */
    @Override
    public void addPhrase(String type, String phrase) {
        util.execUpdate("INSERT INTO " + schemeName + "." + tableName + "(" + typeColumn + ", " + phraseColumn + ")" + " VALUES " + "(\"" + type + "\", \"" + phrase + "\");");
        log.info("Adding new phrase : (type = " + type + ", phrase = " + phrase + ") into (scheme = " + schemeName + ", table = " + tableName + ")");
    }

    /**
     * getting all phrases from DataBase
     * @return - list of phrases
     */
    @Override
    public List<PhraseModel> listPhrases() {
        ResultSet set = util.exec("SELECT * FROM " + schemeName + "." + tableName + ";");
        log.info("Getting all rows from (scheme = " + schemeName + ", table = " + tableName);
        List<PhraseModel> list = Adapter.getPhraseModels(set, log);
        if (list != null) return list;
        return null;
    }



    /**
     * deleting list of phrases from DataBase
     * @param delList - list with phrases for deleting
     */
    @Override
    public void deletePhrases(List<PhraseModel> delList) {
        for (PhraseModel model : delList) {
            deleteId(model);
        }
        log.info("Deleting list of models");
    }

    /**
     * deleting only phrase (not id and not type checking)
     * @param model - only phrase
     */
    @Override
    public void deletePhrase(PhraseModel model) {
        util.execUpdate("DELETE FROM " + schemeName + "." + tableName + " WHERE " + phraseColumn + " = \"" + model.getPhrase() + "\";");
        log.info("Deleting (type = " + model.getType() + ", phrase = " + model.getPhrase() + ") from (scheme = " + schemeName + ", table = " + tableName + ")");
    }

    /**
     * deleting only id (not type and not phrase check)
     * @param model - only id
     */
    @Override
    public void deleteId(PhraseModel model) {
        util.execUpdate("DELETE FROM " + schemeName + "." + tableName + " WHERE " + idColumn + " = " + Integer.toString(model.getId()) + " ;");
        log.info("Deleting (id = " + model.getId() + ") from (scheme = " + schemeName + ", table = " + tableName + ")");
    }

    /**
     * deleting by type and phrase
     * @param model - type and phrase
     */
    @Override
    public void deleteModel(PhraseModel model) {
        util.execUpdate("DELETE FROM " + schemeName + "." + tableName + " WHERE " + phraseColumn + " = \"" + model.getPhrase() + "\" and " + typeColumn + " = \"" + model.getType() + "\" ;");
        log.info("Deleting (type = " + model.getType() + ", phrase = " + model.getPhrase() + ") from (scheme = " + schemeName + ", table = " + tableName + ")");
    }

    @Override
    public void create() {
        util.execUpdate("CREATE TABLE " + tableName + " ( " + idColumn + " INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, " + typeColumn + " VARCHAR (100) NOT NULL, " + phraseColumn + " VARCHAR (200) NOT NULL);");
    }


    /**
     * closing connection from DataBase
     */
    @Override
    public void shutdown() {
        util.shutdown();
        log.info("Shutdown connection");
    }
}
