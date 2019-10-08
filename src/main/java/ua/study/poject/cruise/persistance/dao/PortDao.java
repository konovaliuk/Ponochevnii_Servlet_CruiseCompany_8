package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.Port;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

/**
 * The interface PortDao defines an interaction contract with a Port entity
 */
public interface PortDao {

    /**
     * The create method saves the new Port entity, which is passed to it as a parameter.
     *
     * @param port new entity that needs to be stored
     * @return the id number of the entity under which it is stored
     * @throws GeneralCheckedException
     */
    int create(Port port) throws GeneralCheckedException;

    /**
     * The method findAll returns all the Ports
     *
     * @return List of Ports
     * @throws GeneralCheckedException
     */
    List<Port> findAll() throws GeneralCheckedException;

    /**
     * The method findById returns a Port entity whose number is "id"
     *
     * @param id
     * @return Port entity
     * @throws GeneralCheckedException
     */
    Port findById(Long id) throws GeneralCheckedException;

    /**
     * The close method must be called after you have worked with this class to free resources
     */
    void close();
}
