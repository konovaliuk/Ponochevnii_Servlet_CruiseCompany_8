package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.Ticketclass;

import java.util.List;

/**
 * The interface TicketclassDao defines an interaction contract with a Ticketclass entity
 */
public interface TicketclassDao {

    /**
     * The method finds all Ticketclasses in system
     *
     * @return List of Ticketclass
     */
    List<Ticketclass> findAll();

    /**
     * The close method must be called after you have worked with this class to free resources
     */
    void close();
}
