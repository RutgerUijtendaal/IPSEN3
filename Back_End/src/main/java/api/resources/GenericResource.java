package api.resources;

import api.config.ApiConfiguration;
import com.codahale.metrics.annotation.Timed;
import api.DaoRepository;
import database.daos.GenericDao;
import database.models.DatabaseObject;
import io.dropwizard.jersey.PATCH;
import io.dropwizard.setup.Environment;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import java.util.List;

public abstract class GenericResource<T extends DatabaseObject<T>> {

    protected final GenericDao<T> dao;



    protected GenericResource(GenericDao<T> dao) {
        this.dao = dao;
    }



    @GET
    @Timed
    public List<T> getAll(){
        return dao.getAll();
    }

    @GET
    @Timed
    @Path("/{id}")
    public T getById(@PathParam("id") Integer id){
        return dao.getById(id);
    }

    @POST
    @Timed
    public Integer save(@Valid T object){
        return dao.save(object);
    }

    @PUT
    @Timed
    public boolean updateObject(@Valid T object){
        return dao.update(object);
    }

    @PATCH
    @Timed
    public String updateValue(@Context UriInfo ui){
        MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
        System.out.println(queryParams.getFirst("firstName"));
        return "Updated";
    }

    @DELETE
    @Timed
    public boolean delete(@Valid T object){
        return dao.delete(object);
    }

    @DELETE
    @Timed
    @Path("/{id}")
    public boolean deleteById(@PathParam("id") Integer id){
        return dao.deleteById(id);
    }



    public static void initResources(ApiConfiguration configuration, Environment environment){
        DaoRepository daoRepository = configuration.getDaoRepository();

        registerResource(new AdminResource(daoRepository.adminDao), environment);
        registerResource(new AnswerResource(daoRepository.answerDao), environment);
        registerResource(new ChildResource(daoRepository.childDao), environment);
        registerResource(new CoupleResource(daoRepository.coupleDao), environment);
        registerResource(new DilemmaResource(daoRepository.dilemmaDao), environment);
        registerResource(new ParentResource(daoRepository.parentDao), environment);
        registerResource(new ResultResource(daoRepository.resultDao), environment);
        registerResource(new RightResource(daoRepository.rightDao), environment);
    }

    private static void registerResource(GenericResource resource, Environment environment){
        environment.jersey().register(resource);
    }

}
