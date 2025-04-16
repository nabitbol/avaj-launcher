package avaj_launcher.aircraft;

import avaj_launcher.weather.WeatherTower;
import java.util.Map;

public abstract class Aircraft extends Flyable {

    protected long id;
    protected String name;
    protected Coordinates coordinates;

    static enum AircraftType {
        HELICOPTER("Helicopter"),
        JETPLANE("Jet Plane"),
        BALLOON("Balloon");

        private final String displayName;

        AircraftType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    public Aircraft(long p_id, String p_name, Coordinates p_coordinates) {
        this.id = p_id;
        this.name = p_name;
        this.coordinates = p_coordinates;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void registerTower(WeatherTower p_tower) {
        this.weatherTower = p_tower;
        this.weatherTower.register(this);
    }

    protected static Map<AircraftType, int[][]> coordinatesUpdates = Map.of(
            AircraftType.JETPLANE, new int[][]{
                {0, 10, 2},
                {0, 5, 0},
                {0, 1, 0},
                {0, 0, 7}
            },
            AircraftType.HELICOPTER, new int[][]{
                {10, 0, 2},
                {5, 0, 0},
                {1, 0, 0},
                {0, 0, -12}
            },
            AircraftType.BALLOON, new int[][]{
                {2, 0, 4},
                {0, 0, -5},
                {0, 0, -3},
                {0, 0, -15}
            }
    );

    private int normalizeLongitude(int p_longitude) {
        if (p_longitude > 180) {
            return p_longitude % 180 * -1;
        } else if (p_longitude < -180) {
            return p_longitude % 180;
        } else {
            return p_longitude;
        }
    }

    private int normalizeLatitude(int p_latitude) {
        if (p_latitude > 90) {
            return p_latitude % 90 * -1;
        } else if (p_latitude < -90) {
            return p_latitude % 90;
        } else {
            return p_latitude;
        }
    }

    private int normalizeHeight(int p_height) {
        if (p_height > 100) {
            return 100;
        } else if (p_height < 0) {
            return 0;
        } else {
            return p_height;
        }
    }

    protected Coordinates calculateNewCoordinates(
            AircraftType p_aircraftType,
            String p_weather,
            int p_longitude,
            int p_latitude,
            int p_height
    ) {
        return switch (p_weather) {
            case "SUN" ->
                new Coordinates(
                this.normalizeLongitude(p_longitude + coordinatesUpdates.get(p_aircraftType)[0][0]),
                this.normalizeLatitude(p_latitude + coordinatesUpdates.get(p_aircraftType)[0][1]),
                this.normalizeHeight(p_height + coordinatesUpdates.get(p_aircraftType)[0][2])
                );
            case "RAIN" ->
                new Coordinates(
                this.normalizeLongitude(p_longitude + coordinatesUpdates.get(p_aircraftType)[1][0]),
                this.normalizeLatitude(p_latitude + coordinatesUpdates.get(p_aircraftType)[1][1]),
                this.normalizeHeight(p_height + coordinatesUpdates.get(p_aircraftType)[1][2])
                );
            case "FOG" ->
                new Coordinates(
                this.normalizeLongitude(p_longitude + coordinatesUpdates.get(p_aircraftType)[2][0]),
                this.normalizeLatitude(p_latitude + coordinatesUpdates.get(p_aircraftType)[2][1]),
                this.normalizeHeight(p_height + coordinatesUpdates.get(p_aircraftType)[2][2])
                );
            case "SNOW" ->
                new Coordinates(
                this.normalizeLongitude(p_longitude + coordinatesUpdates.get(p_aircraftType)[3][0]),
                this.normalizeLatitude(p_latitude + coordinatesUpdates.get(p_aircraftType)[3][1]),
                this.normalizeHeight(p_height + coordinatesUpdates.get(p_aircraftType)[3][2])
                );
            default ->
                new Coordinates(0, 0, 0);
        };
    }

}
