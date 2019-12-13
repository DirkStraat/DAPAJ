package nl.hava.dapaj.bankapp.controller;

import nl.hava.dapaj.bankapp.model.*;
import nl.hava.dapaj.bankapp.model.dao.*;
import nl.hava.dapaj.bankapp.service.AccountService;
import nl.hava.dapaj.bankapp.service.CompanyService;
import nl.hava.dapaj.bankapp.service.LoginService;
import nl.hava.dapaj.bankapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@SessionAttributes("user")
@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private SMEAccountDao smeAccountDao;


    Employee employee;

    @PostMapping ("join_dapaj")
    public String join_dapajHandler(Model model){
        return "join_dapaj";
    }
    @GetMapping ("set_password")
    public String set_passwordHandler (Model model){
        return "set_password";
    }
    @PostMapping("do_login")
    public String doLoginHandler(@RequestParam(name = "user_name") String loginName,
                                 @RequestParam(name = "user_password") String userPassword,
                                 Model model) {
        if (loginService.validatePassword(loginName, userPassword)) {
            User user = userService.findUserByLoginName(loginName);
            List<Account> accountList = accountService.getAccountByUserId(user.getCustomerId());
            Company company = companyService.findCompanyIdCompanyName(user);
            System.out.println(smeAccountDao.findSMEAccountByCompanyCompanyId(company.getCompanyId()));
            


            ///System.out.println("company=: "+ companyDao.getCompaniesByCompanyEmployees(user.getCompanies()));
            model.addAttribute("user", user);
            model.addAttribute("accounts", accountList);
            return "customer_welcome";
        }else if(loginService.validateEmployeePassword(loginName, userPassword)){
            Employee rol = employeeDao.findUserByEmployeeLoginName(loginName);
            if (rol.getRole().equals("Manager SME")){
                return "sme_accountmanager_welcome";
            }else if(rol.getRole().equals("Manager Retail")){
                return "private_client_accountmanager_welcome";
            }else{
                model.addAttribute("header_inlog","Naam/password combinatie niet bekend");
                return "login";
            }
        }else {
            model.addAttribute("header_inlog","Naam/password combinatie niet bekend");
            return "login";
        }


        /*if (loginName.equals("NaamMKB") && userPassword.equals("geheim")) {
            model.addAttribute("welcome", loginName);
            return "sme_accountmanager_welcome";
        }else if (loginName.equals("NaamParticulieren")&& userPassword.equals("geheim")) {
            return "private_client_accountmanager_welcome";
        }else if (loginName.equals("NaamRetail")&& userPassword.equals("geheim")){
            return "customer_welcome";
        } else {
            model.addAttribute("header_inlog", "Naam/password combinatie niet bekend.");
            return "login";
        }*/
    }

}
