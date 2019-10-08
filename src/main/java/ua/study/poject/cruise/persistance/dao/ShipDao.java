package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.Ship;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

/**
 * The interface ShipDao defines an interaction contract with a Ship entity
 */
public interface ShipDao {

    /**
     * The create method saves the new Ship entity, which is passed to it as a parameter.
     *
     * @param ship new entity that needs to be stored
     * @return the id number of the entity under which it is stored
     * @throws GeneralCheckedException
     */
    int create(Ship ship) throws GeneralCheckedException;

    /**
     * This method allow find all the Ships that are presented in the system
     *
     * @return List of Ships
     * @throws GeneralCheckedException
     */
    List<Ship> findAll() throws GeneralCheckedException;

    /**
     * The close method must be called after you have worked with this class to free resources
     */
    void close();
}
