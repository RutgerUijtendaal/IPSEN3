package nl.dubio.service;

import nl.dubio.models.Answer;
import nl.dubio.persistance.AnswerDao;
import nl.dubio.persistance.DaoRepository;

import java.util.List;

public class AnswerService implements CrudService<Answer> {
    private final AnswerDao dao;

    public AnswerService() {
        this.dao = DaoRepository.getAnswerDao();
    }

    @Override
    public List<Answer> getAll() {
        return dao.getAll();
    }

    @Override
    public Answer getById(Integer id) {
        return dao.getById(id);
    }

    @Override
    public Integer save(Answer answer) {
        return dao.save(answer);
    }

    @Override
    public boolean update(Answer Answer) {
        return dao.update(Answer);
    }

    @Override
    public boolean delete(Answer Answer) {
        return dao.delete(Answer);
    }

    @Override
    public boolean deleteById(Integer id) {
        return dao.deleteById(id);
    }
}
