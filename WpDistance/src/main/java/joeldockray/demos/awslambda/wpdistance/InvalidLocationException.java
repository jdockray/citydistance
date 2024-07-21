package joeldockray.demos.awslambda.citydistance;

public class InvalidLocationException extends Exception {
	
	public InvalidLocationException(String message) {
		super(message);
	}
	
	public InvalidLocationException(Exception cause) {
		super(cause);
	}
}
