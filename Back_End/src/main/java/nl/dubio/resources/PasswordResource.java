package nl.dubio.resources;

import com.codahale.metrics.annotation.Timed;
import nl.dubio.models.Admin;
import nl.dubio.models.Couple;
import nl.dubio.service.AdminService;
import nl.dubio.service.CoupleService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/password")
public class PasswordResource {
    @POST
    @Timed
    @Path("/request-reset/{email}")
    public boolean requestPasswordReset(@PathParam("email") String email) {
        AdminService adminService = new AdminService();
        CoupleService coupleService = new CoupleService();
        Admin admin = adminService.getByEmail(email);
        if (admin == null) {
            Couple couple = coupleService.getCoupleByEmail(email);
            if (couple == null) {
                return false;
            } else {
                coupleService.resetPasswordRequest(couple);
            }
        } else {
            adminService.resetPasswordRequest(admin);
        }
        return true;
    }

}
