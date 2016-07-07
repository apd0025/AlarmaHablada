package alvaroperezdelgado.alarmahablada.Alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;

import alvaroperezdelgado.alarmahablada.Model.Alarm;
import alvaroperezdelgado.alarmahablada.Model.Container;
import alvaroperezdelgado.alarmahablada.R;

/**
 * Clase que se encarga preparar y decir hablando las cadenas que previamente se han establecido.
 * Se abre pulsando desde la notificación.
 */
public class SpeechAlarm extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private Container container;
    private Alarm alarm;
    private TextToSpeech textToSpeech;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_alarm);
        textToSpeech = new TextToSpeech(this, this);
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            setContentView(R.layout.activity_speech_alarm);
            final Intent intent = new Intent(this, AlarmReceiver.class);

            //sirve para cancelar el sonido de la alarma
            //Inicializamos el alarm manager
            AlarmManager alarmManager = (AlarmManager) this.getSystemService(getApplicationContext().ALARM_SERVICE);
            //Ponemos una cadena en extra en el intent, para saber si hemos pulsado el boton de off
            intent.putExtra("extra", "alarm off");
            //Parar el ringtone
            getApplicationContext().sendBroadcast(intent);
            //----

            alarm = Alarm.getInstance();
            //primero seleccionamos el idioma en el que queremos que lo lea
            textToSpeech.setLanguage(new Locale("spa", "ESP"));

            String aux = null;
            //obtenemos una instancia de container
            container = Container.getInstance();
            //Ponemos la cadena del saludo inicial
            container.setWelcomeSpeech();
            aux = container.getWelcomeSpeech();
            Log.d("SpeechAlarm", "Making string speak");
            //se miran los textos que queremos decir y los preparamos para decirlos
            if (alarm.getSelectWeather()) {
                container.getWeather().setSpeechWeather();
                aux = aux.concat(", " + container.getWeather().getSpeechWeather());
            }
            if (alarm.getSelectCalendar()) {
                container.getListCalendarEvents().setSpeechCalendar();
                aux = aux.concat(", " + container.getListCalendarEvents().getSpeechCalendarEvents());
            }
            if (alarm.getSelectMail()) {
                container.getEmails().setSpeechMail();
                aux = aux.concat(", " + container.getEmails().getSpeechMail());
            }
            if (alarm.getSelectCustom()) {
                aux = aux.concat(", " + container.getCustomMessage());

            }
            //Método que hace hablar a nuestra aplicación
            speak(aux);
        }
        if (status == TextToSpeech.LANG_MISSING_DATA | status == TextToSpeech.LANG_NOT_SUPPORTED) {
            Toast.makeText(this, "ERROR LANG_MISSING_DATA | LANG_NOT_SUPPORTED", Toast.LENGTH_SHORT).show();
        }
    }

    private void speak(String str) {
        textToSpeech.speak(str, TextToSpeech.QUEUE_FLUSH, null);
        textToSpeech.setSpeechRate(0.0f);
        textToSpeech.setPitch(0.0f);
    }

    //cuando se sale de esta actividad
    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            //paramos el texttospeech y lo apagamos
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }

}


