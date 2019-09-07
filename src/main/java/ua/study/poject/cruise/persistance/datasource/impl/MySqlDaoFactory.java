package ua.study.poject.cruise.persistance.datasource.impl;


import org.apache.log4j.Logger;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.PrintableServiceOnShipDao;
import ua.study.poject.cruise.persistance.dao.impl.*;
import ua.study.poject.cruise.persistance.dao.impl.printable.PrintableCruiseDaoImpl;
import ua.study.poject.cruise.persistance.dao.impl.printable.PrintableServiceOnShipDaoImpl;
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
    public CruiseDaoImpl getCruiseDaoImpl() throws GeneralCheckedException {
        try {
            return new CruiseDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    @Override
    public CruiseDaoImpl getCruiseDaoImpl(Atomizer atomizer) {
        return new CruiseDaoImpl((Connection) atomizer.get());
    }

    @Override
    public CruisePortsDaoImpl getCruisePortsDaoImpl() throws GeneralCheckedException {
        try {
            return new CruisePortsDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    @Override
    public CruisePortsDaoImpl getCruisePortsDaoImpl(Atomizer atomizer) {
        return new CruisePortsDaoImpl((Connection) atomizer.get());
    }

    @Override
    public ExcursionDaoImpl getExcursionDaoImpl() throws GeneralCheckedException {
        try {
            return new ExcursionDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    @Override
    public ExcursionDaoImpl getExcursionDaoImpl(Atomizer atomizer) {
        return new ExcursionDaoImpl((Connection) atomizer.get());
    }

    @Override
    public PortDaoImpl getPortDaoImpl() throws GeneralCheckedException {
        try {
            return new PortDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    @Override
    public PortDaoImpl getPortDaoImpl(Atomizer atomizer) {
        return new PortDaoImpl((Connection) atomizer.get());
    }

    @Override
    public RoleDaoImpl getRoleDaoImpl() throws GeneralCheckedException {
        try {
            return new RoleDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    @Override
    public RoleDaoImpl getRoleDaoImpl(Atomizer atomizer) {
        return new RoleDaoImpl((Connection) atomizer.get());
    }

    @Override
    public ServiceDaoImpl getServiceDaoImpl() throws GeneralCheckedException {
        try {
            return new ServiceDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    @Override
    public ServiceDaoImpl getServiceDaoImpl(Atomizer atomizer) {
        return new ServiceDaoImpl((Connection) atomizer.get());
    }

    @Override
    public ShipDaoImpl getShipDaoImpl() throws GeneralCheckedException {
        try {
            return new ShipDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    @Override
    public ShipDaoImpl getShipDaoImpl(Atomizer atomizer) {
        return new ShipDaoImpl((Connection) atomizer.get());
    }

    @Override
    public ShipserviceDaoImpl getShipserviceDaoImpl() throws GeneralCheckedException {
        try {
            return new ShipserviceDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    @Override
    public ShipserviceDaoImpl getShipserviceDaoImpl(Atomizer atomizer) {
        return new ShipserviceDaoImpl((Connection) atomizer.get());
    }

    @Override
    public TicketclassBonusDaoImpl getTicketclassBonusDaoImpl() throws GeneralCheckedException {
        try {
            return new TicketclassBonusDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    @Override
    public TicketclassBonusDaoImpl getTicketclassBonusDaoImpl(Atomizer atomizer) {
        return new TicketclassBonusDaoImpl((Connection) atomizer.get());
    }

    @Override
    public TicketclassDaoImpl getTicketclassDaoImpl() throws GeneralCheckedException {
        try {
            return new TicketclassDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    @Override
    public TicketclassDaoImpl getTicketclassDaoImpl(Atomizer atomizer) {
        return new TicketclassDaoImpl((Connection) atomizer.get());
    }

    @Override
    public TicketDaoImpl getTicketDaoImpl() throws GeneralCheckedException {
        try {
            return new TicketDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    @Override
    public TicketDaoImpl getTicketDaoImpl(Atomizer atomizer) {
        return new TicketDaoImpl((Connection) atomizer.get());
    }

    @Override
    public TicketExcursionDaoImpl getTicketExcursionDaoImpl() throws GeneralCheckedException {
        try {
            return new TicketExcursionDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    @Override
    public TicketExcursionDaoImpl getTicketExcursionDaoImpl(Atomizer atomizer) {
        return new TicketExcursionDaoImpl((Connection) atomizer.get());
    }

    @Override
    public UserDaoImpl getUserDaoImpl() throws GeneralCheckedException {
        try {
            return new UserDaoImpl(ConnectionPool.getConnection());
        } catch (GeneralCheckedException e) {
            LOGGER.error(e);
            throw new GeneralCheckedException("Unsuccessful work with the database ", e);
        }
    }

    @Override
    public UserDaoImpl getUserDaoImpl(Atomizer atomizer) {
        return new UserDaoImpl((Connection) atomizer.get());
    }

    @Override
    public PrintableCruiseDaoImpl getPrintableCruiseDaoImpl() throws GeneralCheckedException {
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
}
