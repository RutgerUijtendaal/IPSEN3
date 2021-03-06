package nl.dubio.service;

import nl.dubio.exceptions.InvalidInputException;
import nl.dubio.models.Answer;
import nl.dubio.models.Dilemma;
import nl.dubio.persistance.AnswerDao;
import nl.dubio.persistance.DaoRepository;
import nl.dubio.persistance.DilemmaDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnswerService implements CrudService<Answer> {

    private final static int maxTextSize = 200;

    private final AnswerDao answerDao;
    private final DilemmaDao dilemmaDao;

    public AnswerService() {
        this.answerDao = DaoRepository.getAnswerDao();
        dilemmaDao = DaoRepository.getDilemmaDao();
    }

    @Override
    public List<Answer> getAll() {
        return answerDao.getAll();
    }

    @Override
    public Answer getById(Integer id) {
        return answerDao.getById(id);
    }

    public Answer[] getByDilemma(Dilemma dilemma) { return answerDao.getByDilemmaId(dilemma.getId()); }

    @Override
    public Integer save(Answer answer) throws InvalidInputException {
        List<String> errors = validate(answer);

        if (errors.size() > 0)
            throw new InvalidInputException(errors);

        return answerDao.save(answer);
    }

    @Override
    public boolean update(Answer answer) throws InvalidInputException {
        List<String> errors = validate(answer);

        if (errors.size() > 0)
            throw new InvalidInputException(errors);

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

    @Override
    public List<String> validate(Answer answer) {
        List<String> errors = new ArrayList<>();

        List<String> allowedExtensions = new ArrayList<>(
                Arrays.asList(".png", ".jpg", null)
        );

        if (! dilemmaDao.idExists(answer.getDilemmaId()))
            errors.add("Invalid dilemma id");
        if (! allowedExtensions.contains(answer.getExtension()))
            errors.add("Invalid file-extension");
        if (answer.getText().length() > maxTextSize)
            errors.add("Text longer then " + maxTextSize + " characters");

        return errors;
    }
}
