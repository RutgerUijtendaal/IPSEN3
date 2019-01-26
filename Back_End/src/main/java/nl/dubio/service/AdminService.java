package nl.dubio.service;

import nl.dubio.ApiApplication;
import nl.dubio.exceptions.InvalidInputException;
import nl.dubio.models.Admin;
import nl.dubio.persistance.AdminDao;
import nl.dubio.persistance.DaoRepository;
import nl.dubio.persistance.RightDao;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

public class AdminService implements CrudService<Admin> {

    private final AdminDao adminDao;

    private final RightDao rightDao;

    public AdminService() {
        this.adminDao = DaoRepository.getAdminDao();
        rightDao = DaoRepository.getRightDao();
    }

    @Override
    public List<Admin> getAll() {
        List<Admin> allAdmins = adminDao.getAll();
        for (Admin a : allAdmins) {
            a.setPassword(null);
        }
        return allAdmins;
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
            e.printStackTrace();
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
        if (! rightDao.idExists(admin.getRights_id()))
            errors.add("Invalid right id");

        /*
        Validating the password is not required since passwords get handled
        in another way (not during creation or updating) for security reasons
         */

        return errors;
    }
}
