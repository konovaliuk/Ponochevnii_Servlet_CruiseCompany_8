package ua.study.poject.cruise.persistance.dao;


import ua.study.poject.cruise.entity.printableentity.PrintableCruise;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface PrintableCruiseDao {

    List<PrintableCruise> findAllForWebPage() throws GeneralCheckedException;

    void close();

}
