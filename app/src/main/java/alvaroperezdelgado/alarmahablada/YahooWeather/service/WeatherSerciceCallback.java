package alvaroperezdelgado.alarmahablada.YahooWeather.service;


import YahooWeather.data.Channel;

/**
 * Created by perez on 6/4/16.
 */
public interface WeatherSerciceCallback {
    void serviceSuccess(Channel channel);
    void serviceFailure(Exception exception);
}
