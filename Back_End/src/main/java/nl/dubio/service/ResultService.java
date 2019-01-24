package nl.dubio.service;

import nl.dubio.ApiApplication;
import nl.dubio.models.*;
import nl.dubio.persistance.DaoRepository;
import nl.dubio.persistance.DilemmaDao;
import nl.dubio.persistance.ResultDao;
import nl.dubio.utils.MailUtility;

import javax.mail.MessagingException;
import java.sql.Timestamp;
import java.util.List;

public class ResultService implements CrudService<Result> {
    private final ResultDao resultDao;

    public ResultService() {
        this.resultDao = DaoRepository.getResultDao();
    }

    public List<Result> getByParent(int parentId) {
        return resultDao.getByParentId(parentId);
    }

    @Override
    public List<Result> getAll() {
        return resultDao.getAll();
    }

    @Override
    public Result getById(Integer id) {
        return resultDao.getById(id);
    }

    public Result getByParent(Parent parent) {
        return resultDao.getByParentId(parent.getId()).get(0);
    }

    public void updateResult(String token, int answerId) {
        ParentService parentService = new ParentService();
        CoupleService coupleService = new CoupleService();

        Parent parent = parentService.getByToken(token);
        Couple couple = coupleService.getByParent(parent);

        Result result = getRecentResultOfParent(parent);

        result.setAnswerId(answerId);
        result.setAnsweredTime(new Timestamp(System.currentTimeMillis()));

        update(result);

        // Revoke access token
        parentService.revokeTokenAccess(parent);

        // Check if both parents have answered
        if (bothHasAnswered(couple)) {
            Parent parentOne = parentService.getById(couple.getParent1Id());
            Parent parentTwo = parentService.getById(couple.getParent2Id());

            Result resultOne = getByParent(parentOne);
            Result resultTwo = getByParent(parentTwo);

            AnswerService answerService = new AnswerService();
            Answer answerOne = answerService.getById(resultOne.getAnswerId());
            Answer answerTwo = answerService.getById(resultTwo.getAnswerId());

            DilemmaDao dilemmaDao = DaoRepository.getDilemmaDao();
            Dilemma dilemma = dilemmaDao.getById(answerOne.getDilemmaId());

            try {
                MailUtility mailUtility = ApiApplication.getMailUtility();
                mailUtility.addFeedbackMailToQueue(
                        parentOne.getEmail(),
                        parentOne.getFirstName(), parentTwo.getFirstName(),
                        dilemma.getTheme(),
                        answerOne.getText(), answerTwo.getText(),
                        dilemma.getFeedback(),
                        couple.getToken()
                );

                mailUtility.addFeedbackMailToQueue(
                        parentTwo.getEmail(),
                        parentTwo.getFirstName(), parentOne.getFirstName(),
                        dilemma.getTheme(),
                        answerTwo.getText(), answerOne.getText(),
                        dilemma.getFeedback(),
                        couple.getToken()
                );
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean bothHasAnswered(Couple couple) {
        return resultDao.isDilemmaAnswered(couple.getParent1Id()) && resultDao.isDilemmaAnswered(couple.getParent2Id());
    }

    public Result getRecentResultOfParent(Parent parent) {
        return resultDao.getRecentByParentId(parent.getId());
    }

    @Override
    public Integer save(Result result) {
        return resultDao.save(result);
    }

    @Override
    public boolean update(Result result) {
        return resultDao.update(result);
    }

    @Override
    public boolean delete(Result result) {
        return resultDao.delete(result);
    }

    @Override
    public boolean deleteById(Integer id) {
        return resultDao.deleteById(id);
    }

    @Override
    public List<String> validate(Result result) {
        return null;
    }
}
