package nl.dubio.service;

import nl.dubio.models.DatabaseObject;

import java.util.List;

public interface CrudService<T extends DatabaseObject<T>> {

     List<T> getAll();

     T getById(Integer id);

     Integer save(T t);

     boolean update(T t);

     boolean delete(T t);

     boolean deleteById(Integer id);

}
