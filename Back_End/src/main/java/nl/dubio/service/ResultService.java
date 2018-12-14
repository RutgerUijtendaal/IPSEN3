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
    public boolean update(Result Result) {
        return resultDao.update(Result);
    }

    @Override
    public boolean delete(Result Result) {
        return resultDao.delete(Result);
    }

    @Override
    public boolean deleteById(Integer id) {
        return resultDao.deleteById(id);
    }
}
