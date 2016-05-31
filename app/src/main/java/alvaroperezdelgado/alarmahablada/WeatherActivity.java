package alvaroperezdelgado.alarmahablada;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import alvaroperezdelgado.alarmahablada.Model.Container;
import alvaroperezdelgado.alarmahablada.Model.User;
import alvaroperezdelgado.alarmahablada.YahooWeather.data.Channel;
import alvaroperezdelgado.alarmahablada.YahooWeather.data.Item;
import alvaroperezdelgado.alarmahablada.YahooWeather.service.WeatherSerciceCallback;
import alvaroperezdelgado.alarmahablada.YahooWeather.service.YahooWeatherService;


public class WeatherActivity extends AppCompatActivity implements WeatherSerciceCallback {


    private TextView tvTemperatura;
    private TextView tvCondicion;
    private TextView tvLocalizacion;
    private ImageView ivWeatherIcon;

    private YahooWeatherService service;
    private ProgressDialog dialog;

    private Container container;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        tvTemperatura=(TextView)findViewById(R.id.tvTemperature);
        tvCondicion=(TextView)findViewById(R.id.tvCondicion);
        tvLocalizacion=(TextView)findViewById(R.id.tvLocation);
        ivWeatherIcon=(ImageView)findViewById(R.id.ivWeatherIcon);

        container=Container.getInstance();

        service=new YahooWeatherService(this);
        dialog=new ProgressDialog(this);
        dialog.setMessage("Loading..");
       // dialog.show();
        user=User.getInstance();
        service.refreshWeather(user.getCity().toString());

    }


    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();

        Item item = channel.getItem();
        int resourceId=getResources().getIdentifier("drawable/icon_" + item.getCondition().getCode(), null, getPackageName());

        @SuppressWarnings("deprecation") Drawable weatherIconDrawable=getResources().getDrawable(resourceId);

        ivWeatherIcon.setImageDrawable(weatherIconDrawable);


        int temperature = item.getCondition().getTemperature();
        String description = item.getCondition().getDescription();
        container.getWeather().setIconResourceId(resourceId);
        container.getWeather().setDegrees(temperature);
        container.getWeather().setCondition(description);

        tvTemperatura.setText(temperature +" "+channel.getUnits().getTemperature()+(char) 0x00B0);
        tvCondicion.setText(description);
        tvLocalizacion.setText(service.getLocation());


    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
