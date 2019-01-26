package nl.dubio.service;

import nl.dubio.ApiApplication;
import nl.dubio.exceptions.InvalidInputException;
import nl.dubio.models.*;
import nl.dubio.persistance.CoupleDao;
import nl.dubio.persistance.DaoRepository;
import nl.dubio.persistance.ParentDao;
import nl.dubio.utils.MailUtility;
import nl.dubio.utils.TokenGenerator;

import javax.mail.MessagingException;
import javax.ws.rs.NotFoundException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Date;
import java.sql.Timestamp;
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

    public Couple getByParent(Parent parent) { return coupleDao.getByParent(parent); }

    @Override
    public Integer save(Couple couple) throws InvalidInputException {
        List<String> errors = validate(couple);

        if (errors.size() > 0)
            throw new InvalidInputException(errors);

        return coupleDao.save(couple);
    }

    @Override
    public boolean update(Couple couple) throws InvalidInputException {
        List<String> errors = validate(couple);

        if (errors.size() > 0)
            throw new InvalidInputException(errors);

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

    public void unregister(String token) throws NotFoundException {
        Couple couple = coupleDao.getByToken(token);

        if (couple == null)
            throw new NotFoundException("No couple for that token");

        coupleDao.delete(couple);
    }

    public int register(CoupleRegistry registry) throws InvalidInputException {
        List<String> errors = validateRegistry(registry);

        if (errors.size() > 0)
            throw new InvalidInputException(errors);

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
            // TODO
            e.printStackTrace();
        }

        return coupleId;
    }

    public Couple getCoupleByEmail(String email) {
        Parent parent = parentDao.getByEmail(email);
        return coupleDao.getByParent(parent);
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

    public void createResultEntry(Couple couple) throws InvalidInputException {
        ParentService parentService = new ParentService();
        ChildService childService = new ChildService();

        DilemmaService dilemmaService = new DilemmaService();
        ResultService resultService = new ResultService();

        Parent[] parents = new Parent[2];
        parents[0] = parentService.getById(couple.getParent1Id());
        parents[1] = parentService.getById(couple.getParent2Id());

        Child child = childService.getByCouple(couple);
        short ageInWeeks = child.getAgeInWeeks();

        try {
            Dilemma dilemma;

            // Get the dilemma
            if (!child.getIsBorn())
                dilemma = dilemmaService.getByWeekNr((short) (ageInWeeks + 15), "voor");
            else
                dilemma = dilemmaService.getByWeekNr(ageInWeeks, "na");

            // Throw exception if the dilemma does not exists
            if (dilemma == null)
                throw new NullPointerException();

            for (Parent parent : parents) {
                Result result = new Result(parent.getId(), null, new Timestamp(System.currentTimeMillis()), null);
                resultService.save(result);

                parent.setToken(TokenGenerator.getToken());
                parentService.update(parent);
            }

            parentService.notifyDilemmaReady(parents[0], dilemma, couple.getToken());
            parentService.notifyDilemmaReady(parents[1], dilemma, couple.getToken());
        } catch (NullPointerException e) {
            parents[0].setToken(null);
            parents[1].setToken(null);

            parentService.update(parents[0]);
            parentService.update(parents[1]);
        }
    }

    @Override
    public List<String> validate(Couple couple) {
        List<String> errors = new ArrayList<>();

        Date currentDate = new Date(System.currentTimeMillis());

        if (couple.getSignupDate().compareTo(currentDate) > 0)
            errors.add("Invalid sign-up date");
        if (! parentDao.idExists(couple.getParent1Id()))
            errors.add("Invalid parent1 id");
        if (! parentDao.idExists(couple.getParent2Id()))
            errors.add("Invalid parent2 id");
        if (! ValidationService.isValidPassword(couple.getPassword()))
            errors.add("Invalid password");

        return errors;
    }
}
