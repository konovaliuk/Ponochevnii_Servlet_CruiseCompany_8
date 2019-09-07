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

public class ShipService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    private AbstractDaoFactory daoFactory = MySqlDaoFactory.getInstance();
    private Ship ship = new Ship();

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
            shipDao = daoFactory.getShipDaoImpl();
            return shipDao.create(ship);
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (shipDao != null)
                shipDao.close();
        }
        return 0;
    }

    public List<Ship> getAllShips() {
        ShipDao shipDao = null;
        List<Ship> list = new ArrayList<>();
        try {
            shipDao = daoFactory.getShipDaoImpl();
            list = shipDao.findAll();
        } catch (GeneralCheckedException e) {
            LOGGER.error("Неудачная работа с shipDaoImpl");
        } finally {
            if (shipDao != null)
                shipDao.close();
        }
        return list;
    }

    public List<PrintableServiceOnShip> getAllServicesByShipId(Long id) {

        PrintableServiceOnShipDao printableServiceOnShipDao = null;
        List<PrintableServiceOnShip> list = new ArrayList<>();
        try {
            printableServiceOnShipDao = daoFactory.getPrintableServiceOnShipDao();
            list = printableServiceOnShipDao.findAllServicesByShipId(id);
        } catch (GeneralCheckedException e) {
            LOGGER.error("Неудачная работа с ShipserviceDao");
        } finally {
            if (printableServiceOnShipDao != null)
                printableServiceOnShipDao.close();
        }
        return list;
    }

    public boolean isServicePresentOnThisShip(Long selectedShipId, Long selectedServiceId) {
        ShipserviceDao shipserviceDao = null;
        try {
            shipserviceDao = daoFactory.getShipserviceDaoImpl();
            return shipserviceDao.isServicePresentOnThisShip(selectedShipId, selectedServiceId);
        } catch (GeneralCheckedException e) {
            LOGGER.error("Неудачная работа с ShipserviceDao");
        } finally {
            if (shipserviceDao != null)
                shipserviceDao.close();
        }
        return false;
    }

    public int addNewServiceToShip(Long shipId, Integer payable, Long serviceId) {

        ShipserviceDao shipserviceDao = null;

        Shipservice shipservice = new Shipservice();
        shipservice.setId(0L);
        shipservice.setShipId(shipId);
        shipservice.setPayable(payable);
        shipservice.setServiceId(serviceId);

        int result = 0;
        try {
            shipserviceDao = daoFactory.getShipserviceDaoImpl();
            result = shipserviceDao.create(shipservice);
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (shipserviceDao != null)
                shipserviceDao.close();
        }
        return result;
    }

}
