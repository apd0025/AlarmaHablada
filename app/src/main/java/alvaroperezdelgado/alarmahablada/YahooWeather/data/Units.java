package alvaroperezdelgado.alarmahablada.YahooWeather.data;

import org.json.JSONObject;

/**
 * Created by perez on 6/4/16.
 */
public class Units implements JSONPopulator {

    private String temperature;

    public String getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {
        temperature= data.optString("temperature");
    }
}
