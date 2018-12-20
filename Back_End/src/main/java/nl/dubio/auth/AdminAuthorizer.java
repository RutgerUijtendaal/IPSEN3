package nl.dubio.auth;

import io.dropwizard.auth.Authorizer;
import nl.dubio.models.Admin;
import nl.dubio.models.Right;
import nl.dubio.persistance.DaoRepository;
import nl.dubio.persistance.RightDao;

public class AdminAuthorizer implements Authorizer<Admin> {

    RightDao rightDao = DaoRepository.getRightDao();

    @Override
    public boolean authorize(Admin admin, String role) {
        System.out.println(role);
        int rightId = admin.getRights_id();
        Right right = rightDao.getById(rightId);
        AdminRights adminRight = AdminRights.fromString(role);
        if (adminRight != null && right != null) {
            return adminRight.hasRight(right);
        }
        return false;
    }

}
