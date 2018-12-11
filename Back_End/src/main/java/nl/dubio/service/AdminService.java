package nl.dubio.service;

import nl.dubio.models.Admin;
import nl.dubio.persistance.AdminDao;
import nl.dubio.persistance.DaoRepository;

import java.util.List;

public class AdminService implements CrudService<Admin> {

    private final AdminDao dao;

    public AdminService() {
        this.dao = DaoRepository.getAdminDao();
    }

    @Override
    public List<Admin> getAll() {
        return dao.getAll();
    }

    @Override
    public Admin getById(Integer id) {
        return dao.getById(id);
    }

    @Override
    public Integer save(Admin admin) {
        return dao.save(admin);
    }

    @Override
    public boolean update(Admin Admin) {
        return dao.update(Admin);
    }

    @Override
    public boolean delete(Admin Admin) {
        return dao.delete(Admin);
    }

    @Override
    public boolean deleteById(Integer id) {
        return dao.deleteById(id);
    }
}
