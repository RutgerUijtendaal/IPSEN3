package nl.dubio.service;

import nl.dubio.ApiApplication;
import nl.dubio.models.Answer;
import nl.dubio.models.Couple;
import nl.dubio.models.Parent;
import nl.dubio.models.Result;
import nl.dubio.persistance.DaoRepository;
import nl.dubio.persistance.ParentDao;
import nl.dubio.persistance.ResultDao;
import nl.dubio.utils.MailUtility;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
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
        return resultDao.getByParentId(parent.getId());
    }

    public void updateResult(String token, int answerId) {
        ParentService parentService = new ParentService();
        CoupleService coupleService = new CoupleService();

        Parent parent = parentService.getByToken(token);
        Couple couple = coupleService.getByParent(parent);

        Result result = getByParent(parent);

        result.setAnswerId(answerId);
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

            try {
                MailUtility mailUtility = ApiApplication.getMailUtility();
                mailUtility.addFeedbackMailToQueue(
                        parentOne.getEmail(),
                        parentOne.getName(), parentTwo.getName(),
                        answerOne.getText(), answerTwo.getText(),
                        couple.getToken()
                );

                mailUtility.addFeedbackMailToQueue(
                        parentTwo.getEmail(),
                        parentTwo.getName(), parentOne.getName(),
                        answerTwo.getText(), answerOne.getText(),
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

    @Override
    public Integer save(Result result) {
        return resultDao.save(result);
    }

    @Override
    public boolean update(Result Result) {
        return resultDao.update(Result);
    }

    @Override
    public boolean delete(Result Result) {
        return resultDao.delete(Result);
    }

    @Override
    public boolean deleteById(Integer id) {
        return resultDao.deleteById(id);
    }
}
