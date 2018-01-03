package Phrases.DataBaseProcessing.MySQL;
import Phrases.DataBaseProcessing.SqlUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

/**
 * Class for working with DataBase (low level)
 */
public class MsqlUtil extends SqlUtil{
    private static final Logger log = LogManager.getLogger(MsqlUtil.class);

    // scheme name on MySQL server          CHANGE TO YOUR
    private static final String scheme = "phr";

    // url of connection on MySQL server    CHANGE TO YOUR
    private static final String url = "jdbc:mysql://localhost:3306/";

    // user and password for MySQL server   CHANGE TO YOUR
    private static final String user = "root";
    private static final String password = "root";


    private static Connection connection;
    private static MsqlUtil instance = null;

    private MsqlUtil() {
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
    @Override
    public ResultSet exec(String sqlRequest) {
        log.info("executing MySQL query = \"" + sqlRequest + "\"....");
        return super.exec(sqlRequest);
    }

    /**
     * executing SQL request for setting and appending data
     * @param sqlRequest - SQL request for executing
     * @return - nothing
     */
    @Override
    public int execUpdate(String sqlRequest) {
        log.info("executing MySQL query = \"" + sqlRequest + "\"....");
        return super.execUpdate(sqlRequest);
    }

    /**
     * close connection with DataBase
     */
    public void shutdown() {
        try {
            log.info("closing MySQL connection to DataBase....");
            connection.close();
            log.info("MySQL connection was closed");
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("can't close MySQL connection to DataBase");
        }
    }


    /**
     * SingleTone pattern
     * @return one copy of SQLUtil
     */
    public static MsqlUtil getInstance() {
        if (instance != null) {
            return instance;
        }
        else {
            return new MsqlUtil();
        }
    }

    public static String getScheme() {
        return scheme;
    }
}
