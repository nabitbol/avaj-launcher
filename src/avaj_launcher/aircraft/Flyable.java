package avaj_launcher.aircraft;

import avaj_launcher.weather.WeatherTower;

public abstract class Flyable {

    protected WeatherTower weatherTower;

    public Flyable() {
    }

    public abstract void updateConditions();

    public void registerTower(WeatherTower p_tower) {
        this.weatherTower = p_tower;
        this.weatherTower.register(this);
    }

}
