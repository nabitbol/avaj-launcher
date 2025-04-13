package avaj_launcher.aircraft;

import java.util.Map;

public class Helicopter extends Aircraft {

    static final AircraftType AIRCRAFT_TYPE = AircraftType.HELICOPTER;
    static final Map<String, String> LOGS = Map.of(
            "SUN", "This is hot.",
            "RAIN", "It's raining men",
            "FOG", "What the fog?",
            "SNOW", "My rotor is going to freeze!"
    );

    public Helicopter(long p_id, String p_name, Coordinates p_coordinates) {
        super(p_id, p_name, p_coordinates);
    }

    @Override
    public void updateConditions() {
        final String weather = super.weatherTower.getWeather(super.coordinates);
        this.coordinates = super.calculateNewCoordinates(
                AIRCRAFT_TYPE,
                weather,
                this.coordinates.getLongitude(),
                this.coordinates.getLatitude(),
                this.coordinates.getHeight()
        );

        System.out.printf("%s#%s (%d): %s \n", AIRCRAFT_TYPE.getDisplayName(), this.name, this.id, LOGS.get(weather));
        if (this.coordinates.getHeight() == 0) {
            System.out.printf("%s#%s (%d): landing.\n", AIRCRAFT_TYPE.getDisplayName(), this.name, this.id);
            this.weatherTower.unregister(this);
        }
    }
}
