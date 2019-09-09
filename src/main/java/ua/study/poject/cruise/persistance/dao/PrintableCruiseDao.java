package ua.study.poject.cruise.persistance.dao;


import ua.study.poject.cruise.entity.printableentity.PrintableCruise;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface PrintableCruiseDao {

    List<PrintableCruise> findAllPrintableCruises() throws GeneralCheckedException;

    List<PrintableCruise> findAllPrintableCruisesByShipId(Long shipId) throws GeneralCheckedException;

    void close();

}
