package alvaroperezdelgado.alarmahablada.ViewControl;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import alvaroperezdelgado.alarmahablada.Help.HelpAlarm;
import alvaroperezdelgado.alarmahablada.Help.HelpCalendar;
import alvaroperezdelgado.alarmahablada.Help.HelpContact;
import alvaroperezdelgado.alarmahablada.Help.HelpMail;
import alvaroperezdelgado.alarmahablada.Help.HelpWeather;
import alvaroperezdelgado.alarmahablada.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Esta clase es la que controla el layout que el TabLayout mostrará.
 * En este caso muestra el fragment layout de la Ayuda.
 * Clase que sirve para acceder a las opciones de ayuda desde el menú principal.
 */
public class TabFragment5Help extends Fragment {


    @Bind(R.id.btCalendarHelp)
    Button btCalendarHelp;
    @Bind(R.id.btMailHelp)
    Button btMailHelp;
    @Bind(R.id.btContactHelp)
    Button btContactHelp;
    @Bind(R.id.btWeatherHelp)
    Button btWeatherHelp;
    @Bind(R.id.btAlarmHelp)
    Button btAlarmHelp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //desde aqui llamamos al layout que queremos que muestre
        View view = inflater.inflate(R.layout.tab_fragment_5help, container, false);
        ButterKnife.bind(this, view);

        btCalendarHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HelpCalendar.class));
            }
        });
        btMailHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HelpMail.class));
            }
        });
        btContactHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HelpContact.class));
            }
        });
        btWeatherHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HelpWeather.class));
            }
        });
        btAlarmHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HelpAlarm.class));
            }
        });
        return view;
    }
}