package nl.dubio.service;

import nl.dubio.exceptions.ClientException;
import nl.dubio.exceptions.InvalidInputException;
import nl.dubio.models.*;
import nl.dubio.models.databag.AnswerDilemmaDatabag;
import nl.dubio.persistance.DaoRepository;
import nl.dubio.persistance.DilemmaDao;

import java.util.ArrayList;
import java.util.List;

public class DilemmaService implements CrudService<Dilemma> {

    private final static int maxThemeSize = 50;
    private final static int maxFeedbackSize = 200;
    private final static int maxPeriodSize = 200;

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

    public Dilemma getByWeekNr(short week, String period) {
        return dilemmaDao.getByWeekNr(week, period);
    }

    public AnswerDilemmaDatabag getByParentToken(String token) throws ClientException {
        ParentDataService parentService = new ParentDataService();
        CoupleService coupleService = new CoupleService();
        ChildService childService = new ChildService();
        ResultService resultService = new ResultService();

        AnswerService answerService = new AnswerService();

        Parent parent = parentService.getByToken(token);
        Couple couple = coupleService.getByParent(parent);
        Child child = childService.getByCouple(couple);

        Result result = resultService.getRecentResultOfParent(parent);

        Dilemma dilemma;

        int age = child.getAgeInWeeks(result.getSentTime());

        age = (age == 0) ? 1 : age;

        if (child.getIsBorn())
            dilemma = dilemmaDao.getByWeekNr(age, "voor");
        else
            dilemma = dilemmaDao.getByWeekNr(age, "na");

        Answer[] answers;

        if(dilemma != null )
            answers = answerService.getByDilemma(dilemma);
        else
            throw new ClientException(404, "No dilemma for this age");


        return new AnswerDilemmaDatabag(dilemma, answers);
    }

    public boolean validAnswer(String token, int answerId) throws ClientException {
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
    public Integer save(Dilemma dilemma) throws InvalidInputException {
        List<String> errors = validate(dilemma);

        if (errors.size() > 0)
            throw new InvalidInputException(errors);

        return dilemmaDao.save(dilemma);
    }

    @Override
    public boolean update(Dilemma dilemma) throws InvalidInputException {
        List<String> errors = validate(dilemma);

        if (errors.size() > 0)
            throw new InvalidInputException(errors);

        return dilemmaDao.update(dilemma);
    }

    @Override
    public boolean delete(Dilemma dilemma) {
        return dilemmaDao.delete(dilemma);
    }

    @Override
    public boolean deleteById(Integer id) {
        return dilemmaDao.deleteById(id);
    }

    @Override
    public List<String> validate(Dilemma dilemma) {
        List<String> errors = new ArrayList<>();

        if (dilemma.getWeekNr() < 0)
            errors.add("Invalid week number");
        if (dilemma.getTheme().length() > maxThemeSize)
            errors.add("Theme is longer then " + maxThemeSize + " characters");
        if (dilemma.getFeedback().length() > maxFeedbackSize)
            errors.add("Feedback is longer then " + maxFeedbackSize + " characters");
        if (dilemma.getPeriod().length() > maxPeriodSize)
            errors.add("Period is longer then " + maxPeriodSize + " characters");

        return errors;
    }
}
