package api.resources;

import api.config.ApiConfiguration;
import com.codahale.metrics.annotation.Timed;
import database.DaoRepository;
import database.daos.GenericDao;
import database.models.DatabaseObject;
import io.dropwizard.setup.Environment;

import javax.validation.Valid;
import javax.ws.rs.*;
import java.util.List;

public abstract class GenericResource<T extends DatabaseObject<T>> {

    protected final GenericDao<T> dao;



    protected GenericResource(GenericDao<T> dao) {
        this.dao = dao;
    }



    @GET
    @Timed
    @Path("/all")
    public List<T> getAll(){
        return dao.getAll();
    }

    @GET
    @Timed
    @Path("/byId")
    public T getById(@QueryParam("id") Integer id){
        return dao.getById(id);
    }

    @POST
    @Timed
    public Integer save(@Valid T object){
        return dao.save(object);
    }

    @PUT
    @Timed
    public boolean update(@Valid T object){
        return dao.update(object);
    }

    @DELETE
    @Timed
    public boolean delete(@Valid T object){
        return dao.delete(object);
    }

    @DELETE
    @Timed
    @Path("/byId")
    public boolean deleteById(@QueryParam("id") Integer id){
        return dao.deleteById(id);
    }



    public static void initResources(ApiConfiguration configuration, Environment environment){
        DaoRepository daoRepository = configuration.getDaoRepository().build();

        final AdminResource adminResource = new AdminResource(daoRepository.adminDao);
        environment.jersey().register(adminResource);

        final AnswerResource answerResource = new AnswerResource(daoRepository.answerDao);
        environment.jersey().register(answerResource);

        final DilemmaResource dilemmaResource = new DilemmaResource(daoRepository.dilemmaDao);
        environment.jersey().register(dilemmaResource);
    }

}
