package nl.hava.dapaj.bankapp.model.dao;

import nl.hava.dapaj.bankapp.model.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDao extends CrudRepository<Company, Integer> {
}
