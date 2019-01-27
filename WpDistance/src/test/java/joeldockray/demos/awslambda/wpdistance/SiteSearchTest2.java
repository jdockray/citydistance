package joeldockray.demos.awslambda.wpdistance;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Integration test invoking the Lambda function handler
// One of the sites is used as the test query location	
public class SiteSearchTest2 {
	static final int QUERY_LOCATION_SITE = 0;
	public Location queryLocation;
	
	@BeforeEach
	public void setUp() {
		queryLocation = SiteSearch.getSites().get(QUERY_LOCATION_SITE).location;
	}

    public void noResults() throws InvalidResultNumberLimitException {
		Assertions.assertEquals(0, SiteSearch.search(queryLocation, 0).count());
	}
	
	@Test
    public void allResults() throws InvalidResultNumberLimitException {
		Assertions.assertEquals(SiteSearch.getSites().size(),
								SiteSearch.search(queryLocation, Integer.MAX_VALUE).count());
	}

	@Test
    public void negativeNumberOfResults() {
		Assertions.assertThrows(InvalidResultNumberLimitException.class,
									() -> SiteSearch.search(queryLocation, Integer.MIN_VALUE));
	}	

}
