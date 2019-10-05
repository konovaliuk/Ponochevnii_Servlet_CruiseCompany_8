package ua.study.poject.cruise.persistance.dao;


import ua.study.poject.cruise.entity.TicketExcursion;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

/**
 * The interface TicketExcursionDao defines an interaction contract with a TicketExcursion entity
 */
public interface TicketExcursionDao {

    /**
     * The create method saves the new TicketExcursion entity, which is depends of "User id" and "Excursion id".
     * @param userId
     * @param excursionId
     * @return the id number of the entity under which it is stored
     * @throws GeneralCheckedException
     */
    int create(Long userId, Long excursionId) throws GeneralCheckedException;

    /**
     * The method findByUserId allows find all of the TicketExcursions entity by "User id"
     * @param id
     * @return List of TicketExcursion
     * @throws GeneralCheckedException
     */
    List<TicketExcursion> findByUserId(Long id) throws GeneralCheckedException;

    /**
     * The close method must be called after you have worked with this class to free resources
     */
    void close();
}
