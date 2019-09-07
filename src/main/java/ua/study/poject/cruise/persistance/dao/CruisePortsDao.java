package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.CruisePorts;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface CruisePortsDao {

    int create(CruisePorts cruisePorts) throws GeneralCheckedException;

    List<CruisePorts> findAllByIdCruise(Long idCruise) throws GeneralCheckedException;

    int delete(CruisePorts cruisePorts) throws GeneralCheckedException;

    void close();
}
