package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.printableentity.PrintableTicketclassBonus;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

/**
 * The interface PrintableTicketclassBonusDao defines an interaction contract with a PrintableTicketclassBonus entity
 */
public interface PrintableTicketclassBonusDao {

    /**
     * This method finds all the PrintableTicketclassBonus by "Cruise id" and "Ticketclass name"
     *
     * @param cruiseId
     * @param ticketclassName
     * @return List of PrintableTicketclassBonus
     * @throws GeneralCheckedException
     */
    List<PrintableTicketclassBonus> getAllBonusesByCruiseIdTicketclassName(Long cruiseId, String ticketclassName) throws GeneralCheckedException;

    /**
     * The close method must be called after you have worked with this class to free resources
     */
    void close();

}
