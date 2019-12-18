package nl.hava.dapaj.bankapp.model;

import javax.persistence.*;

@Entity
public class AuthorizationInvitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int InvitationId;

    @ManyToOne
    private Account account;

    @ManyToOne
    private User user;

    private String keycode;

    public AuthorizationInvitation() {
        super();
    }

    public AuthorizationInvitation(Account account, User user, String keycode) {
        this.account = account;
        this.user = user;
        this.keycode = keycode;
    }

    public int getInvitationId() {
        return InvitationId;
    }

    public void setInvitationId(int invitationId) {
        this.InvitationId = invitationId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getKeycode() {
        return keycode;
    }

    public void setKeycode(String keycode) {
        this.keycode = keycode;
    }
}
