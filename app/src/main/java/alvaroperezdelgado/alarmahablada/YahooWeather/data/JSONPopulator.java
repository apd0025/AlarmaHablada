package alvaroperezdelgado.alarmahablada.YahooWeather.data;

import org.json.JSONObject;

/**
 * Interface que sirve para que las demás hereden de ella y todas tengan el método populate.
 */
public interface JSONPopulator {

    void populate(JSONObject data);
}
