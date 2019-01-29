package nl.dubio.auth;


import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import nl.dubio.models.Admin;
import nl.dubio.persistance.AdminDao;
import nl.dubio.persistance.DaoRepository;
import nl.dubio.service.PasswordService;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

public class AdminAuthenticator implements Authenticator<BasicCredentials, Admin> {

    AdminDao adminDao = DaoRepository.getAdminDao();

    @Override
    public Optional<Admin> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
        String email = basicCredentials.getUsername();
        String password = basicCredentials.getPassword();
        Admin admin = adminDao.getByEmail(email);
        if (admin != null) {
            try {
                boolean valid = PasswordService.isValidPassword(password, admin.getPassword());
                if (valid) {
                    return Optional.of(admin);
                }
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

}
