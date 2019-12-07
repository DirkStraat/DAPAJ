package nl.hava.dapaj.bankapp.model.dao;

import nl.hava.dapaj.bankapp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO extends CrudRepository<User, Integer> {

  User findUserByCustomerId(int customerId);

  List<User> getUserList();
}
