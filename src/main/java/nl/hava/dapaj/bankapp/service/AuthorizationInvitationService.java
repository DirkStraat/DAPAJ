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

    public void inviteAuthorizedRepresentative(AuthorizationInvitation authorizationInvitation){
        //save invite in db
        authorizationInvitationDao.save(authorizationInvitation);
        //add invite to list of invites in user and save in db
        User user = authorizationInvitation.getUser();
        userDao.save(user);
        //add invite to list of invites in account and save in db
        authorizationInvitation.getAccount().getaRInvitations().add(authorizationInvitation);
        accountDao.save(authorizationInvitation.getAccount());
    }

    public List<AuthorizationInvitation> getInvitationsByUser(User user){
        return authorizationInvitationDao.getAuthorizationInvitationsByUserAndInvitationAcceptedFalse(user);
    }

    public List<AuthorizationInvitation> getAuthorizationInvitationsByAccountAndUser(Account account, User user) {
        return authorizationInvitationDao.getAuthorizationInvitationsByAccountAndUserAndInvitationAcceptedFalse(account, user);
    }

    public void removeAuthorizationInvitation(int authorizationInvitationId){
        authorizationInvitationDao.removeAuthorizationInvitationByInvitationId(authorizationInvitationId);
    }

    public void save(AuthorizationInvitation authorizationInvitation){
        authorizationInvitationDao.save(authorizationInvitation);
    }
}
