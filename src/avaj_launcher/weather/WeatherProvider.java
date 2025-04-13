package avaj_launcher.weather;

import avaj_launcher.aircraft.Coordinates;

class WeatherProvider {

    private static WeatherProvider weatherProvider;
    private String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider() {
    }

    /*
    * Implemented according to the singleton stereotype
     */
    public static WeatherProvider getWeatherProvider() {
        if (weatherProvider == null) {
            weatherProvider = new WeatherProvider();
        }
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates p_coordinates) {
        int index = Math.abs((p_coordinates.getLatitude()
                + p_coordinates.getLongitude()
                + p_coordinates.getHeight()) % 4);

        return this.weather[index];
    }
}
