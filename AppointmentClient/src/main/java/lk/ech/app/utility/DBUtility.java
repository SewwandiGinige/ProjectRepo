package lk.ech.app.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by se-9 on 6/20/2017.
 */
public class DBUtility {
    //private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(DBUtility.class);

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //static final String DB_URL = "jdbc:mysql://10.10.49.110/ech_astro"; // local
    static final String DB_URL = "jdbc:mysql://172.16.147.11/ech_astro"; //live
    static final String USERNAME = "root";
    static final String PASSWORD = "astro";


    public static Connection getDBConnection() {

        Connection dbConnection = null;

        try {

            Class.forName(JDBC_DRIVER);

        } catch (ClassNotFoundException e) {
            //LOG.error("<<<<<<<<<<<<<<<<<<<<<<<<exception innnnnnnnnn Classs driverrrrrrrrrrrrrrr>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+e.toString());


        }

        try {

            dbConnection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            return dbConnection;

        } catch (SQLException e) {

            //LOG.error("<<<<<<<<<<<<<<<<<<<<<<<<exception innnnnnnnnn db Connectionnnnnnnnnn>>>>>>>>>>>>>>>>>> "+e.toString());

        }

        return dbConnection;

    }
}
