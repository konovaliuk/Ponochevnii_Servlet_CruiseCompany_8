package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.CruisePorts;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

/**
 * The interface CruisePortsDao defines an interaction contract with a CruisePorts entity
 */
public interface CruisePortsDao {

    /**
     * The create method saves the new CruisePorts entity, which is passed to it as a parameter.
     *
     * @param cruisePorts new entity that needs to be stored
     * @return the id number of the entity under which it is stored
     * @throws GeneralCheckedException
     */
    int create(CruisePorts cruisePorts) throws GeneralCheckedException;

    /**
     * The close method must be called after you have worked with this class to free resources
     */
    void close();
}
