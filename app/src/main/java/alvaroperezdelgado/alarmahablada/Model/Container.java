package alvaroperezdelgado.alarmahablada.Model;

import java.io.File;
import java.util.Calendar;

/**
 * Clase container
 * sirve para guardar los Strings que va a leer nuestra alarma
 * Tambien guardaremos objetos que tengan Strings y accederemos a ellos desde aquí
 */
public class Container {

    //guarda el string con el mensaje customizado
    private String customMessage;
    private String welcomeSpeech;

    private String songName;

    //Guardamos el saludo
    private final String goodMorning = "Buenos dias";
    private final String goodAfternoon = "Buenas tardes";
    private final String goodNight = "Buenas noches";

    //guarda la cancion que queramos reproducir
    private File song = null;
    //guarda el objeto tiempo

    private Weather weather = Weather.getInstance();
    //guarda el objeto mail
    private Emails emails = Emails.getInstance();
    //guarda el objeto user
    private User user = User.getInstance();
    //guarda el objeto ListCalendarEvents
    private ListCalendarEvents listCalendarEvents = ListCalendarEvents.getInstance();

    //guarda un objeto contenedor
    private static Container container;

    /**
     * Método para obtener una instancia de la clase que es singleton
     *
     * @return
     */
    public static Container getInstance() {
        if (container == null) {
            container = new Container();
        }
        return container;
    }

    /**
     * Método para obtener el mensaje personalizado
     *
     * @return
     */
    public String getCustomMessage() {
        return customMessage;
    }

    /**
     * Método para establecer el mensaje personalizado
     *
     * @param customMessage
     */
    public void setCustomMessage(String customMessage) {
        this.customMessage = customMessage;
    }

    /**
     * Método para obtener la canción seleccionada
     *
     * @return
     */
    public File getSong() {
        return song;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    /**
     * Método para establecer una canción
     *
     * @param song
     */
    public void setSong(File song) {
        this.songName = song.getName().toString();
        this.song = song;
    }

    /**
     * Método para obtener el nombre de la canción
     *
     * @return
     */
    public String getSongName() {
        if (getSong() == null) {
            return "Por defecto";
        } else {
            return getSong().getName().toString();
        }
    }

    /**
     * Método que nos devuelve el objeto Weather alojado en Container
     *
     * @return
     */
    public Weather getWeather() {
        return weather;
    }

    /**
     * Método que nos devuelve el objeto Emails alogajo en Container
     *
     * @return
     */
    public Emails getEmails() {
        return emails;
    }

    /**
     * Método que nos devuevle el objeto ListCalendarEvents alojado en Container
     *
     * @return
     */
    public ListCalendarEvents getListCalendarEvents() {
        return listCalendarEvents;
    }

    /**
     * Método que nos devuelve la cadena welcomeSpeech que contiene el saludo inicial
     *
     * @return
     */
    public String getWelcomeSpeech() {
        return welcomeSpeech;
    }

    /**
     * Este metodo sirve para establecer el mensaje de bienvenida
     */
    public void setWelcomeSpeech() {
        String aux = "";
        Calendar cal = Calendar.getInstance();
        //Establecemos si es por la mañana, tarde o noche
        if (cal.getTime().getHours() < 12 && cal.getTime().getHours() > 3) {
            aux = goodMorning;
        } else if (cal.getTime().getHours() < 21 && cal.getTime().getHours() >= 12) {
            aux = goodAfternoon;
        } else {
            aux = goodNight;
        }
        //Añadimos el titulo y el nombre del usuario
        aux = aux.concat(" " + user.getTitle() + " " + user.getName() + ". ");

        int iWeekDay = cal.get(Calendar.DAY_OF_WEEK);
        int day = cal.get(Calendar.DATE);
        int iMonth = cal.get(Calendar.MONTH);

        String month = getMonthString(iMonth);
        String weekDay = getWeekdayString(iWeekDay);
        //Añadimos que dia es
        aux = aux.concat("Hoy es " + weekDay + " dia " + day + " de " + month);
        this.welcomeSpeech = aux;
    }


    /**
     * Traduce de un numero a una cadena que muestra el mes
     *
     * @param
     * @return
     */
    public String getMonthString(int month) {
        String aux;
        switch (month) {
            case 0:
                aux = "Enero";
                return aux;
            case 1:
                aux = "Febrero";
                return aux;
            case 2:
                aux = "Marzo";
                return aux;
            case 3:
                aux = "Abril";
                return aux;
            case 4:
                aux = "Mayo";
                return aux;
            case 5:
                aux = "Junio";
                return aux;
            case 6:
                aux = "Julio";
                return aux;
            case 7:
                aux = "Agosto";
                return aux;
            case 8:
                aux = "Septiembre";
                return aux;
            case 9:
                aux = "Octubre";
                return aux;
            case 10:
                aux = "Noviembre";
                return aux;
            case 11:
                aux = "Diciembre";
                return aux;
            default:
                return "";
        }

    }

    /**
     * @param weekday
     * @return
     */
    public String getWeekdayString(int weekday) {
        String aux;
        switch (weekday) {

            case 1:
                aux = "Domingo";
                return aux;
            case 2:
                aux = "Lunes";
                return aux;
            case 3:
                aux = "Martes";
                return aux;
            case 4:
                aux = "Miércoles";
                return aux;
            case 5:
                aux = "Jueves";
                return aux;
            case 6:
                aux = "Viernes";
                return aux;
            case 7:
                aux = "Sábado";
                return aux;

            default:
                return "";
        }

    }

}
