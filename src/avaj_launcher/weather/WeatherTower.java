package avaj_launcher.weather;

import avaj_launcher.aircraft.Aircraft;
import avaj_launcher.aircraft.Coordinates;

public class WeatherTower extends Tower {

    public String getWeather(Coordinates p_coordinates) {
        return WeatherProvider.getWeatherProvider().getCurrentWeather(p_coordinates);
    }

    public void changeWeather() {
        this.conditionChanged();
    }

    public void register(Aircraft observer) {
        // if (isRegistered(observer)) {
        //     System.out.println("Error: " + observer + " already registered");
        // } else {
        register(observer);
        System.out.printf("Tower says: %s#%s(%d): register to weather tower.\n",
                observer.getClass().getSimpleName(),
                observer.getName(),
                observer.getId()
        );
        // }
    }

    public void unregister(Aircraft observer) {
        if (!isRegistered(observer)) {
            System.out.println("Error: " + observer + " Doesn't exist.");
        } else {
            super.unregister(observer);
            System.out.printf("Tower says: %s#%s(%d): unregister from weather tower.\n",
                    observer.getClass().getSimpleName(),
                    observer.getName(),
                    observer.getId()
            );
        }
    }
}
