package joeldockray.demos.awslambda.wpdistance;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SiteResultOutputTest {
	static final String SITE_NAME = "Bozwiggle";
	static final double LATITUDE_IN = 40.9000000001;
	static final double LONGITUDE_IN = -1;
	static final double DISTANCE_IN = 42387487.48378;
	static final String EXPECTED_LATITUDE_OUT = "40.90000";
	static final String EXPECTED_LONGITUDE_OUT = "-1.00000";
	static final String EXPECTED_DISTANCE_OUT = "42387487";
	static Site testSite;
	static SiteResult testSiteResult;  
    
	// Not in static block due to potential to throw exceptions
	@BeforeAll
	public static void setUp() throws InvalidLocationException, NegativeValueException
	{
		testSite = new Site(SITE_NAME, Location.getLocation(LATITUDE_IN, LONGITUDE_IN));
		testSiteResult = SiteResult.createSiteResult(testSite, DISTANCE_IN);
	}

	@Test
    public void parsingConstruction() {
		SiteResultOutput output = new SiteResultOutput(testSiteResult);
		Assertions.assertEquals(SITE_NAME, output.getSiteName());
		Assertions.assertEquals(EXPECTED_LATITUDE_OUT, output.getLatitude());
		Assertions.assertEquals(EXPECTED_LONGITUDE_OUT, output.getLongitude());
		Assertions.assertEquals(EXPECTED_DISTANCE_OUT, output.getKmDistanceAway());
	}	
	
	@Test
    public void gettersAndSetters() {
		SiteResultOutput output = new SiteResultOutput();
		output.setSiteName(SITE_NAME);
		output.setLatitude(EXPECTED_LATITUDE_OUT);
		output.setLongitude(EXPECTED_LONGITUDE_OUT);
		output.setKmDistanceAway(EXPECTED_DISTANCE_OUT);
		Assertions.assertEquals(SITE_NAME, output.getSiteName());
		Assertions.assertEquals(EXPECTED_LATITUDE_OUT, output.getLatitude());
		Assertions.assertEquals(EXPECTED_LONGITUDE_OUT, output.getLongitude());
		Assertions.assertEquals(EXPECTED_DISTANCE_OUT, output.getKmDistanceAway());
	}
	
	@Test
	public void parseResults() throws NegativeValueException {
		SiteResult furtherAwaySiteResult = SiteResult.createSiteResult(testSite, DISTANCE_IN + 1);
		ArrayList<SiteResult> siteResultList = new ArrayList<SiteResult>();
		siteResultList.add(testSiteResult);		
		siteResultList.add(furtherAwaySiteResult);
		ArrayList<SiteResultOutput> outputList = SiteResultOutput.parseResults(siteResultList.stream());
		Assertions.assertEquals(2, outputList.size());
		Assertions.assertEquals(new SiteResultOutput(testSiteResult), outputList.get(0));
		Assertions.assertEquals(new SiteResultOutput(furtherAwaySiteResult), outputList.get(1));
	}	
	
	@Test
	public void equalsTest() {
		Assertions.assertEquals(new SiteResultOutput(), new SiteResultOutput());
	}
	
	@Test
	public void hashCodeTest() {
		Assertions.assertEquals(new SiteResultOutput().hashCode(), new SiteResultOutput().hashCode());
	}
}
