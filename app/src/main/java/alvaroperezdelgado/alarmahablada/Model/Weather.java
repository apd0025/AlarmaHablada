package alvaroperezdelgado.alarmahablada.Model;

/**
 * Created by perez on 18/4/16.
 */
public class Weather {
    public int degrees=1;
    public String location="Burgos";
    public String condition="Hola";
    public String speechWeather="Vacio";
    public int iconResourceId=1;


    private static Weather weather;

    public static Weather getInstance(){
        if(weather==null){
            weather=new Weather();
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

    public void setLocation(String location) {
        this.location = User.getInstance().getCity();
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
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
}
