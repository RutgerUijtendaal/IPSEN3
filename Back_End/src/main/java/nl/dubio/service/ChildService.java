package nl.dubio.service;

import nl.dubio.models.Child;
import nl.dubio.persistance.ChildDao;
import nl.dubio.persistance.DaoRepository;

import java.util.List;

public class ChildService implements CrudService<Child> {
    private final ChildDao dao;

    public ChildService() {
        this.dao = DaoRepository.getChildDao();
    }

    @Override
    public List<Child> getAll() {
        return dao.getAll();
    }

    @Override
    public Child getById(Integer id) {
        return dao.getById(id);
    }

    @Override
    public Integer save(Child child) {
        return dao.save(child);
    }

    @Override
    public boolean update(Child Child) {
        return dao.update(Child);
    }

    @Override
    public boolean delete(Child Child) {
        return dao.delete(Child);
    }

    @Override
    public boolean deleteById(Integer id) {
        return dao.deleteById(id);
    }
}
