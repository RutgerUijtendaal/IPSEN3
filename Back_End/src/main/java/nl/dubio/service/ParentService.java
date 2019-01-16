package nl.dubio.service;

import nl.dubio.models.Parent;
import nl.dubio.persistance.DaoRepository;
import nl.dubio.persistance.ParentDao;

import java.util.List;

public class ParentService implements CrudService<Parent> {
    private final ParentDao parentDao;

    public ParentService() {
        this.parentDao = DaoRepository.getParentDao();
    }

    @Override
    public List<Parent> getAll() {
        return parentDao.getAll();
    }

    @Override
    public Parent getById(Integer id) {
        Parent parent = parentDao.getById(id);
        System.out.println(parent);
        return parent;
    }

    @Override
    public Integer save(Parent child) {
        return parentDao.save(child);
    }

    @Override
    public boolean update(Parent Parent) {
        return parentDao.update(Parent);
    }

    @Override
    public boolean delete(Parent Parent) {
        return parentDao.delete(Parent);
    }

    @Override
    public boolean deleteById(Integer id) {
        return parentDao.deleteById(id);
    }
}
