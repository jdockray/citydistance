package joeldockray.demos.awslambda.citydistance;

public class InvalidResultNumberLimitException extends PermittedStatus400Exception {
	
	private InvalidResultNumberLimitException(String message, Exception cause) {
		super(message, cause);
	}
	
	public static void reportInvalidResultNumberLimit(String limitString, Exception cause)
		throws InvalidResultNumberLimitException {
		throw new InvalidResultNumberLimitException(
			limitString + " is not a valid number of results to return.", cause);
	}
	
	public static void reportInvalidResultNumberLimit(double invalidLimit, Exception cause)
		throws InvalidResultNumberLimitException {
		reportInvalidResultNumberLimit(String.valueOf(invalidLimit), cause);
	}
	
}