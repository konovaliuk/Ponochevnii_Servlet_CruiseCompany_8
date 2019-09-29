package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.Excursion;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface ExcursionDao {

    int create(Excursion excurision) throws GeneralCheckedException;

    List<Excursion> findAll() throws GeneralCheckedException;

    Excursion findById(Long id) throws GeneralCheckedException;

    int update(Excursion excurision) throws GeneralCheckedException;

    List<Excursion> findByPortId(Long portId) throws GeneralCheckedException;

    void close();

}
