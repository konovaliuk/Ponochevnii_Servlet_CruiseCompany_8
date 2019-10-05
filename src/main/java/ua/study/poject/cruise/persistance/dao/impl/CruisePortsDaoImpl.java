package ua.study.poject.cruise.persistance.dao.impl;

import org.apache.log4j.Logger;
import ua.study.poject.cruise.entity.CruisePorts;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.CruisePortsDao;
import ua.study.poject.cruise.persistance.datasource.impl.SQLExecutor;

import java.sql.Connection;
import java.sql.SQLException;

public class CruisePortsDaoImpl implements CruisePortsDao {

    private static final Logger LOGGER = Logger.getLogger(CruisePortsDaoImpl.class);

    private static final String CREATE = "INSERT INTO cruise_ports (date_in, date_out, cruise_id, port_id) VALUES(?, ?, ?, ?)";
    private static final String CREATE_START_PORT = "INSERT INTO cruise_ports (date_out, cruise_id, port_id) VALUES(?, ?, ?)";
    private static final String CREATE_FINISH_PORT = "INSERT INTO cruise_ports (date_in, cruise_id, port_id) VALUES(?, ?, ?)";


    private Connection connection;

    public CruisePortsDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int create(CruisePorts cruisePorts) throws GeneralCheckedException {
        if (cruisePorts.getDateIn() == null) {
            return SQLExecutor.executeInsertUpdateDelete(connection, CREATE_START_PORT, cruisePorts.getDateOut(), cruisePorts.getCruiseId(), cruisePorts.getPortId());
        }
        if (cruisePorts.getDateOut() == null) {
            return SQLExecutor.executeInsertUpdateDelete(connection, CREATE_FINISH_PORT, cruisePorts.getDateIn(), cruisePorts.getCruiseId(), cruisePorts.getPortId());
        }
        return SQLExecutor.executeInsertUpdateDelete(connection, CREATE, cruisePorts.getDateIn(), cruisePorts.getDateOut(), cruisePorts.getCruiseId(), cruisePorts.getPortId());
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Failed to close connection ", e);
            }
        }
    }
}
