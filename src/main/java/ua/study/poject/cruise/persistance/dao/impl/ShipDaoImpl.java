package ua.study.poject.cruise.persistance.dao.impl;

import org.apache.log4j.Logger;
import ua.study.poject.cruise.entity.Ship;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.ShipDao;
import ua.study.poject.cruise.persistance.datasource.impl.SQLExecutor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShipDaoImpl implements ShipDao {

    private static final Logger LOGGER = Logger.getLogger(ShipDaoImpl.class);

    private static final String CREATE = "INSERT INTO ship (ship_name, n_staff, n_first_class, n_second_class, n_third_class, n_fourth_class) VALUES(?, ?, ? ,? ,? ,?)";
    private static final String FIND_ALL = "SELECT * FROM ship";
    private static final String FIND_BY_ID = "SELECT * FROM ship WHERE id = ?";
    private static final String UPDATE = "UPDATE ship SET ship_name = ?, n_staff = ?, n_first_class = ?, n_second_class = ?, n_third_class = ?, n_fourth_class = ? WHERE id = ?";


    private Connection connection;

    public ShipDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int create(Ship ship) throws GeneralCheckedException {
        return SQLExecutor.executeInsertUpdateDelete(connection, CREATE, ship.getShipName(), ship.getNStaff(), ship.getNFirstClass(), ship.getNSecondClass(), ship.getNThirdClass(), ship.getNFourthClass());
    }

    @Override
    public List<Ship> findAll() throws GeneralCheckedException {
        List<Ship> ships = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery(FIND_ALL);
            while (rs.next())
                ships.add(createShip(rs));
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return ships;
    }

    @Override
    public Ship findById(Long id) throws GeneralCheckedException {
        Ship ship = new Ship();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)){
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                ship = createShip(rs);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return ship;
    }

    @Override
    public int update(Ship ship) throws GeneralCheckedException {
        return SQLExecutor.executeInsertUpdateDelete(connection, UPDATE, ship.getShipName(), ship.getNStaff(), ship.getNFirstClass(), ship.getNSecondClass(), ship.getNThirdClass(), ship.getNFourthClass(), ship.getId());
    }

    private Ship createShip(ResultSet rs) throws SQLException {
        Ship ship = new Ship();
        ship.setId(rs.getLong("id"));
        ship.setShipName(rs.getString("ship_name"));
        ship.setNStaff(rs.getLong("n_staff"));
        ship.setNFirstClass(rs.getLong("n_first_class"));
        ship.setNSecondClass(rs.getLong("n_second_class"));
        ship.setNThirdClass(rs.getLong("n_third_class"));
        ship.setNFourthClass(rs.getLong("n_fourth_class"));
        return ship;
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
