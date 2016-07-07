package alvaroperezdelgado.alarmahablada.Options;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import alvaroperezdelgado.alarmahablada.ViewControl.MainActivity;
import alvaroperezdelgado.alarmahablada.Model.User;
import alvaroperezdelgado.alarmahablada.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Clase que almacena los datos del tratamiento del usuario. Estos datos se utilizarán para
 * referirse al usuario de una forma más personalizada.
 */
public class SetTitleOptions extends AppCompatActivity {
    final String[] datos = {"", "Don", "Doña"};
    //Declaramos un objeto tipo user
    User user;
    //Instanciamos con butterknife los widgets
    @Bind(R.id.rbTitleEmpty)
    RadioButton rbEmpty;
    @Bind(R.id.rbTitleMr)
    RadioButton rbMr;
    @Bind(R.id.rbTitleMrs)
    RadioButton rbMrs;
    @Bind(R.id.rgGroup1)
    RadioGroup radioGroup;
    @Bind(R.id.btAcceptTitleOptions)
    Button accept;
    @Bind(R.id.btCancelTitleOptions)
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_title_options);
        //Necesitamos esto para que se haga la asignacion de los widgets
        ButterKnife.bind(this);
        user = user.getInstance();
        //Cuando pulsamos el boton aceptar nos pone en titulo de usuario lo que seleccionamos
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Está chequed mr?
                String sTitle;
                if (rbMr.isChecked()) {
                    //datos[1]=sr
                    user.setTitle(datos[1]);
                    sTitle = datos[1];
                } else if (rbMrs.isChecked()) {
                    user.setTitle(datos[2]);
                    sTitle = datos[2];
                } else {
                    user.setTitle(datos[0]);
                    sTitle = datos[0];
                }
                savePreferences(sTitle);
                //volvemos a main activity
                startActivity(new Intent(SetTitleOptions.this, MainActivity.class));
            }
        });
        //Cuando pulsamos a cancelar
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //volvemos a main activity
                startActivity(new Intent(SetTitleOptions.this, MainActivity.class));
            }
        });

    }

    /**
     * Método que guarda en un xml MyPreferences el dato del titulo
     *
     * @param sTitle
     */
    public void savePreferences(String sTitle) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("UserTitle", sTitle);
        editor.commit();
    }
}
