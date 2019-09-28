package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.Service;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface ServiceDao {

    int create(Service service) throws GeneralCheckedException;

    List<Service> findAll() throws GeneralCheckedException;

    Service findById(Long id) throws GeneralCheckedException;

    Service findByName(String serviceName) throws GeneralCheckedException;

    void close();
}
