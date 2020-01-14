package nl.hava.dapaj.bankapp.controller;


import nl.hava.dapaj.bankapp.model.SMEAccount;
import nl.hava.dapaj.bankapp.model.User;
import nl.hava.dapaj.bankapp.service.SMEAccountService;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<String> branches = fillBranchList();
        model.addAttribute("welcome", user.getFirstName());
        model.addAttribute("branch_list", branches);
        model.addAttribute("average_saldo", "");
        return "average_balance_branch";
    }

    private List<String> fillBranchList() {
        List<SMEAccount> accountList = smeAccountService.findAllSmeAccounts();
        List<String>branches = new ArrayList<>();
        branches.add("-");
        for (SMEAccount branch: accountList){
            if(!branches.contains(branch.getBranch())){
                branches.add( branch.getBranch());
            }
        }
        return branches;
    }

    @PostMapping("averageSaldo")
    public String avarageSaldo(@RequestParam(name = "sector") String sector, Model model) {
        List<SMEAccount> accountsByBranch = smeAccountService.findsSmeAccountbyBranch(sector);
        double totalSaldo = 0;
        for (SMEAccount account: accountsByBranch){
            if(account.getBranch().equals(sector))
            totalSaldo += account.getBalance();
        }
        double averageSaldo = totalSaldo/accountsByBranch.size();
        List<String> branches = fillBranchList();
        model.addAttribute("branch_list", branches);
        model.addAttribute("selected_branch", sector );
        model.addAttribute("average_saldo", averageSaldo);
        return "average_balance_branch";
    }

}
