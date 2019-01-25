package nl.dubio.service;

import nl.dubio.exceptions.InvalidInputException;
import nl.dubio.models.Admin;
import nl.dubio.persistance.AdminDao;
import nl.dubio.persistance.DaoRepository;
import nl.dubio.persistance.RightDao;

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
        return adminDao.getAll();
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

//        return adminDao.save(admin);
        return -1;
    }

    @Override
    public boolean update(Admin admin) throws InvalidInputException {
        List<String> errors = validate(admin);

        if (errors.size() > 0)
            throw new InvalidInputException(errors);

        return adminDao.update(admin);
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
        if (! ValidationService.isValidPassword(admin.getPassword()))
            errors.add("Invalid password");
        if (! rightDao.idExists(admin.getRights_id()))
            errors.add("Invalid right id");

        return errors;
    }
}