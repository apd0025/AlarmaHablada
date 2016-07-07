package alvaroperezdelgado.alarmahablada.YahooWeather.data;

import org.json.JSONObject;

/**
 * Clase que obtiene el estado del tiempo.
 */
public class Item implements JSONPopulator {
    private Condicion condition;

    public Condicion getCondition() {
        return condition;
    }

    @Override
    public void populate(JSONObject data) {
        condition=new Condicion();
        condition.populate(data.optJSONObject("condition"));
    }
}
