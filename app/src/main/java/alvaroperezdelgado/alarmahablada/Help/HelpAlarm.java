package alvaroperezdelgado.alarmahablada.Help;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import alvaroperezdelgado.alarmahablada.R;
import alvaroperezdelgado.alarmahablada.ViewControl.MainActivity;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Clase que sirve para controlar lo que hace la vista de ayuda de Alarma, en la que se explica
 * como se debe usar la aplicación para poder configurar la alarma correctamente.
 */
public class HelpAlarm extends AppCompatActivity {
    @Bind(R.id.btCancelHelpAlarm)
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_alarm);
        ButterKnife.bind(this);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HelpAlarm.this, MainActivity.class));
            }
        });
    }
}
