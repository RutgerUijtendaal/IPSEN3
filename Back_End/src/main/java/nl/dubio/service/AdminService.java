package nl.dubio.service;

import nl.dubio.models.Admin;
import nl.dubio.persistance.AdminDao;
import nl.dubio.persistance.DaoRepository;

import java.util.List;

public class AdminService implements CrudService<Admin> {

    private final AdminDao adminDao;

    public AdminService() {
        this.adminDao = DaoRepository.getAdminDao();
    }

    @Override
    public List<Admin> getAll() {
        return adminDao.getAll();
    }

    @Override
    public Admin getById(Integer id) {
        return adminDao.getById(id);
    }

    @Override
    public Integer save(Admin admin) {
        return adminDao.save(admin);
    }

    @Override
    public boolean update(Admin Admin) {
        return adminDao.update(Admin);
    }

    @Override
    public boolean delete(Admin Admin) {
        return adminDao.delete(Admin);
    }

    @Override
    public boolean deleteById(Integer id) {
        return adminDao.deleteById(id);
    }
}
