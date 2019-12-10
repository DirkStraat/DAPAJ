package nl.hava.dapaj.bankapp.model.dao;

import nl.hava.dapaj.bankapp.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends CrudRepository<Employee, Integer> {
}
