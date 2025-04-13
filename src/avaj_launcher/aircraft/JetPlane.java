package avaj_launcher.aircraft;

import java.util.Map;

public class JetPlane extends Aircraft {

    static final AircraftType AIRCRAFT_TYPE = AircraftType.JETPLANE;
    static final Map<String, String> LOGS = Map.of(
            "SUN", "It's the Sun",
            "RAIN", "It's raining. Better watch out for lightings.",
            "FOG", "FOOOOOG!",
            "SNOW", "OMG! Winter is coming!"
    );

    public JetPlane(long p_id, String p_name, Coordinates p_coordinates) {
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
