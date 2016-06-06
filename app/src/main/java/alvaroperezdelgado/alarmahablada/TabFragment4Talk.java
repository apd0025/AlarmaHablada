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
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Esta clase es la que controla el layout que el TabLayout mostrará
 * En este caso muestra el fragment layout de la hablar
 * Esta clase contiene toda la gestión de como se comportan los botones a la hora de pulsarlos, establecer que cadenas debe leer cada uno
 */
public class TabFragment4Talk extends Fragment implements TextToSpeech.OnInitListener {

    private TextToSpeech textToSpeech;

    @Bind(R.id.btSpeechMail)
    Button btSpeechMail;
    @Bind(R.id.btSpeechCalendar)
    Button btSpeechCalendar;
    @Bind(R.id.btSpeechCustom)
    Button btSpeechCustom;
    @Bind(R.id.btSpeechWeather)
    Button btSpeechWeather;
    @Bind(R.id.btWeather)
    Button btWeather;
    @Bind(R.id.btMail)
    Button btMail;
    @Bind(R.id.btCalendar)
    Button btCalendar;
    @Bind(R.id.btSpeechHello)
    Button btSpeechHello;
    @Bind(R.id.btStop)
    Button btStop;

    private Container container1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //desde aqui llamamos al layout que queremos que muestre
        View v = inflater.inflate(R.layout.tab_fragment_4talk, container, false);

        //Inyectamos los widgets con butterknife
        ButterKnife.bind(this, v);

        container1 = Container.getInstance();

        textToSpeech = new TextToSpeech(getActivity(), this);

        //Boton de hablar del mail
        btSpeechMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container1.getEmails().setSpeechMail();
                speak(container1.getEmails().getSpeechMail().toString());
            }
        });

        //Boton de hablar de calendario
        btSpeechCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container1.getListCalendarEvents().setSpeechCalendar();
                speak(container1.getListCalendarEvents().getSpeechCalendarEvents());
            }
        });

        //Boton de hablar del mensaje customizado
        btSpeechCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(container1.getCustomMessage());
            }
        });

        //Boton de hablar del tiempo
        btSpeechWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container1.getWeather().setSpeechWeather();
                String cadena = container1.getWeather().getSpeechWeather().toString();
                speak(cadena);
            }
        });

        //Boton de hablar del saludo inicial
        btSpeechHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container1.setWelcomeSpeech();
                String cadena = container1.getWelcomeSpeech().toString();
                speak(cadena);
            }
        });

        //Boton de parada
        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak("");
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

    //Inicializamos lo fundamental para que funcione el textToSpeech
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