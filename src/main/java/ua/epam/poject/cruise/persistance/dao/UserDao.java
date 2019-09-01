package ua.epam.poject.cruise.persistance.dao;

import ua.epam.poject.cruise.entity.User;
import ua.epam.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface UserDao {

    /**
     * Inserts new entity into database
     *
     * @param user
     * @return id of created entity or 0 if error occurred
     */
    int create(User user) throws GeneralCheckedException;

    /**
     * Finds all users
     *
     * @return List of User object
     */
    List<User> findAll() throws GeneralCheckedException;

    /**
     * Finds User entity by userId
     *
     * @param id
     * @return User of null if user not found
     */
    User findById(int id) throws GeneralCheckedException;


    /**
     * Retrieves user by login and password
     *
     * @param login
     * @param password
     * @return User of null if user not found
     */
    User findByLoginAndPassword(String login, String password) throws GeneralCheckedException;

    /**
     * Updates existing account
     *
     * @param user
     * @return id of updated entity
     */
    int update(User user) throws GeneralCheckedException;

    void close();
}
