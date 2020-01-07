package nl.hava.dapaj.bankapp.model.dao;

import nl.hava.dapaj.bankapp.model.Account;
import nl.hava.dapaj.bankapp.model.AuthorizationInvitation;
import nl.hava.dapaj.bankapp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
@Repository
public interface AuthorizationInvitationDao extends CrudRepository<AuthorizationInvitation, Integer> {
    List<AuthorizationInvitation> getAuthorizationInvitationsByUserAndInvitationAcceptedFalse(User user);
    List<AuthorizationInvitation> getAuthorizationInvitationsByAccountAndUserAndInvitationAcceptedFalse(Account account, User user);
    void removeAuthorizationInvitationByInvitationId(int authorizationInvitationId);
    AuthorizationInvitation getAuthorizationInvitationByInvitationId(int authorizationInvitationId);
    AuthorizationInvitation getInvitationByUserAndAccount(User user, Account account);

}
