package nl.dubio.service;

import nl.dubio.models.Parent;
import nl.dubio.persistance.DaoRepository;
import nl.dubio.persistance.ParentDao;

import java.util.List;

public class ParentService implements CrudService<Parent> {
    private final ParentDao dao;

    public ParentService() {
        this.dao = DaoRepository.getParentDao();
    }

    @Override
    public List<Parent> getAll() {
        return dao.getAll();
    }

    @Override
    public Parent getById(Integer id) {
        return dao.getById(id);
    }

    @Override
    public Integer save(Parent child) {
        return dao.save(child);
    }

    @Override
    public boolean update(Parent Parent) {
        return dao.update(Parent);
    }

    @Override
    public boolean delete(Parent Parent) {
        return dao.delete(Parent);
    }

    @Override
    public boolean deleteById(Integer id) {
        return dao.deleteById(id);
    }
}
