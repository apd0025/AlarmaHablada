package alvaroperezdelgado.alarmahablada.Alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by perez on 10/2/16.
 * Esta clase es a la que llama alarmManager en la hora establecida
 * Hereda de broadcastReceiver
 * Llama a RingTonePlayingService que es el que hace que suene la alarma
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //Para comprobar con el log a ver si funciona
        Log.e("AlarmReceiver",", onReceive");

        //Recuperamos el contenido "extra" del intent
        String get_your_string=intent.getExtras().getString("extra");

        Log.e("What is the key?",get_your_string);

        //Creamos el intent que nos lleva al ringtoneservice
        Intent intentService= new Intent(context,RingTonePlayingService.class);

        //Pasamos el contenido "extra" que obtuvimos arriba al RingTonePlayingService
        intentService.putExtra("extra",get_your_string);

        //iniciamos el servicio del RingToneservice
        context.startService(intentService);

    }
}
