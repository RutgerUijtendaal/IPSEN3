package nl.dubio.service;

import nl.dubio.persistance.DaoRepository;
import nl.dubio.persistance.DilemmaDao;
import nl.dubio.models.Dilemma;

import java.util.List;

public class DilemmaService implements CrudService<Dilemma> {

    private final DilemmaDao dao;

    public DilemmaService() {
        this.dao = DaoRepository.getDilemmaDao();
    }

    @Override
    public List<Dilemma> getAll() {
        return dao.getAll();
    }

    @Override
    public Dilemma getById(Integer id) {
        return dao.getById(id);
    }

    @Override
    public Integer save(Dilemma dilemma) {
        return null;
    }

    @Override
    public boolean update(Dilemma object) {
        return false;
    }

    @Override
    public boolean delete(Dilemma object) {
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }
}
