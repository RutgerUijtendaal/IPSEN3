package nl.dubio.service;

import nl.dubio.ApiApplication;
import nl.dubio.exceptions.InvalidInputException;
import nl.dubio.models.Admin;
import nl.dubio.persistance.AdminDao;
import nl.dubio.persistance.DaoRepository;
import nl.dubio.persistance.ParentDao;
import nl.dubio.persistance.RightDao;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminService implements CrudService<Admin> {

    private final AdminDao adminDao;
    private final RightDao rightDao;
    private final ParentDao parentDao;

    public AdminService() {
        this.adminDao = DaoRepository.getAdminDao();
        rightDao = DaoRepository.getRightDao();
        parentDao = DaoRepository.getParentDao();
    }

    @Override
    public List<Admin> getAll() {
        List<Admin> allAdmins = adminDao.getAll();
        for (Admin a : allAdmins) {
            a.setPassword(null);
        }
        return allAdmins;
    }

    public boolean tokenExists(String token) {
        return this.adminDao.tokenExists(token);
    }

    @Override
    public Admin getById(Integer id) {
        return adminDao.getById(id);
    }

    @Override
    public Integer save(Admin admin) throws InvalidInputException {
        List<String> errors = validate(admin);

        if (errors.size() > 0)
            throw new InvalidInputException(errors);

        String password = admin.getPassword();

        try {
            admin.setPassword(PasswordService.generatePasswordHash(admin.getPassword()));
        } catch (Exception e) {
            return 0;
        }

        try {
            ApiApplication.getMailUtility().addNewAdminToQueue(admin.getEmail(), password);
        } catch (MessagingException e) {
            e.printStackTrace();
            return 0;
        }

        return adminDao.save(admin);
    }

    @Override
    public boolean update(Admin admin) throws InvalidInputException {
        List<String> errors = validate(admin);

        if (errors.size() > 0)
            throw new InvalidInputException(errors);

        return adminDao.update(admin);
    }

    public boolean updateWithoutPassword(Admin admin) throws InvalidInputException {
        List<String> errors = validate(admin);

        if (errors.size() > 0)
            throw new InvalidInputException(errors);

        return adminDao.updateWithoutPassword(admin);
    }

    public boolean resetPasswordRequest(Admin admin) {
        String token = "beheerder/nieuw-wachtwoord/" + this.adminDao.resetPasswordRequest(admin);
        if (token != null) {
            try {
                ApiApplication.getMailUtility().addResetPasswordToQueue(admin.getEmail(), "beheerder", token);
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
            e.printStackTrace();
        }

        // return true;
        return this.adminDao.updatePassword(token, hashedPassword);
    }

    public Admin getByEmail(String email) {
        return this.adminDao.getByEmail(email);
    }


    @Override
    public boolean delete(Admin admin) {
        return adminDao.delete(admin);
    }

    @Override
    public boolean deleteById(Integer id) {
        return adminDao.deleteById(id);
    }

    @Override
    public List<String> validate(Admin admin){
        List<String> errors = new ArrayList<>();

        if (! ValidationService.isValidEmail(admin.getEmail()))
            errors.add("Invalid email");
        if (adminDao.emailExists(admin.getEmail()))
            errors.add("Email already exists");
        if (parentDao.emailExists(admin.getEmail()))
            errors.add("Email already exists");
        if (! rightDao.idExists(admin.getRightId()))
            errors.add("Invalid right id");

        /*
        Validating the password is not required since passwords get handled
        in another way (not during creation or updating) for security reasons
         */
        // if (! ValidationService.isValidPassword(admin.getPassword()))
        // errors.add("Invalid password");

        return errors;
    }
}
