package ua.study.poject.cruise.persistance.dao.impl;

import org.apache.log4j.Logger;
import ua.study.poject.cruise.entity.CruisePorts;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.CruisePortsDao;
import ua.study.poject.cruise.persistance.datasource.impl.SQLExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CruisePortsDaoImpl implements CruisePortsDao {

    private static final Logger LOGGER = Logger.getLogger(CruisePortsDaoImpl.class);

    private static final String CREATE = "INSERT INTO cruise_ports (date_in, date_out, cruise_id, port_id) VALUES(?, ?, ?, ?)";
    private static final String CREATE_START_PORT = "INSERT INTO cruise_ports (date_out, cruise_id, port_id) VALUES(?, ?, ?)";
    private static final String CREATE_FINISH_PORT = "INSERT INTO cruise_ports (date_in, cruise_id, port_id) VALUES(?, ?, ?)";
    private static final String FIND_BY_ID_CRUISE = "SELECT * FROM cruise_ports WHERE id = ?";
    private static final String DELETE = "DELETE FROM cruise_ports WHERE id = ?";


    private Connection connection;

    public CruisePortsDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int create(CruisePorts cruisePorts) throws GeneralCheckedException {
        if(cruisePorts.getDateIn() == null)
            return SQLExecutor.executeInsertUpdateDelete(connection, CREATE_START_PORT, cruisePorts.getDateOut(), cruisePorts.getCruiseId(), cruisePorts.getPortId());
        if(cruisePorts.getDateOut() == null)
            return SQLExecutor.executeInsertUpdateDelete(connection, CREATE_FINISH_PORT, cruisePorts.getDateIn(), cruisePorts.getCruiseId(), cruisePorts.getPortId());
        return SQLExecutor.executeInsertUpdateDelete(connection, CREATE, cruisePorts.getDateIn(), cruisePorts.getDateOut(), cruisePorts.getCruiseId(), cruisePorts.getPortId());
    }

    @Override
    public List<CruisePorts> findAllByIdCruise(Long idCruise) throws GeneralCheckedException {
        List<CruisePorts> cruisePorts = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_CRUISE)){
            preparedStatement.setLong(1, idCruise);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
                cruisePorts.add(createCruisePorts(rs));
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return cruisePorts;
    }

    @Override
    public int delete(CruisePorts cruisePorts) throws GeneralCheckedException {
        return SQLExecutor.executeInsertUpdateDelete(connection, DELETE, cruisePorts.getCruiseId());
    }

    private CruisePorts createCruisePorts(ResultSet rs) throws SQLException {
        CruisePorts cruisePorts = new CruisePorts();
        cruisePorts.setId(rs.getLong("id"));
        cruisePorts.setDateIn(rs.getString("date_in"));
        cruisePorts.setDateOut(rs.getString("date_out"));
        cruisePorts.setCruiseId(rs.getLong("cruise_id"));
        cruisePorts.setPortId(rs.getLong("port_id"));
        return cruisePorts;
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
