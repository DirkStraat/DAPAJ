package nl.hava.dapaj.bankapp.controller;


import nl.hava.dapaj.bankapp.model.Account;
import nl.hava.dapaj.bankapp.model.SMEAccount;
import nl.hava.dapaj.bankapp.model.User;
import nl.hava.dapaj.bankapp.service.SMEAccountService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("user")
public class AverageBalanceBranchController {

    @Autowired
    SMEAccountService smeAccountService;

    @GetMapping("average_balance_branch")
    public String average_balance_branchHandler (Model model) {
        User user = (User)model.getAttribute("user");
        List<SMEAccount> accountList = smeAccountService.findAllSmeAccounts();
        List<String>branches = new ArrayList<>();
        for (SMEAccount branch: accountList){
            if(!branches.contains(branch.getBranch())){
                branches.add( branch.getBranch());
            }
        }

        model.addAttribute("welcome", user.getFirstName());
        model.addAttribute("branch_list", branches);
        return "average_balance_branch";
    }
    @GetMapping("averageSaldo")
    public void avarageSaldo(@RequestParam(name = "sector") String sector) {
        System.out.println(sector);
    }

}
