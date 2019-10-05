package ua.study.poject.cruise.persistance.dao.impl;

import org.apache.log4j.Logger;
import ua.study.poject.cruise.entity.Ticket;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.TicketDao;
import ua.study.poject.cruise.persistance.datasource.impl.SQLExecutor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDaoImpl implements TicketDao {

    private static final Logger LOGGER = Logger.getLogger(TicketDaoImpl.class);

    private static final String CREATE = "INSERT INTO ticket (user_id, cruise_id, ticketclass_id) VALUES(?, ?, ?)";
    private static final String FIND_BY_USER_ID = "SELECT * FROM ticket WHERE user_id = ?";

    private Connection connection;

    public TicketDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int create(Ticket ticket) throws GeneralCheckedException {
        return SQLExecutor.executeInsertUpdateDelete(connection, CREATE, ticket.getUserId(), ticket.getCruiseId(), ticket.getTicketclassId());
    }

    @Override
    public List<Ticket> findTicketByUserId(Long id) throws GeneralCheckedException {
        List<Ticket> list = new ArrayList<>();
        Ticket ticket = new Ticket();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USER_ID)){
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                list.add(createTicket(rs));
            }
       } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return list;
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
