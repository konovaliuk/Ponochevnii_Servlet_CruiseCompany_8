package ua.study.poject.cruise.persistance.datasource.impl;

import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.datasource.Atomizer;

public class AtomizerFactory {
    public static Atomizer getAtomizer() throws GeneralCheckedException {
        return new DBAtomizer();
    }
}
