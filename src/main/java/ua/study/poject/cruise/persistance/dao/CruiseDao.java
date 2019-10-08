package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.Cruise;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

/**
 * The interface CruiseDao defines an interaction contract with a Cruise entity
 */
public interface CruiseDao {

    /**
     * The create method saves the new Cruise entity, which is passed to it as a parameter.
     *
     * @param cruise new entity that needs to be stored
     * @return the id number of the entity under which it is stored
     * @throws GeneralCheckedException
     */
    int create(Cruise cruise) throws GeneralCheckedException;

    /**
     * The method findAll returns all the Cruises
     *
     * @return List of Cruises
     * @throws GeneralCheckedException
     */
    List<Cruise> findAll() throws GeneralCheckedException;

    /**
     * The method findById returns a Cruise entity whose number is "id"
     *
     * @param id number of crusie
     * @return Cruise entity
     * @throws GeneralCheckedException
     */
    Cruise findById(Long id) throws GeneralCheckedException;

    /**
     * The close method must be called after you have worked with this class to free resources
     */
    void close();
}
