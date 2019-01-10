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
    public boolean update(Child Child) {
        return childDao.update(Child);
    }

    @Override
    public boolean delete(Child Child) {
        return childDao.delete(Child);
    }

    @Override
    public boolean deleteById(Integer id) {
        return childDao.deleteById(id);
    }
}
