package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.printableentity.PrintableServiceOnShip;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

/**
 * The interface PrintableServiceOnShipDao defines an interaction contract with a PrintableServiceOnShip entity
 */
public interface PrintableServiceOnShipDao {

    /**
     * This method finds all the PrintableServiceOnShip by "Ship id"
     * @param shipId
     * @return List of PrintableServiceOnShip
     * @throws GeneralCheckedException
     */
    List<PrintableServiceOnShip> findAllServicesByShipId(Long shipId) throws GeneralCheckedException;

    /**
     * The close method must be called after you have worked with this class to free resources
     */
    void close();

}

