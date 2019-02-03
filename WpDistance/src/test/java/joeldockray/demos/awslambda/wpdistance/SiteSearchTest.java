package joeldockray.demos.awslambda.wpdistance;

import java.util.HashSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Integration test of the search function
//One of the sites is used as the test query location
public class SiteSearchTest {
	static final int NUMBER_OF_RESULTS = 2;		
	static final int QUERY_LOCATION_SITE = 0;
	public SiteResult[] results;
	public Site testSite;
	
	@BeforeEach
	public void setUp() throws InvalidResultNumberLimitException {
		testSite = SiteSearch.getSites().get(QUERY_LOCATION_SITE);		
		results = SiteSearch.search(testSite.location, NUMBER_OF_RESULTS).toArray(SiteResult[]::new);
	}

	@Test
	public void closestResult() throws NegativeValueException {				
		Assertions.assertTrue(results[0].equals(SiteResult.createSiteResult(testSite, 0.0)));
	}
	
	@Test
	public void resultSorting() {		
		for (int i = 1; i < results.length; i++) {
			Assertions.assertTrue(results[i - 1].kmDistanceAway < results[i].kmDistanceAway);
		}
	}

	@Test
	public void sitesAreValid() {
		for (SiteResult result : results) {
			Assertions.assertTrue(SiteSearch.getSites().contains(result.site));
		}
	}

	@Test
	public void noDuplicates() {
		HashSet<SiteResult> resultSet = new HashSet<SiteResult>();
		for (SiteResult result : results) {
			resultSet.add(result);
		}
		Assertions.assertEquals(results.length, resultSet.size());
	}		
	
}
