package joeldockray.demos.awslambda.wpdistance;

public class InvalidResultNumberLimitException extends Exception{
	
	InvalidResultNumberLimitException(String message) {
		super(message);
	}
	
	InvalidResultNumberLimitException(Exception cause) {
		super(cause);
	}

}