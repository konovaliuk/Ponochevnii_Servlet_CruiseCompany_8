package ua.study.poject.cruise.service;

import org.apache.log4j.Logger;
import ua.study.poject.cruise.entity.Service;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.CruiseDao;
import ua.study.poject.cruise.persistance.dao.ServiceDao;
import ua.study.poject.cruise.persistance.dao.ShipserviceDao;
import ua.study.poject.cruise.persistance.dao.TicketclassBonusDao;
import ua.study.poject.cruise.persistance.datasource.AbstractDaoFactory;
import ua.study.poject.cruise.persistance.datasource.Atomizer;
import ua.study.poject.cruise.persistance.datasource.impl.DBAtomizer;
import ua.study.poject.cruise.persistance.datasource.impl.MySqlDaoFactory;

import java.util.ArrayList;
import java.util.List;

public class ShipserviceService {

    private static final Logger LOGGER = Logger.getLogger(ShipserviceService.class);

    private AbstractDaoFactory daoFactory = MySqlDaoFactory.getInstance();

    public List<Service> getAllServisesInSystem() {
        ServiceDao serviceDao = null;
        List<Service> list = new ArrayList<>();
        try {
            serviceDao = daoFactory.getServiceDaoImpl();
            list = serviceDao.findAll();
        } catch (GeneralCheckedException e) {
            LOGGER.error("Неудачная работа с portDaoImpl");
        } finally {
            if (serviceDao != null)
                serviceDao.close();
        }
        return list;
    }

    public Service getServeceByName(String serviceName) {
        Service service = new Service();
        ServiceDao serviceDao = null;
        try {
            serviceDao = daoFactory.getServiceDaoImpl();
            service = serviceDao.findByName(serviceName);
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (serviceDao != null)
                serviceDao.close();
        }
        return service;
    }

    public Service getServeceById(Long serviceId) {
        Service service = new Service();
        ServiceDao serviceDao = null;
        try {
            serviceDao = daoFactory.getServiceDaoImpl();
            service = serviceDao.findById(serviceId);
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (serviceDao != null)
                serviceDao.close();
        }
        return service;
    }

    public int addNewServiceToSystem(String newServiseInSystem) {

        ServiceDao serviceDao = null;
        Service service = new Service();
        service.setServiceName(newServiseInSystem);

        try {
            serviceDao = daoFactory.getServiceDaoImpl();
            return serviceDao.create(service);
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (serviceDao != null)
                serviceDao.close();
        }
        return 0;
    }

    public int deleteServicesFromShip(Long shipId, List<Long> listServiceId) {

        ShipserviceDao shipserviceDao;
        TicketclassBonusDao ticketclassBonusDao;

        try (Atomizer atomizer = new DBAtomizer()) {
            CruiseDao cruiseDao = daoFactory.getCruiseDaoImpl(atomizer);
            shipserviceDao = daoFactory.getShipserviceDaoImpl(atomizer);
            ticketclassBonusDao = daoFactory.getTicketclassBonusDaoImpl(atomizer);

            int sum = 0; // сумируем все строки, которые изменены в БД

            // если сервис, который надо удалить был добавлен в TicketclassBonus, то надо эту запись удалить в первую очередь. Для этого найдем все ship_service_id
            List<Long> listShipserviceId = new ArrayList<>();
            for (Long aLong : listServiceId) {
                listShipserviceId.addAll(shipserviceDao.findAllIdByShipIdServiceId(shipId, aLong));
            }
            // Теперь надо удалить TicketclassBonus, by ship_service_id

            for (Long tempId : listShipserviceId) {
                sum = ticketclassBonusDao.deleteByShipserviceId(tempId);
            }

            // потом удаляем services c заданного корабля

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
