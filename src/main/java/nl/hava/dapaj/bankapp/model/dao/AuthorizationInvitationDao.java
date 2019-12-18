package nl.hava.dapaj.bankapp.model.dao;

import nl.hava.dapaj.bankapp.model.AuthorizationInvitation;
import nl.hava.dapaj.bankapp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorizationInvitationDao extends CrudRepository<AuthorizationInvitation, Integer> {
    List<AuthorizationInvitation> getAuthorizationInvitationsByUser(User user);
}
