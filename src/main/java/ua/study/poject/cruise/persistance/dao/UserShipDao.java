package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.Ship;
import ua.study.poject.cruise.entity.UserShip;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;
import java.util.Set;

public interface UserShipDao {

    int create(Long shipId, Long userId) throws GeneralCheckedException;

    List<UserShip> findByUserId(Long userId) throws GeneralCheckedException;

    List<UserShip> findByShipId(Long shipId) throws GeneralCheckedException;

    Set<Ship> findShipsByUserId(Long userId) throws GeneralCheckedException;

    int delete(UserShip userShip) throws GeneralCheckedException;

    void close();
}
