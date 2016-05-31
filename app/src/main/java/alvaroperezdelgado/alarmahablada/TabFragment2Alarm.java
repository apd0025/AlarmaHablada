package alvaroperezdelgado.alarmahablada;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import alvaroperezdelgado.alarmahablada.Alarm.AddAlarm;
import alvaroperezdelgado.alarmahablada.Alarm.AlarmReceiver;
import alvaroperezdelgado.alarmahablada.Model.Alarm;
import alvaroperezdelgado.alarmahablada.Model.User;

/**
 * Esta clase es la que controla el layout que el TabLayout mostrará
 * En este caso muestra el fragment layout de la alarma
 */
public class TabFragment2Alarm extends Fragment {

    private TextView tvTabAlarmName;
    private TextView tvTabAlarmTime;
    private TextView tvTabAlarmdays;


    private Button btNewAlarm;
    private Button btCancelAlarm;
    private Alarm alarm;

    //alarmManager
    private AlarmManager alarmManager;
    //Create a pendidgIntent that delays the intent
    //until the specified calendar time
    PendingIntent pendingIntent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Obtenemos la vista
        View v = inflater.inflate(R.layout.tab_fragment_2alarm, container, false);
        //Inicializamos los botones
        btNewAlarm = (Button) v.findViewById(R.id.btNewAlarm);
        btCancelAlarm = (Button) v.findViewById(R.id.btSetAlarmCancel);
        tvTabAlarmName = (TextView) v.findViewById(R.id.tvTabAlarmName);
        tvTabAlarmTime = (TextView) v.findViewById(R.id.tvTabAlarmTime);
        tvTabAlarmdays = (TextView) v.findViewById(R.id.tvTabAlarmdays);

        alarm=Alarm.getInstance();
        //Iniciamos los objetos visuales con valor
        tvTabAlarmName.setText(User.getInstance().getName());
        tvTabAlarmTime.setText(alarm.getHour()+" : "+alarm.getMin());
        tvTabAlarmdays.setText("Dias TODO");
        btNewAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddAlarm.class);
                startActivity(intent);
            }
        });

        final Intent intent = new Intent(getActivity(), AlarmReceiver.class);
        //inicialize our alarmManager
        alarmManager = (AlarmManager) this.getContext().getSystemService(getContext().ALARM_SERVICE);




        btCancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //cancel the alarm
                alarmManager.cancel(pendingIntent);

                //put extra string into intent
                //tells the clock that you pressed the alarm off button
                intent.putExtra("extra", "alarm off");


                //stop the ringtone
                getContext().sendBroadcast(intent);


            }
        });
        //desde aqui llamamos al layout que queremos que muestre
        return v;

    }


}