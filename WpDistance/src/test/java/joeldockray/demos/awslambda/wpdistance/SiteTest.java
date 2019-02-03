package joeldockray.demos.awslambda.wpdistance;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SiteTest {
	static final String SITE_NAME = "ABC123";
	static final double LATITUDE = 0.5;
	static final double LONGITUDE = 180;
	
	@Test
	public void construction() throws InvalidLatitudeException, InvalidLongitudeException {
		Site site = new Site(SITE_NAME, Location.getLocation(LATITUDE, LONGITUDE));
		Assertions.assertEquals(SITE_NAME, site.siteName);
		Assertions.assertEquals(LATITUDE, site.location.latitude);
		Assertions.assertEquals(LONGITUDE, site.location.longitude);
	}	

	@Test
	public void create() throws InvalidLatitudeException, InvalidLongitudeException {
		Site site = Site.createSite(SITE_NAME, LATITUDE, LONGITUDE);
		Assertions.assertEquals(SITE_NAME, site.siteName);
		Assertions.assertEquals(LATITUDE, site.location.latitude);
		Assertions.assertEquals(LONGITUDE, site.location.longitude);
	}

	@Test
	public void equalsTestMatch() throws NegativeValueException, InvalidLatitudeException,
											InvalidLongitudeException {
		Site site1 = Site.createSite(SITE_NAME, LATITUDE, LONGITUDE);
		Site site2 = Site.createSite(SITE_NAME, LATITUDE, LONGITUDE);
		Assertions.assertEquals(site1, site2);
	}

	@Test
	public void equalsTestNoMatch() throws NegativeValueException, InvalidLatitudeException,
											InvalidLongitudeException {
		Site site1 = Site.createSite(SITE_NAME, LATITUDE, LONGITUDE);
		Site site2 = new Site(SITE_NAME, new Location());
		Assertions.assertNotEquals(site1, site2);
	}
	
	@Test
	public void hashCodeTest() throws NegativeValueException, InvalidLatitudeException,
										InvalidLongitudeException {
		Site site1 = Site.createSite(SITE_NAME, LATITUDE, LONGITUDE);
		Site site2 = Site.createSite(SITE_NAME, LATITUDE, LONGITUDE);
		Assertions.assertEquals(site1.hashCode(), site2.hashCode());
	}	
	
	
}
