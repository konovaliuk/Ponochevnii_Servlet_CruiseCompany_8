package ua.study.poject.cruise.persistance.dao.impl.printable;

import org.apache.log4j.Logger;
import ua.study.poject.cruise.entity.printableentity.PrintableCruise;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.PrintableCruiseDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PrintableCruiseDaoImpl implements PrintableCruiseDao {

    private static final Logger LOGGER = Logger.getLogger(PrintableCruiseDao.class);

    private static final String FIND_ALL = "SELECT cruise.id, ship_name, country, city, date_in, date_out FROM cruise \n" +
            "left join ship on ship_id = ship.id \n" +
            "left join cruise_ports on cruise_ports.cruise_id = cruise.id \n" +
            "left join port on cruise_ports.port_id = port.id order by cruise.id, date_in;";

    private Connection connection;

    public PrintableCruiseDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<PrintableCruise> findAllForWebPage() throws GeneralCheckedException {
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
    private PrintableCruise createPrintableCruise(ResultSet rs) throws SQLException {
        PrintableCruise printableCruise = new PrintableCruise();
        printableCruise.setCruiseId(rs.getLong("id"));
        printableCruise.setShipName(rs.getString("ship_name"));
        printableCruise.setCountry(rs.getString("country"));
        printableCruise.setCity(rs.getString("city"));
        printableCruise.setDateIn(rs.getString("date_in"));
        printableCruise.setDateOut(rs.getString("date_out"));
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
