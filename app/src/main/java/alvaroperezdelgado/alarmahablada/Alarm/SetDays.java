package alvaroperezdelgado.alarmahablada.Alarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import java.util.List;

import alvaroperezdelgado.alarmahablada.Model.Alarm;
import alvaroperezdelgado.alarmahablada.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Esta clase sirve para seleccionar los dias de la semana en funcion de unos switch que hemos creado con cada uno de los dias
 */
public class SetDays extends AppCompatActivity {

    //objeto alarma
    private Alarm alarm;

    private enum week {Mon, tue, wed, thu, fri, sat, sun};

    //Button acceptar
    @Bind(R.id.btAcceptDays)
    Button btAceptar;
    @Bind(R.id.swMonday)
    Switch swMon;
    @Bind(R.id.swTuesday)
    Switch swTue;
    @Bind(R.id.swWednesday)
    Switch swWed;
    @Bind(R.id.swThursday)
    Switch swThu;
    @Bind(R.id.swFriday)
    Switch swFri;
    @Bind(R.id.swSaturday)
    Switch swSat;
    @Bind(R.id.swSunday)
    Switch swSun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days);
        //Inyectamos los widgets con ButterKnife
        ButterKnife.bind(this);

        //get instance Alarm
        alarm = Alarm.getInstance();
        List<Boolean> dias;
        dias = alarm.getAlarmDays();


        //mostraremos el switch activado o desactivado en funcion de si esta o no activado
        if (dias.get(0) == true) {
            swMon.setChecked(true);
        }
        if (dias.get(1) == true) {
            swTue.setChecked(true);
        }
        if (dias.get(2) == true) {
            swWed.setChecked(true);
        }
        if (dias.get(3) == true) {
            swThu.setChecked(true);
        }
        if (dias.get(4) == true) {
            swFri.setChecked(true);
        }
        if (dias.get(5) == true) {
            swSat.setChecked(true);
        }
        if (dias.get(6) == true) {
            swSun.setChecked(true);
        }

        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //primero borramos la lista anterior de dias
                alarm.deleteListDays();
                //añadimos el valor que tenga cada dia
                alarm.setAlarmDays(1, swMon.isChecked());
                alarm.setAlarmDays(2, swTue.isChecked());
                alarm.setAlarmDays(3, swWed.isChecked());
                alarm.setAlarmDays(4, swThu.isChecked());
                alarm.setAlarmDays(5, swFri.isChecked());
                alarm.setAlarmDays(6, swSat.isChecked());
                alarm.setAlarmDays(7, swSun.isChecked());

                //Volvemos a la actividad anterior
                startActivity(new Intent(SetDays.this, AddAlarm.class));
            }
        });
    }
}
