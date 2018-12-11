package nl.dubio.service;

import nl.dubio.models.Result;
import nl.dubio.persistance.DaoRepository;
import nl.dubio.persistance.ResultDao;

import java.util.List;

public class ResultService implements CrudService<Result> {
    private final ResultDao dao;

    public ResultService() {
        this.dao = DaoRepository.getResultDao();
    }

    @Override
    public List<Result> getAll() {
        return dao.getAll();
    }

    @Override
    public Result getById(Integer id) {
        return dao.getById(id);
    }

    @Override
    public Integer save(Result result) {
        return dao.save(result);
    }

    @Override
    public boolean update(Result Result) {
        return dao.update(Result);
    }

    @Override
    public boolean delete(Result Result) {
        return dao.delete(Result);
    }

    @Override
    public boolean deleteById(Integer id) {
        return dao.deleteById(id);
    }
}
