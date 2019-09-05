package ua.epam.poject.cruise.persistance.dao;

import ua.epam.poject.cruise.entity.Port;
import ua.epam.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface PortDao {

    int create(Port port) throws GeneralCheckedException;

    List<Port> findAll() throws GeneralCheckedException;

    Port findById(Long id) throws GeneralCheckedException;

    int update(Port port) throws GeneralCheckedException;

    void close();
}
