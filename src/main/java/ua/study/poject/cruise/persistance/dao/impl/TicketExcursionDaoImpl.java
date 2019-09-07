package ua.study.poject.cruise.persistance.dao.impl;

import org.apache.log4j.Logger;
import ua.study.poject.cruise.entity.TicketExcursion;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.TicketExcursionDao;
import ua.study.poject.cruise.persistance.datasource.impl.SQLExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketExcursionDaoImpl implements TicketExcursionDao {

    private static final Logger LOGGER = Logger.getLogger(TicketExcursionDaoImpl.class);


    private static final String CREATE = "INSERT INTO ticket_excursion (ticket_id, excurision_id) VALUES(?, ?)";
    private static final String FIND_BY_TICKET_ID = "SELECT * FROM ticket_excursion WHERE ticket_id = ?";

    private Connection connection;

    public TicketExcursionDaoImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public int create(Long ticketId, Long excursionId) throws GeneralCheckedException {
        return SQLExecutor.executeInsertUpdateDelete(connection, CREATE, ticketId, excursionId);
    }

    @Override
    public List<TicketExcursion> findByTicketId(Long id) throws GeneralCheckedException {
        List<TicketExcursion> ticketExcursions = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_TICKET_ID)){
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
                ticketExcursions.add(createTicketExcursion(rs));
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return ticketExcursions;
    }

    private TicketExcursion createTicketExcursion(ResultSet rs) throws SQLException {
        TicketExcursion ticketExcursion = new TicketExcursion();
        ticketExcursion.setId(rs.getLong("id"));
        ticketExcursion.setTicketId(rs.getLong("ticket_id"));
        ticketExcursion.setExcurisionId(rs.getLong("excurision_id"));
        return ticketExcursion;
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
