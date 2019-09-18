package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.Ticketclass;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface TicketclassDao {

    List<Ticketclass> findAll();

    Ticketclass findById(Long id);

    Ticketclass findByTicketclassName(String className);

    void close();
}
