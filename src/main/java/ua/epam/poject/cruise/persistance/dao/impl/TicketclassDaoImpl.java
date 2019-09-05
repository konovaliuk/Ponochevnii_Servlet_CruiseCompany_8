package ua.epam.poject.cruise.persistance.dao.impl;

import org.apache.log4j.Logger;
import ua.epam.poject.cruise.entity.Ticketclass;
import ua.epam.poject.cruise.exceptions.GeneralCheckedException;
import ua.epam.poject.cruise.persistance.dao.TicketclassDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketclassDaoImpl implements TicketclassDao {

    private static final Logger LOGGER = Logger.getLogger(TicketclassDaoImpl.class);

    private static final String FIND_ALL = "SELECT * FROM ticketclass";
    private static final String FIND_BY_ID = "SELECT * FROM ticketclass WHERE id = ?";
    private static final String FIND_BY_TICKET_CLASS_NAME = "SELECT * FROM ticketclass WHERE ticketclass_name = ?";

    private Connection connection;

    public TicketclassDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Ticketclass> findAll() throws GeneralCheckedException {
        List<Ticketclass> ticketclasses = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(FIND_ALL);
            while (rs.next())
                ticketclasses.add(createTicketclass(rs));
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return ticketclasses;
    }

    @Override
    public Ticketclass findById(Long id) throws GeneralCheckedException {
        Ticketclass ticketclass = new Ticketclass();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                return createTicketclass(rs);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return ticketclass;
    }

    @Override
    public Ticketclass findByTicketclassName(String className) throws GeneralCheckedException {
        Ticketclass ticketclass = new Ticketclass();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_TICKET_CLASS_NAME)) {
            preparedStatement.setString(1, className);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                return createTicketclass(rs);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return ticketclass;
    }

    private Ticketclass createTicketclass(ResultSet rs) throws SQLException {
        Ticketclass ticketclass = new Ticketclass();
        ticketclass.setId(rs.getLong("id"));
        ticketclass.setTicketclassName(rs.getString("ticketclass_name"));
        return ticketclass;
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
