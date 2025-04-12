package avaj_launcher.exceptions;

import java.util.Map;

public class InvalidCoordinateValueException extends RuntimeException {

    public InvalidCoordinateValueType type;
    private static final Map<InvalidCoordinateValueType, String> errors = Map.of(
            InvalidCoordinateValueType.LONGITUDE, "Invalid longitude value.",
            InvalidCoordinateValueType.LATITUDE, "Invalid latitude value.",
            InvalidCoordinateValueType.HEIGHT, "Invalid height value."
    );

    public InvalidCoordinateValueException(InvalidCoordinateValueType type) {
        super("Error: " + errors.get(type));
        this.type = type;
    }

}
