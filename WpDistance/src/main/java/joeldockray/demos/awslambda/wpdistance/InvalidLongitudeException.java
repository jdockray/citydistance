package joeldockray.demos.awslambda.citydistance;

public class InvalidLongitudeException extends PermittedStatus400Exception {

	private InvalidLongitudeException(String message, Exception cause) {
		super(message, cause);
	}	
	
	public static void reportInvalidLongitude(String longitudeString, Exception cause)
		throws InvalidLongitudeException {
		throw new InvalidLongitudeException(longitudeString + " is not a valid longitude.", cause);
	}

	public static void reportInvalidLongitude(String longitudeString)
		throws InvalidLongitudeException {
		reportInvalidLongitude(longitudeString, null);	
	}
	
	public static void reportInvalidLongitude(double longitude, Exception cause)
		throws InvalidLongitudeException {
		reportInvalidLongitude(String.valueOf(longitude), cause);
	}
	
	public static void reportInvalidLongitude(double longitude)
			throws InvalidLongitudeException {
		reportInvalidLongitude(longitude, null);
	}
}
