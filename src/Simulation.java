
import avaj_launcher.aircraft.Aircraft;
import avaj_launcher.aircraft.AircraftFactory;
import avaj_launcher.aircraft.Coordinates;
import avaj_launcher.weather.WeatherTower;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Simulation {

    WeatherTower weatherTower;
    int itterationNumber;

    private static final int AIRCRAFT_TYPE_INDEX = 0;
    private static final int AIRCRAFT_NAME_INDEX = 1;
    private static final int LONGITUDE_INDEX = 2;
    private static final int LATITUDE_INDEX = 3;
    private static final int HEIGHT_INDEX = 4;

    public Simulation() {
        this.weatherTower = new WeatherTower();
        int itterationNumber = 0;
    }

    private Aircraft getNewAircraft(String line, AircraftFactory aircraftFactory) {

        String[] params = line.split(" ");

        Coordinates aircraftCoordinates = new Coordinates(
                Integer.parseInt(params[LONGITUDE_INDEX]),
                Integer.parseInt(params[LATITUDE_INDEX]),
                Integer.parseInt(params[HEIGHT_INDEX]));

        Aircraft aircraft = aircraftFactory.newAircraft(
                params[AIRCRAFT_TYPE_INDEX],
                params[AIRCRAFT_NAME_INDEX],
                aircraftCoordinates
        );

        return aircraft;
    }

    private void loadAircrafts(String[] lines, int lineNumber) {
        AircraftFactory aircraftFactory = AircraftFactory.getAircraftFactory();

        try {

            while (lineNumber < lines.length) {
                Aircraft aircraft = getNewAircraft(lines[lineNumber], aircraftFactory);
                aircraft.registerTower(this.weatherTower);
                lineNumber++;
            }

        } catch (Exception e) {
            System.err.printf("Error line %d: %s", lineNumber, e.getMessage());
        }
    }

    private int getItterationNumber(String line) throws IllegalArgumentException {
        int itterationNumber = Integer.parseInt(line);

        if (itterationNumber < 0) {
            throw new IllegalArgumentException("Wrong number of itterations.");
        }

        return itterationNumber;
    }

    /**
     * @param WeatherTower
     * @param Path
     * @return The number of itteration for the simulation.
     * @author nabitbol
     */
    public int loadSimulation(Path inputFile) {
        String[] lines;
        int lineNumber = 0;

        try {

            lines = Files.readString(inputFile).split("\n");
            this.itterationNumber = getItterationNumber(lines[lineNumber]);
            loadAircrafts(lines, lineNumber + 1);

        } catch (FileNotFoundException e) {
            System.err.printf("%s not found.", inputFile);
        } catch (IOException e) {
            System.err.printf("%s couldn't read from file", inputFile);
        }
        return itterationNumber;
    }

    public void runSimulation() {
        while (this.itterationNumber-- > 0) {
            this.weatherTower.changeWeather();
        }
    }
}
