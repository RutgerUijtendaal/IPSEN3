package nl.dubio.resources;

import io.dropwizard.auth.Auth;
import nl.dubio.auth.AdminRights;
import nl.dubio.auth.Authorizable;
import nl.dubio.models.Admin;
import nl.dubio.models.CoupleListModel;
import nl.dubio.models.Parent;
import nl.dubio.service.CoupleListService;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/couple-list")
@Produces(MediaType.APPLICATION_JSON)
public class CoupleListViewResource {

    private final CoupleListService coupleListService;

    protected CoupleListViewResource() {
        this.coupleListService = new CoupleListService();
    }

    @GET
    @Path("/fakes")
    public List<CoupleListModel> getAllCoupleList() {
        List<CoupleListModel> allCouples = new ArrayList<>();
        for (int i = 1; i < 100; i += 2) {
            allCouples.add(
                    new CoupleListModel(
                            i,
                            new Parent(i, "user" + i, "+" + String.valueOf(1156234200 + i), i + "user@gmail.com", null),
                            new Parent(i+1, "user" + i+1, "+" + String.valueOf(1121132233 + i), i+1 + "user@gmail.com", null)
                    )
            );
        }
        return allCouples;
    }

    @GET
    @RolesAllowed(AdminRights.Constants.DILEMMAS)
    public List<CoupleListModel> getAll(@Auth Admin admin) {
        return this.coupleListService.getAll();
    }
}
