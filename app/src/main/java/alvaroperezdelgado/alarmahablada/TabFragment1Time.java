package alvaroperezdelgado.alarmahablada;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import alvaroperezdelgado.alarmahablada.Model.Container;
import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Esta clase es la que controla el layout que el TabLayout mostrará
 * En este caso muestra el fragment layout de la hora
 */
public class TabFragment1Time extends Fragment {

    TextView tvDate;
    //Widgets tiempo
    @Bind(R.id.tvLocation1)
    TextView tvLocation;
    @Bind(R.id.tvCondition1)
    TextView tvCondition;
    @Bind(R.id.tvTemperature1)
    TextView tvTemperature;
    @Bind(R.id.ivIconWeather1)
    ImageView ivIconWeather;
    //Widgets mail
    @Bind(R.id.tvMail1)
    TextView tvMail1;
    @Bind(R.id.tvMail2)
    TextView tvMail2;
    @Bind(R.id.tvMail3)
    TextView tvMail3;
    @Bind(R.id.tvMail4)
    TextView tvMail4;
    @Bind(R.id.tvMail11)
    TextView tvMail11;
    @Bind(R.id.tvMail22)
    TextView tvMail22;
    @Bind(R.id.tvMail33)
    TextView tvMail33;
    @Bind(R.id.tvMail44)
    TextView tvMail44;
    //Widgets calendario
    @Bind(R.id.tvCalendar1)
    TextView tvCalendar1;
    @Bind(R.id.tvCalendar2)
    TextView tvCalendar2;
    @Bind(R.id.tvCalendar3)
    TextView tvCalendar3;
    @Bind(R.id.tvCalendar4)
    TextView tvCalendar4;
    //Aqui tenemos todos nuestros objetos a mostrar, mail, calendar, weather
    private Container container1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Esto lo hacemos para obtener el formato en el que deseamos mostrar la fecha
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //esto es para obtener la fecha
        Calendar cal = Calendar.getInstance();
        //obtener la vista
        View v = null;

        container1 = Container.getInstance();

        //desde aqui llamamos al layout que queremos que muestre
        //si nuestra version android es superior a Jelly bean ejecutaremos este código, sino el del else
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            //obtener la vista
            v = inflater.inflate(R.layout.tab_fragment_1time, container, false);

            tvDate = (TextView) v.findViewById(R.id.tvDate);
            tvDate.setText(dateFormat.format(cal.getTime()));
        } else {
            //obtener la vista
            v = inflater.inflate(R.layout.tab_fragment_1time2, container, false);
            tvDate = (TextView) v.findViewById(R.id.tvDate2);
            tvDate.setText(dateFormat.format(cal.getTime()));

        }

        //Inyectamos los widgets con butterKnife
        ButterKnife.bind(this, v);

        //ponemos en los widgets del tiempo antes creados la infomacion que necesitamos
        tvLocation.setText(container1.getWeather().getLocation());
        tvCondition.setText(container1.getWeather().getCondition());
        tvTemperature.setText(container1.getWeather().getDegrees() + "\u00B0" + "C");
        //TODO quitar este if warro necesito cargar la clase weather activity antes
        if (container1.getWeather().getIconResourceId() != 1) {
            @SuppressWarnings("deprecation") Drawable weatherIconDrawable = getResources().getDrawable(Container.getInstance().getWeather().getIconResourceId());
            ivIconWeather.setImageDrawable(weatherIconDrawable);
        }

        //Mostramos en pantalla los emails que tenemos en Emails
        int tamMails = container1.getEmails().size();

        tvMail1.setText(container1.getEmails().getEmail(tamMails - 1)[0]);
        tvMail2.setText(container1.getEmails().getEmail(tamMails - 2)[0]);
        tvMail3.setText(container1.getEmails().getEmail(tamMails - 3)[0]);
        tvMail4.setText(container1.getEmails().getEmail(tamMails - 4)[0]);
        tvMail11.setText(" " + container1.getEmails().getEmail(tamMails - 1)[1]);
        tvMail22.setText(" " + container1.getEmails().getEmail(tamMails - 2)[1]);
        tvMail33.setText(" " + container1.getEmails().getEmail(tamMails - 3)[1]);
        tvMail44.setText(" " + container1.getEmails().getEmail(tamMails - 4)[1]);

        //Mostramos por pantalla los eventos del calendario que tenemos en ListCalendarEvents
        int tamCalEvents = container1.getListCalendarEvents().size();

        tvCalendar1.setText(container1.getListCalendarEvents().getCalendarEvent(tamCalEvents - 1).toString());
        tvCalendar2.setText(container1.getListCalendarEvents().getCalendarEvent(tamCalEvents - 2).toString());
        tvCalendar3.setText(container1.getListCalendarEvents().getCalendarEvent(tamCalEvents - 3).toString());
        tvCalendar4.setText(container1.getListCalendarEvents().getCalendarEvent(tamCalEvents - 4).toString());

        Container.getInstance().setWelcomeSpeech();
        return v;
    }

    /**
     * Cargamos la configuración de los datos guardados, usando SharedPreferences que se guardarán en un
     * archivo xml
     */
    public void loadConfiguration(){
      //  SharedPreferences sharedPreferences=getSharedPreferences(String, int);

    }
}