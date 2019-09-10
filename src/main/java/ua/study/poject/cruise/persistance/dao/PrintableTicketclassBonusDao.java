package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.printableentity.PrintableTicketclassBonus;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface PrintableTicketclassBonusDao {

    List<PrintableTicketclassBonus> getAllBonusesByCruiseIdTicketclassId (Long cruiseId, Long ticketclassId) throws GeneralCheckedException;

    List<PrintableTicketclassBonus> getAllBonusesByCruiseIdTicketclassName(Long cruiseId, String ticketclassName) throws GeneralCheckedException;

    void close();

}
