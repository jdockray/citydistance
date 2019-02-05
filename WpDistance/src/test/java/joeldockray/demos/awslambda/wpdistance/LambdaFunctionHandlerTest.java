package joeldockray.demos.awslambda.wpdistance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Integration test invoking the Lambda function handler
//One of the sites is used as the test query location
public class LambdaFunctionHandlerTest {
	static final int QUERY_LOCATION_SITE = 0;
	static final int NUMBER_OF_RESULTS = 2;	
	static final double KM_DISTANCE_TOLERANCE = 0.5;
	public List<SiteResult> results;
	
	@BeforeEach
	public void setUp() {
		Site testSite = SiteSearch.getSites().get(QUERY_LOCATION_SITE);
		LambdaFunctionHandler handler = new LambdaFunctionHandler();
		TestContext context = new TestContext();		
		SiteSearchRequest input
			= new SiteSearchRequest(String.valueOf(testSite.getLocation().getLatitude()),
										String.valueOf(testSite.getLocation().getLongitude()),
										String.valueOf(NUMBER_OF_RESULTS));
		results = handler.handleRequest(input, context);
	}
	
	@Test
	public void numberOfResults() {				
		Assertions.assertEquals(NUMBER_OF_RESULTS, results.size());
	}

	@Test
	public void roundedSiteResultCorrect() throws InvalidLatitudeException, InvalidLongitudeException {
		ArrayList<Site> sites = SiteSearch.getSites();
		HashMap<String, Location> siteMapping = new HashMap<String, Location>();
		for (Site site : sites) {
			siteMapping.put(site.getName(), site.getLocation());
		}
		for (SiteResult result : results) {
			Assertions.assertTrue(
				Location.getDistance(
					siteMapping.get(result.getSite().getName()), result.getSite().getLocation())
						< KM_DISTANCE_TOLERANCE);
		}
	}
	
}
