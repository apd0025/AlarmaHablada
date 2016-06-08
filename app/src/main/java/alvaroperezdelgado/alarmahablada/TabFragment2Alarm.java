package alvaroperezdelgado.alarmahablada;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Esta clase es la que controla el layout que el TabLayout mostrará
 * En este caso muestra el fragment layout de la alarma
 * Esta clase sirve para gestionar la alarma, todas sus propiedades
 */
public class TabFragment2Alarm extends Fragment {

    @Bind(R.id.tvTabAlarmName)
    TextView tvTabAlarmName;
    @Bind(R.id.tvTabAlarmTime)
    TextView tvTabAlarmTime;
    @Bind(R.id.tvIsActive)
    TextView tvTabAlarmdays;

    @Bind(R.id.btNewAlarm)
    Button btNewAlarm;
    @Bind(R.id.btSetAlarmCancel)
    Button btCancelAlarm;
    @Bind(R.id.swIsAllowed)
    Button swIsAllowed;

    private Alarm alarm;

    //alarmManager
    private AlarmManager alarmManager;

    //Crearemos un pendingIntent para retrasar un intent hasta una hora especificada
    PendingIntent pendingIntent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Obtenemos la vista
        View v = inflater.inflate(R.layout.tab_fragment_2alarm, container, false);
        //Inyectar con butterKnife
        ButterKnife.bind(this, v);

        alarm = Alarm.getInstance();
        //Iniciamos los objetos visuales con valor
        tvTabAlarmName.setText(User.getInstance().getName());


        tvTabAlarmTime.setText(alarm.getShowHour() + " : " + alarm.getShowMin());
        tvTabAlarmdays.setText(alarm.getIsActive().toString());
        //Boton de añadir alarma
        btNewAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddAlarm.class);
                startActivity(intent);
            }
        });

        final Intent intent = new Intent(getActivity(), AlarmReceiver.class);
        //Inicializamos el alarm manager
        alarmManager = (AlarmManager) this.getContext().getSystemService(getContext().ALARM_SERVICE);

        //Boton de cancelar alarma
        btCancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Cancelar alarma
                alarmManager.cancel(pendingIntent);

                //Ponemos una cadena en extra en el intent, para saber si hemos pulsado el boton de off
                intent.putExtra("extra", "alarm off");

                //Parar el ringtone
                getContext().sendBroadcast(intent);
                alarm.setIsActive(false);
                System.out.println(alarm.getIsActive().toString());
                startActivity(new Intent(getActivity(),MainActivity2.class));
            }
        });

        if(alarm.getIsRinging()){
            tvTabAlarmName.setText("Hola mundo");
        }
        //Switch comportamiento de este switch
         /*
        swIsAllowed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(swIsAllowed.isActivated()){
                    alarm.setIsAllowed(true);
                }else{
                    alarm.setIsAllowed(false);
                }
            }
        });
*/
        //desde aqui llamamos al layout que queremos que muestre
        return v;

    }


    /**
     * Método que guarda en un xml MyPreferences el dato del Mail
     *
     * @param sHour
     * @param sMin
     */
    public void savePreferences(int sHour, int sMin) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("AlarmIsActive", false);
        editor.commit();
    }

}