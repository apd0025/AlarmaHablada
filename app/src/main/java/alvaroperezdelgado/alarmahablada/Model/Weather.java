package alvaroperezdelgado.alarmahablada.Model;

/**
 * Clase que guarda de manera ordenada los datos necesarios para mostrar la información del tiempo.
 * También se encarga de generar el String que se leerá.
 * Es una clase Singleton ya que solo vamos a querer una instancia de la misma para nuestro proposito
 */
public class Weather {
    public int degrees = 0;
    public String location = "Burgos";
    public String condition = "----";
    public String speechWeather = "No hay información del tiempo";
    public int iconResourceId = 1;

    private static Weather weather;

    public static Weather getInstance() {
        if (weather == null) {
            weather = new Weather();
        }
        return weather;
    }

    public int getDegrees() {
        return degrees;
    }

    public void setDegrees(int degrees) {
        this.degrees = degrees;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation() {
        this.location = User.getInstance().getCity();
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        String aux = translateCondition(condition);
        this.condition = aux;
    }

    public void setSpeechWeather() {
        this.speechWeather = "El tiempo en " + location + " es de " + degrees + " grados centígrados y el clima está " + condition;
    }

    public String getSpeechWeather() {
        return speechWeather;
    }

    public int getIconResourceId() {
        return iconResourceId;
    }

    public void setIconResourceId(int iconResourceId) {
        this.iconResourceId = iconResourceId;
    }

    /**
     * Método que sirve para traducir la condición, ya que esa información viene en ingles
     *
     * @param condition
     * @return
     */
    public String translateCondition(String condition) {
        String aux;
        switch (condition) {
            case "Partly Cloud":
                aux = "Parcialmente Nublado";
                break;
            case "Showers":
                aux = "Lluvioso";
                break;
            case "Partly Cloudy":
                aux = "Nublado";
                break;
            case "AM Showers":
                aux = "Con lluvia mañanera";
                break;
            case "PM Showers":
                aux = "Con lluvia de tarde";
                break;
            case "PM Thunderstorms":
                aux = "Con tormenta de tarde";
                break;
            case "Scattered Thunderstorms":
                aux = "Con tormentas electricas dispersas";
                break;
            case "Light Rain with Thunder":
                aux = "Con lluvia ligera";
                break;
            case "Thunderstorms":
                aux = "Con tormenta electrica";
                break;
            case "Heavy Rain":
                aux = "Con lluvia fuerte";
                break;
            case "Mostly Sunny":
                aux = "Parcialmente soleado";
                break;
            case "Light Rain":
                aux = "Con calabobos";
                break;
            case "Fog":
                aux = "Nuboso";
                break;
            case "Fair":
                aux = "Sereno";
                break;
            case "Sunny":
                aux = "Soleado";
                break;
            case "AM Rain":
                aux = "Con lluvia mañanera";
                break;
            case "PM Rain":
                aux = "Con tormenta de tarde";
                break;
            case "Mostly Cloudy":
                aux = "Mayormente nublado";
                break;
            case "Isolated Thunderstorms":
                aux = "Con tormentas aisladas";
                break;
            case "Thundershowers":
                aux = "Tormentoso";
                break;
            case "Heavy Thunderstorms":
                aux = "Con tormenta fuerte";
                break;
            case "Clear":
                aux = "Despejado";
                break;
            case "Rain":
                aux = "Lluvioso";
                break;
            case "Cloudy":
                aux = "Nublado";
                break;
            default:
                aux = "normal";
                break;


        }
        return aux;
    }
}
