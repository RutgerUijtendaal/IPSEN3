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
import java.util.Arrays;
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

    public boolean tokenExists(String token) {
        return this.coupleDao.tokenExists(token);
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

    public boolean resetPasswordRequest(Couple couple) {
        String token = "ouders/nieuw-wachtwoord/" + this.coupleDao.resetPasswordRequest(couple);
        if (token != null) {
            try {
                Parent parent1 = parentDao.getById(couple.getParent1Id());
                Parent parent2 = parentDao.getById(couple.getParent2Id());
                ApiApplication.getMailUtility().addResetPasswordToQueue(parent1.getEmail(), parent1.getFirstName(), token);
                ApiApplication.getMailUtility().addResetPasswordToQueue(parent2.getEmail(), parent2.getFirstName(), token);
                return true;
            } catch (MessagingException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public boolean updatePassword(String token, String password) throws InvalidInputException {

        if (!ValidationService.isValidPassword(password)) {
            throw new InvalidInputException(Arrays.asList("Invalid password"));
        }

        String hashedPassword = null;
        try {
            hashedPassword = PasswordService.generatePasswordHash(password);
        } catch (Exception e) {
            // TODO
        }

        // return true;
        return this.coupleDao.updatePassword(token, hashedPassword);
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

    private Parent[] getParents(Couple couple) {
        ParentDataService parentService = new ParentDataService();

        Parent[] parents = new Parent[2];
        parents[0] = parentService.getById(couple.getParent1Id());
        parents[1] = parentService.getById(couple.getParent2Id());

        return parents;
    }

    private Dilemma getDilemmaByChild(Child child, Timestamp time) {
        DilemmaService dilemmaService = new DilemmaService();
        short ageInWeeks = child.getAgeInWeeks(time);

        Dilemma dilemma;

        // Get the dilemma
        if (!child.getIsBorn())
            dilemma = dilemmaService.getByWeekNr((short) (ageInWeeks + 15), "voor");
        else
            dilemma = dilemmaService.getByWeekNr((short) (ageInWeeks + 1), "na");

        return dilemma;
    }

    public void remindResultEntry(Couple couple) throws InvalidInputException {
        ParentDataService parentService = new ParentDataService();
        ChildService childService = new ChildService();
        ResultService resultService = new ResultService();

        Parent[] parents = this.getParents(couple);
        Child child = childService.getByCouple(couple);

        try {
            Result result = resultService.getRecentResultOfParent(parents[0]);
            Dilemma dilemma = this.getDilemmaByChild(child, result.getSentTime());
            System.out.println("Dilemma status" + dilemma);

            // Throw exception if the dilemma does not exists
            if (dilemma == null)
                throw new NullPointerException();

            if (parents[0].getToken() != null)
                parentService.notifyDilemmaReady("U heeft nog een onbeantwoord dilemma", parents[0], dilemma, couple.getToken());

            if (parents[1].getToken() != null)
                parentService.notifyDilemmaReady("U heeft nog een onbeantwoord dilemma", parents[1], dilemma, couple.getToken());

            System.out.println("START REMINDER");
        } catch (NullPointerException e) {
//            e.printStackTrace();
        }
    }

    public void createResultEntry(Couple couple) throws InvalidInputException {
        ParentDataService parentService = new ParentDataService();
        ChildService childService = new ChildService();

        DilemmaService dilemmaService = new DilemmaService();
        ResultService resultService = new ResultService();

        Parent[] parents = new Parent[2];
        parents[0] = parentService.getById(couple.getParent1Id());
        parents[1] = parentService.getById(couple.getParent2Id());

        try {
            Result[] results = new Result[2];
            results[0] = new Result(parents[0].getId(), null, new Timestamp(System.currentTimeMillis()), null);
            results[1] = new Result(parents[1].getId(), null, new Timestamp(System.currentTimeMillis()), null);

            Child child = childService.getByCouple(couple);
            short ageInWeeks = child.getAgeInWeeks(new Timestamp(System.currentTimeMillis()));

            Dilemma dilemma;

            // Get the dilemma
            if (!child.getIsBorn())
                dilemma = dilemmaService.getByWeekNr((short) (ageInWeeks + 15), "voor");
            else
                dilemma = dilemmaService.getByWeekNr((short)(ageInWeeks + 1), "na");

            // Throw exception if the dilemma does not exists
            if (dilemma == null) {
                throw new NullPointerException();
            }

            resultService.save(results[0]);
            resultService.save(results[1]);

            parents[0].setToken(TokenGenerator.getToken());
            parents[1].setToken(TokenGenerator.getToken());

            parentService.update(parents[0]);
            parentService.update(parents[1]);

            parentService.notifyDilemmaReady("Er staat een nieuw dilemma voor u klaar", parents[0], dilemma, couple.getToken());
            parentService.notifyDilemmaReady("Er staat een nieuw dilemma voor u klaar", parents[1], dilemma, couple.getToken());
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
