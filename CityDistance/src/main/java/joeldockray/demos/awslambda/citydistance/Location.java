package joeldockray.demos.awslambda.citydistance;

import java.util.Objects;

public class Location {
	public static final double EARTH_RADIUS_KM = 6371.0088; // WGS84 mean radius
	private final double latitude;  // Positive = North, Negative = South
	private final double longitude; // Positive = East,  Negative = West
	private final double[] nv; // Normal vector

	public static Location getLocation(double latitude, double longitude)
			throws InvalidLatitudeException, InvalidLongitudeException {
		if (Double.isNaN(latitude) || Math.abs(latitude) > 90)
			InvalidLatitudeException.reportInvalidLatitude(latitude);
		if (Double.isNaN(longitude) || Math.abs(longitude) > 180)
			InvalidLongitudeException.reportInvalidLongitude(longitude);
		return new Location(latitude, longitude);
	}
		
	protected Location(double latitude, double longitude) {
		this.latitude = latitude;
		
		if (Math.abs(latitude) == 90) this.longitude = 0; // Longitude irrelevant at poles
		else if (longitude == -180) this.longitude = 180;
		else this.longitude = longitude;
		
		double latitudeRadians = Math.toRadians(this.latitude);
		double longitudeRadians = Math.toRadians(this.longitude);
		
		double[] normalVector = new double[3];
		normalVector[0] = Math.cos(latitudeRadians) * Math.cos(longitudeRadians);
		normalVector[1] = Math.cos(latitudeRadians) * Math.sin(longitudeRadians);
		normalVector[2] = Math.sin(latitudeRadians);
		nv = normalVector;
	}
	
	public Location() {
		this(0, 0);
	}

	// Using https://en.wikipedia.org/wiki/Great-circle_distance#Vector_version
	// Up to 0.5% error
	public static double getDistance(Location locationA, Location locationB) {
		return Math.atan2(normalVectorCrossProductMagnitude(locationA, locationB), 
							normalVectorDotProduct(locationA, locationB)) * EARTH_RADIUS_KM;
	}

	// Using https://en.wikipedia.org/wiki/Cross_product#Computing_the_cross_product
	private static double normalVectorCrossProductMagnitude(Location locA, Location locB) {
		return Math.sqrt(square(locA.nv[1] * locB.nv[2] - locA.nv[2] * locB.nv[1])
					   + square(locA.nv[2] * locB.nv[0] - locA.nv[0] * locB.nv[2])   
					   + square(locA.nv[0] * locB.nv[1] - locA.nv[1] * locB.nv[0]));
	}
	
	private static double normalVectorDotProduct(Location locA, Location locB) {
		return locA.nv[0] * locB.nv[0] + locA.nv[1] * locB.nv[1] + locA.nv[2] * locB.nv[2];		
	}

	public Location round(int coordDecimalPlaces) {
		return new Location(round(latitude, coordDecimalPlaces),
								round(longitude, coordDecimalPlaces));
	}
		
	@Override
	public boolean equals(Object obj) {
		return obj != null && obj instanceof Location && obj.hashCode() == hashCode();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(latitude, longitude);
	}
	
	private static double square(double value) {
		return value * value;
	}
	
	private static double round(double value, int decimalPlaces) {		
		assert(decimalPlaces >= 0);
		for (int i = 0; i < decimalPlaces; i++) value *= 10;
		value = (double) Math.round(value);
		for (int i = 0; i < decimalPlaces; i++) value /= 10;
		return value;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}
}
