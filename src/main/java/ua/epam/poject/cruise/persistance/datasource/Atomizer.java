package ua.epam.poject.cruise.persistance.datasource;

import ua.epam.poject.cruise.exceptions.GeneralCheckedException;

import java.sql.SQLException;

public interface Atomizer<T> extends AutoCloseable {
    T get();
    void recordChanges() throws SQLException, GeneralCheckedException;
}
