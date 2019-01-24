package nl.dubio.service;

import nl.dubio.exceptions.InvalidInputException;
import nl.dubio.models.Answer;
import nl.dubio.persistance.AnswerDao;
import nl.dubio.persistance.DaoRepository;
import nl.dubio.persistance.DilemmaDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnswerService implements CrudService<Answer> {

    //TODO should come from configuration
    public final static int maxTextSize = 200;

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

    @Override
    public Integer save(Answer answer) throws InvalidInputException {
        List<String> errors = validate(answer);

        if (errors.size() > 0)
            throw new InvalidInputException(errors);

//        return answerDao.save(answer);
        return -1;
    }

    @Override
    public boolean update(Answer answer) throws InvalidInputException {
        List<String> errors = validate(answer);

        if (errors.size() > 0)
            throw new InvalidInputException(errors);

//        return answerDao.update(answer);
        return true;
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

        //TODO allowed extensions should come from configuration
        List<String> allowedExtensions = new ArrayList<>(
                Arrays.asList(".png", ".jpg")
        );

        if (! dilemmaDao.idExists(answer.getDilemmaId()) )
            errors.add("Invalid dilemma id");
        if(! allowedExtensions.contains(answer.getExtension()))
            errors.add("Invalid file-extension");
        if( answer.getText().length() > maxTextSize )
            errors.add("Text longer then " + maxTextSize + " characters");

        return errors;
    }
}
