
import avaj_launcher.exceptions.InvalidCoordinateValueException;

public class Simulation {

    public static void main(String[] args) {
        final int longitude = 4;
        final int latitude = -91;
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

    }
}
