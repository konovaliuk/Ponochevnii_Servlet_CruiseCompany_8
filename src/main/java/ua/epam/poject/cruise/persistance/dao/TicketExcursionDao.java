package ua.epam.poject.cruise.persistance.dao;


import ua.epam.poject.cruise.entity.TicketExcursion;
import ua.epam.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface TicketExcursionDao {

    int create(int ticketId, int excursionId) throws GeneralCheckedException;

    List<TicketExcursion> findByTicketId(int id) throws GeneralCheckedException;

    void close();
}
