package ua.epam.poject.cruise.persistance.dao;

import ua.epam.poject.cruise.entity.ShipService;
import ua.epam.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface ShipServiceDao {

    int create(ShipService shipService) throws GeneralCheckedException;

    List<ShipService> findByShipId(int id) throws GeneralCheckedException;

//    ShipService findById(int id) throws GeneralCheckedException;

    int update(ShipService shipService) throws GeneralCheckedException;

    // сначала надо удалить с TicketclassBonus
    int delete(ShipService shipService) throws GeneralCheckedException;

    void close();
}
