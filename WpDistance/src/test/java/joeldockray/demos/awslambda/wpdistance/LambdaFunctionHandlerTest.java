package joeldockray.demos.awslambda.wpdistance;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Integration test invoking the Lambda function handler
//One of the sites is used as the test query location
public class LambdaFunctionHandlerTest {
	static final int QUERY_LOCATION_SITE = 0;
	static final int NUMBER_OF_RESULTS = 2;	
	static final double KM_DISTANCE_TOLERANCE = 0.5;
	public ArrayList<SiteResultOutput> results;
	
	@BeforeEach
	public void setUp() {
		Site testSite = SiteSearch.getSites().get(QUERY_LOCATION_SITE);
        LambdaFunctionHandler handler = new LambdaFunctionHandler();
        TestContext context = new TestContext();        
        SiteSearchRequest input
        	= new SiteSearchRequest(String.valueOf(testSite.location.latitude),
        								String.valueOf(testSite.location.longitude),
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
			siteMapping.put(site.siteName, site.location);
		}
        for (SiteResultOutput result : results) {
        	Location locationOfSiteCorrespondingToResult = siteMapping.get(result.getSiteName());
        	Location resultLocation = Location.getLocation(Double.parseDouble(result.getLatitude()),
        													Double.parseDouble(result.getLongitude()));
        	Assertions.assertTrue(
        		Location.getDistance(locationOfSiteCorrespondingToResult, resultLocation) < KM_DISTANCE_TOLERANCE);
        }
	}
	
}
