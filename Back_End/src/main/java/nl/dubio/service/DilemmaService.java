package nl.dubio.service;

import nl.dubio.models.Dilemma;
import nl.dubio.persistance.DaoRepository;
import nl.dubio.persistance.DilemmaDao;

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
        return dao.save(dilemma);
    }

    @Override
    public boolean update(Dilemma Dilemma) {
        return dao.update(Dilemma);
    }

    @Override
    public boolean delete(Dilemma Dilemma) {
        return dao.delete(Dilemma);
    }

    @Override
    public boolean deleteById(Integer id) {
        return dao.deleteById(id);
    }
}
