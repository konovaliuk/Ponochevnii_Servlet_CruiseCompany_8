package ua.epam.poject.cruise.persistance.dao;

import ua.epam.poject.cruise.entity.printableentity.PrintableServiceOnShip;
import ua.epam.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface PrintableServiceOnShipDao {

    List<PrintableServiceOnShip> findAllServicesByShipId(Long id) throws GeneralCheckedException;

    void close();

}

