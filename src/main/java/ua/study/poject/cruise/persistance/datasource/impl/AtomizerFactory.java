package ua.study.poject.cruise.persistance.datasource.impl;

import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.datasource.Atomizer;

/**
 * This factory class is used to create Atomizer
 */
public class AtomizerFactory {

    /**
     * Static method is used to create new Atomizer
     *
     * @return new Atomizer
     * @throws GeneralCheckedException
     */
    public static Atomizer getAtomizer() throws GeneralCheckedException {
        return new DBAtomizer();
    }
}
