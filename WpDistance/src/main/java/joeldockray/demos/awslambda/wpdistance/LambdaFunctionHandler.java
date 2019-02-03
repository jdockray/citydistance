// Joel Dockray 2019

package joeldockray.demos.awslambda.wpdistance;

import java.util.ArrayList;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaFunctionHandler implements RequestHandler<SiteSearchRequest, ArrayList<SiteResultOutput>> {

	// Called by AWS
	@Override
	public ArrayList<SiteResultOutput> handleRequest(SiteSearchRequest input, Context context) {	
		try {
			return handleRequestInternal(input);
		}
		catch (Throwable ex) {
			throw marshalToRuntimeError(ex);
		}
	}
	
	public ArrayList<SiteResultOutput> handleRequestInternal(SiteSearchRequest input)
			throws PermittedStatus400Exception {
		Location queryLocation = input.parseLocation();
		int numberOfResultsToReturn = input.parseNumberOfResults();
		return SiteResultOutput.parseResults(SiteSearch.search(queryLocation, numberOfResultsToReturn));
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
