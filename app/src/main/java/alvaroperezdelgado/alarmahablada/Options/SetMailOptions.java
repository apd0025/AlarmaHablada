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

public class SetMailOptions extends AppCompatActivity {


    @Bind(R.id.btAcceptMailOptions)
    Button acept;
    //boton de cancelar de la activiti que guardará los valores y retornara a la anterior vista
    @Bind(R.id.btCancelMailOptions)
    Button cancel;
    //campo donde introduciremos el texto que queremos guardar
    @Bind(R.id.etSetMailAddress)
    EditText address;
    @Bind(R.id.etSetMailPass)
    EditText password;

    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_mail_options);
        //Necesitamos esto para que se haga la asignacion de los widgets
        ButterKnife.bind(this);
        user= User.getInstance();

        //Controlar lo que pasa al hacer click en el boton aceptar
        acept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mailAddress=address.getText().toString();
                String mailPass=password.getText().toString();
                user.setMailUser(mailAddress);
                user.setMailPass(mailPass);
                savePreferences(mailAddress, mailPass);
                startActivity(new Intent(SetMailOptions.this, MainActivity.class));
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SetMailOptions.this, MainActivity.class));
            }
        });

    }

    /**
     * Método que guarda en un xml MyPreferences el dato del Mail
     * @param sAddress
     * @param sPass
     */
    public void savePreferences(String sAddress,String sPass){
        SharedPreferences sharedPreferences=getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("UserMailUser", sAddress);
        editor.putString("UserMailPass", sPass);
        editor.commit();
    }
}
