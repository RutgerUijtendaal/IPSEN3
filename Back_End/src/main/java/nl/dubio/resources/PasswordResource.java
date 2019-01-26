package nl.dubio.resources;

import com.codahale.metrics.annotation.Timed;
import nl.dubio.models.Admin;
import nl.dubio.models.Couple;
import nl.dubio.models.CoupleListModel;
import nl.dubio.service.AdminService;
import nl.dubio.service.CoupleListService;
import nl.dubio.service.CoupleService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/password")
public class PasswordResource {
    @POST
    @Timed
    @Path("/request-pw-reset/{email}")
    public boolean requestPasswordReset(@PathParam("email") String email) {
        AdminService adminService = new AdminService();
        CoupleService coupleService = new CoupleService();
        Admin admin = adminService.getByEmail(email);
        if (admin == null) {
            Couple couple = coupleService.getCoupleByEmail(email);
            if (couple == null) {
                return false;
            } else {
                System.out.println("found couple for pw reset");
                coupleService.resetPasswordRequest(couple);
            }
        } else {
            System.out.println("found admin for pw reset");
            adminService.resetPasswordRequest(admin);
        }
        return true;
    }

}
