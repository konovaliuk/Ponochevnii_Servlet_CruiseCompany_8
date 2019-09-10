package ua.study.poject.cruise.persistance.datasource;

import ua.study.poject.cruise.exceptions.GeneralCheckedException;

import java.sql.SQLException;

public interface Atomizer<T> extends AutoCloseable {
    T get();
    void recordChanges() throws GeneralCheckedException;
}
