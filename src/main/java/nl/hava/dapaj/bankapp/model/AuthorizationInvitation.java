package nl.hava.dapaj.bankapp.model;

import javax.persistence.*;

@Entity
public class AuthorizationInvitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int invitationId;

    @ManyToOne
    private Account account;

    @ManyToOne
    private User user;

    private String keycode;

    private boolean invitationAccepted;

    public AuthorizationInvitation() {
        super();
    }

    public AuthorizationInvitation(Account account, User user, String keycode) {
        this.account = account;
        this.user = user;
        this.keycode = keycode;
        this.invitationAccepted = false;
    }

    public int getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(int invitationId) {
        this.invitationId = invitationId;
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

    private boolean checkKeycodeLength(String keycode){
        if (keycode.length() == 5){
            return true;
        } else return false;
    }

    private boolean checkKeycode(String keycode){
        if(this.keycode.equals(keycode)){
            return true;
        } else return false;
    }

    public boolean isInvitationAccepted() {
        return invitationAccepted;
    }

    public void setInvitationAccepted(boolean invitationAccepted) {
        this.invitationAccepted = invitationAccepted;
    }
}
