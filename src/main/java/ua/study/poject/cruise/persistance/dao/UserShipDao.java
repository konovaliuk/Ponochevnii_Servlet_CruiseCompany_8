package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.UserShip;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface UserShipDao {

    int create(Long shipId, Long userId) throws GeneralCheckedException;

    List<UserShip> findByUserId(Long userId) throws GeneralCheckedException;

    List<UserShip> findByShipId(Long shipId) throws GeneralCheckedException;

    int delete(UserShip userShip) throws GeneralCheckedException;

    void close();
}