
import avaj_launcher.aircraft.Aircraft;
import avaj_launcher.aircraft.AircraftFactory;
import avaj_launcher.aircraft.Coordinates;
import avaj_launcher.exceptions.InvalidCoordinateValueException;
import avaj_launcher.weather.WeatherTowerBonus;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SimulationBonus {

    public static boolean verifyArgs(String[] args) {
        if (args.length != 1) {
            return false;
        }
        return true;
    }

    public static void simulationLoop(WeatherTowerBonus control, int itterationNumber) {
        int itterrationCount = 0;
        while (itterrationCount < itterationNumber) {
            control.changeWeather();
            itterrationCount++;
        }
    }

    public static void handleExceptions(Exception e, int countLine) {
        switch (e) {
            case IOException exception ->
                System.err.printf("Error: input file: unable to open %s\n",
                        exception.getMessage()
                );
            case InvalidCoordinateValueException exception ->
                System.err.printf("Error: parsing(line %d): %s\n",
                        countLine,
                        exception.getMessage()
                );
            case NumberFormatException exception ->
                System.err.printf("Error: parsing(line %d): Invalid coordinates %s\n",
                        countLine,
                        exception.getMessage().toLowerCase()
                );
            default ->
                System.err.println("Error: default:" + e.getMessage());
        };
        System.exit(0);
    }

    public static void main(String[] args) {
        WeatherTowerBonus control = new WeatherTowerBonus();
        int itterationNumber = 0;
        int countLine = 0;

        if (!verifyArgs(args)) {
            System.err.println("Invalid number of arguments");
            System.exit(0);
        }

        Path inputFile = Paths.get(args[0]);

        try (BufferedReader reader = Files.newBufferedReader(inputFile);) {
            AircraftFactory aircraftFactory = AircraftFactory.getAircraftFactory();
            String line;

            while ((line = reader.readLine()) != null) {
                if (countLine == 0) {
                    itterationNumber = Integer.parseInt(line);
                    countLine++;
                    continue;
                }

                String[] params = line.split(" ");

                Coordinates aircraftCoordinates = new Coordinates(
                        Integer.parseInt(params[2]),
                        Integer.parseInt(params[3]),
                        Integer.parseInt(params[4]));

                Aircraft aircraft = aircraftFactory.newAircraft(
                        params[0],
                        params[1],
                        aircraftCoordinates
                );

                aircraft.registerTower(control);

                countLine++;
            }

        } catch (Exception e) {
            handleExceptions(e, countLine);
        }

        try {
            simulationLoop(control, itterationNumber);
        } catch (InvalidCoordinateValueException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

    }
}
