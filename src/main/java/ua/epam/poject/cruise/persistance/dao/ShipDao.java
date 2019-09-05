package ua.epam.poject.cruise.persistance.dao;

import ua.epam.poject.cruise.entity.Ship;
import ua.epam.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface ShipDao {

    int create(Ship ship) throws GeneralCheckedException;

    List<Ship> findAll() throws GeneralCheckedException;

    Ship findById(Long id) throws GeneralCheckedException;

    int update(Ship ship) throws GeneralCheckedException;

    void close();
}
