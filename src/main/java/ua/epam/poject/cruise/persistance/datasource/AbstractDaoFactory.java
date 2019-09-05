package ua.epam.poject.cruise.persistance.datasource;

import ua.epam.poject.cruise.exceptions.GeneralCheckedException;
import ua.epam.poject.cruise.persistance.dao.PrintableServiceOnShipDao;
import ua.epam.poject.cruise.persistance.dao.impl.*;
import ua.epam.poject.cruise.persistance.dao.impl.printable.PrintableCruiseDaoImpl;

public abstract class AbstractDaoFactory {

    public abstract CruiseDaoImpl getCruiseDaoImpl() throws GeneralCheckedException;

    public abstract CruiseDaoImpl getCruiseDaoImpl(Atomizer atomizer);

    public abstract CruisePortsDaoImpl getCruisePortsDaoImpl() throws GeneralCheckedException;

    public abstract CruisePortsDaoImpl getCruisePortsDaoImpl(Atomizer atomizer);

    public abstract ExcursionDaoImpl getExcursionDaoImpl() throws GeneralCheckedException;

    public abstract ExcursionDaoImpl getExcursionDaoImpl(Atomizer atomizer);

    public abstract PortDaoImpl getPortDaoImpl() throws GeneralCheckedException;

    public abstract PortDaoImpl getPortDaoImpl(Atomizer atomizer);

    public abstract RoleDaoImpl getRoleDaoImpl() throws GeneralCheckedException;

    public abstract RoleDaoImpl getRoleDaoImpl(Atomizer atomizer);

    public abstract ServiceDaoImpl getServiceDaoImpl() throws GeneralCheckedException;

    public abstract ServiceDaoImpl getServiceDaoImpl(Atomizer atomizer);

    public abstract ShipDaoImpl getShipDaoImpl() throws GeneralCheckedException;

    public abstract ShipDaoImpl getShipDaoImpl(Atomizer atomizer);

    public abstract ShipserviceDaoImpl getShipserviceDaoImpl() throws GeneralCheckedException;

    public abstract ShipserviceDaoImpl getShipserviceDaoImpl(Atomizer atomizer);

    public abstract TicketclassBonusDaoImpl getTicketclassBonusDaoImpl() throws GeneralCheckedException;

    public abstract TicketclassBonusDaoImpl getTicketclassBonusDaoImpl(Atomizer atomizer);

    public abstract TicketclassDaoImpl getTicketclassDaoImpl() throws GeneralCheckedException;

    public abstract TicketclassDaoImpl getTicketclassDaoImpl(Atomizer atomizer);

    public abstract TicketDaoImpl getTicketDaoImpl() throws GeneralCheckedException;

    public abstract TicketDaoImpl getTicketDaoImpl(Atomizer atomizer);

    public abstract TicketExcursionDaoImpl getTicketExcursionDaoImpl() throws GeneralCheckedException;

    public abstract TicketExcursionDaoImpl getTicketExcursionDaoImpl(Atomizer atomizer);

    public abstract UserDaoImpl getUserDaoImpl() throws GeneralCheckedException;

    public abstract UserDaoImpl getUserDaoImpl(Atomizer atomizer);

    public abstract PrintableCruiseDaoImpl getPrintableCruiseDaoImpl() throws GeneralCheckedException;

    public abstract PrintableServiceOnShipDao getPrintableServiceOnShipDao() throws GeneralCheckedException;


}
