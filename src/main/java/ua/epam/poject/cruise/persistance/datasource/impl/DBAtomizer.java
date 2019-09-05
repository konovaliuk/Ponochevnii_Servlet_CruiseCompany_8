package ua.epam.poject.cruise.persistance.datasource.impl;

import org.apache.log4j.Logger;
import ua.epam.poject.cruise.exceptions.GeneralCheckedException;
import ua.epam.poject.cruise.persistance.datasource.Atomizer;
import java.sql.Connection;
import java.sql.SQLException;

import static java.sql.Connection.TRANSACTION_SERIALIZABLE;

public class DBAtomizer implements Atomizer {

    private static final Logger LOGGER = Logger.getLogger(DBAtomizer.class);

    private Connection connection;
    private int tempTransactionIsolationLevel;
    private boolean tempAutoCommitState;


    public DBAtomizer() throws GeneralCheckedException {
        try {
            connection = ConnectionPool.getConnection();

            tempTransactionIsolationLevel = connection.getTransactionIsolation();
            tempAutoCommitState = connection.getAutoCommit();

            connection.setTransactionIsolation(TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    public Connection get(){
        return connection;
    }

    @Override
    public void recordChanges() throws GeneralCheckedException {
        try {
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    @Override
    public void close() throws GeneralCheckedException {
        try {
            connection.rollback();
            connection.setAutoCommit(tempAutoCommitState);
            connection.setTransactionIsolation(tempTransactionIsolationLevel);
            connection.close();
        } catch (SQLException e){
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }



}
