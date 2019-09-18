package ua.study.poject.cruise.persistance.dao;


import ua.study.poject.cruise.entity.printableentity.PrintableCruise;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface PrintableCruiseDao {

    List<PrintableCruise> findAllPrintableCruisesWithoutPorts() throws GeneralCheckedException;

    List<PrintableCruise> findAllPrintableCruisesWithoutPortsByShipId(Long shipId) throws GeneralCheckedException;


    void close();

}
