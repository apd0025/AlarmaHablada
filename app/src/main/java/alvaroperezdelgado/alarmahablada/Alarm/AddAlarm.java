package alvaroperezdelgado.alarmahablada.Alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

import alvaroperezdelgado.alarmahablada.MainActivity;
import alvaroperezdelgado.alarmahablada.Model.Alarm;
import alvaroperezdelgado.alarmahablada.Model.Container;
import alvaroperezdelgado.alarmahablada.R;
import butterknife.Bind;
import butterknife.ButterKnife;


public class AddAlarm extends AppCompatActivity {

    private Context context;

    //objeto alarma
    private Alarm alarm;

    //inicializar los componentes de la interfaz
    @Bind(R.id.tvDaysAddClick)
    TextView tvDaysClick;
    @Bind(R.id.tvSongAddClick)
    TextView tvSongClick;
    @Bind(R.id.tvCustomMessageAddClick)
    TextView tvCustomMessageClick;
    @Bind(R.id.tvTimeAddClick)
    TextView tvSetAlarmClick;
    @Bind(R.id.tvTimeShow)
    TextView tvTimeShow;
    @Bind(R.id.tvCustomMessageAddShow)
    TextView tvCustomMessageAddShow;
    @Bind(R.id.tvSongAddShow)
    TextView tvSongAddShow;
    @Bind(R.id.tvDaysAddShow)
    TextView tvDaysAddShow;
    //Inicializar botones
    @Bind(R.id.btAcceptAddAlarm)
    Button btAccept;
    @Bind(R.id.btCancelAddAlarm)
    Button btCancel;

    //Objeto alarm manager que gestiona nuestra alarma
    private AlarmManager alarmManager;

    //Creamos un PendingIntent que retrasara nuestro intent
    //Hasta lo especificado en el calendario
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);

        //Inyectamos los widgets con butterKnife
        ButterKnife.bind(this);

        //obtener una instancia de alarm
        alarm=Alarm.getInstance();

        //mostrar informacion a traves de los textview
        tvTimeShow.setText(alarm.getShowHour()+" : "+alarm.getShowMin());
        tvCustomMessageAddShow.setText(Container.getInstance().getCustomMessage());
        //tvSongAddShow.setText(Container.getInstance().getSong().getName().toString());
        tvDaysAddShow.setText("LUNES TODO");


        //Control de los botones
        tvDaysClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddAlarm.this, SetDays.class));
            }
        });
        tvSongClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddAlarm.this, SelectSong.class));
            }
        });
        tvCustomMessageClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddAlarm.this, SetAlarmCustomMessage.class));
            }
        });

        tvSetAlarmClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddAlarm.this,SetAlarm.class));
            }
        });

        //inicialize our alarmManager
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        //obtener una instancia de alarm
        alarm=Alarm.getInstance();
        //Creamos una instancia de calendar
        final Calendar calendar = Calendar.getInstance();
        //Creamos un intent con quien recive la llamada
        final Intent intent = new Intent(AddAlarm.this, AlarmReceiver.class);

        btAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //put in extra string into intent
                //tell the clock that you pressed the alarm on button
                //put extra works putting name and string
                intent.putExtra("extra", "alarm on");
                calendar.set(Calendar.HOUR_OF_DAY, alarm.getHour());//getCurrentHour()
                calendar.set(Calendar.MINUTE, alarm.getMin());//getCurrentMinute()
                calendar.set(Calendar.SECOND, 0);

                //Creamos un pendingIntent que retrasa un intent
                //Hasta un periodo especificado en el calendario
                //tels the alarm manager to send a delayed intent
                pendingIntent = PendingIntent.getBroadcast(AddAlarm.this, 0,
                        intent, PendingIntent.FLAG_UPDATE_CURRENT);

                //set the alarmManager
                //le pasamos el reloj de tiempo real, el calendario que contiene la hora y minuto a los que vamos a poner la alarma
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis()
                        , pendingIntent);
                startActivity(new Intent(AddAlarm.this, MainActivity.class));
            }
        });

        //el boton cancelar nos devuelve a la ventana anterior sin poner la alarma
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AddAlarm.this, MainActivity.class));
            }
        });
    }

}
