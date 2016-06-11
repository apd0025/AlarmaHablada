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

public class SetNameOptions extends AppCompatActivity {

    private String aux;
    //objeto singleton user donde guardaremos el nombre
    private User user;
    //boton de aceptar de la activity que guardará los valores y retornara a la anterior vista
    @Bind(R.id.btAcceptSetNameOptions)
    Button acept;
    //boton de cancelar de la activity que guardará los valores y retornara a la anterior vista
    @Bind(R.id.btCancelSetNameOptions)
    Button cancel;
    //campo donde introduciremos el texto que queremos guardar
    @Bind(R.id.etSetNameOptions)
    EditText name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_name_options);
        //Necesitamos esto para que se haga la asignacion de los widgets
        ButterKnife.bind(this);

        user=User.getInstance();

        //Controlar lo que pasa al hacer click en el boton aceptar
        acept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sName=name.getText().toString();
                user.setName(sName);
                savePreferences(sName);
                startActivity(new Intent(SetNameOptions.this, MainActivity.class));
            }
        });
        //Cuando pulsamos el boton cancelar
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SetNameOptions.this, MainActivity.class));
            }
        });
    }

    /**
     * Método que guarda en un xml MyPreferences el dato de la ciudad
     * @param sName
     */
    public void savePreferences(String sName){
        SharedPreferences sharedPreferences=getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("UserName", sName);
        editor.commit();
    }
}
