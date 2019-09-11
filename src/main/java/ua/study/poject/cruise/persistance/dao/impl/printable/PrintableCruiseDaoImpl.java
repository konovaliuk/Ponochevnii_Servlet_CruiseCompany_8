package ua.study.poject.cruise.persistance.dao.impl.printable;

import org.apache.log4j.Logger;
import ua.study.poject.cruise.entity.printableentity.PrintableCruise;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.PrintableCruiseDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrintableCruiseDaoImpl implements PrintableCruiseDao {

    private static final Logger LOGGER = Logger.getLogger(PrintableCruiseDao.class);

    private static final String FIND_ALL = "SELECT cruise.id, ship_id, ship_name, port_id, country, city, date_in, date_out FROM cruise " +
            "left join ship on ship_id = ship.id " +
            "left join cruise_ports on cruise_ports.cruise_id = cruise.id " +
            "left join port on cruise_ports.port_id = port.id order by cruise.id, date_in;";

    private static final String FIND_ALL_BY_SHIP_ID = "SELECT cruise.id, ship_id, ship_name, port_id, country, city, date_in, date_out FROM cruise " +
            "left join ship on ship_id = ship.id " +
            "left join cruise_ports on cruise_ports.cruise_id = cruise.id " +
            "left join port on cruise_ports.port_id = port.id  where ship.id = ? group by cruise.id;"; // order by cruise.id, date_in

    private Connection connection;

    public PrintableCruiseDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<PrintableCruise> findAllPrintableCruises() throws GeneralCheckedException {
        List<PrintableCruise> printableCruise = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery(FIND_ALL);
            while (rs.next())
                printableCruise.add(createPrintableCruise(rs));
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return printableCruise;
    }

    @Override
    public List<PrintableCruise> findAllPrintableCruisesByShipId(Long shipId) throws GeneralCheckedException {
        List<PrintableCruise> printableCruise = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_SHIP_ID)){
            preparedStatement.setLong(1, shipId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
                printableCruise.add(createPrintableCruise(rs));
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return printableCruise;
    }

    private PrintableCruise createPrintableCruise(ResultSet rs) throws SQLException {
        PrintableCruise printableCruise = new PrintableCruise();
        printableCruise.setCruiseId(rs.getLong("id"));
        printableCruise.setShipId(rs.getLong("ship_id"));
        printableCruise.setShipName(rs.getString("ship_name"));
        printableCruise.setPortId(rs.getLong("port_id"));
        printableCruise.setCountry(rs.getString("country"));
        printableCruise.setCity(rs.getString("city"));

        Timestamp TSdateIn = rs.getTimestamp("date_in");
        Timestamp TSdateOut = rs.getTimestamp("date_out");

        printableCruise.setDateIn(TSdateIn == null ? null : TSdateIn.toLocalDateTime());
        printableCruise.setDateOut(TSdateOut == null ? null : TSdateOut.toLocalDateTime());
        return printableCruise;
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
