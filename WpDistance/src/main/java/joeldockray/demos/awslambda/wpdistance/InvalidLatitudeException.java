package joeldockray.demos.awslambda.citydistance;

public class InvalidLatitudeException extends PermittedStatus400Exception {
	
	private InvalidLatitudeException(String message, Exception cause) {
		super(message, cause);
	}

	public static void reportInvalidLatitude(String latitudeString, Exception cause) throws InvalidLatitudeException {
		throw new InvalidLatitudeException(latitudeString + " is not a valid latitude.", cause);
	}
	
	public static void reportInvalidLatitude(String latitudeString) throws InvalidLatitudeException {
		reportInvalidLatitude(latitudeString, null);
	}
	
	public static void reportInvalidLatitude(double latitude, Exception cause) throws InvalidLatitudeException {
		reportInvalidLatitude(String.valueOf(latitude), cause);
	}
	
	public static void reportInvalidLatitude(double latitude) throws InvalidLatitudeException {
		reportInvalidLatitude(latitude, null);
	}	
}

