package nl.hava.dapaj.bankapp.controller;

import nl.hava.dapaj.bankapp.model.Account;
import nl.hava.dapaj.bankapp.model.AuthorizationInvitation;
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
@SessionAttributes({"user", "account", "motd"})
public class AddRepresentativeController {
    final static int LENGTE_KOPPELCODE = 5;

    @Autowired
    UserService userService;

    @Autowired
    AuthorizationInvitationService authorizationInvitationService;

    @Autowired
    AccountPageController accountPageController;

    @PostMapping("invite_representative")
    public String inviteRepresentativeHandler(@RequestParam(name = "user_name") String userName,
                                              @RequestParam(name = "keycode") String keycode , Model model) {
        User newRepresentative = userService.findUserByLoginName(userName);
        if (newRepresentative == null){
            model.addAttribute("motd", "Deze gebruikersnaam is niet bekend in het systeem.");
            return "add_representative";
        }
        if (keycode.length() != LENGTE_KOPPELCODE){
            model.addAttribute("motd", "Aantal tekens van koppelcode is niet juist.");
            return "add_representative";
        }

        Account account = (Account)model.getAttribute("account");
        AuthorizationInvitation invitation = authorizationInvitationService.getInvitationByUserAndAccount(newRepresentative, account);
        if (invitation != null){
            model.addAttribute("motd", "Deze uitnodiging is al eerder naar gemachtigde verstuurd.");
            accountPageController.enterAccountPage(account.getAccountID(), model);
        } else {
            AuthorizationInvitation authorizationInvitation = new AuthorizationInvitation(account, newRepresentative, keycode);
            authorizationInvitationService.inviteAuthorizedRepresentative(authorizationInvitation);
            model.addAttribute("motd", "Uitnodiging naar gemachtigde verstuurd. Deze kan de rekening koppelen met de koppelcode.");
            accountPageController.enterAccountPage(account.getAccountID(), model);
        }
        return "account_page";
    }
}
