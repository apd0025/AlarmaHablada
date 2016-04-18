package alvaroperezdelgado.alarmahablada.Model;

import java.io.File;

/**
 * Clase container
 * sirve para guardar los Strings que va a leer nuestra alarma
 */
public class Container {
    //guarda el string con el mensaje customizado
    private String customMessage = "Este es un mensaje personalizado";
    //guarda el string con los mails que vayamos a leer
    private String ubuMailString = "No hay ningun Mail";
    //guarda el string con lo que queremos leer del calendario
    private String ubuCalendarString = "No tienes eventos para hoy";
    //guarda un String con los mensajes de twiter que queremos que lea
    private String twitterString = null;
    //guarda un string con la informacion metereologica
    private String weatherString = "No hay informacion del tiempo";
    //guarda la cancion que queramos reproducir
    private File song = null;
    //guarda el objeto tiempo
    private Weather weather = Weather.getInstance();
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

    public String getUbuMailString() {
        return ubuMailString;
    }

    public void setUbuMailString(String ubuMailString) {
        this.ubuMailString = ubuMailString;
    }

    public String getUbuCalendarString() {
        return ubuCalendarString;
    }

    public void setUbuCalendarString(String ubuCalendarString) {
        this.ubuCalendarString = ubuCalendarString;
    }

    public String getTwitterString() {
        return twitterString;
    }

    public void setTwitterString(String twitterString) {
        this.twitterString = twitterString;
    }

    public String getWeatherString() {
        return weatherString;
    }

    public void setWeatherString(String weatherString) {
        this.weatherString = weatherString;
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

}
