package Phrases.PhrasesAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

/**
 * Class for working with DataBase (low level)
 */
public class SQLUtil {
    private static final Logger log = LogManager.getLogger(SQLUtil.class);

    // scheme name on MySQL server          CHANGE TO YOUR
    private static final String scheme = "phr";

    // url of connection on MySQL server    CHANGE TO YOUR
    private static final String url = "jdbc:mysql://localhost:3306/";

    // user and password for MySQL server   CHANGE TO YOUR
    private static final String user = "root";
    private static final String password = "root";


    private static Connection connection;
    private static SQLUtil instance = null;

    private SQLUtil() {
        try {
            log.info("Connecting to MySQL : (url = " + url + ", user = " + user + ", password = " + password + ").......");
            connection = DriverManager.getConnection(url, user, password);
            log.info("Connection successfully");
        }
        catch (SQLException e) {
            e.printStackTrace();
            log.error("Connection failed - " + e.getMessage());
        }
    }

    /**
     * executing SQL request for getting result
     * @param sqlRequest - SQL request
     * @return - result of request
     */
    public ResultSet exec(String sqlRequest) {
        try {
            Statement statement = connection.createStatement();
            statement.closeOnCompletion();
            log.info("executing query = \"" + sqlRequest + "\"....");
            return statement.executeQuery(sqlRequest);
        }
        catch (SQLException e) {
            e.printStackTrace();
            log.error("can't execute query = \"" + sqlRequest + "\"");
        }
        return null;
    }

    /**
     * executing SQL request for setting and appending data
     * @param sqlRequest - SQL request for executing
     * @return - nothing
     */
    public int execUpdate(String sqlRequest) {
        try {
            Statement statement = connection.createStatement();
            statement.closeOnCompletion();
            log.info("executing query = \"" + sqlRequest + "\"....");
            return statement.executeUpdate(sqlRequest);
        }
        catch (SQLException e) {
            e.printStackTrace();
            log.error("can't execute query = \"" + sqlRequest + "\"");
        }
        return -1;
    }

    /**
     * close connection with DataBase
     */
    public void shutdown() {
        try {
            log.info("closing connection to DataBase....");
            connection.close();
            log.info("connection was closed");
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("can't close connetion to DataBase");
        }
    }


    /**
     * SingleTone pattern
     * @return one copy of SQLUtil
     */
    public static SQLUtil getInstance() {
        if (instance != null) {
            return instance;
        }
        else {
            return new SQLUtil();
        }
    }

    public static String getScheme() {
        return scheme;
    }
}
