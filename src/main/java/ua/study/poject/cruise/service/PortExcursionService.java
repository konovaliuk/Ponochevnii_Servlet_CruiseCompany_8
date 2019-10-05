package ua.study.poject.cruise.service;


import org.apache.log4j.Logger;
import ua.study.poject.cruise.entity.Excursion;
import ua.study.poject.cruise.entity.Port;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.ExcursionDao;
import ua.study.poject.cruise.persistance.dao.PortDao;
import ua.study.poject.cruise.persistance.datasource.AbstractDaoFactory;
import ua.study.poject.cruise.persistance.datasource.impl.MySqlDaoFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a class that contains logic for working with Port and Excursion entities
 */
public class PortExcursionService {

    private static final Logger LOGGER = Logger.getLogger(PortExcursionService.class);

    private AbstractDaoFactory daoFactory = MySqlDaoFactory.getInstance();

    /**
     * The method creates new Port
     *
     * @param country country where the port is located
     * @param city    city where the port is located
     * @return Port id
     */
    public int createPort(String country, String city) {
        Port port = new Port();
        port.setId(0L);
        port.setCountry(country);
        port.setCity(city);

        PortDao portDao = null;
        try {
            portDao = daoFactory.getPortDao();
            return portDao.create(port);
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (portDao != null) {
                portDao.close();
            }
        }
        return 0;
    }


    /**
     * The method creates new Excursion
     *
     * @param excursionName
     * @param price
     * @param description
     * @param port          id of the Port
     * @return Excursion id
     */
    public int createExcursion(String excursionName, double price, String description, Long port) {
        Excursion excurision = new Excursion();
        excurision.setId(0L);
        excurision.setExcursionName(excursionName);
        excurision.setPrice(price);
        excurision.setDescription(description);
        excurision.setPortId(port);

        ExcursionDao excursionDao = null;
        try {
            excursionDao = daoFactory.getExcursionDao();
            return excursionDao.create(excurision);
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (excursionDao != null) {
                excursionDao.close();
            }
        }
        return 0;
    }

    /**
     * The method allows find all the ports in system
     *
     * @return List of Port, or an empty List if the search was unsuccessful
     */
    public List<Port> getAllPorts() {
        PortDao portDao = null;
        List<Port> list = new ArrayList<>();
        try {
            portDao = daoFactory.getPortDao();
            list = portDao.findAll();
        } catch (GeneralCheckedException e) {
            LOGGER.error("Неудачная работа с portDao");
        } finally {
            if (portDao != null) {
                portDao.close();
            }
        }
        return list;
    }

    /**
     * the method finds all excursions that are available in this port
     *
     * @param portId
     * @return List of Excursions, or an empty List if the search was unsuccessful
     */
    public List<Excursion> ViewExcursionsInPortByPortId(Long portId) {
        ExcursionDao excursionDao = null;
        List<Excursion> list = new ArrayList<>();
        try {
            excursionDao = daoFactory.getExcursionDao();
            list = excursionDao.findByPortId(portId);
        } catch (GeneralCheckedException e) {
            LOGGER.error("Неудачная работа с excursionDao");
        } finally {
            if (excursionDao != null) {
                excursionDao.close();
            }
        }
        return list;
    }

    /**
     * the method finds excursion by id
     *
     * @param excursionId
     * @return Excursion or an empty Excursion if the search was unsuccessful
     */
    public Excursion ViewExcursionsInPortById(Long excursionId) {
        ExcursionDao excursionDao = null;
        Excursion excurision = new Excursion();
        try {
            excursionDao = daoFactory.getExcursionDao();
            excurision = excursionDao.findById(excursionId);
        } catch (GeneralCheckedException e) {
            LOGGER.error("Неудачная работа с excursionDao");
        } finally {
            if (excursionDao != null) {
                excursionDao.close();
            }
        }
        return excurision;
    }

    /**
     * method finds port by id
     *
     * @param portId
     * @return Port or an empty Port if the search was unsuccessful
     */
    public Port getPortById(Long portId) {
        Port port = new Port();
        PortDao portDao = null;
        try {
            portDao = daoFactory.getPortDao();
            port = portDao.findById(portId);
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (portDao != null) {
                portDao.close();
            }
        }
        return port;
    }

}
