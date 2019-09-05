package ua.epam.poject.cruise.persistance.dao.impl;

import org.apache.log4j.Logger;
import ua.epam.poject.cruise.entity.Port;
import ua.epam.poject.cruise.exceptions.GeneralCheckedException;
import ua.epam.poject.cruise.persistance.dao.PortDao;
import ua.epam.poject.cruise.persistance.datasource.impl.SQLExecutor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PortDaoImpl implements PortDao {

    private static final Logger LOGGER = Logger.getLogger(PortDaoImpl.class);

    private static final String CREATE = "INSERT INTO port (country, city) VALUES(?, ?)";
    private static final String FIND_ALL = "SELECT * FROM port";
    private static final String FIND_BY_ID = "SELECT * FROM port WHERE id = ?";
    private static final String UPDATE = "UPDATE port SET country = ?, city = ? WHERE id = ?";


    private Connection connection;

    public PortDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int create(Port port) throws GeneralCheckedException {
        return SQLExecutor.executeInsertUpdateDelete(connection, CREATE, port.getCountry(), port.getCity());
    }

    @Override
    public List<Port> findAll() throws GeneralCheckedException {
        List<Port> ports = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery(FIND_ALL);
            while (rs.next())
                ports.add(createPort(rs));
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return ports;
    }

    @Override
    public Port findById(Long id) throws GeneralCheckedException {
        Port port = new Port();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)){
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                return createPort(rs);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return port;
    }

    @Override
    public int update(Port port) throws GeneralCheckedException {
        return SQLExecutor.executeInsertUpdateDelete(connection, UPDATE, port.getCountry(), port.getCity(), port.getId());
    }

    private Port createPort(ResultSet rs) throws SQLException {
        Port port = new Port();
        port.setId(rs.getLong("id"));
        port.setCountry(rs.getString("country"));
        port.setCity(rs.getString("city"));
        return port;
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
