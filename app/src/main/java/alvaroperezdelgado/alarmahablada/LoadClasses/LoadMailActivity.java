package alvaroperezdelgado.alarmahablada.LoadClasses;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import javax.mail.Message;
import javax.mail.MessagingException;

import alvaroperezdelgado.alarmahablada.Alarm.SpeechAlarm;
import alvaroperezdelgado.alarmahablada.Email.EmailManager;
import alvaroperezdelgado.alarmahablada.Model.User;
import alvaroperezdelgado.alarmahablada.R;
import alvaroperezdelgado.alarmahablada.ViewControl.MainActivity;

/**
 * Clase encargada de obtener la información del correo electrónico
 * Guarda esta información en la clase Emails.
 */
public class LoadMailActivity extends AppCompatActivity {
    Message[] messages = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        User user = User.getInstance();
        final EmailManager emailManager = new EmailManager(user.getMailUser(), user.getMailPass(), "gmail.com", "smtp.gmail.com", "imap.gmail.com");

        /**
         * Tarea asincrona que se ejecuta en segundo plano
         */
        try {
            new AsyncTask<String, Void, String>() {
                @Override
                protected String doInBackground(String... strings) {
                    try {
                        messages = emailManager.getMails();
                    } catch (MessagingException e) {
                        e.printStackTrace();

                    }
                    String recive = "init";
                    try {
                        recive = getIntent().getExtras().getString("extra");
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                    if (recive == "init") {
                        //Nos lleva a cargar la información de los mails
                        startActivity(new Intent(LoadMailActivity.this, MainActivity.class));
                    } else {
                        Intent intent = new Intent(LoadMailActivity.this, SpeechAlarm.class);
                        startActivity(intent);
                    }
                    startActivity(new Intent(LoadMailActivity.this, MainActivity.class));
                    return null;
                }

            }.execute();

        } catch (Exception e) {
            Toast.makeText(this, "No ha sido posible obtener la información del correo electrónico", Toast.LENGTH_SHORT).show();

        }
    }
}
