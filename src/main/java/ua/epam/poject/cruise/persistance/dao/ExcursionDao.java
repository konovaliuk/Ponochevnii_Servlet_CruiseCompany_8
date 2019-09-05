package ua.epam.poject.cruise.persistance.dao;

import ua.epam.poject.cruise.entity.Excurision;
import ua.epam.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface ExcursionDao {

    int create(Excurision excurision) throws GeneralCheckedException;

    List<Excurision> findAll() throws GeneralCheckedException;

    Excurision findById(Long id) throws GeneralCheckedException;

    int update(Excurision excurision) throws GeneralCheckedException;

    void close();
}
