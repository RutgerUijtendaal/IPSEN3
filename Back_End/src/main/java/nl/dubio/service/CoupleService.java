package nl.dubio.service;

import nl.dubio.ApiApplication;
import nl.dubio.models.Couple;
import nl.dubio.models.CoupleRegistry;
import nl.dubio.models.Parent;
import nl.dubio.persistance.CoupleDao;
import nl.dubio.persistance.DaoRepository;
import nl.dubio.persistance.ParentDao;
import nl.dubio.utils.MailUtility;
import nl.dubio.utils.TokenGenerator;

import javax.mail.MessagingException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response.Status;
import javax.xml.ws.WebServiceException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CoupleService implements CrudService<Couple> {
    private final CoupleDao coupleDao;
    private final ParentDao parentDao;

    public CoupleService() {
        this.coupleDao = DaoRepository.getCoupleDao();
        this.parentDao = DaoRepository.getParentDao();
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

    @Override
    public List<String> validate(Couple couple) {
        return null;
    }

    public void unregister(String token) throws NotFoundException {
        Couple couple = coupleDao.getByToken(token);

        if(couple == null) {
            throw new NotFoundException("No couple for that token");
        }

        coupleDao.delete(couple);
    }

    public int register(CoupleRegistry registry){
        List<String> errors = validateRegistry(registry);

        if (errors.size() > 0){
            //TODO the errors should be given to the client
            for(String error: errors) {
                System.out.println(error);
            }
            return -1;
        }

        String unregisterToken = TokenGenerator.getToken();
        registry.setToken(unregisterToken);

        int coupleId = 0;
        try {
            coupleId = coupleDao.saveCoupleViaRegistry(registry);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

//         Send welcome mails if successful
        MailUtility mailUtility = ApiApplication.getMailUtility();
        try {
            mailUtility.addWelcomeMailToQueue(registry.getEmail1(), registry.getFirstName1(), unregisterToken);
            mailUtility.addWelcomeMailToQueue(registry.getEmail2(), registry.getFirstName2(), unregisterToken);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return coupleId;
    }

    public Couple getCoupleByEmail(String email) {
        Parent parent = parentDao.getByEmail(email);
        Couple couple = coupleDao.getByParent(parent);
        return couple;
    }

    //TODO better error messages and the messages should come from a constants class
    private List<String> validateRegistry(CoupleRegistry registry) {
        List<String> errors = new ArrayList<>();
        Date currentDate = new Date(System.currentTimeMillis());

        if (! ValidationService.isValidName(registry.getFirstName1()) )
            errors.add("The first name of parent 1 is not valid: " + registry.getFirstName1());
        if (! ValidationService.isValidName(registry.getFirstName2()) )
            errors.add("The first name of parent 2 is not valid: " + registry.getFirstName2());

        if (! ValidationService.isValidPhone(registry.getPhoneNr1()) )
            errors.add("The phone number of parent 1 is not valid");
        if (! ValidationService.isValidPhone(registry.getPhoneNr2()) )
            errors.add("The phone number of parent 2 is not valid");

        if (! ValidationService.isValidEmail(registry.getEmail1()) )
            errors.add("The email of parent 1 is not valid");
        if (! ValidationService.isValidEmail(registry.getEmail2()) )
            errors.add("The email of parent 2 is not valid");

        // If on the same date both born and expected are possible so we let it through
        if (registry.getDate().compareTo(currentDate) != 0) {
            if (registry.getIsBorn()) {
                if (registry.getDate().compareTo(currentDate) > 0)
                    errors.add("Invalid birth date");
            }
            else if (registry.getDate().compareTo(currentDate) < 0)
                errors.add("Invalid birth date");
        }

        if (!ValidationService.isValidPassword(registry.getPassword()))
            errors.add("Password not valid");

        return errors;
    }


}
