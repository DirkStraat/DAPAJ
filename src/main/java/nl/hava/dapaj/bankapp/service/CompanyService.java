package nl.hava.dapaj.bankapp.service;

import nl.hava.dapaj.bankapp.model.Company;
import nl.hava.dapaj.bankapp.model.User;
import nl.hava.dapaj.bankapp.model.dao.CompanyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    CompanyDao companyDao;

    public Company findCompanyIdCompanyName(User user){
        String companyName ="";
        for (Company company:user.getCompanies()) {
            System.out.println(company.getCompanyName());
            companyName=company.getCompanyName();
        }

        return companyDao.findCompanyByCompanyName(companyName);
    }
}