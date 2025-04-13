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

    public String getCurrentWeather() {
        int index = (int) (Math.random() * 4);

        return this.weather[index];
    }
}
