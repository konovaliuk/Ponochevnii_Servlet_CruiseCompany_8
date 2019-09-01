package ua.epam.poject.cruise.persistance.dao;

import ua.epam.poject.cruise.entity.CruisePorts;
import ua.epam.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface CruisePortsDao {

    int create(CruisePorts cruisePorts) throws GeneralCheckedException;

    List<CruisePorts> findAllByIdCruise(int idCruise) throws GeneralCheckedException;

    int delete(CruisePorts cruisePorts) throws GeneralCheckedException;

    void close();
}
