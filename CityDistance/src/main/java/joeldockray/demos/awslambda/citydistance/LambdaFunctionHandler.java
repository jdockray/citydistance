
package joeldockray.demos.awslambda.citydistance;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaFunctionHandler implements RequestHandler<SiteSearchRequest, List<SiteResult>> {

	static final int MAXIMUM_NUMBER_OF_RESULTS = 500;
	
	// Called by AWS
	@Override
	public List<SiteResult> handleRequest(SiteSearchRequest input, Context context) {	
		try {
			return handleRequestInternal(input);
		}
		catch (Throwable ex) {
			throw marshalToRuntimeError(ex);
		}
	}
	
	public List<SiteResult> handleRequestInternal(SiteSearchRequest input)
			throws PermittedStatus400Exception {
		Location queryLocation = input.parseLocation();
		int numberOfResultsToReturn = Math.min(input.parseNumberOfResults(), MAXIMUM_NUMBER_OF_RESULTS);
		return SiteSearch.search(queryLocation, numberOfResultsToReturn);
	}

	public RuntimeException marshalToRuntimeError(Throwable ex) {
		if (ex instanceof PermittedStatus400Exception)
		{
			throw new RuntimeException("400: " + ex.getMessage());
		}
		else
		{
			throw new RuntimeException("500: An unexpected error occurred.");
		}
	}
}
