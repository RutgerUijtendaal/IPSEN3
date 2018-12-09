package nl.dubio.service;

import nl.dubio.models.Parent;

import java.util.List;

public class ParentService implements CrudService<Parent> {
    @Override
    public List<Parent> getAll() {
        return null;
    }

    @Override
    public Parent getById(Integer id) {
        return null;
    }

    @Override
    public Integer save(Parent parent) {
        return null;
    }

    @Override
    public boolean update(Parent parent) {
        return false;
    }

    @Override
    public boolean delete(Parent parent) {
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }
}
