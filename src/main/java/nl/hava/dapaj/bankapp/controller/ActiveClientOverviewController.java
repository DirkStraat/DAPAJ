package nl.hava.dapaj.bankapp.controller;

import nl.hava.dapaj.bankapp.model.Account;
import nl.hava.dapaj.bankapp.model.User;
import nl.hava.dapaj.bankapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@SessionAttributes("user")
@Controller
public class ActiveClientOverviewController {



}
