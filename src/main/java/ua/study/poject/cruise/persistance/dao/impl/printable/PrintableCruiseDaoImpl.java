package ua.study.poject.cruise.persistance.dao.impl.printable;

import org.apache.log4j.Logger;
import ua.study.poject.cruise.entity.printableentity.PrintableCruise;
import ua.study.poject.cruise.entity.printableentity.PrintableCruisePort;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.PrintableCruiseDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrintableCruiseDaoImpl implements PrintableCruiseDao {

    private static final Logger LOGGER = Logger.getLogger(PrintableCruiseDao.class);

    private static final String FIND_ALL = "SELECT cruise.id, ship_id, ship_name, price_first_class, price_second_class, price_third_class, price_fourth_class " +
            "FROM cruise join ship on ship_id = ship.id group by cruise.id;";

    private static final String FIND_ALL_BY_SHIP_ID = "SELECT cruise.id, ship_id, ship_name, price_first_class, price_second_class, price_third_class, price_fourth_class " +
            "FROM cruise join ship on ship_id = ship.id where ship.id = ? group by cruise.id;";

    private Connection connection;

    public PrintableCruiseDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<PrintableCruise> findAllPrintableCruisesWithoutPorts() throws GeneralCheckedException {
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
    public List<PrintableCruise> findAllPrintableCruisesWithoutPortsByShipId(Long shipId) throws GeneralCheckedException {
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
        printableCruise.setPriceFirstClass(rs.getDouble("price_first_class"));
        printableCruise.setPriceSecondClass(rs.getDouble("price_second_class"));
        printableCruise.setPriceThirdClass(rs.getDouble("price_third_class"));
        printableCruise.setPriceFourthClass(rs.getDouble("price_fourth_class"));

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
