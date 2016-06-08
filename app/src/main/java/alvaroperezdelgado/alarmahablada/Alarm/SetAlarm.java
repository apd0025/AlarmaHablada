package alvaroperezdelgado.alarmahablada.Alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import alvaroperezdelgado.alarmahablada.Model.Alarm;
import alvaroperezdelgado.alarmahablada.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SetAlarm extends AppCompatActivity {
    //alarmManager
    private AlarmManager alarmManager;

    //time picker como obtendremos la hora de la alarma
    @Bind(R.id.tmTimePicker)
    TimePicker alarmTimePicker;
    @Bind(R.id.btAcceptSet)
    Button btAcceptTime;
    @Bind(R.id.btCancelSet)
    Button btCancelTime;

    //texto del medio
    private TextView tvUpdateText;

    private Context context;

    //objeto alarma
    private Alarm alarm;
    //Create a pendidgIntent that delays the intent
    //until the specified calendar time
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

        ButterKnife.bind(this);
        //obtenemos el objeto alarma
        this.context = this;

        //inicialize our alarmManager
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //create an instance of a calendar
        final Calendar calendar = Calendar.getInstance();

        //obtenemos el objeto alarma
        alarm = Alarm.getInstance();

        //Boton de aceptar la alarma creamos los datos de lahora
        btAcceptTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour, minute;

                //Esto comprueba si la version es superior a la que necesita o no para usar un metodo u otro
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    //get the string values of the hour and minute
                    hour = alarmTimePicker.getHour();
                    minute = alarmTimePicker.getMinute();
                } else {

                    hour = alarmTimePicker.getCurrentHour();
                    minute = alarmTimePicker.getCurrentMinute();
                }
                //guardamos en alarma la hora y el minuto
                alarm.setMin(minute);
                alarm.setHour(hour);
                savePreferences(hour, minute);
                //Volvemos a la pantalla anterior
                startActivity(new Intent(SetAlarm.this, AddAlarm.class));
            }
        });

        //Boton de cancelar para volver a la pantlala anterior
        btCancelTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Volvemos a la pantalla anterior
                startActivity(new Intent(SetAlarm.this, AddAlarm.class));
            }
        });
    }

    /**
     * Método que guarda en un xml MyPreferences el dato del Mail
     *
     * @param sHour
     * @param sMin
     */
    public void savePreferences(int sHour, int sMin) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("AlarmHour", sHour);
        editor.putInt("AlarmMin", sMin);
        editor.commit();
    }
}
