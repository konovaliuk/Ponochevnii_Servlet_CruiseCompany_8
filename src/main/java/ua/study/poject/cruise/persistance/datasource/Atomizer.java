package ua.study.poject.cruise.persistance.datasource;

import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.sql.SQLException;

public interface Atomizer extends AutoCloseable {
    <T> T get();
    void recordChanges() throws GeneralCheckedException;
}
