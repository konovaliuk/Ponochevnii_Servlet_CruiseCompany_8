package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.printableentity.PrintableCruisePort;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

/**
 * The interface PrintableCruiseDao defines an interaction contract with a PrintableCruise entity
 */
public interface PrintableCruisePortDao {

    /**
     * This method finds all the PrintableCruisePorts by "Cruise id"
     *
     * @param cruiseId
     * @return List of PrintableCruisePorts
     * @throws GeneralCheckedException
     */
    List<PrintableCruisePort> findAllPrintableCruisePortByCruiseId(Long cruiseId) throws GeneralCheckedException;

    /**
     * The close method must be called after you have worked with this class to free resources
     */
    void close();
}
