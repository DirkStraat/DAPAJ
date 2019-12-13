package nl.hava.dapaj.bankapp.controller;

import nl.hava.dapaj.bankapp.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class FillDatabaseController {

    @Autowired
    FactoryService factoryService;

    @GetMapping("fdb")
    public String fillDatabase(){
        factoryService.fillDatabase();

        return "login";
    }

}
