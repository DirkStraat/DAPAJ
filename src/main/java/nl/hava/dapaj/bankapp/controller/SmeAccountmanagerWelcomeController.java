package nl.hava.dapaj.bankapp.controller;

import nl.hava.dapaj.bankapp.model.Employee;
import nl.hava.dapaj.bankapp.model.User;
import nl.hava.dapaj.bankapp.service.EmpoyeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class SmeAccountmanagerWelcomeController {

    /*@Autowired
    LoginController loginController;*/

/*    @GetMapping("average_balance_branch")
    public String average_balance_branchHandler (Model model) {
        User user = (User)model.getAttribute("user");
        model.addAttribute("welcome", user.getFirstName());
        return "average_balance_branch"; }*/

    @GetMapping ("10_highest_balance_smec")
    public String highest_balace_smecHandler (Model model){
        return "10_highest_balance_smec"; }

    @GetMapping ("10_highest_volume_smec")
    public String highest_volume_smecHandler (Model model){
        return "10_highest_volume_smec"; }

    @GetMapping ("link_terminal")
    public String link_terminalHandler (Model model){
        return "link_terminal"; }

    @GetMapping("sme_accountmanager_welcome") // te gebruiken voor redirect naar de customer_welcome pagina
    public String testWelcomeHandler(Model model){
        User user = (User) model.getAttribute("user");
        enterSmeAccountManagerWelcome(model, user);
        return "sme_accountmanager_welcome";
    }
    void enterSmeAccountManagerWelcome(Model model, User user) {
        model.addAttribute("welcome", user.getFirstName());
        model.addAttribute("user", user);
    }
        
}
