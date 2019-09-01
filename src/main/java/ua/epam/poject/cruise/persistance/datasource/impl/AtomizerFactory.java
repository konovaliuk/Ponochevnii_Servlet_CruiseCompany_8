package ua.epam.poject.cruise.persistance.datasource.impl;

import ua.epam.poject.cruise.exceptions.GeneralCheckedException;
import ua.epam.poject.cruise.persistance.datasource.Atomizer;

public class AtomizerFactory {
    public static Atomizer getAtomizer() throws GeneralCheckedException {
        return new DBAtomizer();
    }
}
