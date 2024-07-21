package joeldockray.demos.awslambda.citydistance;

public class PermittedStatus400Exception extends Exception {
	
	PermittedStatus400Exception(String message) {
		super(message);
	}
	
	PermittedStatus400Exception(String message, Exception cause) {
		super(message, cause);
	}
}
