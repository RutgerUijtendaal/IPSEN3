package nl.dubio.auth;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import nl.dubio.models.Couple;
import nl.dubio.models.Parent;
import nl.dubio.persistance.CoupleDao;
import nl.dubio.persistance.DaoRepository;
import nl.dubio.persistance.ParentDao;
import nl.dubio.service.PasswordService;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

public class ParentAuthenticator implements Authenticator<BasicCredentials, Parent> {

    ParentDao parentDao = DaoRepository.getParentDao();
    CoupleDao coupleDao = DaoRepository.getCoupleDao();

    @Override
    public Optional<Parent> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
        String email = basicCredentials.getUsername();
        String password = basicCredentials.getPassword();
        Parent parent = parentDao.getByEmail(email);
        if (parent != null) {
            System.out.println(parent);
            Couple couple = coupleDao.getByParent(parent);
            try {
                boolean valid = PasswordService.isValidPassword(password, couple.getPassword());
                System.out.println(password);
                System.out.println(couple.getPassword());
                System.out.println(valid);
                if (valid) {
                    return Optional.of(parent);
                }
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

}
