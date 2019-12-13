package nl.hava.dapaj.bankapp.controller;

import nl.hava.dapaj.bankapp.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransactionController {
    @Autowired
    FactoryService factoryService;

    @GetMapping("trans")
    public String transactions(){
        factoryService.transactions();

        return "login";
    }
}
