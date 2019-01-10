package nl.dubio.persistance;

import java.util.List;

/**
 *interface of all databse-view persistance
 *
 * defines only the select methods a databse-view DAO must implement
 * since views can only be read in postgresql
 *
 * @param <T> class of the model the crudService interacts with
 */
interface DatabaseViewDao<T>{
    List<T> getAll();

    T getById(int id);
}
