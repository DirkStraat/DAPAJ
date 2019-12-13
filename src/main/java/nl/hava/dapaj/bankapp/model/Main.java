package nl.hava.dapaj.bankapp.model;

import nl.hava.dapaj.bankapp.model.dao.AccountDao;
import nl.hava.dapaj.bankapp.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

public class Main {
    @Autowired
    AccountDao accountDao;

    public static void main(String[] args) {



        /*Set<Customer> customers = fabriekje.generateCustomers(100);
        for (Customer customer: customers) {
            System.out.println(customer);
        }*/

        //SMEAccount smeAccount = fabriekje.generateSMEAccount(fabriekje.generateCompany());
    }
}
