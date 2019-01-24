package nl.dubio.resources;

import io.dropwizard.auth.Auth;
import nl.dubio.auth.AdminRights;
import nl.dubio.models.Admin;
import nl.dubio.models.CoupleListModel;
import nl.dubio.service.CoupleListService;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/couple-list")
@Produces(MediaType.APPLICATION_JSON)
public class CoupleListViewResource {

    private final CoupleListService coupleListService;

    protected CoupleListViewResource() {
        this.coupleListService = new CoupleListService();
    }

    @GET
    @RolesAllowed(AdminRights.Constants.DILEMMAS)
    public List<CoupleListModel> getAll(@Auth Admin admin) {
        return this.coupleListService.getAll();
    }
}
