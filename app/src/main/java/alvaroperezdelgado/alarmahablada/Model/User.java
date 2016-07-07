package alvaroperezdelgado.alarmahablada.Model;

/**
 * Esta clase se encarga de guardar y tratar los datos propios del usuario
 * Como solo habrá una instancia de usuario y no queremos que se creen más
 * esta clase será singleton.
 */
public class User {

    private String mailUser;
    private String mailPass;
    private String city;
    private String name;
    private String title;

    private static User user;

    /**
     * Metodo para obtener una instancia de la clase que es singleton
     *
     * @return
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        Weather.getInstance().setLocation();
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
