package ua.epam.poject.cruise.persistance.dao;


import ua.epam.poject.cruise.entity.TicketExcursion;
import ua.epam.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface TicketExcursionDao {

    int create(Long ticketId, Long excursionId) throws GeneralCheckedException;

    List<TicketExcursion> findByTicketId(Long id) throws GeneralCheckedException;

    void close();
}
