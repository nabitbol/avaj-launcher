
import avaj_launcher.aircraft.Aircraft;
import avaj_launcher.aircraft.AircraftFactory;
import avaj_launcher.aircraft.Coordinates;
import avaj_launcher.weather.WeatherTower;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Spliterator;
import java.util.stream.Stream;

class Simulation {

    final private WeatherTower weatherTower;
    private int itterationNumber;
    private int lineNumber;

    private static final int AIRCRAFT_TYPE_INDEX = 0;
    private static final int AIRCRAFT_NAME_INDEX = 1;
    private static final int LONGITUDE_INDEX = 2;
    private static final int LATITUDE_INDEX = 3;
    private static final int HEIGHT_INDEX = 4;

    public Simulation() {
        this.weatherTower = new WeatherTower();
        this.itterationNumber = 0;
        this.lineNumber = 0;
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

    private void loadAircrafts(String line) {
        AircraftFactory aircraftFactory = AircraftFactory.getAircraftFactory();

        try {

            Aircraft aircraft = getNewAircraft(line, aircraftFactory);
            aircraft.registerTower(this.weatherTower);
            this.lineNumber++;

        } catch (Exception e) {
            System.err.printf("Error line %d: %s", this.lineNumber, e.getMessage());
        }
    }

    private void parseItterationNumber(String line) throws IllegalArgumentException {
        try {
            int parsedNumber = Integer.parseInt(line);

            if (parsedNumber < 0) {
                throw new IllegalArgumentException("Wrong number of itterations.");
            }

            this.itterationNumber = parsedNumber;
        } catch (IllegalArgumentException e) {
            System.err.printf("Error line 0: %s", e.getMessage());
            System.exit(-1);
        } catch (Exception e) {
            System.err.printf("Error line 0: %s", e.getMessage());
        }
    }

    /**
     * @param WeatherTower
     * @param Path
     * @return The number of itteration for the simulation.
     * @author nabitbol
     */
    public int loadSimulation(Path inputFile) {
        try (Stream<String> lines = Files.lines(inputFile)) {
            Spliterator<String> spliterator = lines.spliterator();

            spliterator.tryAdvance((line) -> {
                parseItterationNumber(line);
                this.lineNumber++;
            });

            spliterator.forEachRemaining((line) -> {
                loadAircrafts(line);
                this.lineNumber++;
            });

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
