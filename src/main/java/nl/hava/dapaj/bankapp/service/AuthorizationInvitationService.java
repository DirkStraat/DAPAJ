package nl.hava.dapaj.bankapp.service;

import nl.hava.dapaj.bankapp.model.Account;
import nl.hava.dapaj.bankapp.model.AuthorizationInvitation;
import nl.hava.dapaj.bankapp.model.User;
import nl.hava.dapaj.bankapp.model.dao.AccountDao;
import nl.hava.dapaj.bankapp.model.dao.AuthorizationInvitationDao;
import nl.hava.dapaj.bankapp.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorizationInvitationService {
    @Autowired
    AuthorizationInvitationDao authorizationInvitationDao;

    @Autowired
    UserDao userDao;

    @Autowired
    AccountDao accountDao;

    public void inviteAuthorizedRepresentative(User newRepresentative, Account account, String keycode){
        AuthorizationInvitation authorizationInvitation = new AuthorizationInvitation(account, newRepresentative, keycode);
        newRepresentative.getAuthorizedRepresentativeInvitations().add(authorizationInvitation);
        account.getaRInvitations().add(authorizationInvitation);
        authorizationInvitationDao.save(authorizationInvitation);
    }

    public List<AuthorizationInvitation> getInvitationsByUser(User user){
        return authorizationInvitationDao.getAuthorizationInvitationsByUser(user);
    }

}
