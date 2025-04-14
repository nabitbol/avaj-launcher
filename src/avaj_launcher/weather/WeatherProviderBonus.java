package avaj_launcher.weather;

import avaj_launcher.aircraft.Coordinates;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class WeatherProviderBonus {

    private static final String BASE_API_URL = "https://api.open-meteo.com/v1/forecast?";
    private static WeatherProviderBonus weatherProvider;
    private String[] weather = {"SUN", "RAIN ", "FOG", "SNOW"};

    private WeatherProviderBonus() {
    }

    /*
    * Implemented according to the singleton stereotype
     */
    public static WeatherProviderBonus getWeatherProvider() {
        if (weatherProvider == null) {
            weatherProvider = new WeatherProviderBonus();
        }
        return weatherProvider;
    }

    private String getWeatherFromAPI(int p_longitude, int p_latitude) {
        final String uri = BASE_API_URL
                + "latitude=" + p_latitude
                + "&longitude=" + p_longitude
                + "&current_weather=true";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("name", "application/json")
                .uri(URI.create(uri))
                .build();

        try {
            HttpResponse<String> response
                    = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    public int getWeatherCode(int wmoCode) {
        if (wmoCode < 4) {
            return 0;
        } else if (wmoCode >= 4 && wmoCode <= 48) {
            return 3;
        } else if (wmoCode >= 49 && wmoCode <= 71) {
            return 2;
        } else {
            return 3;
        }
    }

    public int getWeatherCode(String response) {
        final String WEATHER_CODE_STRING = "weathercode";
        String neededInformations
                = response.substring(
                        response.length() - 20,
                        response.length() - 2
                );
        int weatherCodeIndex
                = neededInformations.indexOf(WEATHER_CODE_STRING);
        String rawWmoCode
                = neededInformations.substring(
                        weatherCodeIndex + WEATHER_CODE_STRING.length() + 2,
                        neededInformations.length()
                );

        int wmoCode = Integer.parseInt(rawWmoCode);
        int weatherCode = getWeatherCode(wmoCode);
        return weatherCode;
    }

    public int getWeatherCode(Coordinates p_coordinates) {
        return Math.abs((p_coordinates.getLatitude()
                + p_coordinates.getLongitude()
                + p_coordinates.getHeight()) % 4
        );
    }

    public String getCurrentWeather(Coordinates p_coordinates) {

        String response = this.getWeatherFromAPI(
                p_coordinates.getLongitude(),
                p_coordinates.getLatitude()
        );
        int index = (response != null)
                ? this.getWeatherCode(response)
                : this.getWeatherCode(p_coordinates);
        return this.weather[index];
    }
}
