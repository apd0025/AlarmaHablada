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

/**
 * Esta clase sirve para seleccionar los dias de la semana en funcion de unos switch que hemos creado con cada uno de los dias
 */
public class Days extends AppCompatActivity {

    //Button accept
    private Button btAceptar;
    //objet alarm
    private Alarm alarm;

    private enum week {Mon, tue, wed, thu, fri, sat, sun}

    ;

    private Switch swMon, swTue, swWed, swThu, swFri, swSat, swSun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days);

        //get id switches
        swMon = (Switch) findViewById(R.id.swMonday);
        swTue = (Switch) findViewById(R.id.swTuesday);
        swWed = (Switch) findViewById(R.id.swWednesday);
        swThu = (Switch) findViewById(R.id.swThursday);
        swFri = (Switch) findViewById(R.id.swFriday);
        swSat = (Switch) findViewById(R.id.swSaturday);
        swSun = (Switch) findViewById(R.id.swSunday);

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

        findViewById(R.id.btAcceptDays).setOnClickListener(new View.OnClickListener() {
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
                startActivity(new Intent(Days.this, AddAlarm.class));
            }
        });
    }
}
