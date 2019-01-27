package joeldockray.demos.awslambda.wpdistance;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SiteSearchRequestTest {
    static final String VALID_TEST_LATITUDE = "-80";
    static final String VALID_TEST_LONGITUDE = "130";
    static final String VALID_TEST_RESULT_COUNT = String.valueOf(Integer.MAX_VALUE);
    static final String INVALID_TEST_LATITUDE_OUTSIDE_RANGE = "-180.5";
    static final String INVALID_TEST_LONGITUDE_NAN = "Moose";
    static final String INVALID_TEST_RESULT_COUNT_NEGATIVE = "-1";
    static final String INVALID_TEST_RESULT_COUNT_NAN = "Sponge";
	
	@Test
    public void parse() throws NumberFormatException, InvalidLocationException, InvalidResultNumberLimitException {		        
		SiteSearchRequest request
			= new SiteSearchRequest(VALID_TEST_LATITUDE, VALID_TEST_LONGITUDE, VALID_TEST_RESULT_COUNT);
        Location location = request.parseLocation();
    	Assertions.assertEquals(Double.parseDouble(VALID_TEST_LATITUDE), location.latitude);
    	Assertions.assertEquals(Double.parseDouble(VALID_TEST_LONGITUDE), location.longitude);
    	Assertions.assertEquals(Integer.parseInt(VALID_TEST_RESULT_COUNT), request.parseNumberOfResults());
	}
			
	@Test
    public void gettersAndSetters() throws InvalidLocationException, InvalidResultNumberLimitException {		        
		SiteSearchRequest request = new SiteSearchRequest();
		request.setLatitude(VALID_TEST_LATITUDE);
		request.setLongitude(VALID_TEST_LONGITUDE);
		request.setNumberOfResults(VALID_TEST_RESULT_COUNT);
		Assertions.assertEquals(VALID_TEST_LATITUDE, request.getLatitude());	
    	Assertions.assertEquals(VALID_TEST_LONGITUDE, request.getLongitude());
    	Assertions.assertEquals(VALID_TEST_RESULT_COUNT, request.getNumberOfResults());
	}
	
	@Test
	public void invalidLocationOutsideRange() {		        
		SiteSearchRequest request = new SiteSearchRequest(INVALID_TEST_LATITUDE_OUTSIDE_RANGE,
															VALID_TEST_LONGITUDE, VALID_TEST_RESULT_COUNT);
		Assertions.assertThrows(InvalidLocationException.class, () -> { request.parseLocation(); } );   
	}	

	@Test
    public void invalidLocationNaN() {		        
		SiteSearchRequest request
			= new SiteSearchRequest(VALID_TEST_LATITUDE, INVALID_TEST_LONGITUDE_NAN, VALID_TEST_RESULT_COUNT);
		Assertions.assertThrows(InvalidLocationException.class, () -> { request.parseLocation(); } );   
	}	
		
	@Test
    public void invalidResultNumberNegative() {		        
		SiteSearchRequest request
			= new SiteSearchRequest(VALID_TEST_LATITUDE, VALID_TEST_LONGITUDE, INVALID_TEST_RESULT_COUNT_NEGATIVE);
		Assertions.assertThrows(InvalidResultNumberLimitException.class, () -> { request.parseNumberOfResults(); } );   
	}
	
	@Test
    public void invalidResultNumberNaN() {		        
		SiteSearchRequest request
			= new SiteSearchRequest(VALID_TEST_LATITUDE, VALID_TEST_LONGITUDE, INVALID_TEST_RESULT_COUNT_NAN);
		Assertions.assertThrows(InvalidResultNumberLimitException.class, () -> { request.parseNumberOfResults(); } );   
	}
}
