package ua.epam.poject.cruise.persistance.dao.impl;

import org.apache.log4j.Logger;
import ua.epam.poject.cruise.entity.UserShip;
import ua.epam.poject.cruise.exceptions.GeneralCheckedException;
import ua.epam.poject.cruise.persistance.dao.UserShipDao;
import ua.epam.poject.cruise.persistance.datasource.impl.SQLExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserShipDaoImpl implements UserShipDao {

    private static final Logger LOGGER = Logger.getLogger(UserShipDaoImpl.class);

    private static final String CREATE = "INSERT INTO user_ship (ship_id, user_id) VALUES(?, ?)";
    private static final String FIND_BY_USER_ID = "SELECT * FROM user_ship WHERE user_id = ?";
    private static final String FIND_BY_SHIP_ID = "SELECT * FROM user_ship WHERE ship_id = ?";
    private static final String DELETE = "DELETE FROM user_ship WHERE id = ?";

    private Connection connection;

    public UserShipDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int create(Long shipId, Long userId) throws GeneralCheckedException {
        return SQLExecutor.executeInsertUpdateDelete(connection, CREATE, shipId, userId);
    }

    @Override
    public List<UserShip> findByUserId(Long userId) throws GeneralCheckedException {
        List<UserShip> userShips = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USER_ID)){
            preparedStatement.setLong(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
                userShips.add(createUserShip(rs));
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return userShips;
    }

    @Override
    public List<UserShip> findByShipId(Long shipId) throws GeneralCheckedException {
        List<UserShip> userShips = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_SHIP_ID)){
            preparedStatement.setLong(1, shipId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
                userShips.add(createUserShip(rs));
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return userShips;
    }

    @Override
    public int delete(UserShip userShip) throws GeneralCheckedException {
        return SQLExecutor.executeInsertUpdateDelete(connection, DELETE, userShip.getId());
    }

    private UserShip createUserShip(ResultSet rs) throws SQLException {
        UserShip userShip = new UserShip();
        userShip.setId(rs.getLong("id"));
        userShip.setShipId(rs.getLong("ship_id"));
        userShip.setUserId(rs.getLong("user_id"));
        return userShip;
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
