package ua.study.poject.cruise.persistance.datasource.impl;

import org.apache.log4j.Logger;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * This singletone class encapsulates the connection to the Connection Pool of Tomcat
 */
public class ConnectionPool {

    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private ConnectionPool() {
    }

    private static DataSource ds;

    static {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mypool");
        } catch (NamingException e) {
            LOGGER.error(e);
        }
    }

    /**
     * This method retrieves the connection instance from the Connection Pool of the Tomcat server
     *
     * @return Connection from connectionPool of the Tomcat
     * @throws GeneralCheckedException
     */
    public static Connection getConnection() throws GeneralCheckedException {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Failed to get Connection from Connection Pool ", e);
        }
    }

}
