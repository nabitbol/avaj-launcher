package avaj_launcher.aircraft;

import avaj_launcher.weather.WeatherTower;

public abstract class Aircraft extends Flyable {

    protected long id;
    protected String name;
    protected Coordinates coordinates;

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
            int[] p_coordinates
    ) {
        return new Coordinates(
                normalizeLongitude(p_coordinates[0]),
                normalizeLatitude(p_coordinates[1]),
                normalizeHeight(p_coordinates[2])
        );
    }

    /**
     * Default implementation of updateConditions
     */
    @Override
    public void updateConditions() {
        final String weather = super.weatherTower.getWeather(this.coordinates);

        System.out.printf("Current weather:%s for %s#%s(%d)", weather, "Aircraft", this.name, this.id);
        if (this.coordinates.getHeight() == 0) {
            System.out.printf("%s#%s(%d): landing.\n", "Aircraft", this.name, this.id);
            this.toUnregister = true;
        }
    }

}
