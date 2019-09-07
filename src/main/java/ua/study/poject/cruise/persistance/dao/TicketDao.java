package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.Ticket;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface TicketDao {

    int create(Ticket ticket) throws GeneralCheckedException;

    List<Ticket> findAll() throws GeneralCheckedException;

    Ticket findById(Long id) throws GeneralCheckedException;

    int update(Ticket ticket) throws GeneralCheckedException;

    void close();
}
