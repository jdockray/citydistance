package joeldockray.demos.awslambda.wpdistance;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Integration test of the search function
//One of the sites is used as the test query location
public class SiteSearchTest {
	static final int NUMBER_OF_RESULTS = 2;		
	static final int QUERY_LOCATION_SITE = 0;
	public List<SiteResult> results;
	public Site testSite;
	
	@BeforeEach
	public void setUp() throws InvalidResultNumberLimitException {
		testSite = SiteSearch.getSites().get(QUERY_LOCATION_SITE);		
		results = SiteSearch.search(testSite.getLocation(), NUMBER_OF_RESULTS);
	}

	@Test
	public void closestResult() throws NegativeValueException {				
		Assertions.assertTrue(results.get(0).equals(SiteResult.createSiteResult(testSite, 0)));
	}
	
	@Test
	public void resultSorting() {		
		for (int i = 1; i < results.size(); i++) {
			Assertions.assertTrue(
				results.get(i - 1).getKmDistanceAway() < results.get(i).getKmDistanceAway());
		}
	}

	@Test
	public void sitesAreValid() {
		for (SiteResult result : results) {
			Assertions.assertTrue(SiteSearch.getSites().contains(result.getSite()));
		}
	}

	@Test
	public void noDuplicates() {
		HashSet<SiteResult> resultSet = new HashSet<SiteResult>();
		for (SiteResult result : results) {
			resultSet.add(result);
		}
		Assertions.assertEquals(results.size(), resultSet.size());
	}		
	
}
