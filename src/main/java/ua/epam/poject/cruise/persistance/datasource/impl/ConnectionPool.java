package ua.epam.poject.cruise.persistance.datasource.impl;

import org.apache.log4j.Logger;
import ua.epam.poject.cruise.exceptions.GeneralCheckedException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private ConnectionPool() {
    }

    public static Context ctx;
    private static DataSource ds;

    static {
        try {
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mypool");
        } catch (NamingException e) {
            LOGGER.error(e);
        }
    }

    public static Connection getConnection() throws GeneralCheckedException {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Failed to get Connection from Connection Pool ", e);
        }
    }





/*


    static public Connection getConnection() throws SQLException {
        ResourceBundle rb = ResourceBundle.getBundle("database");
        String url = rb.getString("url");
        String user = rb.getString("login");
        String password = rb.getString("password");
        return DriverManager.getConnection(url, user, password);
    }
*/

//    public void close() {
//        if (connection != null)
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                System.out.println("Не удалось закрыть connection");
//            }
//    }

}
