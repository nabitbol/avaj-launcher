package avaj_launcher.util;

import avaj_launcher.exceptions.InvalidCoordinateValueException;
import avaj_launcher.exceptions.InvalidCoordinateValueType;

public class CoordinatesValidator {

    public static boolean isValidLatitude(int p_latitude) {
        return p_latitude >= -90 && p_latitude <= 90;
    }

    public static boolean isValidLongitude(int p_longitude) {
        return p_longitude >= -180 && p_longitude <= 180;
    }

    public static boolean isValidHeight(int p_height) {
        return p_height >= 0;
    }

    public static void validateCoordinates(int p_longitude, int p_latitude, int p_height) {
        if (!isValidLongitude(p_longitude)) {
            throw new InvalidCoordinateValueException(InvalidCoordinateValueType.LONGITUDE);
        }
        if (!isValidLatitude(p_latitude)) {
            throw new InvalidCoordinateValueException(InvalidCoordinateValueType.LATITUDE);
        }
        if (!isValidHeight(p_height)) {
            throw new InvalidCoordinateValueException(InvalidCoordinateValueType.HEIGHT);
        }
    }
}
