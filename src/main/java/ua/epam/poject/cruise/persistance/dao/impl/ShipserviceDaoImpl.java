package ua.epam.poject.cruise.persistance.dao.impl;

import org.apache.log4j.Logger;
import ua.epam.poject.cruise.entity.Service;
import ua.epam.poject.cruise.entity.Shipservice;
import ua.epam.poject.cruise.exceptions.GeneralCheckedException;
import ua.epam.poject.cruise.persistance.dao.ShipserviceDao;
import ua.epam.poject.cruise.persistance.datasource.impl.SQLExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShipserviceDaoImpl implements ShipserviceDao {

    private static final Logger LOGGER = Logger.getLogger(ShipserviceDaoImpl.class);
// id, ship_id, payable, service_id
    private static final String CREATE = "INSERT INTO ship_service (ship_id, payable, service_id) VALUES(?, ?, ?)";
    private static final String FIND_ALL_ID_BY_SHIPID_SERVICEID = "SELECT id FROM ship_service WHERE ship_id = ? AND service_id = ?";
    private static final String IS_PRESENT_SERVICE_ON_THE_SHIP = "SELECT * FROM ship_service WHERE ship_id = ? AND service_id = ?";
//    private static final String UPDATE = "UPDATE ship_service SET payable = ?, service_id = ? WHERE id = ?";
    private static final String DELETE_BY_SHIPID_SERVICEID = "DELETE FROM ship_service WHERE ship_id = ? AND service_id = ?";


    private Connection connection;

    public ShipserviceDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int create(Shipservice shipService) throws GeneralCheckedException {
        return SQLExecutor.executeInsertUpdateDelete(connection, CREATE, shipService.getShipId(), shipService.getPayable(), shipService.getServiceId());
    }


//    @Override
//    public Shipservice findById(int id) {
//        Shipservice shipService = new Shipservice();
//        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)){
//            preparedStatement.setInt(1, id);
//            ResultSet rs = preparedStatement.executeQuery();
//            if (rs.next())
//                return createShipService(rs);
//        } catch (SQLException e) {
//            LOGGER.error(e);
//            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
//        }
//        return shipService;
//    }

    @Override
    public List<Long> findAllIdByShipIdServiceId(Long shipId, Long serviceId) throws GeneralCheckedException {
        List<Long> listId = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_ID_BY_SHIPID_SERVICEID)){
            preparedStatement.setLong(1, shipId);
            preparedStatement.setLong(2, serviceId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
                listId.add(rs.getLong("id"));
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return listId;
    }

    @Override
    public boolean isServicePresentOnThisShip(Long selectedShipId, Long selectedServiceId) throws GeneralCheckedException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(IS_PRESENT_SERVICE_ON_THE_SHIP)){
            preparedStatement.setLong(1, selectedShipId);
            preparedStatement.setLong(2, selectedServiceId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                return true;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return false;
    }

    @Override
    public int deleteByShipIdServiceId(Long shipId, Long serviceId) throws GeneralCheckedException {
        return SQLExecutor.executeInsertUpdateDelete(connection, DELETE_BY_SHIPID_SERVICEID, shipId, serviceId);
    }

    private Service createService(ResultSet rs) throws SQLException { // service in system
        Service service = new Service();
        service.setId(rs.getLong("id"));
        service.setServiceName(rs.getString("service_name"));
        return service;
    }

    private Shipservice createShipService(ResultSet rs) throws SQLException {
        Shipservice shipService = new Shipservice();
        shipService.setId(rs.getLong("id"));
        shipService.setShipId(rs.getLong("ship_id"));
        shipService.setPayable(rs.getInt("payable"));
        shipService.setServiceId(rs.getLong("service_id"));
        return shipService;
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
