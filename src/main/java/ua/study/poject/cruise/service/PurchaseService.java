package ua.study.poject.cruise.service;

import org.apache.log4j.Logger;
import ua.study.poject.cruise.entity.Excursion;
import ua.study.poject.cruise.entity.Ticket;
import ua.study.poject.cruise.entity.TicketExcursion;
import ua.study.poject.cruise.entity.User;
import ua.study.poject.cruise.entity.printableentity.PrintableCruise;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.*;
import ua.study.poject.cruise.persistance.datasource.AbstractDaoFactory;
import ua.study.poject.cruise.persistance.datasource.impl.MySqlDaoFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a class that contains logic for working with purchase history
 */
public class PurchaseService {

    private static final Logger LOGGER = Logger.getLogger(PurchaseService.class);

    private AbstractDaoFactory daoFactory;

    public PurchaseService() {
        daoFactory = MySqlDaoFactory.getInstance();
    }

    public PurchaseService(AbstractDaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }


    /**
     * This method finds all Cruises that were purchased by the user
     *
     * @param user current user
     * @return List of PrintableCruises
     */
    public List<PrintableCruise> findMyPrintableCruises(User user) {
        List<PrintableCruise> list = new ArrayList<>();
        PrintableCruiseDao printableCruiseDao = null;
        PrintableCruisePortDao printableCruisePortDao = null;
        TicketDao ticketDao = null;
        try {
            printableCruiseDao = daoFactory.getPrintableCruiseDao();
            printableCruisePortDao = daoFactory.getPrintableCruisePortDao();
            ticketDao = daoFactory.getTicketDao();
            List<Ticket> myCruiseTickets = ticketDao.findTicketByUserId(user.getId());
            for (Ticket myCruiseTicket : myCruiseTickets) {
                PrintableCruise printableCruise = printableCruiseDao.findPrintableCruiseWithoutPortsByCruiseId(myCruiseTicket.getCruiseId());
                printableCruise.setPrintableCruisePorts(printableCruisePortDao.findAllPrintableCruisePortByCruiseId(printableCruise.getCruiseId()));
                list.add(printableCruise);
            }
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (printableCruiseDao != null) {
                printableCruiseDao.close();
            }
            if (printableCruisePortDao != null) {
                printableCruisePortDao.close();
            }
            if (ticketDao != null) {
                ticketDao.close();
            }
        }
        return list;
    }

    /**
     * This method finds all Excursions that were purchased by the user
     *
     * @param user current user
     * @return List of Excursion
     */
    public List<Excursion> findMyExcursions(User user) {
        TicketExcursionDao ticketExcursionDao = null;
        ExcursionDao excursionDao = null;
        List<Excursion> myExcursions = new ArrayList<>();
        List<TicketExcursion> ticketExcursionList;
        try {
            ticketExcursionDao = daoFactory.getTicketExcursionDao();
            excursionDao = daoFactory.getExcursionDao();
            ticketExcursionList = ticketExcursionDao.findByUserId(user.getId());

            for (TicketExcursion ticketExcursion : ticketExcursionList) {
                myExcursions.add(excursionDao.findById(ticketExcursion.getExcurisionId()));
            }
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (ticketExcursionDao != null) {
                ticketExcursionDao.close();
            }
            if (excursionDao != null) {
                excursionDao.close();
            }
        }
        return myExcursions;
    }
}
