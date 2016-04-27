package alvaroperezdelgado.alarmahablada.Alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import alvaroperezdelgado.alarmahablada.Model.Alarm;
import alvaroperezdelgado.alarmahablada.R;


public class AddAlarm extends AppCompatActivity {
    //alarmManager
    private AlarmManager alarmManager;

    //time picker como obtendremos la hora de la alarma
    private TimePicker alarmTimePicker;

    //texto del medio
    private TextView tvUpdateText;

    private Context context;

    //objeto alarma
    private Alarm alarm;
    //Create a pendidgIntent that delays the intent
    //until the specified calendar time
    private PendingIntent pendingIntent;

    private TextView tvDaysClick;
    private TextView tvSongClick;
    private TextView tvNameClick;
    private TextView tvSetAlarmClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);
        tvDaysClick = (TextView) findViewById(R.id.tvDaysAddClick);
        tvSongClick = (TextView) findViewById(R.id.tvSongAddClick);
        tvNameClick = (TextView) findViewById(R.id.tvNameAddClick);
        tvSetAlarmClick=(TextView)findViewById(R.id.tvTimeAddClick);

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
        tvNameClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddAlarm.this, SetAlarmName.class));
            }
        });

        tvSetAlarmClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddAlarm.this,SetAlarm.class));
            }
        });


    }

}
