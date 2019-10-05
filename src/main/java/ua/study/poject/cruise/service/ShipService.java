package ua.study.poject.cruise.service;

import org.apache.log4j.Logger;
import ua.study.poject.cruise.entity.Ship;
import ua.study.poject.cruise.entity.Shipservice;
import ua.study.poject.cruise.entity.printableentity.PrintableServiceOnShip;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.PrintableServiceOnShipDao;
import ua.study.poject.cruise.persistance.dao.ShipDao;
import ua.study.poject.cruise.persistance.dao.ShipserviceDao;
import ua.study.poject.cruise.persistance.datasource.AbstractDaoFactory;
import ua.study.poject.cruise.persistance.datasource.impl.MySqlDaoFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * The class implements all the necessary logic for working with the Ship entity
 */
public class ShipService {

    private static final Logger LOGGER = Logger.getLogger(ShipService.class);

    private AbstractDaoFactory daoFactory = MySqlDaoFactory.getInstance();
    private Ship ship = new Ship();

    /**
     * This method create new Ship
     * @param shipName ship name
     * @param nStaff number of staff
     * @param nFirstClass number of first class cabins
     * @param nSecondClass number of second class cabins
     * @param nThirdClass number of third class cabins
     * @param nFourthClass number of fourth class cabins
     * @return Ship id
     */
    public int createShip(String shipName, Long nStaff, Long nFirstClass, Long nSecondClass, Long nThirdClass, Long nFourthClass) {
        ship.setId(0L);
        ship.setShipName(shipName);
        ship.setNStaff(nStaff);
        ship.setNFirstClass(nFirstClass);
        ship.setNSecondClass(nSecondClass);
        ship.setNThirdClass(nThirdClass);
        ship.setNFourthClass(nFourthClass);

        ShipDao shipDao = null;
        try {
            shipDao = daoFactory.getShipDao();
            return shipDao.create(ship);
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (shipDao != null) {
                shipDao.close();
            }
        }
        return 0;
    }

    /**
     * The method returns all the ships that are in the system
     * @return List of Ships
     */
    public List<Ship> getAllShips() {
        ShipDao shipDao = null;
        List<Ship> list = new ArrayList<>();
        try {
            shipDao = daoFactory.getShipDao();
            list = shipDao.findAll();
        } catch (GeneralCheckedException e) {
            LOGGER.error("Неудачная работа с shipDaoImpl");
        } finally {
            if (shipDao != null) {
                shipDao.close();
            }
        }
        return list;
    }

    /**
     * The method returns all the PrintableServiceOnShip by "Ship id"
     * @param id
     * @return
     */
    public List<PrintableServiceOnShip> getAllServicesByShipId(Long id) {

        PrintableServiceOnShipDao printableServiceOnShipDao = null;
        List<PrintableServiceOnShip> list = new ArrayList<>();
        try {
            printableServiceOnShipDao = daoFactory.getPrintableServiceOnShipDao();
            list = printableServiceOnShipDao.findAllServicesByShipId(id);
        } catch (GeneralCheckedException e) {
            LOGGER.error("Неудачная работа с ShipserviceDao");
        } finally {
            if (printableServiceOnShipDao != null) {
                printableServiceOnShipDao.close();
            }
        }
        return list;
    }

    /**
     * The method checks if this service is present on the selected ship
     * @param selectedShipId
     * @param selectedServiceId
     * @return true if the Service present on this Ship
     */
    public boolean isServicePresentOnThisShip(Long selectedShipId, Long selectedServiceId) {
        ShipserviceDao shipserviceDao = null;
        try {
            shipserviceDao = daoFactory.getShipserviceDao();
            return shipserviceDao.isServicePresentOnThisShip(selectedShipId, selectedServiceId);
        } catch (GeneralCheckedException e) {
            LOGGER.error("Неудачная работа с ShipserviceDao");
        } finally {
            if (shipserviceDao != null) {
                shipserviceDao.close();
            }
        }
        return false;
    }

    /**
     * The method adds a new service to the ship. If payable> 0, then the service is considered paid
     * @param shipId
     * @param payable
     * @param serviceId
     * @return "Shipservice id"
     */
    public int addNewServiceToShip(Long shipId, Integer payable, Long serviceId) {

        ShipserviceDao shipserviceDao = null;

        Shipservice shipservice = new Shipservice();
        shipservice.setId(0L);
        shipservice.setShipId(shipId);
        shipservice.setPayable(payable);
        shipservice.setServiceId(serviceId);

        int result = 0;
        try {
            shipserviceDao = daoFactory.getShipserviceDao();
            result = shipserviceDao.create(shipservice);
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (shipserviceDao != null) {
                shipserviceDao.close();
            }
        }
        return result;
    }

}
