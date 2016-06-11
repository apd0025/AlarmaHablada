package alvaroperezdelgado.alarmahablada.Model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Esta clase contendra toda la informacion que podemos tener en un objeto alarma.
 * La utilizaremos para acceder de una forma mas sencilla a nuestra informacion.
 * Esta clase sera singleton ya que solo queremos una alarma
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
    //para saber si la tenemos permitida
    private Boolean isAllowed = false;
    //Para saber si han elegido mail para la alarma
    private Boolean selectMail;
    //Para saber si han elegido calendar para alarma
    private Boolean selectCalendar;
    //Para saber si han elegido weather para alarma
    private Boolean selectWeather;
    //Para saber si han elegido customMesage para alarma
    private Boolean selectCustom;

    private enum week {mon, tue, wed, thu, fri, sat, sun}

    ;

    //0-Mon,1-tue,2-wed,3-thu,4-fri,5-sat,6-sun
    private static List<Boolean> alarmDays = new ArrayList<Boolean>(7);

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
            //quiero forzar que de primeras todos los dias salgan activados
            for (int i = 0; i < 7; i++) {
                alarmDays.add(true);

            }
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

    public List<Boolean> getAlarmDays() {
        return alarmDays;
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

    public Boolean getIsAllowed() {
        return isAllowed;
    }

    public void setIsAllowed(Boolean isAllowed) {
        this.isAllowed = isAllowed;
    }

    public static void setChoosenoptions(List<Boolean> choosenoptions) {
        Alarm.choosenoptions = choosenoptions;
    }

    public void setAlarmDays(int index, boolean value) {
        switch (index) {
            case 1:
                alarmDays.add(0, value);
                break;
            case 2:
                alarmDays.add(1, value);
                break;
            case 3:
                alarmDays.add(2, value);
                break;
            case 4:
                alarmDays.add(3, value);
                break;
            case 5:
                alarmDays.add(4, value);
                break;
            case 6:
                alarmDays.add(5, value);
                break;
            case 7:
                alarmDays.add(6, value);
                break;
            default:
                break;
        }
    }

    /*
    Borra la lista de los dias
     */
    public void deleteListDays() {

        alarmDays.removeAll(alarmDays);
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
