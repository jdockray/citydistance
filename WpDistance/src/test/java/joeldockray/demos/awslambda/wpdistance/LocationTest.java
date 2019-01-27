package joeldockray.demos.awslambda.wpdistance;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LocationTest {
	static final double LATITUDE = 5.123456789;
	static final double ROUNDED_4DP_LATITUDE = 5.1235;
	static final double LONGITUDE = -10.012345678;
	static final double ROUNDED_4DP_LONGITUDE = -10.0123;
	static final Location NORTH_POLE = new Location(90, 0);
	static final Location SOUTH_POLE = new Location(-90, 0);
	static final double ALMOST_POLE_LATITUDE = 90 - Math.ulp(90);
	static final double HALF_EARTH_CIRCUMFERENCE = Location.EARTH_RADIUS_KM * Math.PI;
	static final double THIRD_EARTH_CIRCUMFERENCE = HALF_EARTH_CIRCUMFERENCE * 2 / 3;
	static final double QUARTER_EARTH_CIRCUMFERENCE = HALF_EARTH_CIRCUMFERENCE / 2;
	static final double CALCULATION_TOLERANCE = Math.ulp(1) * 32 * Location.EARTH_RADIUS_KM;
	
	@ParameterizedTest
	@ValueSource(doubles = {90.1, -90.1, Double.NEGATIVE_INFINITY, Double.NaN})
	public void getInvalidLatitude(double latitude) {
		Assertions.assertThrows(InvalidLocationException.class,
			() -> { Location.getLocation(latitude, LONGITUDE); } );
	}
		
	@ParameterizedTest
	@ValueSource(doubles = {180.1, -180.1, Double.POSITIVE_INFINITY, Double.NaN})
	public void getInvalidLongitude(double longitude) {
		Assertions.assertThrows(InvalidLocationException.class,
			() -> { Location.getLocation(LATITUDE, longitude); } );
	}

	@Test
	public void construction() throws InvalidLocationException {	
		Location location = Location.getLocation(LATITUDE, LONGITUDE);
		Assertions.assertEquals(LATITUDE, location.latitude);
		Assertions.assertEquals(LONGITUDE, location.longitude);
	}	
	
	@ParameterizedTest
	@ValueSource(doubles = {90, -90}) // Poles only
	public void longitudeIgnoredAtPoles(double latitude) throws InvalidLocationException {
		Assertions.assertEquals(Location.getLocation(latitude, LONGITUDE),
			Location.getLocation(latitude, -LONGITUDE)); // Longitude deliberately negative
	}
	
	@Test
	public void originAntipodeEquality() throws InvalidLocationException {
		Assertions.assertEquals(Location.getLocation(LATITUDE, -180),
									Location.getLocation(LATITUDE, 180));	
	}

	@Test
	public void distanceBetweenPoles() throws InvalidLocationException {
		Assertions.assertEquals(HALF_EARTH_CIRCUMFERENCE,
									Location.getDistance(NORTH_POLE, SOUTH_POLE));		
	}

	@ParameterizedTest
	@ValueSource(doubles = {-90, 0, 90, 180})	
	public void distancesAroundPoles(double longitude) throws InvalidLocationException {
		double nextLongitude = longitude + 90;
		if (nextLongitude > 180) nextLongitude -= 360;
		Assertions.assertEquals(0, Location.getDistance(Location.getLocation(ALMOST_POLE_LATITUDE, longitude),
								Location.getLocation(ALMOST_POLE_LATITUDE, nextLongitude)),
									CALCULATION_TOLERANCE);
	}	
	
	@ParameterizedTest
	@ValueSource(doubles = {-90, 0, 90, 180})	
	public void distancesAroundEquator(double longitude) throws InvalidLocationException {
		double nextLongitude = longitude + 90;
		if (nextLongitude > 180) nextLongitude -= 360;
		Assertions.assertEquals(QUARTER_EARTH_CIRCUMFERENCE, Location.getDistance(
			Location.getLocation(0, longitude), Location.getLocation(0, nextLongitude)),
				CALCULATION_TOLERANCE);				
	}
	
	@ParameterizedTest
	@ValueSource(doubles = {-90, 0, 90, 180})	
	public void distancesFromEquatorToPoles(double longitude) throws InvalidLocationException {
		Assertions.assertEquals(QUARTER_EARTH_CIRCUMFERENCE, Location.getDistance(
			NORTH_POLE, Location.getLocation(0, longitude)), CALCULATION_TOLERANCE);
		Assertions.assertEquals(QUARTER_EARTH_CIRCUMFERENCE, Location.getDistance(
				SOUTH_POLE, Location.getLocation(0, longitude)), CALCULATION_TOLERANCE);		
	}

	@ParameterizedTest
	@ValueSource(doubles = {-90, 0, 90, 180})	
	public void equatorialAntipodeDistances(double longitude) throws InvalidLocationException {
		double nextLongitude = longitude + 180;
		if (nextLongitude > 180) nextLongitude -= 360;
		Assertions.assertEquals(HALF_EARTH_CIRCUMFERENCE, Location.getDistance(
				Location.getLocation(0, longitude), Location.getLocation(0, nextLongitude)),
					CALCULATION_TOLERANCE);
	}

	@ParameterizedTest
	@ValueSource(doubles = {-90, 0, 90, 180})	
	public void antipodeDistancesAt45Degrees(double longitude)
			throws InvalidLocationException {
		double nextLongitude = longitude + 180;
		if (nextLongitude > 180) nextLongitude -= 360;
		Assertions.assertEquals(HALF_EARTH_CIRCUMFERENCE, Location.getDistance(
				Location.getLocation(45, longitude), Location.getLocation(-45, nextLongitude)),
					CALCULATION_TOLERANCE);
	}
	
	@ParameterizedTest
	@ValueSource(doubles = {-90, 0, 90, 180})	
	public void distanceWithReflectAndRotateAt45Degrees(double longitude)
			throws InvalidLocationException {
		double nextLongitude = longitude + 90;
		if (nextLongitude > 180) nextLongitude -= 360;
		Assertions.assertEquals(THIRD_EARTH_CIRCUMFERENCE, Location.getDistance(
				Location.getLocation(45, longitude), Location.getLocation(-45, nextLongitude)),
					CALCULATION_TOLERANCE);
	}
	
	@Test
	public void round() throws InvalidLocationException {
		Assertions.assertEquals(Location.getLocation(LATITUDE, LONGITUDE).round(4),
			Location.getLocation(ROUNDED_4DP_LATITUDE, ROUNDED_4DP_LONGITUDE));
	}
		
	@Test
	public void equalsTest() {
		Assertions.assertEquals(new Location(), new Location());	
	}
	
	@Test
	public void hashCodeTest() {
		Assertions.assertEquals(new Location().hashCode(), new Location().hashCode());
	}
}
