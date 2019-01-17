package nl.dubio.service;

import nl.dubio.models.*;
import nl.dubio.models.databag.AnswerDilemmaDatabag;
import nl.dubio.persistance.*;

import java.util.List;

public class DilemmaService implements CrudService<Dilemma> {

    private final DilemmaDao dilemmaDao;

    public DilemmaService() {
        this.dilemmaDao = DaoRepository.getDilemmaDao();
    }

    @Override
    public List<Dilemma> getAll() {
        return dilemmaDao.getAll();
    }

    @Override
    public Dilemma getById(Integer id) {
        return dilemmaDao.getById(id);
    }

    public AnswerDilemmaDatabag getByParentToken(String token) {
        ParentService parentService = new ParentService();
        CoupleService coupleService = new CoupleService();
        ChildService childService = new ChildService();

        AnswerService answerService = new AnswerService();

        Parent parent = parentService.getByToken(token);
        Couple couple = coupleService.getByParent(parent);
        Child child = childService.getByCouple(couple);

        Dilemma dilemma;

        int age = child.getAgeInWeeks();

        if (child.getIsBorn())
            dilemma = dilemmaDao.getByWeekNr(age, "voor");
        else
            dilemma = dilemmaDao.getByWeekNr(age, "na");

        Answer[] answers = answerService.getByDilemma(dilemma);

        return new AnswerDilemmaDatabag(dilemma, answers);
    }

    public boolean validAnswer(String token, int answerId) {
        boolean valid = false;

        AnswerDilemmaDatabag databag = getByParentToken(token);
        Answer[] answers = databag.getAnswers();

        for (Answer answer : answers) {
            if (answer.getId() == answerId) {
                valid = true;
                break;
            }
        }

        return valid;
    }

    @Override
    public Integer save(Dilemma dilemma) {
        return dilemmaDao.save(dilemma);
    }

    @Override
    public boolean update(Dilemma dilemma) {
        System.out.println("UPDATE");
        System.out.println(dilemma);
        return dilemmaDao.update(dilemma);
    }

    @Override
    public boolean delete(Dilemma Dilemma) {
        return dilemmaDao.delete(Dilemma);
    }

    @Override
    public boolean deleteById(Integer id) {
        return dilemmaDao.deleteById(id);
    }
}
