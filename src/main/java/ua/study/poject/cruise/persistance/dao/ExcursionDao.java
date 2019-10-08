package ua.study.poject.cruise.persistance.dao;

import ua.study.poject.cruise.entity.Excursion;
import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.util.List;

/**
 * The interface ExcursionDao defines an interaction contract with a Excursion entity
 */
public interface ExcursionDao {

    /**
     * The create method saves the new Excursion entity, which is passed to it as a parameter.
     *
     * @param excurision new entity that needs to be stored
     * @return the id number of the entity under which it is stored
     * @throws GeneralCheckedException
     */
    int create(Excursion excurision) throws GeneralCheckedException;

    /**
     * The method findById returns a Excursion entity whose number is "id"
     *
     * @param id
     * @return Excursion entity
     * @throws GeneralCheckedException
     */
    Excursion findById(Long id) throws GeneralCheckedException;

    /**
     * This method allows to find all excursions in a given port
     *
     * @param portId
     * @return List of Excursion
     * @throws GeneralCheckedException
     */
    List<Excursion> findByPortId(Long portId) throws GeneralCheckedException;

    /**
     * The close method must be called after you have worked with this class to free resources
     */
    void close();

}
