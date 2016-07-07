package alvaroperezdelgado.alarmahablada.YahooWeather.data;

import org.json.JSONObject;

/**
 * Clase que obtiene, a través del archivo JSON obtenido de YahooWeather, units y item.
 */
public class Channel implements JSONPopulator {

    private Item item;
    private Units units;

    public Units getUnits() {
        return units;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public void populate(JSONObject data) {
        units = new Units();
        units.populate(data.optJSONObject("units"));

        item = new Item();
        item.populate(data.optJSONObject("item"));
    }
}
