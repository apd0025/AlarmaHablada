package alvaroperezdelgado.alarmahablada.Alarm;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;

import alvaroperezdelgado.alarmahablada.LoadClasses.LoadActivity;
import alvaroperezdelgado.alarmahablada.Model.Alarm;
import alvaroperezdelgado.alarmahablada.Model.Container;
import alvaroperezdelgado.alarmahablada.R;


/**
 * Esta clase se ocupa de reproducir el sonido de la alarma.
 */
public class SpeechPlayingService extends Service implements TextToSpeech.OnInitListener{

    private int NOTIFICATION_ID=1;
    //mediaPlayer sirve para reproducir canciones
    private MediaPlayer mediaPlayer;
    //start_id controla que boton esta pulsado
    private int start_id;
    //isRunning controla si esta funcionando esta clase
    private boolean isRunning;
    //container contiene lo que queremos leer
    Container container;
    TextToSpeech textToSpeech;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int start_id) {
        Log.e("RingtonePlayingService", ", OnStartCommand");
        Log.i("LocalService", "Received start id " + start_id + ": " + intent);
textToSpeech=new TextToSpeech(this,this);
        //Recuperamos el contenido "extra" del intent
        String state=intent.getExtras().getString("extra");

        Log.e("Ringtone state:extra is",state);

        //comprobar que state no tenga null
        assert state !=null;

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
        Boolean isRinging=false;
        if(!this.isRunning&& start_id==1){
            //obtenemos una instancia de container
            container=Container.getInstance();
            Log.e("There is no music, ", "and you want start");
            Alarm.getInstance().setIsActive(true);
            //si hay algo en el song, o es distinto de nulo que lea esa cancion.




            Alarm.getInstance().setIsRinging(true);
            this.isRunning=true;
            this.start_id=0;


            //TODO poner icono y contenido de los mensajes y mirar lo del build y que se muestre bajando y tambien mirar lo del api
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                //notification
                //set up the notification service
                NotificationManager notificationManager=(NotificationManager)
                        getSystemService(NOTIFICATION_SERVICE);

                //Hacemos un intent que nos lleve a la clase Speech la notificacion
                Intent intent2=new Intent(this.getApplicationContext(),LoadActivity.class);

                //Inicializamos un pendingIntent con el intent anterior
                PendingIntent pendingIntentMainActivity=PendingIntent.getActivity(this,0,intent2,0);

                //construimos la notificacion
                Notification notificationPopup= null;
                //Construimos los parametros de la notificacion
                notificationPopup = new Notification.Builder(this)
                        .setContentTitle("Alarma!!")
                        .setContentText("alarma")
                        .setContentIntent(pendingIntentMainActivity)
                        .setAutoCancel(true)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .build();


            //Configuramos el orden de arranque de la notificacion
            notificationManager.notify(0,notificationPopup);


            }else{//si esta por debajo de jellybean ejecutara esta notficacion
                //Hacemos un intent que nos lleve a la clase Speech

                Intent intent2= new Intent(new Intent(this.getApplicationContext(), LoadActivity.class));
                intent2.putExtra("extra","alarm");
                //Inicializamos un pendingIntent con el intent anterior
                PendingIntent pendingIntent=PendingIntent.getActivity(this.getApplicationContext(),0,intent2,0);

                //construimos la notificacion
                NotificationCompat.Builder builder=new NotificationCompat.Builder(SpeechPlayingService.this);
                builder.setSmallIcon(R.drawable.icon_0);
                builder.setContentIntent(pendingIntent);
                builder.setAutoCancel(true);
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.icon_0));
                builder.setContentTitle("Titulo notificacion");
                builder.setContentText("Contenido hola mundo");
                builder.setSubText("Subtexto eyy que paso");

                //Enviar la notificacion
                NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(NOTIFICATION_ID,builder.build());
            }

            //Cambiamos el estado de la alarma isActive a False
            Alarm.getInstance().setIsActive(false);
            //Lo guardamos en el xml
            savePreferences(false);
        }
        //Si hay musica escuchandose y el usuario pulsa "alarm off"
        //La musica deberia pararse
        else if(this.isRunning&&start_id==0){
            Log.e("There is music, ","and you want end");

            //Paramos el ringtone
            mediaPlayer.stop();

            mediaPlayer.reset();

            this.isRunning=false;
            this.start_id=0;
            Alarm.getInstance().setIsRinging(false);
            Alarm.getInstance().setIsActive(false);
        }
        //Si la musica no esta escuchandose y se pulsa "alarm off"
        //No hacer nada
        else if(!this.isRunning&&start_id==0){
            Log.e("There is no music, ","and you want end");

            this.isRunning=false;
            this.start_id=0;
        }
        //Si la musica esta sonando y se presiona "alarm on"
        //No hacer nada
        else if(this.isRunning&&start_id==1){
            Log.e("There is music, ","and you want start");

            this.isRunning=true;
            this.start_id=1;
        }
        //Si ocurriese algo extraño
        else {
            Log.e("Else  ","somehow");
        }
        //Cambiamos el estado de la alarma isActive a False
        Alarm.getInstance().setIsActive(false);
        //Lo guardamos en el xml
        savePreferences(false);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {


        super.onDestroy();
        this.isRunning=false;
    }
    /**
     * Método que guarda en un xml MyPreferences el dato de is active
     *
     */
    public void savePreferences(Boolean isActive) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("AlarmIsActive", isActive);
        editor.commit();
    }


    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            final Intent intent = new Intent(this, AlarmReceiver.class);

            //sirve para cancelar el sonido de la alarma
            //Inicializamos el alarm manager
            AlarmManager alarmManager = (AlarmManager) this.getSystemService(getApplicationContext().ALARM_SERVICE);
            //Ponemos una cadena en extra en el intent, para saber si hemos pulsado el boton de off
            intent.putExtra("extra", "alarm off");
            //Parar el ringtone
            getApplicationContext().sendBroadcast(intent);
            //----


            //primero seleccionamos el idioma en el que queremos que lo lea
            textToSpeech.setLanguage(new Locale("spa", "ESP"));

            String aux=null;
            //obtenemos una instancia de container
            container=Container.getInstance();
            //Ponemos la cadena del saludo inicial
            container.setWelcomeSpeech();
            aux=container.getWelcomeSpeech();
            Log.d("SpeechAlarm", "Making string speak");
            //se miran los textos que queremos decir y los preparamos para decirlos
            if(Alarm.getInstance().getSelectWeather()){
                container.getWeather().setSpeechWeather();
                aux=aux.concat(", "+container.getWeather().getSpeechWeather());
            }
            if(Alarm.getInstance().getSelectCalendar()){
                container.getListCalendarEvents().setSpeechCalendar();
                aux=aux.concat(", "+container.getListCalendarEvents().getSpeechCalendarEvents());
            }
            if(Alarm.getInstance().getSelectMail()){
                container.getEmails().setSpeechMail();
                aux=aux.concat(", "+container.getEmails().getSpeechMail());
            }
            if(Alarm.getInstance().getSelectCustom()){
                aux=aux.concat(", "+container.getCustomMessage());

            }
            //Método que hace hablar a nuestra aplicación
            speak(aux);
        }
        if ( status == TextToSpeech.LANG_MISSING_DATA | status == TextToSpeech.LANG_NOT_SUPPORTED )
        {
            Toast.makeText(this, "ERROR LANG_MISSING_DATA | LANG_NOT_SUPPORTED", Toast.LENGTH_SHORT).show();
        }
    }

    private void speak( String str )
    {
        textToSpeech.speak( str, TextToSpeech.QUEUE_FLUSH, null );
        textToSpeech.setSpeechRate( 0.0f );
        textToSpeech.setPitch( 0.0f );
    }
}
