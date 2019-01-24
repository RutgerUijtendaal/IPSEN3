package nl.dubio.service;

import nl.dubio.models.Result;
import nl.dubio.persistance.DaoRepository;
import nl.dubio.persistance.ResultDao;

import java.util.List;

public class ResultService implements CrudService<Result> {
    private final ResultDao resultDao;

    public ResultService() {
        this.resultDao = DaoRepository.getResultDao();
    }

    public List<Result> getByParent(int parentId) {
        return resultDao.getByParentId(parentId);
    }

    @Override
    public List<Result> getAll() {
        return resultDao.getAll();
    }

    @Override
    public Result getById(Integer id) {
        return resultDao.getById(id);
    }

    @Override
    public Integer save(Result result) {
        return resultDao.save(result);
    }

    @Override
    public boolean update(Result result) {
        return resultDao.update(result);
    }

    @Override
    public boolean delete(Result result) {
        return resultDao.delete(result);
    }

    @Override
    public boolean deleteById(Integer id) {
        return resultDao.deleteById(id);
    }

    @Override
    public List<String> validate(Result result) {
        return null;
    }
}
