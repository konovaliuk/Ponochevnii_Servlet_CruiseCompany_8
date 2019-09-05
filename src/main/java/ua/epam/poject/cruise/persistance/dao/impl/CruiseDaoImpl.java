package ua.epam.poject.cruise.persistance.dao.impl;

import org.apache.log4j.Logger;
import ua.epam.poject.cruise.entity.Cruise;
import ua.epam.poject.cruise.entity.printableentity.PrintableCruise;
import ua.epam.poject.cruise.exceptions.GeneralCheckedException;
import ua.epam.poject.cruise.persistance.dao.CruiseDao;
import ua.epam.poject.cruise.persistance.datasource.impl.SQLExecutor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CruiseDaoImpl implements CruiseDao {

    private static final Logger LOGGER = Logger.getLogger(CruiseDaoImpl.class);

    private static final String CREATE = "INSERT INTO cruise (ship_id, price_first_class, price_second_class, price_third_class, price_fourth_class) VALUES(?, ?, ?, ?, ?)";
    private static final String FIND_ALL = "SELECT * FROM cruise";
    private static final String FIND_BY_ID = "SELECT * FROM cruise WHERE id = ?";
    private static final String FIND_BY_SHIP_ID = "SELECT * FROM cruise WHERE ship_id = ?";
    private static final String UPDATE = "UPDATE cruise SET ship_id = ?, price_first_class = ?, price_second_class = ?, price_third_class = ?, price_fourth_class = ? WHERE id = ?";


    private Connection connection;

    public CruiseDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int create(Cruise cruise) throws GeneralCheckedException {
        return SQLExecutor.executeInsertUpdateDelete(connection, CREATE, cruise.getShipId(), cruise.getPriceFirstClass(), cruise.getPriceSecondClass(), cruise.getPriceThirdClass(), cruise.getPriceFourthClass());
    }

    @Override
    public List<Cruise> findAll() throws GeneralCheckedException {
        List<Cruise> cruises = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery(FIND_ALL);
            while (rs.next())
                cruises.add(createCruise(rs));
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return cruises;
    }

    @Override
    public Cruise findById(Long id) throws GeneralCheckedException {
        Cruise cruise = new Cruise();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)){
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                cruise = createCruise(rs);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return cruise;
    }

    @Override
    public List<Cruise> findAllByShipId(Long shipId) throws GeneralCheckedException {
        List<Cruise> cruises = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_SHIP_ID)){
            preparedStatement.setLong(1, shipId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                cruises.add(createCruise(rs));
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return cruises;
    }

    @Override
    public int update(Cruise cruise) throws GeneralCheckedException {
        return SQLExecutor.executeInsertUpdateDelete(connection, UPDATE, cruise.getShipId(), cruise.getPriceFirstClass(), cruise.getPriceSecondClass(), cruise.getPriceThirdClass(), cruise.getPriceFourthClass(), cruise.getId());
    }

    private Cruise createCruise(ResultSet rs) throws SQLException {
        Cruise cruise = new Cruise();
        cruise.setId(rs.getLong("id"));
        cruise.setShipId(rs.getLong("ship_id"));
        cruise.setPriceFirstClass(rs.getDouble("price_first_class"));
        cruise.setPriceSecondClass(rs.getDouble("price_second_class"));
        cruise.setPriceThirdClass(rs.getDouble("price_third_class"));
        cruise.setPriceFourthClass(rs.getDouble("price_fourth_class"));
        return cruise;
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
