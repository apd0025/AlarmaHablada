package alvaroperezdelgado.alarmahablada;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import javax.mail.Message;
import javax.mail.MessagingException;

import alvaroperezdelgado.alarmahablada.Email.EmailManager;

public class MailActivity extends AppCompatActivity {
    Message[] messages=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        final EmailManager emailManager = new EmailManager("apd0025tfg", "444044ap", "gmail.com", "smtp.gmail.com", "imap.gmail.com");

        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                try {
                    messages = emailManager.getMails();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }

                startActivity(new Intent(MailActivity.this, MainActivity.class));
                return null;
            }

        }.execute();
        findViewById(R.id.btMailBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MailActivity.this, MainActivity.class));
            }
        });
    }
}
