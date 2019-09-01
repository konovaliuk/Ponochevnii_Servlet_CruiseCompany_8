package ua.epam.poject.cruise.persistance.dao.impl;

import org.apache.log4j.Logger;
import ua.epam.poject.cruise.entity.ShipService;
import ua.epam.poject.cruise.exceptions.GeneralCheckedException;
import ua.epam.poject.cruise.persistance.dao.ShipServiceDao;
import ua.epam.poject.cruise.persistance.datasource.impl.SQLExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShipServiceDaoImpl implements ShipServiceDao {

    private static final Logger LOGGER = Logger.getLogger(ShipServiceDaoImpl.class);

    private static final String CREATE = "INSERT INTO ship_service (ship_id, payable, service_id) VALUES(?, ?, ?)";
    private static final String FIND_BY_SHIP_ID = "SELECT * FROM ship_service WHERE ship_id = ?";
    private static final String FIND_BY_ID = "SELECT * FROM ship_service WHERE id = ?";
    private static final String UPDATE = "UPDATE ship_service SET payable = ?, service_id = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM ship_service WHERE id = ?";


    private Connection connection;

    public ShipServiceDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int create(ShipService shipService) throws GeneralCheckedException {
        return SQLExecutor.executeInsertUpdateDelete(connection, CREATE, shipService.getShipId(), shipService.getPayable(), shipService.getServiceId());
    }

    @Override
    public List<ShipService> findByShipId(int id) throws GeneralCheckedException {
        List<ShipService> shipServices = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_SHIP_ID)){
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
                shipServices.add(createShipService(rs));
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return shipServices;
    }

//    @Override
//    public ShipService findById(int id) {
//        ShipService shipService = new ShipService();
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
    public int update(ShipService shipService) throws GeneralCheckedException {
        return SQLExecutor.executeInsertUpdateDelete(connection, UPDATE, shipService.getPayable(), shipService.getServiceId(), shipService.getId());
    }

    @Override
    public int delete(ShipService shipService) throws GeneralCheckedException {
        return SQLExecutor.executeInsertUpdateDelete(connection, DELETE, shipService.getId());
    }

    private ShipService createShipService(ResultSet rs) throws SQLException {
        ShipService shipService = new ShipService();
        shipService.setId(rs.getInt("id"));
        shipService.setShipId(rs.getInt("ship_id"));
        shipService.setPayable(rs.getInt("payable"));
        shipService.setServiceId(rs.getInt("service_id"));
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
