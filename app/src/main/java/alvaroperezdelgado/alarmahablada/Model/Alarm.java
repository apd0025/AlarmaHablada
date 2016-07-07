package alvaroperezdelgado.alarmahablada.Model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Esta clase contendra toda la información que podemos tener en un objeto alarma.
 * La utilizaremos para acceder de una forma mas sencilla a nuestra información.
 * Esta clase sera singleton ya que solo queremos una alarma.
 */

public class Alarm {
    private int hour;
    private int min;
    private Calendar calendar;
    private String showHour, showMin;
    //para saber si la hemos activado
    private Boolean isActive = false;
    //para saber si esta sonando
    private Boolean isRinging = false;
    //Para saber si han elegido mail para la alarma
    private Boolean selectMail = true;
    //Para saber si han elegido calendar para alarma
    private Boolean selectCalendar = true;
    //Para saber si han elegido weather para alarma
    private Boolean selectWeather = true;
    //Para saber si han elegido customMesage para alarma
    private Boolean selectCustom = true;
    //0-customMessage, 1-Mail, 2-Calendar, 3-weathfer, 4-music
    private static List<Boolean> choosenoptions = new ArrayList<Boolean>(6);

    private static Alarm alarm;

    //con este metodo obtenemos la instancia de alarm
     /*
    Metodo para obtener una instancia de la clase que es singleton
     */
    public static Alarm getInstance() {
        if (alarm == null) {
            alarm = new Alarm();

        }
        return alarm;

    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
        this.showHour = convertOneDigit(hour);
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
        this.showMin = convertOneDigit(min);
    }


    public String getShowHour() {
        return showHour;
    }

    public String getShowMin() {
        return showMin;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsRinging() {
        return isRinging;
    }

    public void setIsRinging(Boolean isRinging) {
        this.isRinging = isRinging;
    }

    public Boolean getSelectCalendar() {
        return selectCalendar;
    }

    public void setSelectCalendar(Boolean selectCalendar) {
        this.selectCalendar = selectCalendar;
    }

    public Boolean getSelectWeather() {
        return selectWeather;
    }

    public void setSelectWeather(Boolean selectWeather) {
        this.selectWeather = selectWeather;
    }

    public Boolean getSelectCustom() {
        return selectCustom;
    }

    public void setSelectCustom(Boolean selectCustom) {
        this.selectCustom = selectCustom;
    }

    public Boolean getSelectMail() {
        return selectMail;
    }

    public void setSelectMail(Boolean selectMail) {
        this.selectMail = selectMail;
    }


    public static void setChoosenoptions(List<Boolean> choosenoptions) {
        Alarm.choosenoptions = choosenoptions;
    }

    /**
     * Este método sirve para poner un 0 delante cuando se necesita
     *
     * @param aux
     * @return
     */
    public String convertOneDigit(int aux) {
        String string;
        //Colocamos el 0 delante de la hora y el minuto para que quede bien
        if (aux < 10) {
            string = "0" + aux;
        } else {
            string = String.valueOf(aux);
        }
        return string;
    }

    public void deleteListChoosenoptions() {
        choosenoptions.removeAll(choosenoptions);
    }


}
