
import avaj_launcher.aircraft.AircraftFactory;
import avaj_launcher.aircraft.Coordinates;
import avaj_launcher.aircraft.Flyable;
import avaj_launcher.exceptions.InvalidCoordinateValueException;
import avaj_launcher.weather.WeatherTower;

public class Simulation {

    public static void main(String[] args) {
        final int longitude = 4;
        final int latitude = -89;
        final int height = 10;

        final int longitude2 = 2;
        final int latitude2 = 3;
        final int height2 = 20;
        try {

            AircraftFactory aircraftFactory = AircraftFactory.getAircraftFactory();

            Coordinates coordinatesInstance = new Coordinates(longitude, latitude, height);
            final String name = "Falcon";

            Coordinates coordinatesInstance2 = new Coordinates(longitude2, latitude2, height2);
            final String name2 = "Falcon";

            Flyable aircraft = aircraftFactory.newAircraft("Helicopter", name, coordinatesInstance);
            Flyable aircraft2 = aircraftFactory.newAircraft("JetPlane", name2, coordinatesInstance2);
            Flyable aircraft3 = aircraftFactory.newAircraft("Balloon", name, coordinatesInstance2);

            WeatherTower control = new WeatherTower();

            aircraft.registerTower(control);
            aircraft2.registerTower(control);
            aircraft3.registerTower(control);

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
