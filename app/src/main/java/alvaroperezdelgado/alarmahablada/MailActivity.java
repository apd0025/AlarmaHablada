package alvaroperezdelgado.alarmahablada;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import javax.mail.Message;
import javax.mail.MessagingException;

import alvaroperezdelgado.alarmahablada.Email.EmailManager;
import alvaroperezdelgado.alarmahablada.Model.User;

/**
 * Clase encargada de obtener la información del correo electrónico
 * Guarda esta información en la clase Emails
 */
public class MailActivity extends AppCompatActivity {
    Message[] messages=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        User user=User.getInstance();
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

                    startActivity(new Intent(MailActivity.this, MainActivity2.class));
                    return null;
                }

            }.execute();

        }catch(Exception e){
            Toast.makeText(this, "No ha sido posible obtener la información del correo electrónico", Toast.LENGTH_SHORT).show();

        }
    }
}
