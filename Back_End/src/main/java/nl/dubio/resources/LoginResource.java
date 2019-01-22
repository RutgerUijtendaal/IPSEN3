package nl.dubio.resources;


import nl.dubio.models.Admin;
import nl.dubio.models.Couple;
import nl.dubio.models.LoginModel;
import nl.dubio.models.Parent;
import nl.dubio.persistance.AdminDao;
import nl.dubio.persistance.CoupleDao;
import nl.dubio.persistance.DaoRepository;
import nl.dubio.persistance.ParentDao;
import nl.dubio.service.PasswordService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Path("/login")
public class LoginResource {

    private AdminDao adminDao = DaoRepository.getAdminDao();
    private ParentDao parentDao = DaoRepository.getParentDao();
    private CoupleDao coupleDao = DaoRepository.getCoupleDao();

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public LoginModel login(LoginModel loginModel)  {
        System.out.println(loginModel.getPassword());
        try {
            Parent parent = parentDao.getByEmail(loginModel.getEmail());
            System.out.println(parent);
            if (parent != null) {
                Couple couple = coupleDao.getByParent(parent);
                if (PasswordService.isValidPassword(loginModel.getPassword(), couple.getPassword())) {
                    loginModel.setType("user");
                    //TODO FIX THIS
                    loginModel.setName(parent.getFirstName());
                    return loginModel;
                }
            }

            Admin admin = adminDao.getByEmail(loginModel.getEmail());
            if (admin != null) {
                if (PasswordService.isValidPassword(loginModel.getPassword(), admin.getPassword())) {
                    loginModel.setType("admin");
                    loginModel.setName(admin.getName());
                    return loginModel;
                }
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        throw new NotAuthorizedException("");
    }

}
