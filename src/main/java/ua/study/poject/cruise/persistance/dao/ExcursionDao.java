package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.Excurision;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

public interface ExcursionDao {

    int create(Excurision excurision) throws GeneralCheckedException;

    List<Excurision> findAll() throws GeneralCheckedException;

    Excurision findById(Long id) throws GeneralCheckedException;

    int update(Excurision excurision) throws GeneralCheckedException;

    List<Excurision> findByPortId(Long portId) throws GeneralCheckedException;

    void close();

}
