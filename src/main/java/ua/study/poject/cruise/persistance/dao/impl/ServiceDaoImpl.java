package ua.study.poject.cruise.persistance.dao.impl;

import org.apache.log4j.Logger;
import ua.study.poject.cruise.entity.Service;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.ServiceDao;
import ua.study.poject.cruise.persistance.datasource.impl.SQLExecutor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDaoImpl implements ServiceDao {

    private static final Logger LOGGER = Logger.getLogger(ServiceDaoImpl.class);

    private static final String CREATE = "INSERT INTO service (service_name) VALUES(?)";
    private static final String FIND_ALL = "SELECT * FROM service";
    private static final String FIND_BY_ID = "SELECT * FROM service WHERE id = ?";
    private static final String FIND_BY_NAME = "SELECT * FROM service WHERE service_name = ?";
    private static final String UPDATE = "UPDATE service SET service_name = ? WHERE id = ?";


    private Connection connection;

    public ServiceDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int create(Service service) throws GeneralCheckedException {
        return SQLExecutor.executeInsertUpdateDelete(connection, CREATE, service.getServiceName());
    }

    @Override
    public List<Service> findAll() throws GeneralCheckedException {
        List<Service> services = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery(FIND_ALL);
            while (rs.next())
                services.add(createService(rs));
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return services;
    }

    @Override
    public Service findById(Long id) throws GeneralCheckedException {
        Service service = new Service();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)){
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                service = createService(rs);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return service;
    }

    @Override
    public Service findByName(String serviceName) throws GeneralCheckedException {
        Service service = new Service();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME)){
            preparedStatement.setString(1, serviceName);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                service = createService(rs);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return service;
    }

    private Service createService(ResultSet rs) throws SQLException {
        Service service = new Service();
        service.setId(rs.getLong("id"));
        service.setServiceName(rs.getString("service_name"));
        return service;
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