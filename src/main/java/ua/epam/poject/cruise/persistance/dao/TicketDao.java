package ua.epam.poject.cruise.persistance.dao;

import ua.epam.poject.cruise.entity.Ticket;
import ua.epam.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface TicketDao {

    int create(Ticket ticket) throws GeneralCheckedException;

    List<Ticket> findAll() throws GeneralCheckedException;

    Ticket findById(int id) throws GeneralCheckedException;

    int update(Ticket ticket) throws GeneralCheckedException;

    void close();
}
