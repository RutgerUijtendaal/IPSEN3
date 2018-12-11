package nl.dubio.service;

import nl.dubio.models.Right;
import nl.dubio.persistance.DaoRepository;
import nl.dubio.persistance.RightDao;

import java.util.List;

public class RightService implements CrudService<Right> {
    private final RightDao dao;

    public RightService() {
        this.dao = DaoRepository.getRightDao();
    }

    @Override
    public List<Right> getAll() {
        return dao.getAll();
    }

    @Override
    public Right getById(Integer id) {
        return dao.getById(id);
    }

    @Override
    public Integer save(Right right) {
        return dao.save(right);
    }

    @Override
    public boolean update(Right Right) {
        return dao.update(Right);
    }

    @Override
    public boolean delete(Right Right) {
        return dao.delete(Right);
    }

    @Override
    public boolean deleteById(Integer id) {
        return dao.deleteById(id);
    }
}
