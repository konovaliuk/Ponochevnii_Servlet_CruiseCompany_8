package ua.study.poject.cruise.persistance.dao.impl;

import org.apache.log4j.Logger;
import ua.study.poject.cruise.entity.Ticketclass;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.TicketclassDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketclassDaoImpl implements TicketclassDao {

    private static final Logger LOGGER = Logger.getLogger(TicketclassDaoImpl.class);

    private static final String FIND_ALL = "SELECT * FROM ticketclass";
    private static final String FIND_BY_ID = "SELECT * FROM ticketclass WHERE id = ?";
    private static final String FIND_BY_TICKET_CLASS_NAME = "SELECT * FROM ticketclass WHERE ticketclass_name = ?";

    private Connection connection;

    private static Map<Long, Ticketclass> ticketclasses = null;

    public TicketclassDaoImpl(Connection connection) {
        this.connection = connection;
        if (ticketclasses == null)
            fillTicketclassesMap();
    }

    @Override
    public List<Ticketclass> findAll() {
        return new ArrayList<>(ticketclasses.values());
    }

    private synchronized void fillTicketclassesMap() {
        if (ticketclasses == null) {
            ticketclasses = new HashMap<>();
            try (Statement statement = connection.createStatement()) {
                ResultSet rs = statement.executeQuery(FIND_ALL);
                Ticketclass tempTicketclass;
                while (rs.next()) {
                    tempTicketclass = createTicketclass(rs);
                    ticketclasses.put(tempTicketclass.getId(), tempTicketclass);
                }
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
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
