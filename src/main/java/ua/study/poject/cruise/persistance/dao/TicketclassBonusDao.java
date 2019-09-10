package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.TicketclassBonus;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface TicketclassBonusDao {

    int create(TicketclassBonus ticketclassBonus) throws GeneralCheckedException;

    int delete(TicketclassBonus ticketclassBonus) throws GeneralCheckedException;

    int deleteByShipserviceId(Long id) throws GeneralCheckedException;

    int deleteById(Long id) throws GeneralCheckedException;

    void close();
}
