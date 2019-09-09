package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.printableentity.PrintableServiceOnShip;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface PrintableServiceOnShipDao {

    List<PrintableServiceOnShip> findAllServicesByShipId(Long shipId) throws GeneralCheckedException;

    PrintableServiceOnShip findServiceById(Long id) throws GeneralCheckedException;

    void close();

}

