package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.Ship;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.Set;

/**
 * The interface UserShipDao defines an interaction contract with a UserShip entity
 */
public interface UserShipDao {

    /**
     * The create method saves the new UserShip entity, which is determined by the parameters "Ship id" and "User id"
     *
     * @param shipId
     * @param userId
     * @return the id number of the entity under which it is stored
     * @throws GeneralCheckedException
     */
    int create(Long shipId, Long userId) throws GeneralCheckedException;

    /**
     * The method findShipsByUserId returns all the Ships by "User id"
     *
     * @param userId
     * @return Set of Ship
     * @throws GeneralCheckedException
     */
    Set<Ship> findShipsByUserId(Long userId) throws GeneralCheckedException;

    /**
     * The close method must be called after you have worked with this class to free resources
     */
    void close();
}
