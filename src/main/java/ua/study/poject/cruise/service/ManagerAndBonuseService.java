package ua.study.poject.cruise.service;

import org.apache.log4j.Logger;
import ua.study.poject.cruise.entity.Ship;
import ua.study.poject.cruise.entity.Ticketclass;
import ua.study.poject.cruise.entity.printableentity.PrintableTicketclassBonus;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.PrintableTicketclassBonusDao;
import ua.study.poject.cruise.persistance.dao.TicketclassDao;
import ua.study.poject.cruise.persistance.dao.UserShipDao;
import ua.study.poject.cruise.persistance.datasource.AbstractDaoFactory;
import ua.study.poject.cruise.persistance.datasource.impl.MySqlDaoFactory;

import java.util.ArrayList;
import java.util.List;

public class ManagerAndBonuseService {

    private static final Logger LOGGER = Logger.getLogger(ManagerAndBonuseService.class);

    private AbstractDaoFactory daoFactory = MySqlDaoFactory.getInstance();

    public List<Ship> getAllShipsByManagerId(Long managerId) {
        List<Ship> list = new ArrayList<>();
        UserShipDao userShipDao = null;
        try {
            userShipDao = daoFactory.getUserShipDaoImpl();
            list.addAll(userShipDao.findShipsByUserId(managerId));
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (userShipDao != null)
                userShipDao.close();
        }
        return list;
    }


    public List<Ticketclass> getAllTicketclass() {
        List<Ticketclass> list = new ArrayList<>();
        TicketclassDao ticketclassDao = null;
        try {
            TicketclassDao ticketclassDao1 = daoFactory.getTicketclassDaoImpl();
            list = ticketclassDao.findAll();
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (ticketclassDao != null)
                ticketclassDao.close();
        }
        return list;
    }

    public List<PrintableTicketclassBonus> getAllBonusesByCruiseIdTicketclassId(Long cruiseId, Long ticketclassId) {

        PrintableTicketclassBonusDao printableTicketclassBonusDao = null;
        List<PrintableTicketclassBonus> list = new ArrayList<>();
        try {
            printableTicketclassBonusDao = daoFactory.getPrintableTicketclassBonusDao();
            list = printableTicketclassBonusDao.getAllBonusesByCruiseIdTicketclassId(cruiseId, ticketclassId);
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (printableTicketclassBonusDao != null)
                printableTicketclassBonusDao.close();
        }
        return list;
    }
}
