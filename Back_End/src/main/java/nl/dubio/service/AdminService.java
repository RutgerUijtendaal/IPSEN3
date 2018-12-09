package nl.dubio.service;

import nl.dubio.models.Admin;

import java.util.List;

public class AdminService implements CrudService<Admin> {
    @Override
    public List<Admin> getAll() {
        return null;
    }

    @Override
    public Admin getById(Integer id) {
        return null;
    }

    @Override
    public Integer save(Admin admin) {
        return null;
    }

    @Override
    public boolean update(Admin admin) {
        return false;
    }

    @Override
    public boolean delete(Admin admin) {
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }
}
