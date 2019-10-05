package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.Ticket;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

/**
 * The interface TicketDao defines an interaction contract with a Ticket entity
 */
public interface TicketDao {

    /**
     * The create method saves the new Ticket entity, which is passed to it as a parameter.
     * @param ticket new entity that needs to be stored
     * @return the id number of the entity under which it is stored
     * @throws GeneralCheckedException
     */
    int create(Ticket ticket) throws GeneralCheckedException;

    /**
     * The method findTicketByUserId returns all the Tickets by "User id"
     * @param id
     * @return List of Ticket
     * @throws GeneralCheckedException
     */
    List<Ticket> findTicketByUserId(Long id) throws GeneralCheckedException;

    /**
     * The close method must be called after you have worked with this class to free resources
     */
    void close();
}
