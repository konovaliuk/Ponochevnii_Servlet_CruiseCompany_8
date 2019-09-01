package ua.epam.poject.cruise.persistance.dao;


import ua.epam.poject.cruise.entity.printableentity.PrintableCruise;
import ua.epam.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface PrintableCruiseDao {

    List<PrintableCruise> findAllForWebPage() throws GeneralCheckedException;

    void close();

}
