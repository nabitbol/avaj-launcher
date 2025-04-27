package avaj_launcher.aircraft;

import java.util.Map;

public class JetPlane extends Aircraft {

    static final Map<String, int[]> MOOVES = Map.of(
            "SUN", new int[]{0, 10, 2},
            "RAIN", new int[]{0, 5, 0},
            "FOG", new int[]{0, 1, 0},
            "SNOW", new int[]{0, 0, 7}
    );

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
        this.coordinates = super.calculateNewCoordinates(MOOVES.get(weather));

        System.out.printf("%s#%s(%d): %s \n", "JetPlane", this.name, this.id, LOGS.get(weather));
        if (this.coordinates.getHeight() == 0) {
            System.out.printf("%s#%s(%d): landing.\n", "JetPlane", this.name, this.id);
            this.weatherTower.unregister(this);
        }
    }
}
