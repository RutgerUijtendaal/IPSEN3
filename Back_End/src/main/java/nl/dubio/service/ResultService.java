package nl.dubio.service;

import nl.dubio.models.Result;

import java.util.List;

public class ResultService implements CrudService<Result> {
    @Override
    public List<Result> getAll() {
        return null;
    }

    @Override
    public Result getById(Integer id) {
        return null;
    }

    @Override
    public Integer save(Result result) {
        return null;
    }

    @Override
    public boolean update(Result result) {
        return false;
    }

    @Override
    public boolean delete(Result result) {
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }
}
