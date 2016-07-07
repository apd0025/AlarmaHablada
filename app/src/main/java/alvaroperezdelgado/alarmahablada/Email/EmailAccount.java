package alvaroperezdelgado.alarmahablada.Email;

/**
 * Clase que guarda los datos de nuestro Correo electrónico.
 * Por defecto para hacer pruebas he dejado una cuenta funcional.
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

