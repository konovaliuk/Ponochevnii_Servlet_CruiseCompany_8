package ua.epam.poject.cruise.persistance.dao;

import ua.epam.poject.cruise.entity.Service;
import ua.epam.poject.cruise.entity.Shipservice;
import ua.epam.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface ShipserviceDao {

    int create(Shipservice shipService) throws GeneralCheckedException;

//    Shipservice findById(int id) throws GeneralCheckedException;

    boolean isServicePresentOnThisShip(Long selectedShipId, Long selectedServiceId) throws GeneralCheckedException;

    int deleteByShipIdServiceId(Long shipId, Long serviceId) throws GeneralCheckedException;

    List<Long> findAllIdByShipIdServiceId(Long shipId, Long serviceId) throws GeneralCheckedException;

    void close();
}
