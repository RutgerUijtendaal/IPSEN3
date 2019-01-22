package nl.dubio.service;

import nl.dubio.models.Answer;
import nl.dubio.persistance.AnswerDao;
import nl.dubio.persistance.DaoRepository;

import java.util.List;

public class AnswerService implements CrudService<Answer> {
    private final AnswerDao answerDao;

    public AnswerService() {
        this.answerDao = DaoRepository.getAnswerDao();
    }

    @Override
    public List<Answer> getAll() {
        return answerDao.getAll();
    }

    @Override
    public Answer getById(Integer id) {
        return answerDao.getById(id);
    }

    @Override
    public Integer save(Answer answer) {
        return answerDao.save(answer);
    }

    @Override
    public boolean update(Answer answer) {
        return answerDao.update(answer);
    }

    @Override
    public boolean delete(Answer answer) {
        return answerDao.delete(answer);
    }

    @Override
    public boolean deleteById(Integer id) {
        return answerDao.deleteById(id);
    }
}
