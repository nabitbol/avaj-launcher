package avaj_launcher.aircraft;

public class AircraftFactory {

    private static AircraftFactory aircraftFactory;
    private static long lastId;

    private AircraftFactory() {
    }

    public static AircraftFactory getAircraftFactory() {
        if (aircraftFactory == null) {
            aircraftFactory = new AircraftFactory();
        }
        return aircraftFactory;
    }

    public Flyable newAircraft(String Type, String p_name, Coordinates p_coordinates) {
        lastId += 1;
        long newAircraftId = lastId;

        return switch (Type) {
            case "Helicopter" ->
                new Helicopter(newAircraftId, p_name, p_coordinates);
            case "Jetplane" ->
                new JetPlane(newAircraftId, p_name, p_coordinates);
            case "Balloon" ->
                new Balloon(newAircraftId, p_name, p_coordinates);
            default ->
                new Helicopter(newAircraftId, p_name, p_coordinates);
        };
    }
}
