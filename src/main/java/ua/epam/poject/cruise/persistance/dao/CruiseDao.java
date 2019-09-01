package ua.epam.poject.cruise.persistance.dao;

import ua.epam.poject.cruise.entity.Cruise;
import ua.epam.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface CruiseDao {

    int create(Cruise cruise) throws GeneralCheckedException;

    List<Cruise> findAll() throws GeneralCheckedException;

    Cruise findById(int id) throws GeneralCheckedException;

    int update(Cruise cruise) throws GeneralCheckedException;

    void close();
}
