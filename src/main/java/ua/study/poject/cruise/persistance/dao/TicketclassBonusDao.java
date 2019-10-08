package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.TicketclassBonus;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

/**
 * The interface TicketclassBonusDao defines an interaction contract with a TicketclassBonus entity
 */
public interface TicketclassBonusDao {

    /**
     * The create method saves the new TicketclassBonus entity, which is passed to it as a parameter.
     *
     * @param ticketclassBonus new entity that needs to be stored
     * @return the id number of the entity under which it is stored
     * @throws GeneralCheckedException
     */
    int create(TicketclassBonus ticketclassBonus) throws GeneralCheckedException;

    /**
     * This method removes all TicketclassBonuse records by "Shipservice id"
     *
     * @param id
     * @return Amount of deleted records
     * @throws GeneralCheckedException
     */
    int deleteByShipserviceId(Long id) throws GeneralCheckedException;

    /**
     * This method removes TicketclassBonuse record by "id"
     *
     * @param id
     * @return Amount of deleted record
     * @throws GeneralCheckedException
     */
    int deleteById(Long id) throws GeneralCheckedException;

    /**
     * The close method must be called after you have worked with this class to free resources
     */
    void close();
}
