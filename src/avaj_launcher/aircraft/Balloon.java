package avaj_launcher.aircraft;

import java.util.Map;

public class Balloon extends Aircraft {

    static final Map<String, int[]> MOOVES = Map.of(
            "SUN", new int[]{2, 0, 4},
            "RAIN", new int[]{0, 0, -5},
            "FOG", new int[]{0, 0, -3},
            "SNOW", new int[]{0, 0, -15}
    );

    static final Map<String, String> LOGS = Map.of(
            "SUN", "Let's enjoy the good weather and take some pics.",
            "RAIN", "Damn you rain! You messed up my baloon.",
            "FOG", "I like to make long sentence to talk about the weather(PS it's the FOG!).",
            "SNOW", "It's snowing. We're gonna crash."
    );

    public Balloon(long p_id, String p_name, Coordinates p_coordinates) {
        super(p_id, p_name, p_coordinates);
    }

    @Override
    public void updateConditions() {
        final String weather = super.weatherTower.getWeather(super.coordinates);
        this.coordinates = super.calculateNewCoordinates(MOOVES.get(weather));

        System.out.printf("%s#%s(%d): %s \n", "Balloon", this.name, this.id, LOGS.get(weather));
        if (this.coordinates.getHeight() == 0) {
            System.out.printf("%s#%s(%d): landing.\n", "Balloon", this.name, this.id);
            this.weatherTower.unregister(this);
        }
    }
}
