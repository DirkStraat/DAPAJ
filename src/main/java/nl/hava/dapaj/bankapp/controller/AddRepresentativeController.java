package nl.hava.dapaj.bankapp.controller;

import nl.hava.dapaj.bankapp.model.Account;
import nl.hava.dapaj.bankapp.model.User;
import nl.hava.dapaj.bankapp.service.AccountService;
import nl.hava.dapaj.bankapp.service.AuthorizationInvitationService;
import nl.hava.dapaj.bankapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"user", "account"})
public class AddRepresentativeController {

    @Autowired
    UserService userService;

    @Autowired
    AuthorizationInvitationService authorizationInvitationService;

    @PostMapping("invite_representative")
    public String inviteRepresentativeHandler(@RequestParam(name = "user_name") String userName,
                                              @RequestParam(name = "keycode") String keycode , Model model) {
        User newRepresentative = userService.findUserByLoginName(userName);
        Account account = (Account)model.getAttribute("account");
        authorizationInvitationService.inviteAuthorizedRepresentative(newRepresentative, account, keycode);
        //hier nog code om account_page te vullen
        return "account_page";
    }
}
