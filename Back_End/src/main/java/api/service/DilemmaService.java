package api.service;

import api.CrudService;
import database.daos.DilemmaDao;
import database.models.Dilemma;

import javax.ws.rs.core.UriInfo;
import java.util.List;

public class DilemmaService implements CrudService<Dilemma> {

    private final DilemmaDao dao;



    public DilemmaService(DilemmaDao dao) {
        this.dao = dao;
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
    public boolean updateObject(Dilemma object) {
        return false;
    }

    @Override
    public String updateValue(UriInfo ui) {
        return null;
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
