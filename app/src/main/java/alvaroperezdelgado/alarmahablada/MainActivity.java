package alvaroperezdelgado.alarmahablada;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import alvaroperezdelgado.alarmahablada.Model.Alarm;
import alvaroperezdelgado.alarmahablada.Model.Container;
import alvaroperezdelgado.alarmahablada.Model.User;

public class MainActivity extends AppCompatActivity {

    private User user;
    private Alarm alarm;
    private Container container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //instanciamos los objetos user y alarm
        user=User.getInstance();
        alarm=Alarm.getInstance();
        container=Container.getInstance();
        loadConfiguration();
        startActivity(new Intent(this, MainActivity2.class));
    }

    public void loadConfiguration(){
        SharedPreferences sharedPreferences=getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        //Cargar los datos del usuario
        user.setName(sharedPreferences.getString("UserName", "User"));
        user.setMailUser(sharedPreferences.getString("UserMailUser", "apd0025tfg"));
        user.setMailPass(sharedPreferences.getString("UserMailPass", "444044ap"));
        user.setCity(sharedPreferences.getString("UserCity", "Burgos"));
        user.setTitle(sharedPreferences.getString("UserTitle", ""));

        //Cargar los datos de alarma
        alarm.setHour(sharedPreferences.getInt("AlarmHour", 10));
        alarm.setMin(sharedPreferences.getInt("AlarmMin", 0));


        //Cargar el mensaje customizado
        container.setCustomMessage(sharedPreferences.getString("ContainerCustomMessage","Este es un mensaje personalizado"));
        container.setSongName(sharedPreferences.getString("ContainerSongName","Por defecto"));
        //Log nos indica si hemos cargado las preferencias compartidas
        Log.d("LoadPreferences","SharedPreferences Loaded");
    }
}
