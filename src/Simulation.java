
import avaj_launcher.aircraft.Coordinates;
import avaj_launcher.aircraft.Helicopter;
import avaj_launcher.aircraft.JetPlane;
import avaj_launcher.exceptions.InvalidCoordinateValueException;
import avaj_launcher.weather.WeatherTower;

public class Simulation {

    public static void main(String[] args) {
        final int longitude = 4;
        final int latitude = -89;
        final int height = 10;

        final int longitude2 = 4;
        final int latitude2 = -89;
        final int height2 = 10;
        try {

            Coordinates coordinatesInstance = new Coordinates(longitude, latitude, height);
            final long id = 133;
            final String name = "Falcon";

            Coordinates coordinatesInstance2 = new Coordinates(longitude2, latitude2, height2);
            final long id2 = 132;
            final String name2 = "Falcon";

            WeatherTower control = new WeatherTower();

            Helicopter aircraft = new Helicopter(id, name, coordinatesInstance);
            JetPlane aircraft2 = new JetPlane(id2, name2, coordinatesInstance2);
            aircraft.registerTower(control);
            aircraft2.registerTower(control);

            int i = 0;
            while (i < 20) {
                control.changeWeather();
                i++;
            }

        } catch (InvalidCoordinateValueException e) {
            System.out.println(e.getMessage());
        }

    }
}
