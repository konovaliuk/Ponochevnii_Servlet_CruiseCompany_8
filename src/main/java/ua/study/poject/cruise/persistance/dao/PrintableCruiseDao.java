package ua.study.poject.cruise.persistance.dao;


import ua.study.poject.cruise.entity.printableentity.PrintableCruise;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

/**
 * The interface PrintableCruiseDao defines an interaction contract with a PrintableCruise entity
 */
public interface PrintableCruiseDao {

    /**
     * This method finds all the PrintableCruises without List of ports
     *
     * @return List of PrintableCruises
     * @throws GeneralCheckedException
     */
    List<PrintableCruise> findAllPrintableCruisesWithoutPorts() throws GeneralCheckedException;

    /**
     * This method finds all the PrintableCruises without List of ports by "Ship id"
     *
     * @param shipId
     * @return List of PrintableCruises
     * @throws GeneralCheckedException
     */
    List<PrintableCruise> findAllPrintableCruisesWithoutPortsByShipId(Long shipId) throws GeneralCheckedException;

    /**
     * This method finds a PrintableCruise without List of ports by "Cruise id"
     *
     * @param cruiseId
     * @return
     * @throws GeneralCheckedException
     */
    PrintableCruise findPrintableCruiseWithoutPortsByCruiseId(Long cruiseId) throws GeneralCheckedException;

    /**
     * The close method must be called after you have worked with this class to free resources
     */
    void close();

}
