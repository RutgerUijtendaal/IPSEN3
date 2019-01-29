package nl.dubio.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.auth.Auth;
import jdk.nashorn.internal.parser.Token;
import nl.dubio.exceptions.InvalidInputException;
import nl.dubio.models.Admin;
import nl.dubio.service.AdminService;
import nl.dubio.utils.TokenGenerator;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/admin")
public class AdminResource extends GenericResource<Admin> {

    public AdminResource() {
        super(new AdminService());
    }

    @GET
    @Timed
    //TODO Roles Allowed
    public List<Admin> getAll(@Auth Admin admin){
        return crudService.getAll();
    }

    @POST
    @Timed
    public Integer save(@Auth Admin admin, @Valid Admin object) throws InvalidInputException {
        String randomString = TokenGenerator.randomString(8);
        object.setPassword(randomString);
        return this.crudService.save(object);
    }


    @DELETE
    @Timed
    @Path("/{id}")
    public boolean delete(@Auth Admin admin,
                          @PathParam("id") int id) {
        return this.crudService.deleteById(id);
    }


    @PUT
    @Timed
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/password/{token}")
    public boolean updatePassword(@PathParam("token") String token,
                                  String password) throws InvalidInputException {
        if (token.length() != 32 || password.length() < 4) {
            return false;
        }

        if (!((AdminService)this.crudService).tokenExists(token)) {
            return false;
        }

        return ((AdminService)this.crudService).updatePassword(token, password);
    }

    @PUT
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public boolean update(@Auth Admin admin, @Valid Admin object) throws InvalidInputException {
        return ((AdminService)this.crudService).updateWithoutPassword(object);
    }

}
