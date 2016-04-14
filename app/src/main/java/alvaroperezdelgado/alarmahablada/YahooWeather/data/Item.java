package alvaroperezdelgado.alarmahablada.YahooWeather.data;

import org.json.JSONObject;

/**
 * Created by perez on 6/4/16.
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
