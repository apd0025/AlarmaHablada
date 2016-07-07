package alvaroperezdelgado.alarmahablada.Alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import alvaroperezdelgado.alarmahablada.LoadClasses.LoadActivity;
import alvaroperezdelgado.alarmahablada.Model.Alarm;
import alvaroperezdelgado.alarmahablada.Model.Container;
import alvaroperezdelgado.alarmahablada.R;


/**
 * Clase que se ocupa de reproducir el sonido de la alarma.
 */
public class RingTonePlayingService extends Service {

    private int NOTIFICATION_ID = 1;
    //mediaPlayer sirve para reproducir canciones
    private MediaPlayer mediaPlayer;
    //start_id controla que boton esta pulsado
    private int start_id;
    //isRunning controla si esta funcionando esta clase
    private boolean isRunning;
    //container contiene lo que queremos leer
    Container container;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int start_id) {
        Log.e("RingtonePlayingService", ", OnStartCommand");
        Log.i("LocalService", "Received start id " + start_id + ": " + intent);

        //Recuperamos el contenido "extra" del intent
        String state = intent.getExtras().getString("extra");

        Log.e("Ringtone state:extra is", state);

        //comprobar que state no tenga null
        assert state != null;

        //Cambiamos la id en funcion de lo que nos ha llegado en "extra"
        switch (state) {
            case "alarm on":
                start_id = 1;
                break;
            case "alarm off":
                start_id = 0;
                break;
            default:
                start_id = 0;
                break;
        }


        //Si no hay musica sonando y se presiona "alarm on"
        //La musica empezara a sonar
        Boolean isRinging = false;
        if (!this.isRunning && start_id == 1) {
            //obtenemos una instancia de container
            container = Container.getInstance();
            Log.e("There is no music, ", "and you want start");
            Alarm.getInstance().setIsActive(true);
            //si hay algo en el song, o es distinto de nulo que lea esa cancion.
            if (!(container.getSong() == null)) {
                //creamos una uri a traves de nuestro archivo File cancion para pasarselo al mediaPlayer
                Uri u = Uri.parse(container.getSong().toString());

                //creamos el media player pasandole el contexto y el audio que queremos escuchar
                mediaPlayer = MediaPlayer.create(this, u);
            }//sino que ponga una cancion por defecto
            else {
                //cancion por defecto
                mediaPlayer = MediaPlayer.create(this, R.raw.dove);
            }

            //Iniciamos la musica
            mediaPlayer.start();
            //Como iniciar una actividad desde aqui
            //startActivity(new Intent(RingTonePlayingService.this, AddMessageUser.class));
            Alarm.getInstance().setIsRinging(true);
            this.isRunning = true;
            this.start_id = 0;


            //TODO poner icono y contenido de los mensajes y mirar lo del build y que se muestre bajando y tambien mirar lo del api
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                //notification
                //set up the notification service
                NotificationManager notificationManager = (NotificationManager)
                        getSystemService(NOTIFICATION_SERVICE);

                //Hacemos un intent que nos lleve a la clase Speech la notificacion
                Intent intent2 = new Intent(this.getApplicationContext(), LoadActivity.class);

                intent2.putExtra("extra", "alarm");
                //Inicializamos un pendingIntent con el intent anterior
                PendingIntent pendingIntentMainActivity = PendingIntent.getActivity(this, 0, intent2, 0);


                //construimos la notificacion
                Notification notificationPopup = null;
                //Construimos los parametros de la notificacion
                notificationPopup = new Notification.Builder(this)
                        .setContentTitle("Alarma!!")
                        .setContentText("Alarma Hablada")
                        .setContentIntent(pendingIntentMainActivity)
                        .setAutoCancel(true)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .build();


                //Configuramos el orden de arranque de la notificacion
                notificationManager.notify(0, notificationPopup);


            } else {//si esta por debajo de jellybean ejecutara esta notficacion
                //Hacemos un intent que nos lleve a la clase Speech

                Intent intent2 = new Intent(new Intent(this.getApplicationContext(), LoadActivity.class));
                intent2.putExtra("extra", "alarm");

                //Inicializamos un pendingIntent con el intent anterior
                PendingIntent pendingIntent = PendingIntent.getActivity(this.getApplicationContext(), 0, intent2, 0);


                //construimos la notificacion
                NotificationCompat.Builder builder = new NotificationCompat.Builder(RingTonePlayingService.this);
                builder.setSmallIcon(R.drawable.reloj_notify);
                builder.setContentIntent(pendingIntent);
                builder.setAutoCancel(true);
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                builder.setContentTitle("Alarma!!");
                builder.setContentText("Alarma Hablada");
                builder.setSubText("Pulsa para escuchar");

                //Enviar la notificacion
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(NOTIFICATION_ID, builder.build());
            }

            //Cambiamos el estado de la alarma isActive a False
            Alarm.getInstance().setIsActive(false);
            //Lo guardamos en el xml
            savePreferences(false);
        }
        //Si hay musica escuchandose y el usuario pulsa "alarm off"
        //La musica deberia pararse
        else if (this.isRunning && start_id == 0) {
            Log.e("There is music, ", "and you want end");

            //Paramos el ringtone
            mediaPlayer.stop();

            mediaPlayer.reset();

            this.isRunning = false;
            this.start_id = 0;
            Alarm.getInstance().setIsRinging(false);
            Alarm.getInstance().setIsActive(false);
            Alarm.getInstance().setIsActive(false);
        }
        //Si la musica no esta escuchandose y se pulsa "alarm off"
        //No hacer nada
        else if (!this.isRunning && start_id == 0) {
            Log.e("There is no music, ", "and you want end");

            this.isRunning = false;
            this.start_id = 0;
        }
        //Si la musica esta sonando y se presiona "alarm on"
        //No hacer nada
        else if (this.isRunning && start_id == 1) {
            Log.e("There is music, ", "and you want start");

            this.isRunning = true;
            this.start_id = 1;
        }
        //Si ocurriese algo extraño
        else {
            Log.e("Else  ", "somehow");
        }
        //Cambiamos el estado de la alarma isActive a False
        Alarm.getInstance().setIsActive(false);
        //Lo guardamos en el xml
        savePreferences(false);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

        Log.e("RingTonePlayingService", ", onDestroy");

        super.onDestroy();
        this.isRunning = false;
    }

    /**
     * Método que guarda en un xml MyPreferences el dato de is active
     */
    public void savePreferences(Boolean isActive) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("AlarmIsActive", isActive);
        editor.commit();
    }


}
