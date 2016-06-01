package alvaroperezdelgado.alarmahablada.Model;

import java.io.File;

/**
 * Clase container
 * sirve para guardar los Strings que va a leer nuestra alarma
 */
public class Container {
    //guarda el string con el mensaje customizado
    private String customMessage = "Este es un mensaje personalizado";

    //guarda el string con lo que queremos leer del calendario
    private String ubuCalendarString = "No tienes eventos para hoy";

    //Guardamos el saludo
    private final String goodMorning="Buenos dias";
    private final String goodAfternoon="Buenas tardes";
    private final String goodNight="Buenas noches";


    //guarda la cancion que queramos reproducir
    private File song = null;
    //guarda el objeto tiempo
    private Weather weather = Weather.getInstance();
    //guarda el objeto mail
    private Emails emails=Emails.getInstance();
    //guarda el objeto user
    private User user=User.getInstance();


    //guarda un objeto contenedor
    private static Container container;


    /*
       Metodo para obtener una instancia de la clase que es singleton
        */
    public static Container getInstance() {
        if (container == null) {
            container = new Container();
        }
        return container;
    }

    public String getCustomMessage() {
        return customMessage;
    }

    public void setCustomMessage(String customMessage) {
        this.customMessage = customMessage;
    }

    public String getUbuCalendarString() {
        return ubuCalendarString;
    }

    public void setUbuCalendarString(String ubuCalendarString) {
        this.ubuCalendarString = ubuCalendarString;
    }

    public File getSong() {
        return song;
    }

    public void setSong(File song) {
        this.song = song;
    }

    public Weather getWeather() {
        return weather;
    }

    public Emails getEmails(){
        return emails;
    }

}
