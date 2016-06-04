package alvaroperezdelgado.alarmahablada;


import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

import alvaroperezdelgado.alarmahablada.Calendar.CalendarActivity;
import alvaroperezdelgado.alarmahablada.Model.Container;

/**
 * Esta clase es la que controla el layout que el TabLayout mostrará
 * En este caso muestra el fragment layout de la hablar
 */
public class TabFragment4Talk extends Fragment implements TextToSpeech.OnInitListener {

    private TextToSpeech textToSpeech;
    private Button btSpeechMail;
    private Button btSpeechCalendar;
    private Button btSpeechCustom;
    private Button btSpeechWeather;
    private Button btWeather;
    private Button btMail;
    private Button btCalendar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //desde aqui llamamos al layout que queremos que muestre
        View v = inflater.inflate(R.layout.tab_fragment_4talk, container, false);
        btSpeechMail = (Button) v.findViewById(R.id.btSpeechMail);
        btSpeechCalendar = (Button) v.findViewById(R.id.btSpeechCalendar);
        btSpeechCustom = (Button) v.findViewById(R.id.btSpeechCustom);
        btSpeechWeather = (Button) v.findViewById(R.id.btSpeechWeather);
        btWeather = (Button) v.findViewById(R.id.btWeather);
        btMail = (Button) v.findViewById(R.id.btMail);
        btCalendar=(Button)v.findViewById(R.id.btCalendar);

        textToSpeech = new TextToSpeech(getActivity(), this);


        btSpeechMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity(),MailActivity.class));
                Container.getInstance().getEmails().setSpeechMail();
                System.out.println(Container.getInstance().getEmails().getSpeechMail().toString());
                speak(Container.getInstance().getEmails().getSpeechMail().toString());
            }
        });

        btSpeechCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                speak(Container.getInstance().getListCalendarEvents().getSpeechCalendarEvents());
            }
        });

        btSpeechCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(Container.getInstance().getCustomMessage());
            }
        });

        btSpeechWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Container.getInstance().getWeather().setSpeechWeather();
                String cadena=Container.getInstance().getWeather().getSpeechWeather().toString();
                speak(cadena);
            }
        });

        btWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WeatherActivity.class);
                startActivity(intent);
            }
        });
        btMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MailActivity.class));
            }
        });
        btCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CalendarActivity.class));
            }
        });
        return v;
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            //primero seleccionamos el idioma en el que queremos que lo lea
            textToSpeech.setLanguage(new Locale("spa", "ESP"));

        }
        if (status == TextToSpeech.LANG_MISSING_DATA | status == TextToSpeech.LANG_NOT_SUPPORTED) {
            Toast.makeText(getActivity(), "ERROR LANG_MISSING_DATA | LANG_NOT_SUPPORTED", Toast.LENGTH_SHORT).show();
        }
    }

    //Metodo speak que define algunos parametros de speak
    private void speak(String str) {
        textToSpeech.speak(str, TextToSpeech.QUEUE_FLUSH, null);
        textToSpeech.setSpeechRate(0.0f);
        textToSpeech.setPitch(0.0f);
    }

    //cuando se sale de esta actividad
    @Override
    public void onDestroy() {
        if (textToSpeech != null) {
            //paramos el texttospeech y lo apagamos
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}