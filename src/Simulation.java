
import avaj_launcher.aircraft.Coordinates;
import avaj_launcher.exceptions.InvalidCoordinateValueException;
import avaj_launcher.weather.WeatherTower;

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

            WeatherTower control = new WeatherTower();

            control.register(1);
            control.register(1);
            control.register(2);
            control.register(3);
            control.changeWeather();

        } catch (InvalidCoordinateValueException e) {
            System.out.println(e.getMessage());
        }

    }
}
