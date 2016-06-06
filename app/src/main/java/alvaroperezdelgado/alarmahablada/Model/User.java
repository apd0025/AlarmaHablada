package alvaroperezdelgado.alarmahablada.Model;

/**
 * Esta clase se encarga de guardar y tratar los datos propios del usuario
 * Como solo habra una instancia de usuario y no queremos que se creen mas
 * esta clase sera singleton
 */
public class User {

    private int id = 1;
    private String mailUser="apd0025tfg";
    private String mailPass="444044ap";
    private String city = "Burgos";
    private String name="Usuario";
    private String title="";
    private String twitterUser;
    private String twitterPass;
    private static User user;

    /*
    Metodo para obtener una instancia de la clase que es singleton
     */
    public static User getInstance() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public String getMailUser() {
        return mailUser;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setMailUser(String mailUser) {
        this.mailUser = mailUser;
    }

    public String getMailPass() {
        return mailPass;
    }

    public void setMailPass(String mailPass) {
        this.mailPass = mailPass;
    }

    public String getTwitterUser() {
        return twitterUser;
    }

    public void setTwitterUser(String twitterUser) {
        this.twitterUser = twitterUser;
    }

    public String getTwitterPass() {
        return twitterPass;
    }

    public void setTwitterPass(String twitterPass) {
        this.twitterPass = twitterPass;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static User getUser() {
        return user;
    }
    public static void setUser(User user) {
        User.user = user;
    }


}
