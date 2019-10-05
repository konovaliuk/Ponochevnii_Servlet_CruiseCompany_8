package ua.study.poject.cruise.persistance.dao.impl;

import org.apache.log4j.Logger;
import ua.study.poject.cruise.entity.Excursion;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.ExcursionDao;
import ua.study.poject.cruise.persistance.datasource.impl.SQLExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExcursionDaoImpl implements ExcursionDao {

    private static final Logger LOGGER = Logger.getLogger(ExcursionDaoImpl.class);

    private static final String CREATE = "INSERT INTO excurision (excursion_name, price, description, port_id) VALUES(?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM excurision WHERE id = ?";
    private static final String FIND_ALL_BY_PORT_ID = "SELECT * FROM excurision WHERE port_id = ?";

    private Connection connection;

    public ExcursionDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int create(Excursion excurision) throws GeneralCheckedException {
        return SQLExecutor.executeInsertUpdateDelete(connection, CREATE, excurision.getExcursionName(), excurision.getPrice(), excurision.getDescription(), excurision.getPortId());
    }

    @Override
    public Excursion findById(Long id) throws GeneralCheckedException {
        Excursion excurision = new Excursion();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                excurision = createExcurision(rs);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return excurision;
    }

    @Override
    public List<Excursion> findByPortId(Long portId) throws GeneralCheckedException {
        List<Excursion> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_PORT_ID)) {
            preparedStatement.setLong(1, portId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                list.add(createExcurision(rs));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return list;
    }

    private Excursion createExcurision(ResultSet rs) throws SQLException {
        Excursion excurision = new Excursion();
        excurision.setId(rs.getLong("id"));
        excurision.setExcursionName(rs.getString("excursion_name"));
        excurision.setPrice(rs.getDouble("price"));
        excurision.setDescription(rs.getString("description"));
        excurision.setPortId(rs.getLong("port_id"));
        return excurision;
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
