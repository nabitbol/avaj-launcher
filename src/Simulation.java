
import avaj_launcher.exceptions.InvalidCoordinateValueException;
import avaj_launcher.weather.WeatherProvider;
import avaj_launcher.aircraft.Coordinates;

public class Simulation {

    public static void main(String[] args) {
        final int longitude = 4;
        final int latitude = -89;
        final int height = 10;

        try {

            Coordinates coordinatesInstance = new Coordinates(longitude, latitude, height);
            final String output = String.format("longitude: %d\nlatitude: %d\nheight: %d",
                    coordinatesInstance.getLongitude(),
                    coordinatesInstance.getLatitude(),
                    coordinatesInstance.getHeight());
            System.out.println(output);
        } catch (InvalidCoordinateValueException e) {
            System.out.println(e.getMessage());
        }

        Tower tower = new Tower();
        tower.register(3);
        tower.register(4);
        tower.register(5);
        tower.unregister(3);
        tower.unregister(3);
        tower.register(3);
        tower.conditionChanged();

        WeatherProvider weatherProvider = WeatherProvider.getWeatherProvider();
        String currentWeather = weatherProvider.getCurrentWeather();
        System.out.println("Random generated weather: " + currentWeather);

    }
}
