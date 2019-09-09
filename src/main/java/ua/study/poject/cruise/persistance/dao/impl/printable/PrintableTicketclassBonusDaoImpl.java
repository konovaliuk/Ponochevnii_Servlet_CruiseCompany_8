package ua.study.poject.cruise.persistance.dao.impl.printable;

import org.apache.log4j.Logger;
import ua.study.poject.cruise.entity.Ticketclass;
import ua.study.poject.cruise.entity.printableentity.PrintableServiceOnShip;
import ua.study.poject.cruise.entity.printableentity.PrintableTicketclassBonus;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.PrintableTicketclassBonusDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrintableTicketclassBonusDaoImpl implements PrintableTicketclassBonusDao {

    private static final Logger LOGGER = Logger.getLogger(PrintableTicketclassBonusDaoImpl.class);

    private static final String FIND_ALL_BY_CRUISE_ID_TICKETCLASS_ID = "SELECT ticketclass_bonus.id AS ticketclass_bonus_id, cruise_id, ship_service_id, service_id, service_name, payable, ticketclass_id, ticketclass_name " +
            "FROM ticketclass_bonus " +
            "JOIN ship_service ON ship_service_id = ship_service.id " +
            "JOIN service ON service_id = service.id " +
            "JOIN ticketclass ON ticketclass_id = ticketclass.id WHERE cruise_id = ? AND ticketclass_id = ?;";

    private Connection connection;

    public PrintableTicketclassBonusDaoImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<PrintableTicketclassBonus> getAllBonusesByCruiseIdTicketclassId(Long cruiseId, Long ticketclassId) throws GeneralCheckedException {
        List<PrintableTicketclassBonus> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_CRUISE_ID_TICKETCLASS_ID)) {
            preparedStatement.setLong(1, cruiseId);
            preparedStatement.setLong(2, ticketclassId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
                list.add(createPrintableTicketclassBonus(rs));
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return list;
    }

    private PrintableTicketclassBonus createPrintableTicketclassBonus(ResultSet rs) throws SQLException {
        PrintableTicketclassBonus ticketclassBonus = new PrintableTicketclassBonus();
        ticketclassBonus.setTicketClassBonusId(rs.getLong("ticketclass_bonus_id"));
        ticketclassBonus.setCruiseId(rs.getLong("cruise_id"));

        PrintableServiceOnShip printableServiceOnShip = new PrintableServiceOnShip();
        printableServiceOnShip.setId(rs.getLong("ship_service_id"));
        printableServiceOnShip.setServiceId(rs.getLong("service_id"));
        printableServiceOnShip.setServiceName(rs.getString("service_name"));
        printableServiceOnShip.setPayable(rs.getInt("payable"));
        ticketclassBonus.setPrintableServiceOnShip(printableServiceOnShip);

        Ticketclass ticketclass = new Ticketclass();
        ticketclass.setId(rs.getLong("ticket_class_id"));
        ticketclass.setTicketclassName(rs.getString("ticket_class_name"));
        ticketclassBonus.setTicketclass(ticketclass);

        return ticketclassBonus;
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
