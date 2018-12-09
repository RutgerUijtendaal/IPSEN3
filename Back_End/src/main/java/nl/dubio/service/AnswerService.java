package nl.dubio.service;

import nl.dubio.models.Answer;

import java.util.List;

public class AnswerService implements CrudService<Answer> {
    @Override
    public List<Answer> getAll() {
        return null;
    }

    @Override
    public Answer getById(Integer id) {
        return null;
    }

    @Override
    public Integer save(Answer answer) {
        return null;
    }

    @Override
    public boolean update(Answer answer) {
        return false;
    }

    @Override
    public boolean delete(Answer answer) {
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }
}
