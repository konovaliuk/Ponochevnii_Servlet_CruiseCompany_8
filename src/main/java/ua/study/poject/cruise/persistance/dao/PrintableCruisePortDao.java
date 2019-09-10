package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.printableentity.PrintableCruisePort;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface PrintableCruisePortDao {

    List<PrintableCruisePort> findAllPrintableCruisePortByCruiseId(Long cruiseId) throws GeneralCheckedException;

    void close();
}
