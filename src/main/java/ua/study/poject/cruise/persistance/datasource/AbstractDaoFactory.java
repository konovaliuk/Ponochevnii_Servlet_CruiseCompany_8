package ua.study.poject.cruise.persistance.datasource;

import ua.study.poject.cruise.entity.printableentity.PrintableCruisePort;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.*;
import ua.study.poject.cruise.persistance.dao.impl.*;
import ua.study.poject.cruise.persistance.dao.impl.printable.PrintableCruiseDaoImpl;

import java.util.List;

public abstract class AbstractDaoFactory {

    public abstract CruiseDao getCruiseDao() throws GeneralCheckedException;

    public abstract CruiseDao getCruiseDao(Atomizer atomizer);

    public abstract CruisePortsDao getCruisePortsDao() throws GeneralCheckedException;

    public abstract CruisePortsDao getCruisePortsDao(Atomizer atomizer);

    public abstract ExcursionDao getExcursionDao() throws GeneralCheckedException;

    public abstract ExcursionDao getExcursionDao(Atomizer atomizer);

    public abstract PortDao getPortDao() throws GeneralCheckedException;

    public abstract PortDao getPortDao(Atomizer atomizer);

    public abstract RoleDao getRoleDao() throws GeneralCheckedException;

    public abstract RoleDao getRoleDao(Atomizer atomizer);

    public abstract ServiceDao getServiceDao() throws GeneralCheckedException;

    public abstract ServiceDao getServiceDao(Atomizer atomizer);

    public abstract ShipDao getShipDao() throws GeneralCheckedException;

    public abstract ShipDao getShipDao(Atomizer atomizer);

    public abstract ShipserviceDao getShipserviceDao() throws GeneralCheckedException;

    public abstract ShipserviceDao getShipserviceDao(Atomizer atomizer);

    public abstract TicketclassBonusDao getTicketclassBonusDao() throws GeneralCheckedException;

    public abstract TicketclassBonusDao getTicketclassBonusDao(Atomizer atomizer);

    public abstract TicketclassDao getTicketclassDao() throws GeneralCheckedException;

    public abstract TicketclassDao getTicketclassDao(Atomizer atomizer);

    public abstract TicketDao getTicketDao() throws GeneralCheckedException;

    public abstract TicketDao getTicketDao(Atomizer atomizer);

    public abstract TicketExcursionDao getTicketExcursionDao() throws GeneralCheckedException;

    public abstract TicketExcursionDao getTicketExcursionDao(Atomizer atomizer);

    public abstract UserDao getUserDao() throws GeneralCheckedException;

    public abstract UserDao getUserDao(Atomizer atomizer);

    public abstract UserShipDao getUserShipDao() throws GeneralCheckedException;

    public abstract UserShipDao getUserShipDao(Atomizer atomizer);

    public abstract PrintableCruiseDao getPrintableCruiseDao() throws GeneralCheckedException;

    public abstract PrintableServiceOnShipDao getPrintableServiceOnShipDao() throws GeneralCheckedException;

    public abstract PrintableTicketclassBonusDao getPrintableTicketclassBonusDao() throws GeneralCheckedException;

    public abstract PrintableCruisePortDao getPrintableCruisePortDao() throws GeneralCheckedException;

}
