package avaj_launcher.aircraft;

import avaj_launcher.weather.WeatherTower;

public abstract class Flyable {

    protected WeatherTower weatherTower;
    protected boolean toUnregister;

    public Flyable() {
        this.toUnregister = false;
    }

    public abstract void updateConditions();

    public boolean getToUnregister() {
        return this.toUnregister;
    }

    public void setToUnregister(boolean newToUnregister) {
        this.toUnregister = newToUnregister;
    }

    public void registerTower(WeatherTower p_tower) {
        this.weatherTower = p_tower;
        this.weatherTower.register(this);
    }

}
