package alvaroperezdelgado.alarmahablada.Options;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import alvaroperezdelgado.alarmahablada.ViewControl.MainActivity;
import alvaroperezdelgado.alarmahablada.Model.User;
import alvaroperezdelgado.alarmahablada.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SetCityOptions extends AppCompatActivity {

    //boton de aceptar de la activiti que guardará los valores y retornara a la anterior vista
    @Bind(R.id.btAcceptCityOptions)
    Button acept;
    //boton de cancelar de la activiti que guardará los valores y retornara a la anterior vista
    @Bind(R.id.btCancelCityOptions)
    Button cancel;
    //campo donde introduciremos el texto que queremos guardar
    @Bind(R.id.etSetCityOptions)
    EditText city;

    //objeto singleton user donde guardaremos el nombre
    private User user;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_city_options);
        //Necesitamos esto para que se haga la asignacion de los widgets
        ButterKnife.bind(this);
        user=User.getInstance();

        //Controlar lo que pasa al hacer click en el boton aceptar
        acept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sCity=city.getText().toString();
                user.setCity(sCity);
                savePreferences(sCity);
                startActivity(new Intent(SetCityOptions.this, MainActivity.class));
            }
        });
        //cuando pulsamos el boton cancelar
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SetCityOptions.this, MainActivity.class));
            }
        });
    }

    /**
     * Método que guarda en un xml MyPreferences el dato de la ciudad
     * @param sCity
     */
    public void savePreferences(String sCity){
        SharedPreferences sharedPreferences=getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("UserCity",sCity);
        editor.commit();
    }
}
