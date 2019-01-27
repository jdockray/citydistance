// Joel Dockray 2019

package joeldockray.demos.awslambda.wpdistance;

import java.util.ArrayList;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaFunctionHandler implements RequestHandler<SiteSearchRequest, ArrayList<SiteResultOutput>> {

    // Visible for testing
	public ArrayList<SiteResultOutput> handleRequestInternal(SiteSearchRequest input)
    		throws InvalidLocationException, InvalidResultNumberLimitException {
		Location queryLocation = input.parseLocation();
		int numberOfResultsToReturn = input.parseNumberOfResults();
		return SiteResultOutput.parseResults(SiteSearch.search(queryLocation, numberOfResultsToReturn));
	}
	
	// Called by AWS
	@Override
    public ArrayList<SiteResultOutput> handleRequest(SiteSearchRequest input, Context context) {
		try {
			return handleRequestInternal(input);
		}
		catch (InvalidLocationException | InvalidResultNumberLimitException ex) {
			throw new RuntimeException(ex);
		}
    }
}
