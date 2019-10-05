package ua.study.poject.cruise.persistance.datasource;

import ua.study.poject.cruise.exceptions.GeneralCheckedException;
import ua.study.poject.cruise.persistance.datasource.impl.AtomizerFactory;

import java.sql.SQLException;

/**
 * The Atomizer interface is designed to organize the atomicity of writing data to the storage.
 * In the case of using the database, this interface is a wrapper for Connection,
 * which ensures that all DAO implementations will use the same Connection,
 * this will make it possible to write to all tables atomically.
 * This class implements the AutoCloseable interface, so the close method will be
 * called automatically if you use try with resources.
 * The close method discards all changes and releases resources.
 * To record changes, you must call the recordChanges method.
 * If this is not done, then when the close method is executed, all changes will be discarded
 *
 * @see AtomizerFactory
 */
public interface Atomizer extends AutoCloseable {

    /**
     * The method "get" returns an object, that allow to organize atomicity.
     * For example, if you are using databases, this method will return Connection
     * @param <T>
     * @return
     */
    <T> T get();

    /**
     * To record changes, you must call the recordChanges method.
     * If this is not done, then when the close method is executed, all changes will be discarded
     * @throws GeneralCheckedException
     */
    void recordChanges() throws GeneralCheckedException;
}
