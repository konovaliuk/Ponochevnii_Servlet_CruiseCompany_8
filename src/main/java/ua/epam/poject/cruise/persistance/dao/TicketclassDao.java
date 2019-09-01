package ua.epam.poject.cruise.persistance.dao;

import ua.epam.poject.cruise.entity.Ticketclass;
import ua.epam.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface TicketclassDao {

    List<Ticketclass> findAll() throws GeneralCheckedException;

    Ticketclass findById(int id) throws GeneralCheckedException;

    Ticketclass findByTicketclassName(String className) throws GeneralCheckedException;

    void close();
}
