package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.Ticketclass;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface TicketclassDao {

    List<Ticketclass> findAll() throws GeneralCheckedException;

    Ticketclass findById(Long id) throws GeneralCheckedException;

    Ticketclass findByTicketclassName(String className) throws GeneralCheckedException;

    void close();
}
