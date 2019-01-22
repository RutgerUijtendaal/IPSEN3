package nl.dubio.service;

import nl.dubio.models.Child;
import nl.dubio.persistance.ChildDao;
import nl.dubio.persistance.DaoRepository;

import java.util.List;

public class ChildService implements CrudService<Child> {
    private final ChildDao childDao;

    public ChildService() {
        this.childDao = DaoRepository.getChildDao();
    }

    @Override
    public List<Child> getAll() {
        return childDao.getAll();
    }

    @Override
    public Child getById(Integer id) {
        return childDao.getById(id);
    }

    @Override
    public Integer save(Child child) {
        return childDao.save(child);
    }

    @Override
    public boolean update(Child child) {
        return childDao.update(child);
    }

    @Override
    public boolean delete(Child child) {
        return childDao.delete(child);
    }

    @Override
    public boolean deleteById(Integer id) {
        return childDao.deleteById(id);
    }
}
