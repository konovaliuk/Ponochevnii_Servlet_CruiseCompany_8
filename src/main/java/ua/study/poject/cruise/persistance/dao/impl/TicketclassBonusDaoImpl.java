package ua.study.poject.cruise.persistance.dao.impl;

import org.apache.log4j.Logger;
import ua.study.poject.cruise.entity.TicketclassBonus;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.TicketclassBonusDao;
import ua.study.poject.cruise.persistance.datasource.impl.SQLExecutor;

import java.sql.Connection;
import java.sql.SQLException;

public class TicketclassBonusDaoImpl implements TicketclassBonusDao {

    private static final Logger LOGGER = Logger.getLogger(TicketclassBonusDaoImpl.class);

    private static final String CREATE = "INSERT INTO ticketclass_bonus (ticketclass_id, ship_service_id, cruise_id) VALUES(?, ?, ?)";
    private static final String DELETE = "DELETE FROM ticketclass_bonus WHERE id = ?";
    private static final String DELETE_BY_SHIPSERVICE_ID = "DELETE FROM ticketclass_bonus WHERE ship_service_id = ?";

    private Connection connection;

    public TicketclassBonusDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int create(TicketclassBonus ticketclassBonus) throws GeneralCheckedException {
        return SQLExecutor.executeInsertUpdateDelete(connection, CREATE, ticketclassBonus.getTicketclassId(), ticketclassBonus.getShipServiceId(), ticketclassBonus.getCruiseId());
    }

    @Override
    public int deleteByShipserviceId(Long id) throws GeneralCheckedException {
        return SQLExecutor.executeInsertUpdateDelete(connection, DELETE_BY_SHIPSERVICE_ID, id);
    }

    @Override
    public int deleteById(Long id) throws GeneralCheckedException {
        return SQLExecutor.executeInsertUpdateDelete(connection, DELETE, id);
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
