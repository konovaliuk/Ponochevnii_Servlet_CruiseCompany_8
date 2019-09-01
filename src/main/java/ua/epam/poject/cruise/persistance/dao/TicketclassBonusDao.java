package ua.epam.poject.cruise.persistance.dao;

import ua.epam.poject.cruise.entity.TicketclassBonus;
import ua.epam.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface TicketclassBonusDao {

    int create(TicketclassBonus ticketclassBonus) throws GeneralCheckedException;

    List<TicketclassBonus> findAllByIdCruiseIdTicketclass(int idCruise, int idTicketClass) throws GeneralCheckedException;

    int delete(TicketclassBonus ticketclassBonus) throws GeneralCheckedException;

    void close();
}
