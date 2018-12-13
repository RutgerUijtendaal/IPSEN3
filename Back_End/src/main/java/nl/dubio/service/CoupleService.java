package nl.dubio.service;

import nl.dubio.ApiApplication;
import nl.dubio.models.Couple;
import nl.dubio.models.CoupleRegistry;
import nl.dubio.persistance.CoupleDao;
import nl.dubio.persistance.DaoRepository;
import nl.dubio.utils.MailUtility;

import javax.mail.MessagingException;
import java.sql.Date;
import java.util.List;

public class CoupleService implements CrudService<Couple> {
    private final CoupleDao dao;

    public CoupleService() {
        this.dao = DaoRepository.getCoupleDao();
    }

    @Override
    public List<Couple> getAll() {
        return dao.getAll();
    }

    @Override
    public Couple getById(Integer id) {
        return dao.getById(id);
    }

    @Override
    public Integer save(Couple couple) {
        if (couple.getSignupDate().compareTo(new Date(System.currentTimeMillis())) < 0){
            return dao.save(couple);
        }

        return -1;
    }

    @Override
    public boolean update(Couple couple) {
        return dao.update(couple);
    }

    @Override
    public boolean delete(Couple couple) {
        return dao.delete(couple);
    }

    @Override
    public boolean deleteById(Integer id) {
        return dao.deleteById(id);
    }

    public int register(CoupleRegistry registry){
        ValidationService.checkMultipleValidations(
            ValidationService.isValidName(registry.getFirstName1()),
            ValidationService.isValidName(registry.getFirstName2()),
            ValidationService.isValidInternationalPhone(registry.getPhoneNr1()) || ValidationService.isValidInternationalPhone(
                ValidationService.localPhoneToInternational(registry.getPhoneNr1())),
            ValidationService.isValidInternationalPhone(registry.getPhoneNr2()) || ValidationService.isValidInternationalPhone(
                ValidationService.localPhoneToInternational(registry.getPhoneNr2())),
            ValidationService.isValidEmail(registry.getEmail1()),
            ValidationService.isValidEmail(registry.getEmail2()),
            registry.getIsBorn() ?
                registry.getDate().compareTo(new Date(System.currentTimeMillis())) < 0 :
                registry.getDate().compareTo(new Date(System.currentTimeMillis())) > 0,
            ValidationService.isValidPassword(registry.getPassword())
        );

        int coupleId = dao.saveCoupleViaRegistry(registry);

        // Send welcome mails if successful
        MailUtility mailUtility = ApiApplication.getMailUtility();
        try {
            mailUtility.addWelcomeMailToQueue(registry.getEmail1(), registry.getFirstName1());
            mailUtility.addWelcomeMailToQueue(registry.getEmail2(), registry.getFirstName2());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return coupleId;
    }
}
