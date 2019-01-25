package nl.dubio.service;

import nl.dubio.ApiApplication;
import nl.dubio.exceptions.InvalidInputException;
import nl.dubio.models.Couple;
import nl.dubio.models.Dilemma;
import nl.dubio.models.Parent;
import nl.dubio.persistance.DaoRepository;
import nl.dubio.persistance.ParentDao;
import nl.dubio.utils.MailUtility;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

public class ParentService implements CrudService<Parent> {
    private final ParentDao parentDao;

    public ParentService() {
        this.parentDao = DaoRepository.getParentDao();
    }

    @Override
    public List<Parent> getAll() {
        return parentDao.getAll();
    }

    @Override
    public Parent getById(Integer id) {
        Parent parent = parentDao.getById(id);
        System.out.println(parent);
        return parent;
    }

    public Parent getByToken(String token) { return parentDao.getByToken(token); }

    public boolean revokeTokenAccess(Parent parent) {
        parent.setToken(null);
        return parentDao.update(parent);
    }

    public void notifyDilemmaReady(Parent parent, Dilemma dilemma, String unregisterToken) {
        MailUtility mailUtility = ApiApplication.getMailUtility();

        try {
            mailUtility.addDilemmaReadyToQueue(
                    parent.getEmail(),
                    parent.getFirstName(),
                    dilemma.getTheme(),
                    parent.getToken(),
                    unregisterToken);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer save(Parent parent) throws InvalidInputException {
        List<String> errors = validate(parent);

        if (errors.size() > 0)
            throw new InvalidInputException(errors);

        return parentDao.save(parent);
    }

    @Override
    public boolean update(Parent parent) throws InvalidInputException {
        List<String> errors = validate(parent);

        if (errors.size() > 0)
            throw new InvalidInputException(errors);

        return parentDao.update(parent);
    }

    @Override
    public boolean delete(Parent parent) {
        return parentDao.delete(parent);
    }

    @Override
    public boolean deleteById(Integer id) {
        return parentDao.deleteById(id);
    }

    @Override
    public List<String> validate(Parent parent) {
        List<String> errors = new ArrayList<>();

        if (! ValidationService.isValidName(parent.getFirstName()))
            errors.add("Invalid name");
        if (! ValidationService.isValidEmail(parent.getEmail()))
            errors.add("Invalid email");
        if (! ValidationService.isValidPhone(parent.getPhoneNr()))
            errors.add("Invalid phone number");

        return errors;
    }
}
