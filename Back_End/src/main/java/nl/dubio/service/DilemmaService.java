package nl.dubio.service;

import nl.dubio.models.Dilemma;
import nl.dubio.persistance.DaoRepository;
import nl.dubio.persistance.DilemmaDao;

import java.util.List;

public class DilemmaService implements CrudService<Dilemma> {

    private final DilemmaDao dilemmaDao;

    public DilemmaService() {
        this.dilemmaDao = DaoRepository.getDilemmaDao();
    }

    @Override
    public List<Dilemma> getAll() {
        return dilemmaDao.getAll();
    }

    @Override
    public Dilemma getById(Integer id) {
        return dilemmaDao.getById(id);
    }

    @Override
    public Integer save(Dilemma dilemma) {
        return dilemmaDao.save(dilemma);
    }

    @Override
    public boolean update(Dilemma dilemma) {
        System.out.println("UPDATE");
        System.out.println(dilemma);
        return dilemmaDao.update(dilemma);
    }

    @Override
    public boolean delete(Dilemma Dilemma) {
        return dilemmaDao.delete(Dilemma);
    }

    @Override
    public boolean deleteById(Integer id) {
        return dilemmaDao.deleteById(id);
    }
}
