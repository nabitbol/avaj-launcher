
import avaj_launcher.aircraft.Aircraft;
import avaj_launcher.aircraft.AircraftFactory;
import avaj_launcher.aircraft.Coordinates;
import avaj_launcher.exceptions.InvalidCoordinateValueException;
import avaj_launcher.weather.WeatherTower;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Simulation {

    public static boolean verifyArgs(String[] args) {
        if (args.length != 1) {
            return false;
        }
        return true;
    }

    public static void handleExceptions(Exception e, int lineNumber) {
        switch (e) {
            case IOException exception ->
                System.err.printf("Error: input file: unable to open %s\n",
                        exception.getMessage()
                );
            case InvalidCoordinateValueException exception ->
                System.err.printf("Error: parsing(line %d): %s\n",
                        lineNumber,
                        exception.getMessage()
                );
            case NumberFormatException exception ->
                System.err.printf("Error: parsing(line %d): Invalid coordinates %s\n",
                        lineNumber,
                        exception.getMessage().toLowerCase()
                );
            default ->
                System.err.println("Error: default:" + e.getMessage());
        }
        System.exit(0);
    }

    public static Aircraft getNewAircraft(String line, AircraftFactory aircraftFactory) {

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

        return aircraft;
    }

    public static void runSimulation(WeatherTower weatherTower, int itterationNumber) {
        while (itterationNumber-- > 0) {
            weatherTower.changeWeather();
        }
    }

    public static void setOutputStream(String outputFile) throws FileNotFoundException, IOException {
        Files.delete(Paths.get(outputFile));
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile, true);
        PrintStream outputStream = new PrintStream(fileOutputStream);
        System.setOut(outputStream);
    }

    static public int loadSimulation(WeatherTower weatherTower, Path inputFile) {
        int itterationNumber = 0;
        int lineNumber = 0;

        try (BufferedReader reader = Files.newBufferedReader(inputFile);) {
            AircraftFactory aircraftFactory = AircraftFactory.getAircraftFactory();
            String line;

            while ((line = reader.readLine()) != null) {
                if (lineNumber == 0) {
                    itterationNumber = Integer.parseInt(line);
                    lineNumber++;
                    continue;
                }
                Aircraft aircraft = getNewAircraft(line, aircraftFactory);
                aircraft.registerTower(weatherTower);
                lineNumber++;
            }

        } catch (Exception e) {
            handleExceptions(e, lineNumber);
        }
        return itterationNumber;
    }

    public static void main(String[] args) {
        WeatherTower weatherTower = new WeatherTower();
        int itterationNumber = 0;

        if (!verifyArgs(args)) {
            System.err.println("Invalid number of arguments");
            System.exit(0);
        }

        Path inputFile = Paths.get(args[0]);
        String outputFile = "simulation.txt";

        // getSimulationConfig();
        try {
            setOutputStream(outputFile);
            itterationNumber = loadSimulation(weatherTower, inputFile);
            runSimulation(weatherTower, itterationNumber);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }
}
