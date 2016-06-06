package alvaroperezdelgado.alarmahablada.Alarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import alvaroperezdelgado.alarmahablada.Model.Container;
import alvaroperezdelgado.alarmahablada.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SetAlarmCustomMessage extends AppCompatActivity {

    //Declaramos los widgets que vamos a usar
    //cadena par aguardar el mensaje personalizado
    private String aux;
    //boton de aceptar de la activiti que guardará los valores y retornara a la anterior vista
    @Bind(R.id.btSetCustomMessageAccept)
    Button acept;
    //boton de cancelar de la activiti que guardará los valores y retornara a la anterior vista
    @Bind(R.id.btSetCustomMessageCancel)
    Button cancel;
    //campo donde introduciremos el texto que queremos guardar
    @Bind(R.id.etSetName)
    EditText message;
    //objeto singleton alarm donde guardaremos el mensaje
    private Container container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm_custom_message);

        //Inyectamos los widgets con butterKnife
        ButterKnife.bind(this);

        //obtenemos la instancia del contenedor
        container = Container.getInstance();

        //Controlar lo que pasa al hacer click en el boton aceptar
        acept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aux = message.getText().toString();
                container.setCustomMessage(aux);
                startActivity(new Intent(SetAlarmCustomMessage.this, AddAlarm.class));
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SetAlarmCustomMessage.this, AddAlarm.class));
            }
        });
    }
}
