package nl.dubio.service;

import nl.dubio.exceptions.InvalidInputException;
import nl.dubio.models.Rating;
import nl.dubio.persistance.DaoRepository;
import nl.dubio.persistance.DilemmaDao;
import nl.dubio.persistance.RatingDao;

import java.util.ArrayList;
import java.util.List;

public class RatingService implements CrudService<Rating> {

    private final RatingDao ratingDao;
    private final DilemmaDao dilemmaDao;

    public RatingService() {
        this.ratingDao = DaoRepository.getRatingDao();
        this.dilemmaDao = DaoRepository.getDilemmaDao();
    }

    @Override
    public List<Rating> getAll() {
        return ratingDao.getAll();
    }

    @Override
    public Rating getById(Integer id) {
        return ratingDao.getById(id);
    }

    @Override
    public Integer save(Rating rating) throws InvalidInputException {
        List<String> errors = validate(rating);

        if (errors.size() > 0)
            throw new InvalidInputException(errors);

        return ratingDao.save(rating);
    }

    @Override
    public boolean update(Rating rating) throws InvalidInputException {
        List<String> errors = validate(rating);

        if (errors.size() > 0)
            throw new InvalidInputException(errors);

        return ratingDao.update(rating);
    }

    @Override
    public boolean delete(Rating rating) {
        return ratingDao.delete(rating);
    }

    @Override
    public boolean deleteById(Integer id) {
        return ratingDao.deleteById(id);
    }

    @Override
    public List<String> validate(Rating rating) {
        List<String> errors = new ArrayList<>();

        if(!dilemmaDao.dilemmaExists(rating.getDilemmaId())) {
            errors.add("Invalid dilemma id");
        }
        if(rating.getRating() != -1 && rating.getRating() != 1) {
            errors.add("Invalid rating");
        }

        return errors;
    }
}
