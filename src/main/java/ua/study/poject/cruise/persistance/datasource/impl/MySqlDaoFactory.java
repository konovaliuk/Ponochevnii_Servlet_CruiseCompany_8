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

/**
 * This class is an implementation of AbstractDao Factory for working with the MySql database
 *
 * @see AbstractDaoFactory
 */
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
    public CruiseDaoImpl getCruiseDao(Atomizer atomizer) {
        return new CruiseDaoImpl(atomizer.get());
    }

    @Override
    public CruisePortsDaoImpl getCruisePortsDao(Atomizer atomizer) {
        return new CruisePortsDaoImpl(atomizer.get());
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
    public PortDaoImpl getPortDao() throws GeneralCheckedException {
        try {
            return new PortDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
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
    public ServiceDaoImpl getServiceDao() throws GeneralCheckedException {
        try {
            return new ServiceDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
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
        return new ShipserviceDaoImpl(atomizer.get());
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
        return new TicketclassBonusDaoImpl(atomizer.get());
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
        return new TicketDaoImpl(atomizer.get());
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
        return new TicketExcursionDaoImpl(atomizer.get());
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
