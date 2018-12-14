package nl.dubio.service;

import nl.dubio.ApiApplication;
import nl.dubio.models.Couple;
import nl.dubio.models.CoupleRegistry;
import nl.dubio.persistance.CoupleDao;
import nl.dubio.persistance.DaoRepository;
import nl.dubio.utils.MailUtility;

import javax.mail.MessagingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CoupleService implements CrudService<Couple> {
    private final CoupleDao coupleDao;

    public CoupleService() {
        this.coupleDao = DaoRepository.getCoupleDao();
    }

    @Override
    public List<Couple> getAll() {
        return coupleDao.getAll();
    }

    @Override
    public Couple getById(Integer id) {
        return coupleDao.getById(id);
    }

    @Override
    public Integer save(Couple couple) {
        if (couple.getSignupDate().compareTo(new Date(System.currentTimeMillis())) < 0){
            return coupleDao.save(couple);
        }

        return -1;
    }

    @Override
    public boolean update(Couple couple) {
        return coupleDao.update(couple);
    }

    @Override
    public boolean delete(Couple couple) {
        return coupleDao.delete(couple);
    }

    @Override
    public boolean deleteById(Integer id) {
        return coupleDao.deleteById(id);
    }

    public int register(CoupleRegistry registry){
        List<String> errors = validateRegistry(registry);

        if (errors.size() > 0){
            //TODO the errors should be given to the client
            return -1;
        }

        int coupleId = coupleDao.saveCoupleViaRegistry(registry);

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

    //TODO better error messages and the messages should come from a constants class
    private List<String> validateRegistry(CoupleRegistry registry) {
        List<String> errors = new ArrayList<>();

        if (! ValidationService.isValidName(registry.getFirstName1()) )
            errors.add("The first name of parent 1 is not valid");
        if (! ValidationService.isValidName(registry.getFirstName2()) )
            errors.add("The first name of parent 2 is not valid");

        if (! ValidationService.isValidPhone(registry.getPhoneNr1()) )
            errors.add("The phone number of parent 1 is not valid");
        if (! ValidationService.isValidPhone(registry.getPhoneNr2()) )
            errors.add("The phone number of parent 2 is not valid");

        if (! ValidationService.isValidEmail(registry.getEmail1()) )
            errors.add("The email of parent 1 is not valid");
        if (! ValidationService.isValidEmail(registry.getEmail2()) )
            errors.add("The email of parent 2 is not valid");

        if (registry.getIsBorn())
            if( registry.getDate().compareTo(new Date(System.currentTimeMillis())) > 0 )
                errors.add("The birth date of the baby is not valid");
        else
            if( registry.getDate().compareTo(new Date(System.currentTimeMillis())) < 0 )
                errors.add("The birth date of the baby is not valid");

        return errors;
    }


}
