package nl.hava.dapaj.bankapp.controller;

import nl.hava.dapaj.bankapp.model.*;
import nl.hava.dapaj.bankapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.print.attribute.standard.JobKOctets;
import java.util.List;

@Controller
@SessionAttributes({"user", "account", "linkAccount"})
public class LinkAccountController {

    private static final int LENGTE_KOPPELCODE = 5;
    @Autowired
    UserService userService;

    @Autowired
    AuthorizationInvitationService authorizationInvitationService;

    @Autowired
    SMEAccountService smeAccountService;

    @Autowired
    CompanyService companyService;

    @Autowired
    AccountService accountService;

    @PostMapping("link_account")
    public String inviteRepresentativeHandler(@RequestParam(name = "keycode") String keycode , Model model) {
        Account linkAccount = (Account)model.getAttribute("linkAccount");
        User user = (User)model.getAttribute("user");
        List<AuthorizationInvitation> authorizationInvitations = authorizationInvitationService.getAuthorizationInvitationsByAccountAndUser(linkAccount, user);
        AuthorizationInvitation invitation = authorizationInvitations.get(0);
        String keycodeFromInvitation = invitation.getKeycode();

        //check of koppelcode juiste lengte heeft
        if (keycode.length() != LENGTE_KOPPELCODE){
            model.addAttribute("motd", "Aantal tekens van koppelcode is niet juist.");
            return "link_account";
        }

        //check of koppelcode klopt
        if (!keycode.equals(keycodeFromInvitation) ){
            model.addAttribute("motd", "Koppelcode is niet juist.");
            return "link_account";
        }

        //check sme of retail
        SMEAccount smeLinkAccount = smeAccountService.getSMEAccountByAccountId(linkAccount.getAccountID());
        if (smeLinkAccount != null){
            //als sme voeg gebruiker toe aan Company Employees
            Company company = smeLinkAccount.getCompany();
            company.getCompanyEmployees().add(user);
            companyService.saveCompany(company);
            user.addCompany(company);
            userService.save(user);
            model.addAttribute("motd", "Rekening is succesvol gekoppeld.");

        } else {
            //als retail voeg gebruiker toe aan account
            linkAccount.getCustomers().add((Customer)user);
            ((Customer) user).getAccounts().add(linkAccount);
            accountService.save(linkAccount);
            userService.save(user);
        }

        //change invitationaccepted to true and save to db
        invitation.setInvitationAccepted(true);
        authorizationInvitationService.save(invitation);

        return "customer_welcome";
    }

}
