package ua.epam.poject.cruise.service;


import org.apache.log4j.Logger;
import ua.epam.poject.cruise.entity.Excurision;
import ua.epam.poject.cruise.entity.Port;
import ua.epam.poject.cruise.exceptions.GeneralCheckedException;
import ua.epam.poject.cruise.persistance.dao.ExcursionDao;
import ua.epam.poject.cruise.persistance.dao.PortDao;
import ua.epam.poject.cruise.persistance.datasource.AbstractDaoFactory;
import ua.epam.poject.cruise.persistance.datasource.impl.MySqlDaoFactory;

import java.util.ArrayList;
import java.util.List;

public class PortExcursionService {

    private static final Logger LOGGER = Logger.getLogger(PortExcursionService.class);

    private AbstractDaoFactory daoFactory = MySqlDaoFactory.getInstance();

    public int createPort(String country, String city) {
        Port port = new Port();
        port.setId(0L);
        port.setCountry(country);
        port.setCity(city);

        PortDao portDao = null;
        try {
            portDao = daoFactory.getPortDaoImpl();
            return portDao.create(port);
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (portDao != null)
                portDao.close();
        }
        return 0;
    }


    public int createExcursion(String excursionName, double price, String description, Long port) {
        Excurision excurision = new Excurision();
        excurision.setId(0L);
        excurision.setExcursionName(excursionName);
        excurision.setPrice(price);
        excurision.setDescription(description);
        excurision.setPortId(port);

        ExcursionDao excursionDao = null;
        try {
            excursionDao = daoFactory.getExcursionDaoImpl();
            return excursionDao.create(excurision);
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
        } finally {
            if (excursionDao != null)
                excursionDao.close();
        }
        return 0;
    }

    public List<Port> getAllPorts() {
        PortDao portDao = null;
        List<Port> list = new ArrayList<>();
        try {
            portDao = daoFactory.getPortDaoImpl();
            list = portDao.findAll();
        } catch (GeneralCheckedException e) {
            LOGGER.error("Неудачная работа с portDaoImpl");
        } finally {
            if (portDao != null)
                portDao.close();
        }
        return list;
    }
}
