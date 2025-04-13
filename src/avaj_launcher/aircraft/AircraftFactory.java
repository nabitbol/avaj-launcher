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

    public Aircraft newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
        lastId += 1;
        long newAircraftId = lastId;

        return switch (p_type) {
            case "Helicopter" ->
                new Helicopter(newAircraftId, p_name, p_coordinates);
            case "JetPlane" ->
                new JetPlane(newAircraftId, p_name, p_coordinates);
            case "Balloon" ->
                new Balloon(newAircraftId, p_name, p_coordinates);
            default ->
                throw new IllegalArgumentException("Invalid aircraft type " + p_type);
        };
    }

}
