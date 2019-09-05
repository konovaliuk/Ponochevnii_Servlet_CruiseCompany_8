package ua.epam.poject.cruise.persistance.dao;

import ua.epam.poject.cruise.entity.TicketclassBonus;
import ua.epam.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface TicketclassBonusDao {

    int create(TicketclassBonus ticketclassBonus) throws GeneralCheckedException;

    List<TicketclassBonus> findAllByIdCruiseIdTicketclass(Long idCruise, Long idTicketClass) throws GeneralCheckedException;

    int delete(TicketclassBonus ticketclassBonus) throws GeneralCheckedException;

    int deleteAllByCruiseIdShipservicesId(Long cruiseId, Long shipserviceId) throws GeneralCheckedException;

    int deleteByShipserviceId(Long id) throws GeneralCheckedException;

    void close();
}
