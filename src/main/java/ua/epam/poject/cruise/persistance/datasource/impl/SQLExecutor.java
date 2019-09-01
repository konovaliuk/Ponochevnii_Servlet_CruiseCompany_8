package ua.epam.poject.cruise.persistance.datasource.impl;

import org.apache.log4j.Logger;
import ua.epam.poject.cruise.exceptions.GeneralCheckedException;

import java.sql.*;

public class SQLExecutor {

    private static final Logger LOGGER = Logger.getLogger(SQLExecutor.class);

    public static int executeInsertUpdateDelete(Connection connection, String query, Object... args) throws GeneralCheckedException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < args.length; i++)
                preparedStatement.setObject(i + 1, args[i]);
            int rowsAffected = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return rowsAffected;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }
}
