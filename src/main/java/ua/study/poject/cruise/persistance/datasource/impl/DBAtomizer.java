package ua.study.poject.cruise.persistance.datasource.impl;

import org.apache.log4j.Logger;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.datasource.Atomizer;

import java.sql.Connection;
import java.sql.SQLException;

import static java.sql.Connection.TRANSACTION_SERIALIZABLE;

/**
 * This is an implementation of the Atomizer interface that works with databases
 */
public class DBAtomizer implements Atomizer {

    private static final Logger LOGGER = Logger.getLogger(DBAtomizer.class);

    private Connection connection;
    private boolean alreadyCommitted = false;

    DBAtomizer() throws GeneralCheckedException {

        try {
            connection = ConnectionPool.getConnection();
            connection.setTransactionIsolation(TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    public Connection get() {
        return connection;
    }

    @Override
    public void recordChanges() throws GeneralCheckedException {
        try {
            connection.commit();
            alreadyCommitted = true;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    @Override
    public void close() throws GeneralCheckedException {
        try {
            if (!alreadyCommitted) {
                connection.rollback();
            }
            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }
}
