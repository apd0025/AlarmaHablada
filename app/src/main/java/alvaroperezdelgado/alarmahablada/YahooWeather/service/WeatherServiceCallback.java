package alvaroperezdelgado.alarmahablada.YahooWeather.service;


import alvaroperezdelgado.alarmahablada.YahooWeather.data.Channel;

/**
 * Interface que realiza la conexión con el API de YahooWeather
 */
public interface WeatherServiceCallback {
    void serviceSuccess(Channel channel);
    void serviceFailure(Exception exception);
}
