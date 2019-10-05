package ua.study.poject.cruise.service;

import org.apache.log4j.Logger;
import ua.study.poject.cruise.entity.Ship;
import ua.study.poject.cruise.entity.Ticketclass;
import ua.study.poject.cruise.entity.TicketclassBonus;
import ua.study.poject.cruise.entity.printableentity.PrintableTicketclassBonus;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.PrintableTicketclassBonusDao;
import ua.study.poject.cruise.persistance.dao.TicketclassBonusDao;
import ua.study.poject.cruise.persistance.dao.TicketclassDao;
import ua.study.poject.cruise.persistance.dao.UserShipDao;
import ua.study.poject.cruise.persistance.datasource.AbstractDaoFactory;
import ua.study.poject.cruise.persistance.datasource.impl.MySqlDaoFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * the class contains logic for working with Bonus and Manager entities
 */
public class ManagerAndBonuseService {

    private static final Logger LOGGER = Logger.getLogger(ManagerAndBonuseService.class);

    private AbstractDaoFactory daoFactory = MySqlDaoFactory.getInstance();

    /**
     * the method allows you to find all the ships on which the Manager is allowed to manage bonuses
     *
     * @param managerId
     * @return List of Ship
     */
    public List<Ship> getAllShipsByManagerId(Long managerId) {
        List<Ship> list = new ArrayList<>();
        UserShipDao userShipDao = null;
        try {
            userShipDao = daoFactory.getUserShipDao();
            list.addAll(userShipDao.findShipsByUserId(managerId));
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (userShipDao != null) {
                userShipDao.close();
            }
        }
        return list;
    }

    /**
     * The method allows you to get a list of all Ticketclass that are in the system
     *
     * @return List of Ticketclasses
     */
    public List<Ticketclass> getAllTicketclass() {
        List<Ticketclass> list = new ArrayList<>();
        TicketclassDao ticketclassDao = null;
        try {
            ticketclassDao = daoFactory.getTicketclassDao();
            list = ticketclassDao.findAll();
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (ticketclassDao != null) {
                ticketclassDao.close();
            }
        }
        return list;
    }

    /**
     * The method allows you to get a list of all PrintableTicketclassBonus that are available
     * for this ticket class in the selected cruise
     *
     * @param cruiseId
     * @param ticketclassName
     * @return
     */
    public List<PrintableTicketclassBonus> getAllBonusesByCruiseIdTicketclassName(Long cruiseId, String ticketclassName) {

        PrintableTicketclassBonusDao printableTicketclassBonusDao = null;
        List<PrintableTicketclassBonus> list = new ArrayList<>();
        try {
            printableTicketclassBonusDao = daoFactory.getPrintableTicketclassBonusDao();
            list = printableTicketclassBonusDao.getAllBonusesByCruiseIdTicketclassName(cruiseId, ticketclassName);
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (printableTicketclassBonusDao != null) {
                printableTicketclassBonusDao.close();
            }
        }
        return list;
    }

    /**
     * The method saves a bonus for the selected ticket class
     *
     * @param ticketclassId
     * @param selectedshipserviceid
     * @param selectedCruiseId
     * @return
     */
    public int addBonus(Long ticketclassId, Long selectedshipserviceid, Long selectedCruiseId) {
        TicketclassBonusDao ticketclassBonusDao = null;
        int result = 0;
        try {
            ticketclassBonusDao = daoFactory.getTicketclassBonusDao();
            TicketclassBonus ticketclassBonus = new TicketclassBonus();
            ticketclassBonus.setTicketclassId(ticketclassId);
            ticketclassBonus.setShipServiceId(selectedshipserviceid);
            ticketclassBonus.setCruiseId(selectedCruiseId);
            result = ticketclassBonusDao.create(ticketclassBonus);
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (ticketclassBonusDao != null) {
                ticketclassBonusDao.close();
            }
        }
        return result;
    }

    /**
     * The method removes a bonus by "ticketclassBonus id".
     *
     * @param ticketclassBonusIdList list of "ticketclassBonus id" to be removed
     * @return
     */
    public int deleteBonuses(List<Long> ticketclassBonusIdList) {
        TicketclassBonusDao ticketclassBonusDao = null;
        int sum = 0;
        try {
            ticketclassBonusDao = daoFactory.getTicketclassBonusDao();
            for (Long id : ticketclassBonusIdList) {
                sum += ticketclassBonusDao.deleteById(id);
            }
            return sum;
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (ticketclassBonusDao != null) {
                ticketclassBonusDao.close();
            }
        }
        return 0;
    }
}
