package ua.study.poject.cruise.service;

import org.apache.log4j.Logger;
import ua.study.poject.cruise.entity.Service;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.ServiceDao;
import ua.study.poject.cruise.persistance.dao.ShipserviceDao;
import ua.study.poject.cruise.persistance.dao.TicketclassBonusDao;
import ua.study.poject.cruise.persistance.datasource.AbstractDaoFactory;
import ua.study.poject.cruise.persistance.datasource.Atomizer;
import ua.study.poject.cruise.persistance.datasource.impl.AtomizerFactory;
import ua.study.poject.cruise.persistance.datasource.impl.MySqlDaoFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * The class implements all the necessary logic for working with the Shipservice and Service entities
 */
public class ShipserviceService {

    private static final Logger LOGGER = Logger.getLogger(ShipserviceService.class);

    private AbstractDaoFactory daoFactory;

    public ShipserviceService() {
        daoFactory = MySqlDaoFactory.getInstance();
    }

    public ShipserviceService(AbstractDaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * The method finds all services in the system
     *
     * @return List of Services
     */
    public List<Service> getAllServisesInSystem() {
        ServiceDao serviceDao = null;
        List<Service> list = new ArrayList<>();
        try {
            serviceDao = daoFactory.getServiceDao();
            list = serviceDao.findAll();
        } catch (GeneralCheckedException e) {
            LOGGER.error("Неудачная работа с portDaoImpl");
        } finally {
            if (serviceDao != null) {
                serviceDao.close();
            }
        }
        return list;
    }

    /**
     * The method finds service by the "Service name"
     *
     * @param serviceName
     * @return Service
     */
    public Service getServeceByName(String serviceName) {
        Service service = new Service();
        ServiceDao serviceDao = null;
        try {
            serviceDao = daoFactory.getServiceDao();
            service = serviceDao.findByName(serviceName);
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (serviceDao != null) {
                serviceDao.close();
            }
        }
        return service;
    }

    /**
     * The method finds service by the "Service id"
     *
     * @param serviceId
     * @return Service
     */
    public Service getServeceById(Long serviceId) {
        Service service = new Service();
        ServiceDao serviceDao = null;
        try {
            serviceDao = daoFactory.getServiceDao();
            service = serviceDao.findById(serviceId);
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (serviceDao != null) {
                serviceDao.close();
            }
        }
        return service;
    }

    /**
     * The method adds new Service in the system
     *
     * @param newServiseInSystem
     * @return "Service id"
     */
    public int addNewServiceToSystem(String newServiseInSystem) {
        ServiceDao serviceDao = null;
        Service service = new Service();
        service.setServiceName(newServiseInSystem);
        try {
            serviceDao = daoFactory.getServiceDao();
            return serviceDao.create(service);
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (serviceDao != null) {
                serviceDao.close();
            }
        }
        return 0;
    }

    /**
     * Method removes a service from the Ship
     *
     * @param shipId        selected Ship
     * @param listServiceId List of "Services id"
     * @return number of deleted records
     */
    public int deleteServicesFromShip(Long shipId, List<Long> listServiceId) {
        ShipserviceDao shipserviceDao;
        TicketclassBonusDao ticketclassBonusDao;
        try (Atomizer atomizer = AtomizerFactory.getAtomizer()) { // AutoCloseable
            shipserviceDao = daoFactory.getShipserviceDao(atomizer);
            ticketclassBonusDao = daoFactory.getTicketclassBonusDao(atomizer);

            int sum = 0; // summarize all the rows that are changed in the database

            /* if the service to be deleted has been added to TicketclassBonus,
               then this record must be deleted first. To do this, find all ship_service_id */
            List<Long> listShipserviceId = new ArrayList<>();
            for (Long aLong : listServiceId) {
                listShipserviceId.addAll(shipserviceDao.findAllIdByShipIdServiceId(shipId, aLong));
            }

            /* Now we need to remove TicketclassBonus, by ship_service_id */
            for (Long tempId : listShipserviceId) {
                sum = ticketclassBonusDao.deleteByShipserviceId(tempId);
            }

            /* then we remove services from the given ship */
            for (Long tempId : listServiceId) {
                sum = shipserviceDao.deleteByShipIdServiceId(shipId, tempId);
            }

            atomizer.recordChanges();
            return sum;

        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } catch (Exception e) {
            LOGGER.error(e);
            return 0;
        }
        return 0;
    }


}
