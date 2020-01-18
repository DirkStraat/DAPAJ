package nl.hava.dapaj.bankapp.model.dao;

import nl.hava.dapaj.bankapp.model.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends CrudRepository <User, Integer> {

  User findUserByCustomerId(int customerId);
  User findUserByLoginName(String loginName);
  List<Customer> findCustomerByaccounts(Account accountId);

  User findUserBySocialSecurityNumber(String socialSecurityNumber);
  List<Customer> findCustomerBySocialSecurityNumber(String socialSecurityNumber);
}
