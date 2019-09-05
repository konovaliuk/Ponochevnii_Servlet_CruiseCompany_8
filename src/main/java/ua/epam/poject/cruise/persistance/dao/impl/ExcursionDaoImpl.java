package ua.epam.poject.cruise.persistance.dao.impl;

import org.apache.log4j.Logger;
import ua.epam.poject.cruise.entity.Excurision;
import ua.epam.poject.cruise.exceptions.GeneralCheckedException;
import ua.epam.poject.cruise.persistance.dao.ExcursionDao;
import ua.epam.poject.cruise.persistance.datasource.impl.SQLExecutor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExcursionDaoImpl implements ExcursionDao {

    private static final Logger LOGGER = Logger.getLogger(ExcursionDaoImpl.class);

    private static final String CREATE = "INSERT INTO excurision (excursion_name, price, description, port_id) VALUES(?, ?, ?, ?)";
    private static final String FIND_ALL = "SELECT * FROM excurision";
    private static final String FIND_BY_ID = "SELECT * FROM excurision WHERE id = ?";
    private static final String UPDATE = "UPDATE excurision SET excursion_name = ?, price = ?, description = ?, port_id = ? WHERE id = ?";


    private Connection connection;

    public ExcursionDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int create(Excurision excurision) throws GeneralCheckedException {
        return SQLExecutor.executeInsertUpdateDelete(connection, CREATE, excurision.getExcursionName(), excurision.getPrice(), excurision.getDescription(), excurision.getPortId());
    }

    @Override
    public List<Excurision> findAll() throws GeneralCheckedException {
        List<Excurision> excurisions = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery(FIND_ALL);
            while (rs.next())
                excurisions.add(createExcurision(rs));
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return excurisions;
    }

    @Override
    public Excurision findById(Long id) throws GeneralCheckedException {
        Excurision excurision = new Excurision();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)){
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                excurision = createExcurision(rs);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return excurision;
    }

    @Override
    public int update(Excurision excurision) throws GeneralCheckedException {
        return SQLExecutor.executeInsertUpdateDelete(connection, UPDATE, excurision.getExcursionName(), excurision.getPrice(), excurision.getDescription(), excurision.getPortId(), excurision.getId());
    }

    private Excurision createExcurision(ResultSet rs) throws SQLException {
        Excurision excurision = new Excurision();
        excurision.setId(rs.getLong("id"));
        excurision.setExcursionName(rs.getString("excursion_name"));
        excurision.setPrice(rs.getDouble("price"));
        excurision.setDescription(rs.getString("description"));
        excurision.setPortId(rs.getLong("port_id"));
        return excurision;
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
