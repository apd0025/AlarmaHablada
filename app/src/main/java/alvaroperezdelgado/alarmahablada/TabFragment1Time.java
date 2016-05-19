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

/**
 * Esta clase es la que controla el layout que el TabLayout mostrará
 * En este caso muestra el fragment layout de la hora
 */
public class TabFragment1Time extends Fragment {

    private TextView tvDate;
    private TextView tvLocation;
    private TextView tvCondition;
    private TextView tvTemperature;
    private ImageView ivIconWeather;
    private TextView tvMail1,tvMail2,tvMail3,tvMail4,tvMail5;
    private TextView tvCalendar1,tvCalendar2,tvCalendar3,tvCalendar4,tvCalendar5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Esto lo hacemos para obtener el formato en el que deseamos mostrar la fecha
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //esto es para obtener la fecha
        Calendar cal = Calendar.getInstance();
        //obtener la vista
        View v = null;

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
        //creamos los widgets que van a visualizar el tiempo
        tvLocation = (TextView) v.findViewById(R.id.tvLocation1);
        tvCondition = (TextView) v.findViewById(R.id.tvCondition1);
        tvTemperature = (TextView) v.findViewById(R.id.tvTemperature1);
        ivIconWeather = (ImageView) v.findViewById(R.id.ivIconWeather1);

        //creamos los widgets que van a visualizar los mail
        tvMail1 = (TextView) v.findViewById(R.id.tvMail1);
        tvMail2 = (TextView) v.findViewById(R.id.tvMail2);
        tvMail3 = (TextView) v.findViewById(R.id.tvMail3);
        tvMail4 = (TextView) v.findViewById(R.id.tvMail4);
        tvMail5 = (TextView) v.findViewById(R.id.tvMail5);

        //creamos los widgets que van a visualizar los calendarios
        tvCalendar1 = (TextView) v.findViewById(R.id.tvCalendar1);
        tvCalendar2 = (TextView) v.findViewById(R.id.tvCalendar2);
        tvCalendar3 = (TextView) v.findViewById(R.id.tvCalendar3);
        tvCalendar4 = (TextView) v.findViewById(R.id.tvCalendar4);
        tvCalendar5 = (TextView) v.findViewById(R.id.tvCalendar5);

        //ponemos en los widgets antes creados la infomacion que necesitamos
        tvLocation.setText(Container.getInstance().getWeather().getLocation());
        tvCondition.setText(Container.getInstance().getWeather().getCondition());
        tvTemperature.setText(Container.getInstance().getWeather().getDegrees() + "\u00B0"+"C");
        //TODO quitar este if warro necesito cargar la clase weather activity antes
        if(Container.getInstance().getWeather().getIconResourceId()!=1) {
            @SuppressWarnings("deprecation") Drawable weatherIconDrawable = getResources().getDrawable(Container.getInstance().getWeather().getIconResourceId());
            ivIconWeather.setImageDrawable(weatherIconDrawable);
        }

        return v;
    }
}