package ua.study.poject.cruise.persistance.dao.impl.printable;

import org.apache.log4j.Logger;
import ua.study.poject.cruise.entity.printableentity.PrintableServiceOnShip;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.PrintableServiceOnShipDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrintableServiceOnShipDaoImpl implements PrintableServiceOnShipDao {

    private static final Logger LOGGER = Logger.getLogger(PrintableServiceOnShipDaoImpl.class);

    private static final String FIND_ALL_SERVICES_BY_SHIP_ID = "SELECT ship_service.id, service.id AS service_id, service_name, payable FROM ship_service INNER JOIN service ON service.id = service_id WHERE ship_id = ?";

    private Connection connection;

    public PrintableServiceOnShipDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<PrintableServiceOnShip> findAllServicesByShipId(Long shipId) throws GeneralCheckedException {
        List<PrintableServiceOnShip> printableServiceOnShips = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SERVICES_BY_SHIP_ID)) {
            preparedStatement.setLong(1, shipId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
                printableServiceOnShips.add(createPrintableCruise(rs));
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return printableServiceOnShips;
    }

    private PrintableServiceOnShip createPrintableCruise(ResultSet rs) throws SQLException {
        PrintableServiceOnShip printableServiceOnShip = new PrintableServiceOnShip();
        printableServiceOnShip.setShipServiceId(rs.getLong("id"));
        printableServiceOnShip.setServiceId(rs.getLong("service_id"));
        printableServiceOnShip.setServiceName(rs.getString("service_name"));
        printableServiceOnShip.setPayable(rs.getInt("payable"));
        return printableServiceOnShip;
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