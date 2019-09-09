package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.Shipservice;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface ShipserviceDao {

    int create(Shipservice shipService) throws GeneralCheckedException;

//    Shipservice findById(int id) throws GeneralCheckedException;

    boolean isServicePresentOnThisShip(Long selectedShipId, Long selectedServiceId) throws GeneralCheckedException;

    int deleteByShipIdServiceId(Long shipId, Long serviceId) throws GeneralCheckedException;

    List<Long> findAllIdByShipIdServiceId(Long shipId, Long serviceId) throws GeneralCheckedException;

    List<Long> findAllIdByShipId(Long shipId) throws GeneralCheckedException;

    void close();
}
