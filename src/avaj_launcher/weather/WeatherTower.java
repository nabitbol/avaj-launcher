package avaj_launcher.weather;

public class WeatherTower extends Tower {

    public String getWeather() {
        return WeatherProvider.getWeatherProvider().getCurrentWeather();
    }

    public void changeWeather() {
        this.conditionChanged();
    }

}
