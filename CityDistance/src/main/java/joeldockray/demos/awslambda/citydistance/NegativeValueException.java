package joeldockray.demos.awslambda.citydistance;

public class NegativeValueException extends Exception {	

	protected NegativeValueException() {
		super();	
	}

	protected NegativeValueException(String message) {
		super(message);	
	}
	
	public static <T extends Number> void verifyValuePositive(T valueToEvaluate, String message)
			throws NegativeValueException {
		if (valueToEvaluate.doubleValue() < 0) {
			throw new NegativeValueException(message);
		}
	}
	
	public static <T extends Number> void verifyValuePositive(T valueToEvaluate) throws NegativeValueException {
		verifyValuePositive(valueToEvaluate, null);
	}	
}
