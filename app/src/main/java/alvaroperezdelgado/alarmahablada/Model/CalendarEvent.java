package alvaroperezdelgado.alarmahablada.Model;

import java.util.Date;

/**
 * Esta clase sirve para modelar un evento del calendario.
 * Guardaremos el titulo del evento, la descripcion del mismo y sus fechas de inicio y terminación.
 */
public class CalendarEvent {
    private String title, description;
    private Date begin, end;
    private boolean allDay;

    public CalendarEvent() {

    }

    /**
     * Constructor para el caso de que no haya nada que devuelva vacio
     *
     * @param title
     */
    public CalendarEvent(String title) {
        setTitle(title);
    }

    /**
     * Constructor normal
     *
     * @param title
     * @param description
     * @param begin
     * @param end
     */
    public CalendarEvent(String title, String description, Date begin, Date end) {
        setTitle(title);
        setDescription(description);
        setBegin(begin);
        setEnd(end);
    }

    /**
     * Obtener la descripción
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Poner la descripción
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtener la fecha de inicio
     *
     * @return
     */
    public Date getBegin() {
        return begin;
    }

    /**
     * Poner la fecha de inicio
     *
     * @param begin
     */
    public void setBegin(Date begin) {
        this.begin = begin;
    }

    /**
     * Obtener la fecha de fin
     *
     * @return
     */
    public Date getEnd() {
        return end;
    }

    /**
     * Poner la fecha de fin
     *
     * @param end
     */
    public void setEnd(Date end) {
        this.end = end;
    }

    /**
     * Obtenemos si es un evento allday
     *
     * @return
     */
    public boolean isAllDay() {
        return allDay;
    }

    /**
     * Poner que es un evento allday
     *
     * @param allDay
     */
    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }

    /**
     * Obtener el titulo
     *
     * @return
     */
    public String getTitle() {

        return title;
    }

    /**
     * Poner el titulo
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        //si tiene de titulo no es que no existe ese evento y no ponemos nada en pantalla
        if (getTitle() == "No") {
            return "";
        } else {
            int minutesB = getBegin().getMinutes();
            int minutesE = getBegin().getMinutes();
            String minutesBeg = "";
            String minutesEnd = "";
            //Esto es solo para que se quede el 00
            if (minutesB == 0) {
                minutesBeg = "00";
            } else {
                minutesBeg = String.valueOf(minutesB);
            }
            if (minutesE == 0) {
                minutesEnd = "00";
            } else {
                minutesEnd = String.valueOf(minutesE);
            }
            //Si un evento empieza y termina a la misma hora pero del dia siguiente es que dura to do el dia.
            if (getBegin().getHours() == getEnd().getHours() && getBegin().getMinutes() == getEnd().getMinutes()) {
                return getTitle() + ". Todo el dia.";
            }

            return getTitle() + ". De " + getBegin().getHours() + ":" + minutesBeg + " a " + getEnd().getHours() + ":" + minutesEnd + ".";

        }
    }
}
