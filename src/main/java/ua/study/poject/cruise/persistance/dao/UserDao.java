package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.User;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

/**
 * The interface UserDao defines an interaction contract with a User entity
 */
public interface UserDao {

    /**
     * The create method saves the new User entity, which is passed to it as a parameter.
     *
     * @param user
     * @return the id number of the entity under which it is stored
     */
    int create(User user) throws GeneralCheckedException;


    /**
     * This method finds user by login and password
     *
     * @param login
     * @param password
     * @return User entity
     */
    User findByLoginAndPassword(String login, String password) throws GeneralCheckedException;

    /**
     * This method finds User by login
     *
     * @param login
     * @return
     * @throws GeneralCheckedException
     */
    User findByLogin(String login) throws GeneralCheckedException;

    /**
     * Updates existing account
     *
     * @param user
     * @return id of updated entity
     */
    int update(User user) throws GeneralCheckedException;

    /**
     * The close method must be called after you have worked with this class to free resources
     */
    void close();
}
