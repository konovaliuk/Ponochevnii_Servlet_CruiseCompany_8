package ua.epam.poject.cruise.persistance.dao.impl;

import org.apache.log4j.Logger;
import ua.epam.poject.cruise.entity.Ticket;
import ua.epam.poject.cruise.exceptions.GeneralCheckedException;
import ua.epam.poject.cruise.persistance.dao.TicketDao;
import ua.epam.poject.cruise.persistance.datasource.impl.SQLExecutor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDaoImpl implements TicketDao {

    private static final Logger LOGGER = Logger.getLogger(TicketDaoImpl.class);

    private static final String CREATE = "INSERT INTO ticket (user_id, cruise_id, ticketclass_id) VALUES(?, ?, ?)";
    private static final String FIND_ALL = "SELECT * FROM ticket";
    private static final String FIND_BY_ID = "SELECT * FROM ticket WHERE id = ?";
    private static final String UPDATE = "UPDATE ticket SET user_id = ?, cruise_id = ?, ticketclass_id = ? WHERE id = ?";


    private Connection connection;

    public TicketDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int create(Ticket ticket) throws GeneralCheckedException {
        return SQLExecutor.executeInsertUpdateDelete(connection, CREATE, ticket.getUserId(), ticket.getCruiseId(), ticket.getTicketclassId());
    }

    @Override
    public List<Ticket> findAll() throws GeneralCheckedException {
        List<Ticket> tickets = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery(FIND_ALL);
            while (rs.next())
                tickets.add(createTicket(rs));
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return tickets;
    }

    @Override
    public Ticket findById(Long id) throws GeneralCheckedException {
        Ticket ticket = new Ticket();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)){
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                return createTicket(rs);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return ticket;
    }

    @Override
    public int update(Ticket ticket) throws GeneralCheckedException {
        return SQLExecutor.executeInsertUpdateDelete(connection, UPDATE, ticket.getUserId(), ticket.getCruiseId(), ticket.getTicketclassId(), ticket.getId());
    }

    private Ticket createTicket(ResultSet rs) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(rs.getLong("id"));
        ticket.setUserId(rs.getLong("user_id"));
        ticket.setCruiseId(rs.getLong("cruise_id"));
        ticket.setTicketclassId(rs.getLong("ticketclass_id"));
        return ticket;
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
