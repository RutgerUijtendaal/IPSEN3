package nl.dubio.service;

import nl.dubio.models.Child;

import java.util.List;

public class ChildService implements CrudService<Child> {
    @Override
    public List<Child> getAll() {
        return null;
    }

    @Override
    public Child getById(Integer id) {
        return null;
    }

    @Override
    public Integer save(Child child) {
        return null;
    }

    @Override
    public boolean update(Child child) {
        return false;
    }

    @Override
    public boolean delete(Child child) {
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }
}
