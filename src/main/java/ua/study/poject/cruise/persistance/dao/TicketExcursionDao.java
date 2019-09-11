package ua.study.poject.cruise.persistance.dao;


import ua.study.poject.cruise.entity.TicketExcursion;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface TicketExcursionDao {

    int create(Long userId, Long excursionId) throws GeneralCheckedException;

    List<TicketExcursion> findByUserId(Long id) throws GeneralCheckedException;

    void close();
}
