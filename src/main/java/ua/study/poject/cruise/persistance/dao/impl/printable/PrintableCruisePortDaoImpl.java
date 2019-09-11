package ua.study.poject.cruise.persistance.dao.impl.printable;

import org.apache.log4j.Logger;
import ua.study.poject.cruise.entity.printableentity.PrintableCruisePort;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.PrintableCruisePortDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrintableCruisePortDaoImpl implements PrintableCruisePortDao {

    private static final Logger LOGGER = Logger.getLogger(PrintableCruisePortDaoImpl.class);


    private static final String FIND_ALL_BY_CRUISE_ID = "SELECT cruise_ports.id AS cruise_port_id, date_in, date_out, cruise_id, port_id, country, city " +
            "FROM cruise_ports JOIN port ON port_id = port.id WHERE cruise_id = ?;";

    private Connection connection;

    public PrintableCruisePortDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<PrintableCruisePort> findAllPrintableCruisePortByCruiseId(Long cruiseId) throws GeneralCheckedException {
        List<PrintableCruisePort> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_CRUISE_ID)) {
            preparedStatement.setLong(1, cruiseId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
                list.add(createPrintableCruisePort(rs));
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return list;
    }

    private PrintableCruisePort createPrintableCruisePort(ResultSet rs) throws SQLException {
        PrintableCruisePort printableCruisePort = new PrintableCruisePort();
        printableCruisePort.setCruisePortsId(rs.getLong("cruise_port_id"));

        Timestamp TSdateIn = rs.getTimestamp("date_in");
        Timestamp TSdateOut = rs.getTimestamp("date_out");

        printableCruisePort.setDateIn(TSdateIn == null ? null : TSdateIn.toLocalDateTime());
        printableCruisePort.setDateOut(TSdateOut == null ? null : TSdateOut.toLocalDateTime());
        printableCruisePort.setCruisePortsId(rs.getLong("cruise_id"));
        printableCruisePort.setPortId(rs.getLong("port_id"));
        printableCruisePort.setCountry(rs.getString("country"));
        printableCruisePort.setCity(rs.getString("city"));
        return printableCruisePort;
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
