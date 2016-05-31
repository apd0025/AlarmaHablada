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


public class AddAlarm extends AppCompatActivity {

    private Context context;

    //objeto alarma
    private Alarm alarm;

    //inicializar los componentes de la interfaz
    private TextView tvDaysClick;
    private TextView tvSongClick;
    private TextView tvCustomMessageClick;
    private TextView tvSetAlarmClick;
    private TextView tvTimeShow;
    private TextView tvCustomMessageAddShow;
    private TextView tvSongAddShow;
    private TextView tvDaysAddShow;

    //Inicializar botones
    private Button btAccept;
    private Button btCancel;

    //Objeto alarm manager que gestiona nuestra alarma
    private AlarmManager alarmManager;

    //Creamos un PendingIntent que retrasara nuestro intent
    //Hasta lo especificado en el calendario
    PendingIntent pendingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);
        tvDaysClick = (TextView) findViewById(R.id.tvDaysAddClick);
        tvSongClick = (TextView) findViewById(R.id.tvSongAddClick);
        tvCustomMessageClick = (TextView) findViewById(R.id.tvCustomMessageAddClick);
        tvSetAlarmClick=(TextView)findViewById(R.id.tvTimeAddClick);
        btAccept=(Button)findViewById(R.id.btAcceptAddAlarm);
        btCancel=(Button)findViewById(R.id.btCancelAddAlarm);
        tvTimeShow=(TextView)findViewById(R.id.tvTimeShow);
        tvCustomMessageAddShow =(TextView)findViewById(R.id.tvCustomMessageAddShow);
        tvSongAddShow =(TextView)findViewById(R.id.tvSongAddShow);
        tvDaysAddShow=(TextView)findViewById(R.id.tvDaysAddShow);

        //obtener una instancia de alarm
        alarm=Alarm.getInstance();

        //mostrar informacion a traves de los textview
        tvTimeShow.setText(alarm.getHour()+" : "+alarm.getMin());
        tvCustomMessageAddShow.setText(Container.getInstance().getCustomMessage());
        //tvSongAddShow.setText(Container.getInstance().getSong().getName().toString());
        tvDaysAddShow.setText("LUNES TODO");


        //Control de los botones
        tvDaysClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddAlarm.this, Days.class));
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
