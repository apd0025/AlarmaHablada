package alvaroperezdelgado.alarmahablada.Calendar;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que sirve para modelar una lista de eventos.
 * Guardara todos los eventos que tenemos en un dia.
 */
public class ListCalendarEvents {

    //Lista donde vamos a guardar los eventos que tenemos en un dia
    private List<CalendarEvent> eventList = new ArrayList<CalendarEvent>();
    private String speechCalendarEvents = "";
    private static ListCalendarEvents listCalendarEvents;

    public static ListCalendarEvents getInstance() {

        if (listCalendarEvents == null) {
            listCalendarEvents = new ListCalendarEvents();
        }

        return listCalendarEvents;
    }

    /**
     * Método que sirve para añadir eventos a la lista de eventos.
     *
     * @param calendarEvent
     */
    public void addCalendarEvent(CalendarEvent calendarEvent) {
        eventList.add(calendarEvent);
    }


    /**
     * Método que sirve para eliminar por completo nuestra lista de eventos.
     * Lo utilizaremos cada vez que queramos cargar de nuevo nuestros eventos, para que no esten por duplicado.
     */
    public void removeAll() {
        setSpeechCalendarEvents("No hay eventos para hoy");
        eventList.removeAll(eventList);
    }

    /**
     * Método que nos devuelve el número de eventos que tenemos almacenados.
     *
     * @return eventList.size()
     */
    public int size() {
        return eventList.size();
    }

    /**
     * Método que sirve para devolvernos un evento dentro de la lista.
     *
     * @param index
     * @return
     */
    public CalendarEvent getCalendarEvent(int index) {
        if (index < size() && index >= 0) {
            return eventList.get(index);
        } else {
            return new CalendarEvent("No");
        }
    }

    public String getSpeechCalendarEvents() {
        return speechCalendarEvents;
    }

    public void setSpeechCalendarEvents(String speechCalendarEvents) {
        this.speechCalendarEvents = speechCalendarEvents;
    }


    /**
     * Método que sirve para crear una cadena con lo que vamos a decir hablando
     */
    public void setSpeechCalendar() {
        String aux = "";
        if (size() < 1) {
            setSpeechCalendarEvents("No hay eventos para hoy");
        } else {
            this.speechCalendarEvents = "Tienes " + size() + " eventos en tu calendario para hoy.";
            for (int i = size() - 1; i > size() - 4&&i>=0; i--) {

                if (getCalendarEvent(i).getBegin().getHours() == getCalendarEvent(i).getEnd().getHours()) {
                    aux = "Tienes un evento con título " + getCalendarEvent(i).getTitle() + " que dura todo el día. ";

                } else {
                    aux = "Tienes un evento con título " + getCalendarEvent(i).getTitle() + " que empieza a las " + getCalendarEvent(i).getBegin().getHours() + ". ";
                }
                this.speechCalendarEvents = this.speechCalendarEvents.concat(aux);
            }
        }
    }

}
