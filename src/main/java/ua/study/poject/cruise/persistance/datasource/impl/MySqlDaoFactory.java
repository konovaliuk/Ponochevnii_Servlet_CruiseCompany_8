package ua.study.poject.cruise.persistance.datasource.impl;


import org.apache.log4j.Logger;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.PrintableCruisePortDao;
import ua.study.poject.cruise.persistance.dao.PrintableServiceOnShipDao;
import ua.study.poject.cruise.persistance.dao.PrintableTicketclassBonusDao;
import ua.study.poject.cruise.persistance.dao.impl.*;
import ua.study.poject.cruise.persistance.dao.impl.printable.PrintableCruiseDaoImpl;
import ua.study.poject.cruise.persistance.dao.impl.printable.PrintableCruisePortDaoImpl;
import ua.study.poject.cruise.persistance.dao.impl.printable.PrintableServiceOnShipDaoImpl;
import ua.study.poject.cruise.persistance.dao.impl.printable.PrintableTicketclassBonusDaoImpl;
import ua.study.poject.cruise.persistance.datasource.AbstractDaoFactory;
import ua.study.poject.cruise.persistance.datasource.Atomizer;

import java.sql.Connection;

public class MySqlDaoFactory extends AbstractDaoFactory {

    private static MySqlDaoFactory instance;

    private MySqlDaoFactory() {
    }

    public static MySqlDaoFactory getInstance() {
        if (instance == null) {
            instance = new MySqlDaoFactory();
        }
        return instance;
    }

    private static final Logger LOGGER = Logger.getLogger(MySqlDaoFactory.class);

    @Override
    public CruiseDaoImpl getCruiseDao() throws GeneralCheckedException {
        try {
            return new CruiseDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    @Override
    public CruiseDaoImpl getCruiseDao(Atomizer atomizer) {
        return new CruiseDaoImpl((Connection) atomizer.get());
    }

    @Override
    public CruisePortsDaoImpl getCruisePortsDao() throws GeneralCheckedException {
        try {
            return new CruisePortsDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    @Override
    public CruisePortsDaoImpl getCruisePortsDao(Atomizer atomizer) {
        return new CruisePortsDaoImpl((Connection) atomizer.get());
    }

    @Override
    public ExcursionDaoImpl getExcursionDao() throws GeneralCheckedException {
        try {
            return new ExcursionDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    @Override
    public ExcursionDaoImpl getExcursionDao(Atomizer atomizer) {
        return new ExcursionDaoImpl((Connection) atomizer.get());
    }

    @Override
    public PortDaoImpl getPortDao() throws GeneralCheckedException {
        try {
            return new PortDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    @Override
    public PortDaoImpl getPortDao(Atomizer atomizer) {
        return new PortDaoImpl((Connection) atomizer.get());
    }

    @Override
    public RoleDaoImpl getRoleDao() throws GeneralCheckedException {
        try {
            return new RoleDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    @Override
    public RoleDaoImpl getRoleDao(Atomizer atomizer) {
        return new RoleDaoImpl((Connection) atomizer.get());
    }

    @Override
    public ServiceDaoImpl getServiceDao() throws GeneralCheckedException {
        try {
            return new ServiceDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    @Override
    public ServiceDaoImpl getServiceDao(Atomizer atomizer) {
        return new ServiceDaoImpl((Connection) atomizer.get());
    }

    @Override
    public ShipDaoImpl getShipDao() throws GeneralCheckedException {
        try {
            return new ShipDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    @Override
    public ShipDaoImpl getShipDao(Atomizer atomizer) {
        return new ShipDaoImpl((Connection) atomizer.get());
    }

    @Override
    public ShipserviceDaoImpl getShipserviceDao() throws GeneralCheckedException {
        try {
            return new ShipserviceDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    @Override
    public ShipserviceDaoImpl getShipserviceDao(Atomizer atomizer) {
        return new ShipserviceDaoImpl((Connection) atomizer.get());
    }

    @Override
    public TicketclassBonusDaoImpl getTicketclassBonusDao() throws GeneralCheckedException {
        try {
            return new TicketclassBonusDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    @Override
    public TicketclassBonusDaoImpl getTicketclassBonusDao(Atomizer atomizer) {
        return new TicketclassBonusDaoImpl((Connection) atomizer.get());
    }

    @Override
    public TicketclassDaoImpl getTicketclassDao() throws GeneralCheckedException {
        try {
            return new TicketclassDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    @Override
    public TicketclassDaoImpl getTicketclassDao(Atomizer atomizer) {
        return new TicketclassDaoImpl((Connection) atomizer.get());
    }

    @Override
    public TicketDaoImpl getTicketDao() throws GeneralCheckedException {
        try {
            return new TicketDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    @Override
    public TicketDaoImpl getTicketDao(Atomizer atomizer) {
        return new TicketDaoImpl((Connection) atomizer.get());
    }

    @Override
    public TicketExcursionDaoImpl getTicketExcursionDao() throws GeneralCheckedException {
        try {
            return new TicketExcursionDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    @Override
    public TicketExcursionDaoImpl getTicketExcursionDao(Atomizer atomizer) {
        return new TicketExcursionDaoImpl((Connection) atomizer.get());
    }

    @Override
    public UserDaoImpl getUserDao() throws GeneralCheckedException {
        try {
            return new UserDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    @Override
    public UserDaoImpl getUserDao(Atomizer atomizer) {
        return new UserDaoImpl((Connection) atomizer.get());
    }

    @Override
    public PrintableCruiseDaoImpl getPrintableCruiseDao() throws GeneralCheckedException {
        try {
            return new PrintableCruiseDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    @Override
    public PrintableServiceOnShipDao getPrintableServiceOnShipDao() throws GeneralCheckedException {
        try {
            return new PrintableServiceOnShipDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    @Override
    public UserShipDaoImpl getUserShipDao() throws GeneralCheckedException {
        try {
            return new UserShipDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    @Override
    public UserShipDaoImpl getUserShipDao(Atomizer atomizer) {
        return new UserShipDaoImpl((Connection) atomizer.get());
    }

    @Override
    public PrintableTicketclassBonusDao getPrintableTicketclassBonusDao() throws GeneralCheckedException {
        try {
            return new PrintableTicketclassBonusDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    @Override
    public PrintableCruisePortDao getPrintableCruisePortDao() throws GeneralCheckedException {
        try {
            return new PrintableCruisePortDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }
}
