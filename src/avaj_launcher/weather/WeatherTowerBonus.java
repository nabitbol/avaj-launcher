package avaj_launcher.weather;

import avaj_launcher.aircraft.Coordinates;

public class WeatherTowerBonus extends WeatherTower {

    @Override
    public String getWeather(Coordinates p_coordinates) {
        return WeatherProviderBonus.getWeatherProvider().getCurrentWeather(p_coordinates);
    }

}
