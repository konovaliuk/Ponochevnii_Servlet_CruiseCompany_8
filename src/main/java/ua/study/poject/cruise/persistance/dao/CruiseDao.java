package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.Cruise;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface CruiseDao {

    int create(Cruise cruise) throws GeneralCheckedException;

    List<Cruise> findAll() throws GeneralCheckedException;

    Cruise findById(Long id) throws GeneralCheckedException;

    List<Cruise> findAllByShipId(Long shipId) throws GeneralCheckedException;

    int update(Cruise cruise) throws GeneralCheckedException;

    void close();
}
