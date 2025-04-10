public class WeatherProvider {

    private static WeatherProvider weatherProvider;
    private String[] weather;

    private WeatherProvider() {
        String[] weatherIntializer = {"RAIN", "FOG", "SUN", "SNOW"};
        this.weather = new String[4];

        System.arraycopy(weatherIntializer,
                            0,
                            this.weather,
                            0,
                            weatherIntializer.length);
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
        int index = (int)(Math.random() * 4);

        return this.weather[index];
    }
}