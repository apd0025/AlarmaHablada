package alvaroperezdelgado.alarmahablada;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

import alvaroperezdelgado.alarmahablada.Calendar.CalendarEvent;
import alvaroperezdelgado.alarmahablada.Calendar.ListCalendarEvents;
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
        loadCalendar();
        startActivity(new Intent(this,WeatherActivity.class));
        //startActivity(new Intent(this, MainActivity2.class));
    }

    /**
     * Método que carga la configuracion anterior de un archivo .xml
     */
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
        alarm.setIsActive(sharedPreferences.getBoolean("AlarmIsActive", false));
        alarm.setIsAllowed(sharedPreferences.getBoolean("AlarmIsAllowed",false));

        //Cargar el mensaje customizado
        container.setCustomMessage(sharedPreferences.getString("ContainerCustomMessage", "Este es un mensaje personalizado"));
        container.setSongName(sharedPreferences.getString("ContainerSongName", "Por defecto"));
        //Log nos indica si hemos cargado las preferencias compartidas
        Log.d("LoadPreferences", "SharedPreferences Loaded");
    }

    /**
     * Método que se encarga de cargar los datos del calendario
     */
    public void loadCalendar(){
        //Creamos variables para dia mes año
        int day,month,year ;
        //Ponemos una fecha de inicio donde mirará a ver si hay ahí eventos
        Calendar beginTime = Calendar.getInstance();
        long startMills = beginTime.getTimeInMillis();

        //Ponemos una fecha de final donde mirará a ver si hay ahí eventos
        Calendar endTime = Calendar.getInstance();
        year = Calendar.getInstance().get(Calendar.YEAR);
        day = Calendar.getInstance().get(Calendar.DATE);
        month=beginTime.get(Calendar.MONTH);
        endTime.set(year, month, day, 23, 59);
        long endMills = endTime.getTimeInMillis();

        //Obtenemos la lista de eventos y la borramos, porque vamos a volver a recuperar todos
        ListCalendarEvents listCalendarEvents=ListCalendarEvents.getInstance();
        listCalendarEvents.removeAll();
        ContentResolver contentResolver = getContentResolver();
        //Comprobacion que nos pide el propio codigo
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        //Creamos un cursor para que nos muestre todos los calendarios que tenemos
        final Cursor cursor = contentResolver.query(CalendarContract.Calendars.CONTENT_URI,
                (new String[]{CalendarContract.Calendars._ID, CalendarContract.Calendars.CALENDAR_DISPLAY_NAME}), null, null, null);

        //Nos vamos moviendo por el
        while (cursor.moveToNext()) {
            final String _id = cursor.getString(0);
            final String displayName = cursor.getString(1);
            String[] calendarID = new String[]{_id};
            calendarID = new String[]{_id};

            Uri.Builder builder = CalendarContract.Instances.CONTENT_URI.buildUpon();

            //Pasamosla fecha de inicio y final
            ContentUris.appendId(builder, startMills);
            ContentUris.appendId(builder, endMills);
            //creamos otro cursor para mirar dentro de ese calendario
            Cursor eventCursor = contentResolver.query(builder.build(), new String[]{CalendarContract.Instances.TITLE,
                            CalendarContract.Instances.BEGIN, CalendarContract.Instances.END, CalendarContract.Instances.DESCRIPTION},
                    CalendarContract.Instances.CALENDAR_ID + " = ?", calendarID, null);
            //Recorremos todos los eventos para ese dia en concreto
            while (eventCursor.moveToNext()) {
                final String title = eventCursor.getString(0);
                final Date begin = new Date(eventCursor.getLong(1));
                final Date end = new Date(eventCursor.getLong(2));
                final String description = eventCursor.getString(3);

                //Añadimos a nuestra clase listCalendarEvents el evento obtenido
                listCalendarEvents.addCalendarEvent(new CalendarEvent(title, description, begin, end));
                Log.d("Cursor", "Title: " + title + "\tDescription: " + description + "\tBegin: " + begin.getHours()+":"+begin.getMinutes() + "\tEnd: " + end.getHours()+":"+end.getMinutes());
            }
        }
    }
}
