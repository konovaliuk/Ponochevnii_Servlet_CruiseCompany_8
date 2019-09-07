package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.Port;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface PortDao {

    int create(Port port) throws GeneralCheckedException;

    List<Port> findAll() throws GeneralCheckedException;

    Port findById(Long id) throws GeneralCheckedException;

    int update(Port port) throws GeneralCheckedException;

    void close();
}
