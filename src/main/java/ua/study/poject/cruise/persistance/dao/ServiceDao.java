package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.Service;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

/**
 * The interface ServiceDao defines an interaction contract with a Service entity
 */
public interface ServiceDao {

    /**
     * The create method saves the new Service entity, which is passed to it as a parameter.
     * @param service new entity that needs to be stored
     * @return the id number of the entity under which it is stored
     * @throws GeneralCheckedException
     */
    int create(Service service) throws GeneralCheckedException;

    /**
     * This method allows find all the Services that are presented in the system
     * @return List of Service
     * @throws GeneralCheckedException
     */
    List<Service> findAll() throws GeneralCheckedException;

    /**
     * This method allows find the Services by "id"
     * @param id
     * @return Service
     * @throws GeneralCheckedException
     */
    Service findById(Long id) throws GeneralCheckedException;

    /**
     * This method allows find the Services by "service name"
     * @param serviceName
     * @return Service
     * @throws GeneralCheckedException
     */
    Service findByName(String serviceName) throws GeneralCheckedException;

    /**
     * The close method must be called after you have worked with this class to free resources
     */
    void close();
}
