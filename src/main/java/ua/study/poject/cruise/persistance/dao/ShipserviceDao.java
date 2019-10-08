package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.Shipservice;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

/**
 * The interface ShipserviceDao defines an interaction contract with a Shipservice entity
 */
public interface ShipserviceDao {

    /**
     * The create method saves the new Shipservice entity, which is passed to it as a parameter.
     *
     * @param shipService new entity that needs to be stored
     * @return the id number of the entity under which it is stored
     * @throws GeneralCheckedException
     */
    int create(Shipservice shipService) throws GeneralCheckedException;

    /**
     * This method checks if service is present on this ship
     *
     * @param selectedShipId
     * @param selectedServiceId
     * @return true if Service present on this Ship
     * @throws GeneralCheckedException
     */
    boolean isServicePresentOnThisShip(Long selectedShipId, Long selectedServiceId) throws GeneralCheckedException;

    /**
     * This method removes service from the ship
     *
     * @param shipId
     * @param serviceId
     * @return amount of deleted records
     * @throws GeneralCheckedException
     */
    int deleteByShipIdServiceId(Long shipId, Long serviceId) throws GeneralCheckedException;

    /**
     * This method finds all records by the "Ship id" and "Service id"
     *
     * @param shipId
     * @param serviceId
     * @return List of "id"
     * @throws GeneralCheckedException
     */
    List<Long> findAllIdByShipIdServiceId(Long shipId, Long serviceId) throws GeneralCheckedException;

    /**
     * The close method must be called after you have worked with this class to free resources
     */
    void close();
}
