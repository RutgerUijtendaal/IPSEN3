package nl.dubio.service;

import nl.dubio.models.Right;
import nl.dubio.persistance.DaoRepository;
import nl.dubio.persistance.RightDao;

import java.util.List;

public class RightService implements CrudService<Right> {
    private final RightDao rightDao;

    public RightService() {
        this.rightDao = DaoRepository.getRightDao();
    }

    @Override
    public List<Right> getAll() {
        return rightDao.getAll();
    }

    @Override
    public Right getById(Integer id) {
        return rightDao.getById(id);
    }

    @Override
    public Integer save(Right right) {
        return rightDao.save(right);
    }

    @Override
    public boolean update(Right right) {
        return rightDao.update(right);
    }

    @Override
    public boolean delete(Right right) {
        return rightDao.delete(right);
    }

    @Override
    public boolean deleteById(Integer id) {
        return rightDao.deleteById(id);
    }
}
