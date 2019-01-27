package joeldockray.demos.awslambda.wpdistance;

public class InvalidLocationException extends Exception {
	
	public InvalidLocationException(String message) {
		super(message);
	}
	
	public InvalidLocationException(Exception cause) {
		super(cause);
	}
}
