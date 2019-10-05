package ua.study.poject.cruise.persistance.datasource;

import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.dao.*;
import ua.study.poject.cruise.persistance.datasource.impl.AtomizerFactory;

/**
 * An abstract factory allows you to use different factories that create
 * implementations of the required DAO classes.
 * If you need to organize the transaction during the execution of the method,
 * you must first obtain an instance of the Atomizer class and when creating a DAO instance,
 * pass this Atomizer instance as a parameter to each GET method
 * @see Atomizer
 * @see AtomizerFactory
 */
public abstract class AbstractDaoFactory {

    public abstract CruiseDao getCruiseDao(Atomizer atomizer);

    public abstract CruisePortsDao getCruisePortsDao(Atomizer atomizer);

    public abstract ExcursionDao getExcursionDao() throws GeneralCheckedException;

    public abstract PortDao getPortDao() throws GeneralCheckedException;

    public abstract RoleDao getRoleDao() throws GeneralCheckedException;

    public abstract ServiceDao getServiceDao() throws GeneralCheckedException;

    public abstract ShipDao getShipDao() throws GeneralCheckedException;

    public abstract ShipserviceDao getShipserviceDao() throws GeneralCheckedException;

    public abstract ShipserviceDao getShipserviceDao(Atomizer atomizer);

    public abstract TicketclassBonusDao getTicketclassBonusDao() throws GeneralCheckedException;

    public abstract TicketclassBonusDao getTicketclassBonusDao(Atomizer atomizer);

    public abstract TicketclassDao getTicketclassDao() throws GeneralCheckedException;

    public abstract TicketDao getTicketDao() throws GeneralCheckedException;

    public abstract TicketDao getTicketDao(Atomizer atomizer);

    public abstract TicketExcursionDao getTicketExcursionDao() throws GeneralCheckedException;

    public abstract TicketExcursionDao getTicketExcursionDao(Atomizer atomizer);

    public abstract UserDao getUserDao() throws GeneralCheckedException;

    public abstract UserShipDao getUserShipDao() throws GeneralCheckedException;

    public abstract PrintableCruiseDao getPrintableCruiseDao() throws GeneralCheckedException;

    public abstract PrintableServiceOnShipDao getPrintableServiceOnShipDao() throws GeneralCheckedException;

    public abstract PrintableTicketclassBonusDao getPrintableTicketclassBonusDao() throws GeneralCheckedException;

    public abstract PrintableCruisePortDao getPrintableCruisePortDao() throws GeneralCheckedException;

}
