package avaj_launcher.aircraft;

import java.util.Map;

public class Helicopter extends Aircraft {

    static final Map<String, int[]> MOOVES = Map.of(
            "SUN", new int[]{10, 0, 2},
            "RAIN", new int[]{5, 0, 0},
            "FOG", new int[]{1, 0, 0},
            "SNOW", new int[]{0, 0, -12}
    );

    static final Map<String, String> LOGS = Map.of(
            "SUN", "This is hot.",
            "RAIN", "It's raining men.",
            "FOG", "What the fog?",
            "SNOW", "My rotor is going to freeze!"
    );

    public Helicopter(long p_id, String p_name, Coordinates p_coordinates) {
        super(p_id, p_name, p_coordinates);
    }

    @Override
    public void updateConditions() {
        final String weather = super.weatherTower.getWeather(super.coordinates);
        this.coordinates = super.calculateNewCoordinates(MOOVES.get(weather));

        System.out.printf("%s#%s(%d): %s \n", "Helicopter", this.name, this.id, LOGS.get(weather));
        if (this.coordinates.getHeight() == 0) {
            System.out.printf("%s#%s(%d): landing.\n", "Helicopter", this.name, this.id);
            this.toUnregister = true;
        }
    }
}
