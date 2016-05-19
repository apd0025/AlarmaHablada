package alvaroperezdelgado.alarmahablada.Email;

/**
 * Created by perez on 10/5/16.
 */
public class EmailAccount {
    public String urlServer = "gmail.com";
    public String username = "apd0025tfg";
    public String password = "444044ap";
    public String emailAddress;
    public EmailAccount(String username, String password, String urlServer) {
        this.username = username;
        this.password = password;
        this.urlServer = urlServer;
        this.emailAddress = username + "@" + urlServer;
    }
}

