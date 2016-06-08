package alvaroperezdelgado.alarmahablada;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import alvaroperezdelgado.alarmahablada.Model.Container;
import alvaroperezdelgado.alarmahablada.Model.User;
import alvaroperezdelgado.alarmahablada.YahooWeather.data.Channel;
import alvaroperezdelgado.alarmahablada.YahooWeather.data.Item;
import alvaroperezdelgado.alarmahablada.YahooWeather.service.WeatherSerciceCallback;
import alvaroperezdelgado.alarmahablada.YahooWeather.service.YahooWeatherService;

/**
 * Clase que se encarga de obtener los datos del tiempo
 */
public class WeatherActivity extends AppCompatActivity implements WeatherSerciceCallback {

    private YahooWeatherService service;
    private ProgressDialog dialog;

    private Container container;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO cargar imagen
        setContentView(R.layout.activity_weather);

        container = Container.getInstance();

        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading..");
        // dialog.show();
        user = User.getInstance();
        service.refreshWeather(user.getCity().toString());

    }


    /**
     * Método que controla el comportamiento de la aplicación si esta todo correcto.
     *
     * @param channel
     */
    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();

        Item item = channel.getItem();
        int resourceId = getResources().getIdentifier("drawable/icon_" + item.getCondition().getCode(), null, getPackageName());

        @SuppressWarnings("deprecation") Drawable weatherIconDrawable = getResources().getDrawable(resourceId);

        int temperature = item.getCondition().getTemperature();
        String description = item.getCondition().getDescription();
        container.getWeather().setIconResourceId(resourceId);
        container.getWeather().setDegrees(temperature);
        container.getWeather().setCondition(description);

        //Nos lleva a cargar la información de los mails
        startActivity(new Intent(this, MailActivity.class));

    }

    /**
     * Método que sirve para manejar el comportamiento de la aplicación si esta no puede cargar
     * correctamente la información
     *
     * @param exception
     */
    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, "No ha sido posible obtener la información meteorológica", Toast.LENGTH_SHORT).show();
        //Si falla al cargar el tiempo qeu pase a cargar los mails
        startActivity(new Intent(this, MailActivity.class));
    }
}
