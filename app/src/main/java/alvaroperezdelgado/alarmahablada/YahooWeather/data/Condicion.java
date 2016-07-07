package alvaroperezdelgado.alarmahablada.YahooWeather.data;

import org.json.JSONObject;

/**
 * Clase que obtien y guarda la información de la temperatura y la descripción del tiempo.
 */
public class Condicion implements JSONPopulator {
    private int code;
    private int temperature;
    private String description;

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public int getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {
        code=data.optInt("code");
        temperature=data.optInt("temp");
        description=data.optString("text");
    }
}
