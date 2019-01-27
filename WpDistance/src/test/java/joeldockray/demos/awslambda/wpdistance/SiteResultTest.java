package joeldockray.demos.awslambda.wpdistance;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SiteResultTest {
	static final String SITE_NAME = "This is a reasonably long side name for testing purposes";
	static final double LATITUDE = -50;
	static final double LONGITUDE = -90;
	static Site testSite;
    
	// Not in static block due to potential to throw exceptions
	@BeforeAll
	public static void setUp() throws InvalidLocationException, NegativeValueException
	{
		testSite = new Site(SITE_NAME, Location.getLocation(LATITUDE, LONGITUDE));
	}

	@Test
    public void constructionWithLocation() {
		SiteResult result = new SiteResult(testSite, testSite.location);
		Assertions.assertEquals(testSite, result.site);
		Assertions.assertEquals(0, result.kmDistanceAway);
	}	

	@Test
    public void constructionWithDistance() throws NegativeValueException {
		SiteResult result = SiteResult.createSiteResult(testSite, 0);
		Assertions.assertEquals(testSite, result.site);
		Assertions.assertEquals(0, result.kmDistanceAway);
	}	

	@Test
    public void sortedStream() throws NegativeValueException {
		SiteResult closerResult = SiteResult.createSiteResult(testSite, 1);
		SiteResult furtherAwayResult = SiteResult.createSiteResult(testSite, 2);
		ArrayList<SiteResult> unsortedSiteResults = new ArrayList<SiteResult>();
		unsortedSiteResults.add(furtherAwayResult);
		unsortedSiteResults.add(closerResult);
		List<SiteResult> sortedSiteResults = SiteResult.sortedStream(unsortedSiteResults).collect(Collectors.toList());
		Assertions.assertEquals(2, sortedSiteResults.size());
		Assertions.assertEquals(closerResult, sortedSiteResults.get(0));
		Assertions.assertEquals(furtherAwayResult, sortedSiteResults.get(1));
	}	

	@Test
	public void equalsTest() throws NegativeValueException {
		SiteResult result1 = SiteResult.createSiteResult(testSite, 0);
		SiteResult result2 = SiteResult.createSiteResult(testSite, 0);
		Assertions.assertEquals(result1, result2);
	}
	
	@Test
	public void hashCodeTest() throws NegativeValueException {
		SiteResult result1 = SiteResult.createSiteResult(testSite, 0);
		SiteResult result2 = SiteResult.createSiteResult(testSite, 0);
		Assertions.assertEquals(result1.hashCode(), result2.hashCode());
	}
}
