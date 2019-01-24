package nl.dubio.service;

import nl.dubio.exceptions.InvalidInputException;
import nl.dubio.models.Admin;
import nl.dubio.models.DatabaseObject;

import java.util.List;

public interface CrudService<T extends DatabaseObject<T>> {

     List<T> getAll();

     T getById(Integer id);

     Integer save(T t) throws InvalidInputException;

     boolean update(T t) throws InvalidInputException;

     boolean delete(T t);

     boolean deleteById(Integer id);

    List<String> validate(T t);
}
