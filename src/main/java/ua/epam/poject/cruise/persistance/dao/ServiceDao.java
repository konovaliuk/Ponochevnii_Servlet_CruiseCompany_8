package ua.epam.poject.cruise.persistance.dao;

import ua.epam.poject.cruise.entity.Service;
import ua.epam.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface ServiceDao {

    int create(Service service) throws GeneralCheckedException;

    List<Service> findAll() throws GeneralCheckedException;

    Service findById(int id) throws GeneralCheckedException;

    int update(Service service) throws GeneralCheckedException;

    void close();
}
