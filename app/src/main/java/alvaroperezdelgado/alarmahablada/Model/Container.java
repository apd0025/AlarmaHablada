package alvaroperezdelgado.alarmahablada.Model;

import java.io.File;
import java.util.Calendar;

import alvaroperezdelgado.alarmahablada.Calendar.ListCalendarEvents;

/**
 * Clase container
 * sirve para guardar los Strings que va a leer nuestra alarma
 */
public class Container {
    //guarda el string con el mensaje customizado
    private String customMessage = "Este es un mensaje personalizado";
    private String welcomeSpeech = "";
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

    public File getSong() {
        return song;
    }

    public void setSong(File song) {
        this.song = song;
    }

    public Weather getWeather() {
        return weather;
    }

    public Emails getEmails() {
        return emails;
    }

    public ListCalendarEvents getListCalendarEvents() {
        return listCalendarEvents;
    }

    public String getWelcomeSpeech() {
        return welcomeSpeech;
    }

    /**
     * Este metodo sirve para establecer el mensaje de bienvenida
     */
    public void setWelcomeSpeech() {
        String aux = "";
        Calendar cal = Calendar.getInstance();
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

        String month=getMonthString(iMonth);
        String weekDay=getWeekdayString(iWeekDay);
        //Añadimos que dia es
        aux = aux.concat("Hoy es " + weekDay + " dia " + day + " de " + month);
        this.welcomeSpeech=aux;
    }


    /**
     * Traduce de un numero a una cadena que muestra el mes
     *
     * @param month
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
                aux = "Miercoles";
                return aux;
            case 5:
                aux = "Jueves";
                return aux;
            case 6:
                aux = "Viernes";
                return aux;
            case 7:
                aux = "Sabado";
                return aux;

            default:
                return "";
        }

    }

}
