package alvaroperezdelgado.alarmahablada.Email;


import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Clase que sirve para autenticar la cuenta, es necesaria para javaMail.
 */
public class EmailAuthenticator extends Authenticator {
    private EmailAccount account;

    public EmailAuthenticator(EmailAccount account) {
        super();
        this.account = account;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(account.emailAddress, account.password);
    }
}
