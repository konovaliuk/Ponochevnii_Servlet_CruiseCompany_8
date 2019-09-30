package ua.study.poject.cruise.persistance.dao.impl;

import org.apache.log4j.Logger;
import ua.study.poject.cruise.entity.Role;
import ua.study.poject.cruise.entity.User;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.UserDao;
import ua.study.poject.cruise.persistance.datasource.impl.SQLExecutor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    private static final String FIND_ALL = "SELECT user.id, login, password, first_name, second_name, email, tel, role_id, role.role FROM user JOIN role ON user.role_id = role.id;";
    private static final String FIND_BY_ID = "SELECT user.id, login, password, first_name, second_name, email, tel, role_id, role.role FROM user JOIN role ON user.role_id = role.id WHERE user.id = ?";
    private static final String FIND_BY_LOGIN_PASSWORD = "SELECT user.id, login, password, first_name, second_name, user.email, user.tel, user.role_id, role.role FROM user JOIN role ON user.role_id = role.id WHERE login = ? AND password = ?";
    private static final String FIND_BY_LOGIN = "SELECT user.id, login, password, first_name, second_name, user.email, user.tel, user.role_id, role.role FROM user JOIN role ON user.role_id = role.id WHERE login = ?";
    private static final String CREATE = "INSERT INTO user (login, password, first_name, second_name, email, tel, role_id) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE user SET login = ?, password = ?, first_name = ?, second_name = ?, email = ?, tel = ?, role_id = ? WHERE id = ?";

    private Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int create(User user) throws GeneralCheckedException {
        return SQLExecutor.executeInsertUpdateDelete(connection, CREATE, user.getLogin(), user.getPassword(),
                user.getFirstName(), user.getSecondName(), user.getEmail(), user.getTel(), user.getRole().getId());
    }

    @Override
    public List<User> findAll() throws GeneralCheckedException {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery(FIND_ALL);
            while (rs.next())
                users.add(createUser(rs));
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return users;
    }

    @Override
    public User findById(Long id) throws GeneralCheckedException {
        User user = new User();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)){
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                user = createUser(rs);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return user;
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws GeneralCheckedException {
        User user = new User();
        if(login == null || password == null)
            return user;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_LOGIN_PASSWORD)){
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                user = createUser(rs);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return user;
    }
    @Override
    public User findByLogin(String login) throws GeneralCheckedException {
        User user = new User();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_LOGIN)){
            preparedStatement.setString(1, login);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
                user = createUser(rs);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
        return user;
    }

    @Override
    public int update(User user) throws GeneralCheckedException {
        return SQLExecutor.executeInsertUpdateDelete(connection, UPDATE, user.getLogin(), user.getPassword(),
                user.getFirstName(), user.getSecondName(), user.getEmail(), user.getTel(), user.getRole().getId(), user.getId());
    }

    private User createUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setFirstName(rs.getString("first_name"));
        user.setSecondName(rs.getString("second_name"));
        user.setEmail(rs.getString("email"));
        user.setTel(rs.getString("tel"));
        Role role = new Role();
        role.setId(rs.getLong("role_id"));
        role.setRole(rs.getString("role"));
        user.setRole(role);
        return user;
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
