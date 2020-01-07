package nl.hava.dapaj.bankapp.controller;

import nl.hava.dapaj.bankapp.model.*;
import nl.hava.dapaj.bankapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes({"user", "account", "linkAccount", "invitation"})
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

    @Autowired
    LoginController loginController;

    @GetMapping("link_account")
    public String inviteRepresentativeHandler(@RequestParam(name = "keycode") String keycode,
                                              @RequestParam(name = "invitationId") int invitationId,
                                              Model model) {
        //gebruiker uit model
        User user = (User)model.getAttribute("user");
        //lijst uitnodigingen uit model
        List<AuthorizationInvitation> authorizationInvitations = authorizationInvitationService.getInvitationsByUser(user);
        AuthorizationInvitation authorizationInvitation = authorizationInvitationService.getInvitationByInvitationId(invitationId);
        String keycodeFromInvitation = authorizationInvitation.getKeycode();

        //check of koppelcode juiste lengte heeft
        if (keycode.length() != LENGTE_KOPPELCODE){
            return wrongKey(model, authorizationInvitations, "Aantal tekens van koppelcode is niet juist.");
        }

        //check of koppelcode klopt
        if (!keycode.equals(keycodeFromInvitation) ){
            return wrongKey(model, authorizationInvitations, "Koppelcode is niet juist.");
        }

        //check sme of retail
        SMEAccount smeLinkAccount = smeAccountService.getSMEAccountByAccountId(authorizationInvitation.getAccount().getAccountID());
        if (smeLinkAccount != null){
            //als sme voeg gebruiker toe aan Company Employees
            addUserToCompany(model, user, smeLinkAccount);

        } else {
            //als retail voeg gebruiker toe aan account
            addUserToAccount(model, user, authorizationInvitation);
        }

        //change invitationaccepted to true and save to db
        setInvitationAccepted(model, user, authorizationInvitation);

        return "customer_welcome";
    }

    private String wrongKey(Model model, List<AuthorizationInvitation> authorizationInvitations, String s) {
        model.addAttribute("invitations", authorizationInvitations);
        model.addAttribute("motd", s);
        return "link_account";
    }

    private void setInvitationAccepted(Model model, User user, AuthorizationInvitation authorizationInvitation) {
        authorizationInvitation.setInvitationAccepted(true);
        authorizationInvitationService.save(authorizationInvitation);
        loginController.enterCustomerWelcome(user.getLoginName(), model);
    }

    private void addUserToAccount(Model model, User user, AuthorizationInvitation authorizationInvitation) {
        authorizationInvitation.getAccount().getCustomers().add((Customer)user);
        ((Customer) user).getAccounts().add(authorizationInvitation.getAccount());
        accountService.save(authorizationInvitation.getAccount());
        userService.save(user);
        model.addAttribute("motd", "Rekening is succesvol gekoppeld.");
    }

    private void addUserToCompany(Model model, User user, SMEAccount smeLinkAccount) {
        Company company = smeLinkAccount.getCompany();
        company.getCompanyEmployees().add(user);
        companyService.saveCompany(company);
        user.addCompany(company);
        userService.save(user);
        model.addAttribute("motd", "Rekening is succesvol gekoppeld.");
    }

}
