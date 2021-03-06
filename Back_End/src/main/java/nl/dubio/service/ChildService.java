package nl.dubio.service;

import nl.dubio.exceptions.InvalidInputException;
import nl.dubio.models.Child;
import nl.dubio.models.Couple;
import nl.dubio.persistance.ChildDao;
import nl.dubio.persistance.CoupleDao;
import nl.dubio.persistance.DaoRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ChildService implements CrudService<Child> {
    private final ChildDao childDao;
    private final CoupleDao coupleDao;

    public ChildService() {
        this.childDao = DaoRepository.getChildDao();
        coupleDao = DaoRepository.getCoupleDao();
    }

    public Child getByCouple(int coupleId) {
        return childDao.getByCouple(coupleDao.getById(coupleId));
    }

    public Child getByCouple(Couple couple) { return childDao.getByCouple(couple); }

    public Child setChildBorn(int childId) {
        Child child = childDao.getById(childId);
        child.setIsBorn(true);
        child.setDate(new Date(System.currentTimeMillis()));
        childDao.update(child);
        return child;
    }

    @Override
    public List<Child> getAll() {
        return childDao.getAll();
    }

    @Override
    public Child getById(Integer id) {
        return childDao.getById(id);
    }

    @Override
    public Integer save(Child child) throws InvalidInputException {
        List<String> errors = validate(child);

        if (errors.size() > 0)
            throw new InvalidInputException(errors);

        return childDao.save(child);
    }

    @Override
    public boolean update(Child child) throws InvalidInputException {
        List<String> errors = validate(child);

        if (errors.size() > 0)
            throw new InvalidInputException(errors);

        return childDao.update(child);
    }

    @Override
    public boolean delete(Child child) {
        return childDao.delete(child);
    }

    @Override
    public boolean deleteById(Integer id) {
        return childDao.deleteById(id);
    }

    @Override
    public List<String> validate(Child child) {
        List<String> errors = new ArrayList<>();

        Date currentDate = new Date(System.currentTimeMillis());

        if (! coupleDao.idExists(child.getCoupleId()))
            errors.add("Invalid couple id");
        if (child.getDate().compareTo(currentDate) != 0) {
            if (child.getIsBorn()) {
                if (child.getDate().compareTo(currentDate) > 0)
                    errors.add("Invalid birth date");
            }
            else if (child.getDate().compareTo(currentDate) < 0)
                errors.add("Invalid birth date");
        }

        return errors;
    }
}
