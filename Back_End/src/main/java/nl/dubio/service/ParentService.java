package nl.dubio.service;

import nl.dubio.models.Parent;
import nl.dubio.persistance.DaoRepository;
import nl.dubio.persistance.ParentDao;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

public class ParentService implements CrudService<Parent> {
    private final ParentDao parentDao;

    public ParentService() {
        this.parentDao = DaoRepository.getParentDao();
    }

    @Override
    public List<Parent> getAll() {
        return parentDao.getAll();
    }

    @Override
    public Parent getById(Integer id) {
        Parent parent = parentDao.getById(id);
        System.out.println(parent);
        return parent;
    }

    @Override
    public Integer save(Parent parent) {
//        return parentDao.save(parent);
        throw new WebApplicationException("oepsie woepsie", Response.Status.CONFLICT);
    }

    @Override
    public boolean update(Parent parent) {
        return parentDao.update(parent);
    }

    @Override
    public boolean delete(Parent parent) {
        return parentDao.delete(parent);
    }

    @Override
    public boolean deleteById(Integer id) {
        return parentDao.deleteById(id);
    }

    @Override
    public List<String> validate(Parent parent) {
        return null;
    }
}
