package ua.epam.poject.cruise.persistance.dao.impl;

import org.apache.log4j.Logger;
import ua.epam.poject.cruise.entity.TicketclassBonus;
import ua.epam.poject.cruise.exceptions.GeneralCheckedException;
import ua.epam.poject.cruise.persistance.dao.TicketclassBonusDao;
import ua.epam.poject.cruise.persistance.datasource.impl.SQLExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketclassBonusDaoImpl implements TicketclassBonusDao {

    private static final Logger LOGGER = Logger.getLogger(TicketclassBonusDaoImpl.class);

    private static final String CREATE = "INSERT INTO ticketclass_bonus (ticketclass_id, ship_service_id, cruise_id) VALUES(?, ?, ?)";
    private static final String FIND_BY_ID_CRUISE_ID_TICKETCLASS = "SELECT * FROM ticketclass_bonus WHERE cruise_id = ? AND ticketclass_id = ?";
    private static final String DELETE = "DELETE FROM ticketclass_bonus WHERE id = ?";


    private Connection connection;

    public TicketclassBonusDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int create(TicketclassBonus ticketclassBonus) throws GeneralCheckedException {
        return SQLExecutor.executeInsertUpdateDelete(connection, CREATE, ticketclassBonus.getTicketclassId(), ticketclassBonus.getShipServiceId(), ticketclassBonus.getCruiseId());
    }

    @Override
    public List<TicketclassBonus> findAllByIdCruiseIdTicketclass(int idCruise, int idTicketClass) throws GeneralCheckedException {
        List<TicketclassBonus> ticketclassBonuses = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_CRUISE_ID_TICKETCLASS)){
            preparedStatement.setInt(1, idCruise);
            preparedStatement.setInt(2, idTicketClass);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
                ticketclassBonuses.add(createTicketclassBonus(rs));
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return ticketclassBonuses;
    }

    @Override
    public int delete(TicketclassBonus ticketclassBonus) throws GeneralCheckedException {
        return SQLExecutor.executeInsertUpdateDelete(connection, DELETE, ticketclassBonus.getId());
    }

    private TicketclassBonus createTicketclassBonus(ResultSet rs) throws SQLException {
        TicketclassBonus ticketclassBonus = new TicketclassBonus();
        ticketclassBonus.setId(rs.getInt("id"));
        ticketclassBonus.setTicketclassId(rs.getInt("ticketclass_id"));
        ticketclassBonus.setShipServiceId(rs.getInt("ship_service_id"));
        ticketclassBonus.setCruiseId(rs.getInt("cruise_id"));
        return ticketclassBonus;
    }

    public void close(){
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Failed to close connection ", e);
            }
        }
    }
}