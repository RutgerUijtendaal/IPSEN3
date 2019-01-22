package nl.dubio.service;

import nl.dubio.ApiApplication;
import nl.dubio.models.Couple;
import nl.dubio.models.Dilemma;
import nl.dubio.models.Parent;
import nl.dubio.persistance.DaoRepository;
import nl.dubio.persistance.ParentDao;
import nl.dubio.utils.MailUtility;

import javax.mail.MessagingException;
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
        return parentDao.getById(id);
    }

    public Parent getByToken(String token) { return parentDao.getByToken(token); }

    public boolean revokeTokenAccess(Parent parent) {
        parent.setToken(null);
        return parentDao.update(parent);
    }

    public void notifyDilemmaReady(Parent parent, Dilemma dilemma, String unregisterToken) {
        MailUtility mailUtility = ApiApplication.getMailUtility();

        try {
            mailUtility.addDilemmaReadyToQueue(parent.getEmail(), parent.getFirstName(), dilemma.getTheme(), parent.getToken(), unregisterToken);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer save(Parent child) {
        return parentDao.save(child);
    }

    @Override
    public boolean update(Parent Parent) {
        return parentDao.update(Parent);
    }

    @Override
    public boolean delete(Parent Parent) {
        return parentDao.delete(Parent);
    }

    @Override
    public boolean deleteById(Integer id) {
        return parentDao.deleteById(id);
    }
}
