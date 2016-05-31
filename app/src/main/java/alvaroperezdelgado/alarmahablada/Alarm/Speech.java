package alvaroperezdelgado.alarmahablada.Alarm;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Locale;

import alvaroperezdelgado.alarmahablada.Model.Container;
import alvaroperezdelgado.alarmahablada.R;


public class Speech extends Activity implements TextToSpeech.OnInitListener
{
    private TextToSpeech textToSpeech;
    private EditText editText;
    private Switch swEsEn;
    private Button btnSpanish;
    private Button btSpeechCustom;
    private Button btSpeechWeather;
    private Container container;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate(savedInstanceState);
        //setContentView( R.layout.activity_speech );

        //btnSpanish = ( Button ) findViewById( R.id.btSpeechSpa );
        btSpeechCustom = ( Button ) findViewById( R.id.btSpeechCustom );
        btSpeechWeather=(Button)findViewById(R.id.btSpeechWeather);

        //Instanciamos los componentes de la vista
        textToSpeech = new TextToSpeech( this, this );
        //editText = ( EditText ) findViewById( R.id.etEntry );


        //obtenemos una instancia de container
        container=Container.getInstance();


        btnSpanish.setOnClickListener( new View.OnClickListener()
        {
            @Override public void onClick( View v )
            {

                if(swEsEn.isChecked()==false){
                    textToSpeech.setLanguage(new Locale("spa", "ESP"));
                    speak(editText.getText().toString());
                }else{
                    textToSpeech.setLanguage( Locale.ENGLISH );
                    speak( editText.getText().toString() );
                }
            }
        } );

        
        //cuando se pulse el boton mensaje personalizado leera el mensaje antes almacenado
        //en container
        btSpeechCustom.setOnClickListener( new View.OnClickListener()
        {
            @Override public void onClick( View v )
            {
                //si el switch esta sin seleccionar hablara en castellano, sino en ingles
                if(swEsEn.isChecked()==false){
                    //primero seleccionamos el idioma en el que queremos que lo lea
                    textToSpeech.setLanguage(new Locale("spa", "ESP"));
                    //con el metodo speak leemos el contenido de el string
                    speak(container.getCustomMessage().toString());

                }else{
                    textToSpeech.setLanguage( Locale.ENGLISH );
                    speak( container.getCustomMessage().toString() );
                }
            }
        } );

        btSpeechWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(swEsEn.isChecked()==false){
                    //primero seleccionamos el idioma en el que queremos que lo lea
                    textToSpeech.setLanguage(new Locale("spa", "ESP"));
                    //con el metodo speak leemos el contenido de el string
                    speak(container.getWeather().getSpeechWeather().toString());

                }else{
                    textToSpeech.setLanguage( Locale.ENGLISH );
                    speak( container.getWeather().getSpeechWeather().toString());
                }
            }
        });

    }

    @Override
    public void onInit( int status )
    {
        if (status == TextToSpeech.SUCCESS) {
            //primero seleccionamos el idioma en el que queremos que lo lea
            textToSpeech.setLanguage(new Locale("spa", "ESP"));
            //con el metodo speak leemos el contenido de el string
            speak(container.getCustomMessage().toString());

        }
        if ( status == TextToSpeech.LANG_MISSING_DATA | status == TextToSpeech.LANG_NOT_SUPPORTED )
        {
            Toast.makeText( this, "ERROR LANG_MISSING_DATA | LANG_NOT_SUPPORTED", Toast.LENGTH_SHORT ).show();
        }
    }

    private void speak( String str )
    {
        textToSpeech.speak( str, TextToSpeech.QUEUE_FLUSH, null );
        textToSpeech.setSpeechRate( 0.0f );
        textToSpeech.setPitch( 0.0f );
    }

    //cuando se sale de esta actividad
    @Override
    protected void onDestroy()
    {
        if ( textToSpeech != null )
        {
            //paramos el texttospeech y lo apagamos
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}