package nl.hava.dapaj.bankapp.model.dao;

import nl.hava.dapaj.bankapp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository <User, Integer> {

  User findUserByCustomerId(int customerId);
  User findUserByLoginName(String loginName);
}
